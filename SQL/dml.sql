INSERT INTO Members (email, password, first_name, last_name, birth_date, phone, address, emergency_phone) VALUES
('john.doe1@example.com', 'password123', 'John', 'Doe', '1990-01-01', '555-100-0001', '123 Brooke Lane, Ottawa, ON K1V2E9', '555-200-0001'),
('jane.smith1@example.com', 'password123', 'Jane', 'Smith', '1991-02-01', '555-100-0002', '124 Brooke Lane, Ottawa, ON K1V2E9', '555-200-0002'),
('michael.jones1@example.com', 'password123', 'Michael', 'Jones', '1992-03-01', '555-100-0003', '125 Brooke Lane, Ottawa, ON K1V2E9', '555-200-0003'),
('emily.davis1@example.com', 'password123', 'Emily', 'Davis', '1993-04-01', '555-100-0004', '126 Brooke Lane, Ottawa, ON K1V2E9', '555-200-0004'),
('david.wilson1@example.com', 'password123', 'David', 'Wilson', '1994-05-01', '555-100-0005', '127 Brooke Lane, Ottawa, ON K1V2E9', '555-200-0005'),
('laura.taylor1@example.com', 'password123', 'Laura', 'Taylor', '1995-06-01', '555-100-0006', '128 Brooke Lane, Ottawa, ON K1V2E9', '555-200-0006'),
('kevin.brown1@example.com', 'password123', 'Kevin', 'Brown', '1996-07-01', '555-100-0007', '129 Brooke Lane, Ottawa, ON K1V2E9', '555-200-0007'),
('sarah.moore1@example.com', 'password123', 'Sarah', 'Moore', '1997-08-01', '555-100-0008', '130 Brooke Lane, Ottawa, ON K1V2E9', '555-200-0008');

INSERT INTO MemberHealthInfo (member_id, height, weight, bmi, resting_heart_rate, systolic_bp, diastolic_bp, waist_girth) VALUES
(1, 175, 70, 23, 70, 120, 80, 32),
(2, 180, 95, 29, 72, 121, 81, 36),
(3, 165, 85, 31, 75, 130, 85, 38),
(4, 170, 75, 26, 71, 119, 78, 31),
(5, 160, 100, 39, 78, 140, 90, 42),
(6, 158, 55, 22, 67, 116, 75, 28),
(7, 182, 105, 32, 80, 145, 95, 44),
(8, 176, 78, 25, 74, 123, 83, 35);


-- In Progress Goals
INSERT INTO MemberGoal (member_id, description, target_date) VALUES
(1, 'Lose 10 lbs', '2024-06-15'),
(1, 'Run 5K without stopping', '2024-09-15'),
(2, 'Squat 200lbs', '2024-06-15'),
(2, '30 minutes of meditation daily', '2024-09-15'),
(3, 'Lose 5% body fat', '2024-06-15'),
(3, 'Increase bench press by 50lbs', '2024-09-15'),
(4, 'Run a half marathon', '2024-06-15'),
(4, 'Drink 2L of water daily', '2024-09-15'),
(5, 'Complete a triathlon', '2024-06-15'),
(5, '100 consecutive push-ups', '2024-09-15'),
(6, 'Lose 15 lbs', '2024-06-15'),
(6, 'Hold a 5-minute plank', '2024-09-15'),
(7, 'Squat 250lbs', '2024-06-15'),
(7, 'Daily yoga for flexibility', '2024-09-15'),
(8, 'Cut out processed sugar', '2024-06-15'),
(8, '10,000 steps daily', '2024-09-15');


