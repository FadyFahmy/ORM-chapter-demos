CREATE TABLE course (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100),
	created_date DATETIME,
	last_modified_date DATETIME
);

CREATE TABLE review (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	course INTEGER references course(id),
	rating INTEGER,
	description VARCHAR(500)
);

CREATE TABLE student (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100),
	line1 VARCHAR(500),
	line2 VARCHAR(500),
	city VARCHAR(500)
);

CREATE TABLE course_student (
	course INTEGER REFERENCES course(id),
	student INTEGER REFERENCES student(id),
	PRIMARY KEY (course, student)
);

INSERT INTO course(id, name, created_date, last_modified_date) 
values (10001, 'intro to jdbc', sysdate(), sysdate());
INSERT INTO course(id, name, created_date, last_modified_date) 
values (10002, 'differences between jdbc and jpa', sysdate(), sysdate());

INSERT INTO review(id, course, rating, description) 
values (20001, 10001, 5, 'very great course');
INSERT INTO review(id, course, rating, description)
values (20002, 10001, 4, 'glad there is something just like that in the course');


INSERT INTO student(id, name, line1, line2, city) 
VALUES (30001, 'fady', 'line 1', 'line 2', 'random address');
INSERT INTO student(id, name, line1, line2, city) 
VALUES (30002, 'andrew', 'line 1', 'line 2', 'random address');
INSERT INTO student(id, name, line1, line2, city) 
VALUES (30003, 'nich', 'line 1', 'line 2', 'random address');
INSERT INTO student(id, name, line1, line2, city) 
VALUES (30004, 'timothy', 'line 1', 'line 2', 'random address');


INSERT INTO course_student(course, student) VALUES (10001, 30001);
INSERT INTO course_student(course, student) VALUES (10001, 30002);
INSERT INTO course_student(course, student) VALUES (10001, 30003);
INSERT INTO course_student(course, student) VALUES (10001, 30004);
INSERT INTO course_student(course, student) VALUES (10002, 30001);
INSERT INTO course_student(course, student) VALUES (10002, 30002);
INSERT INTO course_student(course, student) VALUES (10002, 30003);
INSERT INTO course_student(course, student) VALUES (10002, 30004);



