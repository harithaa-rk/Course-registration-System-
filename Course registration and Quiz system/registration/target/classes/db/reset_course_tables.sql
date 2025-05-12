-- Drop tables (order matters due to FK constraint)
DROP TABLE IF EXISTS course_registration;
DROP TABLE IF EXISTS course;

-- Recreate course table
CREATE TABLE course (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    courseName VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    duration VARCHAR(100) NOT NULL,
    fee DOUBLE NOT NULL
);

-- Recreate course_registration table
CREATE TABLE course_registration (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    course_id BIGINT NOT NULL,
    student_name VARCHAR(255) NOT NULL,
    student_email VARCHAR(255) NOT NULL,
    registration_date DATETIME,
    user_id BIGINT,
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES course(id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user_info(id)
);

-- Insert sample courses
INSERT INTO course (courseName, description, duration, fee) VALUES
('Java Programming', 'Learn Java from scratch', '10 weeks', 1000.0),
('Web Development', 'HTML, CSS, JS, and more', '8 weeks', 900.0),
('Data Structures', 'Core CS concepts', '12 weeks', 1200.0);

-- Now you can register for courses using the correct course IDs (1, 2, 3)