-- Completed Goals
INSERT INTO MemberGoal (member_id, description, target_date, completed, completed_date) VALUES
(1, 'Walk 10,000 steps daily', '2023-07-15', TRUE, '2023-06-30'),
(2, 'Swim 1km twice a week', '2023-07-15', TRUE, '2023-08-01'),
(3, 'Cycle 100km in a month', '2023-08-15', TRUE, '2023-08-20'),
(4, 'Achieve 20 consecutive pull-ups', '2023-09-15', TRUE, '2023-09-10'),
(5, 'Master the crow pose in yoga', '2023-10-15', TRUE, '2023-10-20'),
(6, 'Complete a 24-hour fast', '2023-11-15', TRUE, '2023-11-05'),
(7, 'Reduce body fat by 5%', '2023-12-15', TRUE, '2023-12-20'),
(8, 'Jog for 30 minutes daily', '2024-01-15', TRUE, '2024-01-10');

-- Exercise Routines
INSERT INTO MemberExerciseRoutine (member_id, routine_num) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8);


-- Insert Exercise Logs Into Routines
INSERT INTO ExerciseLog (routine_id, exercise_name, num_sets, num_reps, duration, weight) VALUES
(1, 'Squat', 3, 10, NULL, 100),
(1, 'Bench Press', 3, 10, NULL, 80),
(1, 'Bent Over Row', 3, 10, NULL, 60),
(1, 'DB Bicep Curl', 3, 10, NULL, 20),
(2, 'Reverse Lunges', 3, 12, NULL, 50), 
(2, 'Push Ups', 3, 8, NULL, NULL),
(2, 'Pull Ups', 3, 6, NULL, NULL),
(2, 'Cable Tricep Extension', 3, 15, NULL, 30),
(3, 'Elliptical', NULL, NULL, 30, NULL),
(3, 'Jumping Jacks', 3, NULL, 1, NULL),
(3, 'Sit Ups', 3, 30, NULL, NULL),
(4, 'Treadmill', NULL, NULL, 20, NULL), 
(4, 'Barbell Squats', 3, 12, NULL, 120),
(4, 'Deadlifts', 3, 6, NULL, 200),
(5, 'Dumbbell Chest Fly', 4, 12, NULL, 30), 
(5, 'Single Leg Deadlift', 3, 12, NULL, 40),
(5, 'Lat Pull Downs', 3, 9, NULL, 100),
(6, 'Squat', 3, 10, NULL, 100),
(6, 'Bench Press', 3, 10, NULL, 80),
(6, 'Bent Over Row', 3, 10, NULL, 60),
(6, 'DB Bicep Curl', 3, 10, NULL, 20),
(7, 'Reverse Lunges', 3, 12, NULL, 50), 
(7, 'Push Ups', 3, 8, NULL, NULL),
(7, 'Pull Ups', 3, 6, NULL, NULL),
(7, 'Cable Tricep Extension', 3, 15, NULL, 30),
(8, 'Treadmill', NULL, NULL, 20, NULL),
(8, 'Barbell Squats', 3, 12, NULL, 120),
(8, 'Deadlifts', 3, 6, NULL, 200);


-- Trainers
INSERT INTO Trainers (email, name, password, start_time, end_time) VALUES
('alex.jordan@example.com', 'Alex Jordan', 'pass1234', 7, 15),
('casey.smith@example.com', 'Casey Smith', 'pass1234', 8, 16),
('jamie.lee@example.com', 'Jamie Lee', 'pass1234', 9, 17),
('morgan.kai@example.com', 'Morgan Kai', 'pass1234', 10, 18);


-- Procedure for inserting treatment sessions, which ensures there's not a conflict in the trainer's
-- or member's schedule

CREATE OR REPLACE FUNCTION insert_training_session(
    _trainer_id INT,
    _member_id INT,
    _date DATE,
    _time INT
) RETURNS VOID AS $$
BEGIN
    -- Check for trainer and member availability
    IF NOT EXISTS (
        SELECT 1 FROM FitnessClass
        WHERE trainer_id = _trainer_id AND date = _date AND time = _time
        UNION
        SELECT 1 FROM TrainingSession
        WHERE trainer_id = _trainer_id AND date = _date AND time = _time
        UNION
        SELECT 1 FROM TrainingSession
        WHERE member_id = _member_id AND date = _date AND time = _time
    ) THEN
        INSERT INTO TrainingSession (trainer_id, member_id, date, time)
        VALUES (_trainer_id, _member_id, _date, _time);
    ELSE
        RAISE EXCEPTION 'Conflict detected for trainer or member. Insert aborted.';
    END IF;
