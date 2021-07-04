insert into course(id, name, created_date, last_updated_date, is_deleted) values(10001, 'jpa in 100 steps', sysdate(), sysdate(), false); 
insert into course(id, name, created_date, last_updated_date, is_deleted) values(10002, 'jpa in 101 steps', sysdate(), sysdate(), false); 
insert into course(id, name, created_date, last_updated_date, is_deleted) values(10003, 'jpa in 102 steps', sysdate(), sysdate(), false); 
insert into course(id, name, created_date, last_updated_date, is_deleted) values(10004, 'jpa in 103 steps', sysdate(), sysdate(), false); 
insert into course(id, name, created_date, last_updated_date, is_deleted) values(10005, 'jpa in 104 steps', sysdate(), sysdate(), false); 
insert into course(id, name, created_date, last_updated_date, is_deleted) values(10006, 'jpa in 105 steps', sysdate(), sysdate(), false); 
insert into course(id, name, created_date, last_updated_date, is_deleted) values(10007, 'jpa in 106 steps', sysdate(), sysdate(), false); 
insert into course(id, name, created_date, last_updated_date, is_deleted) values(10008, 'jpa in 107 steps', sysdate(), sysdate(), false); 

insert into passport(id, number) values(40001, 'URFHU33');
insert into passport(id, number) values(40002, 'OGKT4T4');
insert into passport(id, number) values(40003, 'FDFR7VJ');

insert into student(id, name, passport_id) values(20001, 'Ranga', 40001);
insert into student(id, name, passport_id) values(20002, 'Adam', 40002);
insert into student(id, name, passport_id) values(20003, 'Jane', 40003);

insert into review(id, description, rating, course_id) values(50001,'Great Course', 'FIVE', 10001);
insert into review(id, description, rating, course_id) values(50002, 'Good Course', 'FIVE', 10001);
insert into review(id, description, rating, course_id) values(50003, 'Bad Course', 'FIVE', 10003);

insert into student_course(student_id, course_id) values(20001,10001);
insert into student_course(student_id, course_id) values(20002,10001);
insert into student_course(student_id, course_id) values(20003,10001);
insert into student_course(student_id, course_id) values(20001,10003);