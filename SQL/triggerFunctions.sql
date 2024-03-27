-- TRIGGER FUNCTIONS

-- function that removes entries from ClassParticipants table when a class date/time changes
-- this effectively un-enrolls users from a class when it's edited

CREATE OR REPLACE FUNCTION delete_class_participants()
RETURNS TRIGGER AS $$
BEGIN
    DELETE FROM ClassParticipants WHERE class_id = OLD.class_id;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER UpdateClassParticipants
AFTER UPDATE OF date, time ON FitnessClass
FOR EACH ROW
EXECUTE FUNCTION delete_class_participants();


-- This function updates the invoices corresponding to participants of the fitness class
-- to 'Cancelled' when the class' date/time is changed

CREATE OR REPLACE FUNCTION update_invoices_on_class_update()
RETURNS TRIGGER AS $$
BEGIN
    -- Update invoices to cancelled upon a fitness class being deleted or having its date/time changed
    UPDATE Invoice
    SET Status = 'Cancelled'
    WHERE payment_id IN (
        SELECT payment_id FROM ClassInvoice WHERE class_id = OLD.class_id
    );

    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER UpdateClassInvoicesOnClassUpdate
AFTER UPDATE OF date, time ON FitnessClass
FOR EACH ROW
EXECUTE FUNCTION update_invoices_on_class_update();

-- This function updates the invoices corresponding to participants of the fitness class
-- to 'Cancelled' when the class is deleted

CREATE OR REPLACE FUNCTION update_invoices_on_class_delete()
RETURNS TRIGGER AS $$
DECLARE
    paymentIds INT[];
BEGIN
    -- Collect payment IDs related to the class being deleted
    SELECT array_agg(payment_id) INTO paymentIds
    FROM ClassInvoice
    WHERE class_id = OLD.class_id;
    
    -- Update related invoices to 'Cancelled'
    IF paymentIds IS NOT NULL THEN
        UPDATE Invoice
        SET Status = 'Cancelled'
        WHERE payment_id = ANY(paymentIds);
    END IF;
    
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER UpdateClassInvoicesOnClassDelete
BEFORE DELETE ON FitnessClass
FOR EACH ROW
EXECUTE FUNCTION update_invoices_on_class_delete();


-- This function updates the fitness class invoices to 'Cancelled' when a member leaves an upcoming class

CREATE OR REPLACE FUNCTION update_class_invoices_status_when_member_leaves()
RETURNS TRIGGER AS $$
BEGIN
    -- Update the invoice to 'Cancelled' for the member dropping the class
    UPDATE Invoice
    SET Status = 'Cancelled'
    WHERE payment_id IN (
        SELECT payment_id FROM ClassInvoice 
        WHERE class_id = OLD.class_id AND member_id = OLD.member_id
    );

    -- Optionally, if you want to remove the entry from ClassInvoice as well
    DELETE FROM ClassInvoice
    WHERE class_id = OLD.class_id AND member_id = OLD.member_id;

    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER UpdateClassInvoicesOnUserCancel
AFTER DELETE ON ClassParticipants
FOR EACH ROW
EXECUTE FUNCTION update_class_invoices_status_when_member_leaves();


-- This function update Invoices to 'Cancelled' when a TrainingSession is cancelled by the Member or Trainer

CREATE OR REPLACE FUNCTION delete_training_session_and_update_invoices()
RETURNS TRIGGER AS $$
DECLARE
    payment_ids INT[];
BEGIN
    -- Collect payment_ids before deletion
    SELECT array_agg(payment_id) INTO payment_ids
    FROM SessionInvoice
    WHERE session_id = OLD.session_id;
    
    -- Delete related entries in SessionInvoice
    DELETE FROM SessionInvoice WHERE session_id = OLD.session_id;
    
    -- Update related Invoice entries to 'cancelled'
    IF payment_ids IS NOT NULL THEN
        UPDATE Invoice
        SET status = 'cancelled'
        WHERE payment_id = ANY(payment_ids);
    END IF;
    
    -- Proceed with the deletion of the TrainingSession entry
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER delete_training_session
BEFORE DELETE ON TrainingSession
FOR EACH ROW
EXECUTE FUNCTION delete_training_session_and_update_invoices()


-- This function handles automatically adding an Invoices when a member enrolls in a class

CREATE OR REPLACE FUNCTION create_invoice_for_class()
RETURNS TRIGGER AS $$
DECLARE
    new_payment_id INT;
BEGIN
    -- Insert into Invoices and get the generated payment_id
    INSERT INTO Invoice (member_id, Type, Cost)
    VALUES (NEW.member_id, ‘Class’, 10) 
    RETURNING payment_id INTO new_payment_id;
    
    -- Insert into ClassInvoice with the new payment_id and session_id
    INSERT INTO ClassInvoice (payment_id, class_id, member_id)
    VALUES (new_payment_id, NEW.class_id, NEW.member_id);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER AddNewClassInvoice
AFTER INSERT ON ClassParticipants
FOR EACH ROW
EXECUTE FUNCTION create_invoice_for_class()



-- This function handles automatically generating an invoice when a Member schedules a new trainer session 

CREATE OR REPLACE FUNCTION create_invoice_for_session()
RETURNS TRIGGER AS $$
DECLARE
    new_payment_id INT;
BEGIN
    -- Insert into Invoice and get the generated payment_id
    INSERT INTO Invoice (member_id, Type, Cost)
    VALUES (NEW.member_id, ‘Session’, 50) 
    RETURNING payment_id INTO new_payment_id;
    
    -- Insert into SessionInvoice with the new payment_id and session_id
    INSERT INTO SessionInvoice (payment_id, session_id)
    VALUES (new_payment_id, NEW.session_id);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER AddNewSessionInvoice
AFTER INSERT ON TrainingSession
FOR EACH ROW
EXECUTE FUNCTION create_invoice_for_session()


-- This function generates a membership invoice when the user registers

CREATE OR REPLACE FUNCTION add_membership_invoice()
RETURNS TRIGGER AS $$
BEGIN
	INSERT INTO Invoice (member_id, Type, Cost)
	VALUES (NEW.member_id, ‘Membership’, 500);

	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER AddMembershipInvoice
AFTER INSERT ON Members
FOR EACH ROW
EXECUTE FUNCTION add_membership_invoice();



-- This function handles automatically deleting training sessions that fall outside of 
-- a Trainer's scheduled hours when they modify their start and or end time 

CREATE OR REPLACE FUNCTION fn_cleanup_sessions()
RETURNS TRIGGER AS $$
BEGIN
    -- Delete TrainingSession records that no longer fit the trainer's new schedule
    DELETE FROM TrainingSession ts
    WHERE ts.trainer_id = OLD.trainer_id
      AND ts.date > CURRENT_DATE
      AND (
           ts.time < NEW.start_time
           OR ts.time > NEW.end_time
         );

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_trainer_times
AFTER UPDATE OF start_time, end_time ON Trainers
FOR EACH ROW
WHEN (OLD.start_time IS DISTINCT FROM NEW.start_time OR OLD.end_time IS DISTINCT FROM NEW.end_time)
EXECUTE FUNCTION fn_cleanup_sessions();