END;
$$ LANGUAGE plpgsql;


-- Adding Training Sessions
SELECT insert_training_session(1, 1, '2024-04-20', 9);
SELECT insert_training_session(1, 3, '2024-04-21', 10);
SELECT insert_training_session(1, 5, '2024-04-22', 11);
SELECT insert_training_session(1, 7, '2024-04-23', 9);
SELECT insert_training_session(1, 1, '2024-04-24', 10);

SELECT insert_training_session(2, 2, '2024-04-20', 14);
SELECT insert_training_session(2, 4, '2024-04-21', 15);
SELECT insert_training_session(2, 6, '2024-04-22', 16);
SELECT insert_training_session(2, 8, '2024-04-23', 14);
SELECT insert_training_session(2, 2, '2024-04-24', 15);

SELECT insert_training_session(3, 3, '2024-04-25', 9);
SELECT insert_training_session(3, 5, '2024-04-26', 10);
SELECT insert_training_session(3, 7, '2024-04-27', 11);
SELECT insert_training_session(3, 1, '2024-04-28', 9);
SELECT insert_training_session(3, 3, '2024-04-29', 10);

SELECT insert_training_session(4, 4, '2024-04-25', 14);
SELECT insert_training_session(4, 6, '2024-04-26', 15);
SELECT insert_training_session(4, 8, '2024-04-27', 16);
SELECT insert_training_session(4, 2, '2024-04-28', 14);
SELECT insert_training_session(4, 4, '2024-04-29', 15);


-- Rooms
INSERT INTO Room (room_name) VALUES
('Studio 1'),
('Studio 2'),
('Studio 3'), 
('Yoga Room');


-- Procedure for making sure there’s not a conflicting trainer session or class when adding a 
-- new fitnessClass 

CREATE OR REPLACE FUNCTION insert_fitness_class(
    _trainer_id INT,
    _date DATE,
    _time INT,
    _name VARCHAR,
    _room_id INT
) RETURNS VOID AS $$
BEGIN
    -- Check for trainer availability
    IF NOT EXISTS (
        SELECT 1 FROM FitnessClass
        WHERE trainer_id = _trainer_id AND date = _date AND time = _time
        UNION
        SELECT 1 FROM TrainingSession
        WHERE trainer_id = _trainer_id AND date = _date AND time = _time
    ) AND NOT EXISTS (
        -- Check for room availability
        SELECT 1 FROM FitnessClass
        WHERE room_id = _room_id AND date = _date AND time = _time
    ) THEN
        INSERT INTO FitnessClass (trainer_id, date, time, name, room_id)
        VALUES (_trainer_id, _date, _time, _name, _room_id);
    ELSE
        RAISE EXCEPTION 'Conflict detected for trainer or room. Insert aborted.';
    END IF;
END;
$$ LANGUAGE plpgsql;


-- Adding FitnessClasses 
SELECT insert_fitness_class(1, '2024-04-20', 8, 'Strength 101', 1);
SELECT insert_fitness_class(1, '2024-04-27', 8, 'Strength 101', 1);
SELECT insert_fitness_class(2, '2024-04-21', 18, 'Strength 101', 1);
SELECT insert_fitness_class(2, '2024-04-25', 18, 'Strength 101', 1);
SELECT insert_fitness_class(3, '2024-04-21', 8, 'Spin', 2);
SELECT insert_fitness_class(3, '2024-04-25', 8, 'Spin', 2);
SELECT insert_fitness_class(1, '2024-04-23', 8, 'Spin', 2);
SELECT insert_fitness_class(1, '2024-04-26', 8, 'Spin', 2);
SELECT insert_fitness_class(4, '2024-04-20', 8, 'Yoga', 4);
SELECT insert_fitness_class(4, '2024-04-22', 8, 'Yoga', 4);
SELECT insert_fitness_class(4, '2024-04-19', 17, 'Zumba', 3);
SELECT insert_fitness_class(3, '2024-04-26', 17, 'Zumba', 3);


