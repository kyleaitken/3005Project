CREATE TABLE IF NOT EXISTS Members (
    member_id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    phone VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL, 
    emergency_phone VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS MemberHealthInfo (
    member_id INT PRIMARY KEY,
    height INT,
    weight INT,
    bmi INT,
    resting_heart_rate INT,
    systolic_bp INT,
    diastolic_bp INT,
    waist_girth INT,
    FOREIGN KEY (member_id) REFERENCES Members(member_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS MemberGoal (
    goal_id SERIAL PRIMARY KEY,
    member_id INT NOT NULL,
    description VARCHAR(255) NOT NULL,
    target_date DATE NOT NULL,
    completed BOOLEAN DEFAULT FALSE,
    completed_date DATE,
    FOREIGN KEY (member_id) REFERENCES Members(member_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS MemberExerciseRoutine (
    routine_id SERIAL PRIMARY KEY,
    member_id INT NOT NULL,
    FOREIGN KEY (member_id) REFERENCES Members(member_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ExerciseLog (
    log_id SERIAL PRIMARY KEY,
    routine_id INT NOT NULL,
    exercise_name VARCHAR(255) NOT NULL,
    num_sets INT,
    num_reps INT,
    duration INT, 
    weight INT,
    FOREIGN KEY (routine_id) REFERENCES MemberExerciseRoutine(routine_id) ON DELETE CASCADE,
    UNIQUE (routine_id, exercise_name)
);

CREATE TABLE IF NOT EXISTS Trainer (
    trainer_id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    start_time INT CHECK (start_time >= 7 AND start_time <= 19),
    end_time INT CHECK (end_time >= 8 AND end_time <= 20 AND end_time > start_time)
);

CREATE TABLE IF NOT EXISTS TrainingSession (
    session_id SERIAL PRIMARY KEY,
    trainer_id INT NOT NULL,
    member_id INT NOT NULL,
    date DATE NOT NULL,
    time INT NOT NULL,
    FOREIGN KEY (trainer_id) REFERENCES Trainer(trainer_id),
    FOREIGN KEY (member_id) REFERENCES Members(member_id),
    UNIQUE (trainer_id, date, time),
    UNIQUE (member_id, date, time)
);

CREATE TABLE IF NOT EXISTS Room (
    room_id SERIAL PRIMARY KEY,
    room_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS FitnessClass (
    class_id SERIAL PRIMARY KEY,
    trainer_id INT NOT NULL,
    date DATE NOT NULL,
    time INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    room_id INT NOT NULL,
    FOREIGN KEY (trainer_id) REFERENCES Trainer(trainer_id),
    FOREIGN KEY (room_id) REFERENCES Room(room_id),
    UNIQUE (trainer_id, date, time),
    UNIQUE (room_id, date, time)
);

CREATE TABLE IF NOT EXISTS ClassParticipants (
    class_id INT,
    member_id INT,
    PRIMARY KEY (class_id, member_id),
    FOREIGN KEY (class_id) REFERENCES FitnessClass(class_id) ON DELETE CASCADE,
    FOREIGN KEY (member_id) REFERENCES Members(member_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Admin (
    admin_id SERIAL PRIMARY KEY,
    smail VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Invoice (
    payment_id SERIAL PRIMARY KEY,
    member_id INT NOT NULL,
    type VARCHAR(255) NOT NULL,
    cost INT NOT NULL,
    status VARCHAR(255) DEFAULT 'Unpaid',
    FOREIGN KEY (member_id) REFERENCES Members(member_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS SessionInvoice (
    payment_id INT PRIMARY KEY,
    session_id INT NOT NULL,
    FOREIGN KEY (payment_id) REFERENCES Invoice(payment_id),
    FOREIGN KEY (session_id) REFERENCES TrainingSession(session_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ClassInvoice (
    payment_id INT PRIMARY KEY,
    class_id INT NOT NULL,
    member_id INT NOT NULL,
    FOREIGN KEY (payment_id) REFERENCES Invoice(payment_id),
    FOREIGN KEY (member_id) REFERENCES Members(member_id) ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES FitnessClass(class_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Equipment (
    equipment_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    needs_repair BOOLEAN NOT NULL DEFAULT FALSE
);
