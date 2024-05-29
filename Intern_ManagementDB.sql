DROP DATABASE IF EXISTS InternMSDB;
CREATE DATABASE InternMSDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE InternMSDB;

CREATE TABLE Semeters (
	semeter_id INT PRIMARY KEY AUTO_INCREMENT,
    semeter_name VARCHAR(50) NOT NULL,
    start_date DATE, 
    end_date DATE
);

CREATE TABLE Intern (
    intern_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    full_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(50) NOT NULL,
    major VARCHAR(50),
    company VARCHAR(10) NOT NULL,
    job_title VARCHAR(100),
    link_cv VARCHAR(255),
    staff_id VARCHAR(50) NOT NULL,
    status ENUM('intern', 'alumni'),
    semeter_id INT,
    FOREIGN KEY (semeter_id) REFERENCES Semeters(semeter_id)
);

CREATE TABLE User (
	user_id INT PRIMARY KEY AUTO_INCREMENT,
	email VARCHAR(50) NOT NULL UNIQUE,
    is_hr BINARY,
    is_manager BINARY,
    is_mentor BINARY,
    is_intern BINARY,
    intern_id INT,
    FOREIGN KEY (intern_id) REFERENCES Intern(intern_id)
);

CREATE TABLE Manager (
    manager_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(50),
    birth_date DATE,
    phone_number VARCHAR(50),
    user_id INT,
	FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Mentor (
    mentor_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(50),
    birth_date DATE,
    phone_number VARCHAR(50),
    user_id INT,
	FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE InternAssign (
    assign_id INT PRIMARY KEY AUTO_INCREMENT,
    intern_id INT,
    mentor_id INT,
--     is_selected TINYINT(1) DEFAULT 0,
    FOREIGN KEY (intern_id) REFERENCES Intern(intern_id),
    FOREIGN KEY (mentor_id) REFERENCES Mentor(mentor_id)
);

CREATE TABLE LabRoom (
	room_id INT PRIMARY KEY AUTO_INCREMENT,
    room_name VARCHAR(255) NOT NULL,
    is_assigned BINARY,
    mentor_id INT UNIQUE,
    FOREIGN KEY (mentor_id) REFERENCES Mentor(mentor_id)
);

CREATE TABLE Mission (
    mis_id INT PRIMARY KEY AUTO_INCREMENT,
    mis_name VARCHAR(100) NOT NULL,
    mis_status ENUM('Not start', 'On-going', 'Finished'),
    mis_description TEXT,
    link varchar(50),
    start_date TIMESTAMP,
    deadline TIMESTAMP,
    mentor_id INT,
    intern_id INT,
    FOREIGN KEY (mentor_id) REFERENCES Mentor(mentor_id),
    FOREIGN KEY (intern_id) REFERENCES Intern(intern_id)
);

CREATE TABLE MissionReport (
    mission_rp_id INT PRIMARY KEY AUTO_INCREMENT,
    intern_id INT NOT NULL,
    mis_id INT,
    report_content TEXT,
    link VARCHAR(50),
    submission_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     reviewer_id INT,
    FOREIGN KEY (intern_id) REFERENCES Intern(intern_id),
    FOREIGN KEY (mis_id) REFERENCES Mission(mis_id)
--     FOREIGN KEY (reviewer_id) REFERENCES Mentor(mentor_id)
);

CREATE TABLE MentorReport (
    report_id INT PRIMARY KEY AUTO_INCREMENT,
    report_name VARCHAR(100),
    report_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    link VARCHAR(100),
    mentor_id INT,
    FOREIGN KEY (mentor_id) REFERENCES Mentor(mentor_id)
);

CREATE TABLE Schedule (
    schedule_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    day_of_week ENUM('MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN') NOT NULL,
    location VARCHAR(255),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    attendance_status ENUM('absent', 'present', 'not yet') NOT NULL DEFAULT 'not yet',
    intern_id INT,
    room_id INT,
    FOREIGN KEY (intern_id) REFERENCES Intern(intern_id),
    FOREIGN KEY (room_id) REFERENCES LabRoom(room_id)
);

CREATE TABLE Work_OverTime (
	overtime_id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    schedule_id INT, 
    intern_id INT,
    FOREIGN KEY (schedule_id) REFERENCES Schedule(schedule_id),
    FOREIGN KEY (intern_id) REFERENCES Intern(intern_id)
);

CREATE TABLE Attendance (
    attendance_id INT PRIMARY KEY AUTO_INCREMENT,
    intern_id INT NOT NULL,
    schedule_id INT NOT NULL,
    check_in_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    check_out_time TIMESTAMP NULL,    -- Thời gian checkout (có thể NULL nếu chưa checkout)
    ip_address VARCHAR(50),           -- Địa chỉ IP Wi-Fi của phòng lab
    FOREIGN KEY (intern_id) REFERENCES Intern(intern_id),
    FOREIGN KEY (schedule_id) REFERENCES Schedule(schedule_id)
);