-- Procedure function to check if member has conflicting class or session before 
-- enrolling them in a class

CREATE OR REPLACE FUNCTION add_class_participant(
    _class_id INT,
    _member_id INT
) RETURNS VOID AS $$
DECLARE
    class_date DATE;
    class_time INT;
    conflicting_class BOOLEAN;
BEGIN
    -- Get the date and time of the class
    SELECT date, time INTO class_date, class_time FROM FitnessClass WHERE class_id = _class_id;

    -- Check for a TrainingSession conflict
    IF EXISTS (
        SELECT 1 FROM TrainingSession 
        WHERE member_id = _member_id AND date = class_date AND time = class_time
    ) THEN
        RAISE EXCEPTION 'Member has a conflicting training session.';
    END IF;

    -- Check for conflicting classes in FitnessClass
    SELECT EXISTS (
        SELECT 1 FROM FitnessClass FC
        INNER JOIN ClassParticipants CP ON FC.class_id = CP.class_id
        WHERE FC.class_id <> _class_id AND FC.date = class_date AND FC.time = class_time AND CP.member_id = _member_id
    ) INTO conflicting_class;
    
    -- If there's a conflicting class, raise an exception
    IF conflicting_class THEN
        RAISE EXCEPTION 'Member is already participating in another class at the same time.';
    END IF;
    
    -- If no conflicts, insert the member into the class
    INSERT INTO ClassParticipants (class_id, member_id)
    VALUES (_class_id, _member_id);
    
    RAISE NOTICE 'Member successfully added to class.';
END;
$$ LANGUAGE plpgsql;


-- Adding Class Participants (Enrolling members in a class)
SELECT add_class_participant(1, 1);
SELECT add_class_participant(2, 2);
SELECT add_class_participant(3, 3);
SELECT add_class_participant(4, 4);
SELECT add_class_participant(1, 5);
SELECT add_class_participant(2, 6);
SELECT add_class_participant(3, 7);
SELECT add_class_participant(4, 8);
SELECT add_class_participant(5, 5);
SELECT add_class_participant(6, 6);
SELECT add_class_participant(7, 7);
SELECT add_class_participant(8, 8);
SELECT add_class_participant(5, 1);
SELECT add_class_participant(6, 2);
SELECT add_class_participant(7, 3);
SELECT add_class_participant(8, 4);
SELECT add_class_participant(9, 1);
SELECT add_class_participant(10, 2);
SELECT add_class_participant(11, 3);
SELECT add_class_participant(12, 4);
SELECT add_class_participant(9, 5);
SELECT add_class_participant(10, 6);
SELECT add_class_participant(11, 7);
SELECT add_class_participant(12, 8);


-- Admin
INSERT INTO Admin (email, password) VALUES
('admin@example.com', 'password');


-- Equipment
INSERT INTO Equipment (name, needs_repair) VALUES
('Treadmill', FALSE),
('Rowing Machine', FALSE),
('Elliptical', TRUE),
('Exercise Bike', FALSE),
('Kettlebells', FALSE),
('Dumbbell Set', TRUE),
('Barbell Set', FALSE),
('Leg Press Machine', TRUE),
('Smith Machine', FALSE),
('Battle Ropes', FALSE),
('Medicine Balls', FALSE),
('Plyometric Boxes', FALSE),
('Foam Roller', FALSE),
('Yoga Mats', FALSE),
('Resistance Bands', TRUE);

