﻿USE master;  
GO 

--Delete the dailyreport database if it exists.  
IF EXISTS(SELECT * from sys.databases WHERE name='dailyreport')  
BEGIN
	ALTER DATABASE dailyreport SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE dailyreport;  
END


--Create a new database called dailyreport.  
CREATE DATABASE dailyreport;  
GO

USE dailyreport;  
GO

-- CREATE TABLE RIGHTS
CREATE TABLE rights (
    id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    name NVARCHAR(50),
    level INT,
    created_at SMALLDATETIME,
    updated_at SMALLDATETIME
);
GO

-- CREATE TABLE USERS
CREATE TABLE users (
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    employee_code VARCHAR(12) NOT NULL,
    password VARCHAR(18) NOT NULL,
	email VARCHAR(120),
    name NVARCHAR(50),
    rights INT FOREIGN KEY REFERENCES rights(id),
    created_at SMALLDATETIME,
	updated_at SMALLDATETIME
);
GO

-- CREATE TABLE DEPARTMENTS
CREATE TABLE departments (
    id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    department_code VARCHAR(4) NOT NULL,
    name NVARCHAR(50),
    created_at SMALLDATETIME,
    updated_at SMALLDATETIME
);
GO

-- CREATE TABLE DEPARTMENT_DETAIL
CREATE TABLE department_details (
	user_id INT PRIMARY KEY NOT NULL FOREIGN KEY REFERENCES users(id),
    department_id INT NOT NULL FOREIGN KEY REFERENCES departments(id),
    created_at SMALLDATETIME,
	updated_at SMALLDATETIME
);
GO

-- CREATE TABLE PROJECTS
CREATE TABLE projects (
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    project_code VARCHAR(4) NOT NULL,
    name NVARCHAR(50),
    start_at DATE,
    finish_at DATE,
    created_at SMALLDATETIME,
	updated_at SMALLDATETIME
);
GO

-- CREATE TABLE REPORTS
CREATE TABLE reports (
    id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	project_id INT NOT NULL FOREIGN KEY REFERENCES projects(id),
	user_id INT NOT NULL FOREIGN KEY REFERENCES users(id),
    approver_id INT FOREIGN KEY REFERENCES users(id),
	approved_at SMALLDATETIME,
	created_at SMALLDATETIME,
	updated_at SMALLDATETIME
);
GO

-- CREATE TABLE TASK
CREATE TABLE tasks (
    id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    task_code VARCHAR(4) NOT NULL,
    name NVARCHAR(50),
    created_at SMALLDATETIME,
    updated_at SMALLDATETIME
);
GO

-- CREATE TABLE REPORT_DETAILS
CREATE TABLE report_details (
    id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	report_id INT NOT NULL FOREIGN KEY REFERENCES reports(id),
    task_id INT NOT NULL FOREIGN KEY REFERENCES tasks(id),
    time_worked FLOAT,
	note NVARCHAR(500),
	created_at SMALLDATETIME,
    updated_at SMALLDATETIME
);
GO

-- INSERT INTO RIGHTS
--1
INSERT INTO rights(name, level)
VALUES ('Giam Doc', 1);
--1
INSERT INTO rights(name, level)
VALUES ('Quan Ly', 2);
--1
INSERT INTO rights(name, level)
VALUES ('Truong Nhom', 3);

-- INSERT INTO USER
--1
INSERT INTO users(employee_code, password, rights, email, name)
VALUES ('hanh', '1111', 1, 'hanh.cho.do@gmail.com', 'Nguyen Hanh');
--1
INSERT INTO users(employee_code, password, email, name)
VALUES ('giaduc', '1111', 'giaduc@gmail.com', 'Gia Duc');

-- INSERT INTO PROJECT
--1
INSERT INTO projects(project_code, name, start_at, finish_at)
VALUES ('abc3', 'Du an 1', CAST(N'2018-03-25' AS DateTime), CAST(N'2018-05-01' AS DateTime));
--1
INSERT INTO projects(project_code, name, start_at, finish_at)
VALUES ('f6c1', 'Du an 2', CAST(N'2018-03-26' AS DateTime), CAST(N'2018-05-02' AS DateTime));
--1
INSERT INTO projects(project_code, name, start_at, finish_at)
VALUES ('gbg8', 'Du an 3', CAST(N'2018-03-27' AS DateTime), CAST(N'2018-05-03' AS DateTime));
--1
INSERT INTO projects(project_code, name, start_at, finish_at)
VALUES ('abc3', 'Du an 4', CAST(N'2018-03-28' AS DateTime), CAST(N'2018-05-04' AS DateTime));
--1
INSERT INTO projects(project_code, name, start_at, finish_at)
VALUES ('f6c1', 'Du an 5', CAST(N'2018-03-29' AS DateTime), CAST(N'2018-05-05' AS DateTime));

