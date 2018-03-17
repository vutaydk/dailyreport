USE master;  
GO 

--Delete the dailyreport database if it exists.  
IF EXISTS(SELECT * from sys.databases WHERE name='dailyreport')  
BEGIN  
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

-- CREATE TABLE USERS
CREATE TABLE users (
	id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    employee_code varchar(12) NOT NULL,
    password varchar(18),
    rights int,
    name nvarchar(150),
    created_at datetime,
	updated_at datetime
	CONSTRAINT FK_rights_users FOREIGN KEY (rights)
    REFERENCES rights(id)
);
GO

-- CREATE TABLE REPORTS
CREATE TABLE tasks (
    id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    task_code varchar(4),
    name nvarchar(50),
    created_at datetime,
    updated_at datetime
);
GO

-- CREATE TABLE REPORTS
CREATE TABLE reports (
    id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    time_worked time(7),
    note nvarchar(500),
	project_id int,
	task_id int,
	user_id int,
    approver int,
	approved_at datetime,
	created_at datetime,
	updated_at datetime,
	CONSTRAINT FK_project_report FOREIGN KEY (project_id)
    REFERENCES projects(id),
    CONSTRAINT FK_task_report FOREIGN KEY (task_id)
    REFERENCES tasks(id),
	CONSTRAINT FK_users_report FOREIGN KEY (user_id)
    REFERENCES users(id),
	CONSTRAINT FK_approver_report FOREIGN KEY (approver)
    REFERENCES users(id)
);
GO


-- INSERT INTO RIGHTS
INSERT INTO rights(name, level)
VALUES ('director', 3); 

INSERT INTO rights(name, level)
VALUES ('manager', 2);

INSERT INTO rights(name, level)
VALUES ('leader', 1);

-- INSERT INTO USERS
INSERT INTO users(employee_code, password, rights, name)
VALUES ('hanh', '1111', 1, 'Nguyen Hanh'); 
INSERT INTO users(employee_code, password, name)
VALUES ('giaduc', '1111', 'Gia duc'); 
