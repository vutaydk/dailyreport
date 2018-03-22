USE master;  
GO 

--Delete the dailyreport database if it exists.  
IF EXISTS(SELECT * from sys.databases WHERE name='dailyreport')  
BEGIN  
	ALTER DATABASE dailyreport SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE dailyreport;  
END


--Create a new database called dailyreport.  
CREATE DATABASE dailyreport;  

USE dailyreport;  
GO 

-- CREATE TABLE RIGHTS
CREATE TABLE rights (
    id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    name nvarchar(50),
    level int,
    created_at datetime,
    updated_at datetime
);
GO

-- CREATE TABLE USERS
CREATE TABLE users (
	id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    employee_code varchar(12) NOT NULL,
    password varchar(18),
	email varchar(120),
    name nvarchar(50),
    rights int FOREIGN KEY REFERENCES rights(id),
    created_at datetime,
	updated_at datetime
);
GO

-- CREATE TABLE DEPARTMENTS
CREATE TABLE departments (
    id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    department_code varchar(4),
    name nvarchar(50),
    created_at datetime,
    updated_at datetime
);
GO

-- CREATE TABLE DEPARTMENT_DETAIL
CREATE TABLE department_details (
	user_id int PRIMARY KEY NOT NULL FOREIGN KEY REFERENCES users(id),
    department_id int FOREIGN KEY REFERENCES departments(id),
    created_at datetime,
	updated_at datetime
);
GO

-- CREATE TABLE PROJECTS
CREATE TABLE projects (
	id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    project_code varchar(4) NOT NULL,
    name nvarchar(50),
    start_at date,
    finish_at date,
    created_at datetime,
	updated_at datetime
);
GO

-- CREATE TABLE REPORTS
CREATE TABLE reports (
    id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	project_id int FOREIGN KEY REFERENCES projects(id),
	user_id int FOREIGN KEY REFERENCES users(id),
    approver_id int FOREIGN KEY REFERENCES users(id),
	approved_at datetime,
	created_at datetime,
	updated_at datetime
);
GO

-- CREATE TABLE TASK
CREATE TABLE tasks (
    id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    task_code varchar(4),
    name nvarchar(50),
    created_at datetime,
    updated_at datetime
);
GO

-- CREATE TABLE TASK_DETAILS
CREATE TABLE task_details (
    id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	time_worked time(7),
	note nvarchar(500),
    task_id int FOREIGN KEY REFERENCES tasks(id),
	report_id int FOREIGN KEY REFERENCES reports(id),
    user_id int FOREIGN KEY REFERENCES users(id),
    created_at datetime,
    updated_at datetime
);
GO

-- INSERT INTO RIGHTS
INSERT INTO rights(name, level)
VALUES ('Giam Doc', 1);

INSERT INTO rights(name, level)
VALUES ('Quan Ly', 2);

INSERT INTO rights(name, level)
VALUES ('Truong Nhom', 3);

-- INSERT INTO USERS
INSERT INTO users(employee_code, password, rights, email, name)
VALUES ('hanh', '1111', 1, 'hanh.cho.do@gmail.com', 'Nguyen Hanh');
INSERT INTO users(employee_code, password, email, name)
VALUES ('giaduc', '1111', 'giaduc@gmail.com', 'Gia Duc');

-- INSERT INTO PROJECT
INSERT INTO projects(project_code, name)
VALUES ('abc3', 'Du an 1');
INSERT INTO projects(project_code, name)
VALUES ('f6c1', 'Du an 2');
INSERT INTO projects(project_code, name)
VALUES ('gbg8', 'Du an 3');

-- INSERT INTO DEPARTMENT
INSERT INTO departments(department_code, name)
VALUES ('aksd', 'Phong ban 1');
INSERT INTO departments(department_code, name)
VALUES ('jd28', 'Phong ban 2');
INSERT INTO departments(department_code, name)
VALUES ('1jk0', 'Phong ban 3');