-- INSERT INTO DEPARTMENT
--1
INSERT INTO departments(department_code, name)
VALUES ('aksd', 'Phong ban 1');
--1
INSERT INTO departments(department_code, name)
VALUES ('jd28', 'Phong ban 2');
--1
INSERT INTO departments(department_code, name)
VALUES ('1jk0', 'Phong ban 3');
--1
INSERT INTO departments(department_code, name)
VALUES ('aksd', 'Phong ban 4');
--1
INSERT INTO departments(department_code, name)
VALUES ('jd28', 'Phong ban 5');

-- INSERT INTO TASK
--1
INSERT INTO tasks(task_code, name)
VALUES ('aksd', 'Task 1');
--1
INSERT INTO tasks(task_code, name)
VALUES ('kgtd', 'Task 2');
--1
INSERT INTO tasks(task_code, name)
VALUES ('mnyt', 'Task 3');
--1
INSERT INTO tasks(task_code, name)
VALUES ('kgtd', 'Task 4');
--1
INSERT INTO tasks(task_code, name)
VALUES ('mnyt', 'Task 5');

-- INSERT INTO REPORT
--1
INSERT INTO reports(project_id, user_id, created_at)
VALUES (1, 1, CAST(N'2018-03-25' AS DateTime));
--1
INSERT INTO reports(project_id, user_id, created_at)
VALUES (2, 1, CAST(N'2018-03-25' AS DateTime));
--1
INSERT INTO reports(project_id, user_id, created_at)
VALUES (3, 2, CAST(N'2018-03-26' AS DateTime));
--1
INSERT INTO reports(project_id, user_id, created_at)
VALUES (4, 2, CAST(N'2018-03-26' AS DateTime));
--1
INSERT INTO reports(project_id, user_id, created_at)
VALUES (5, 1, CAST(N'2018-03-25' AS DateTime));

-- INSERT INTO REPORT_DETAIL
--1
INSERT INTO report_details(report_id, task_id, time_worked, note)
VALUES (1, 1, CAST(RAND(CHECKSUM(NEWID())) * 20 as INT)+3, 'NOTE');
--1
INSERT INTO report_details(report_id, task_id, time_worked, note)
VALUES (2, 2, CAST(RAND(CHECKSUM(NEWID())) * 20 as INT)+3, 'NOTE 1');
--1
INSERT INTO report_details(report_id, task_id, time_worked, note)
VALUES (3, 5, CAST(RAND(CHECKSUM(NEWID())) * 20 as INT)+3, 'NOTE 2');
--1
INSERT INTO report_details(report_id, task_id, time_worked, note)
VALUES (4, 2, CAST(RAND(CHECKSUM(NEWID())) * 20 as INT)+3, 'NOTE 3');
--1
INSERT INTO report_details(report_id, task_id, time_worked, note)
VALUES (1, 3, CAST(RAND(CHECKSUM(NEWID())) * 20 as INT)+3, 'NOTE 4');
--1
INSERT INTO report_details(report_id, task_id, time_worked, note)
VALUES (4, 4, CAST(RAND(CHECKSUM(NEWID())) * 10 as INT), 'NOTE 5');
--1
INSERT INTO report_details(report_id, task_id, time_worked, note)
VALUES (1, 1, CAST(RAND(CHECKSUM(NEWID())) * 20 as INT)+3, 'NOTE');
--1
INSERT INTO report_details(report_id, task_id, time_worked, note)
VALUES (5, 2, CAST(RAND(CHECKSUM(NEWID())) * 20 as INT)+3, 'NOTE 1');
--1
INSERT INTO report_details(report_id, task_id, time_worked, note)
VALUES (3, 5, CAST(RAND(CHECKSUM(NEWID())) * 20 as INT)+3, 'NOTE 2');
--1
INSERT INTO report_details(report_id, task_id, time_worked, note)
VALUES (4, 2, CAST(RAND(CHECKSUM(NEWID())) * 20 as INT)+3, 'NOTE 3');
--1
INSERT INTO report_details(report_id, task_id, time_worked, note)
VALUES (1, 3, CAST(RAND(CHECKSUM(NEWID())) * 20 as INT)+3, 'NOTE 4');
--1
INSERT INTO report_details(report_id, task_id, time_worked, note)
VALUES (2, 4, CAST(RAND(CHECKSUM(NEWID())) * 10 as INT), 'NOTE 5');