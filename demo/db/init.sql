-- 1. Choose or initialize your database
CREATE DATABASE IF NOT EXISTS springboot_db;
USE springboot_db;

-- 2. Drop the tables if they already exist (avoids "table already exists" errors)
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;

-- 3. Create the parent table first (to handle foreign key references smoothly)
CREATE TABLE departments (
    dept_id INT AUTO_INCREMENT,
    dept_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (dept_id)
);

-- 4. Create the child table 
CREATE TABLE employees (
    emp_id INT AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    hire_date DATE DEFAULT (CURRENT_DATE),
    dept_id INT,
    PRIMARY KEY (emp_id),
    FOREIGN KEY (dept_id) REFERENCES departments(dept_id) ON DELETE SET NULL
);

-- 5. Insert records into the parent table
INSERT INTO departments (dept_name) 
VALUES 
    ('Engineering'),
    ('Marketing'),
    ('Human Resources');

-- 6. Insert records into the child table (matches the generated dept_ids)
INSERT INTO employees (first_name, last_name, email, dept_id) 
VALUES 
    ('Jane', 'Doe', 'jane.doe@example.com', 1),
    ('John', 'Smith', 'john.smith@example.com', 1),
    ('Alice', 'Johnson', 'alice.j@example.com', 2);

-- 7. Verify the records were inserted successfully
SELECT * FROM departments;
SELECT * FROM employees;
