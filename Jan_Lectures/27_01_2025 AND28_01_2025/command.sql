-- 27_01_2025
select * from employees;
 
drop table employees;
 
CREATE TYPE designation_enum AS ENUM ('Programmer', 'Tester', 'Admin', 'Manager');
 
 
CREATE TABLE employees(
	eid SERIAL PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	age SMALLINT,
	gender VARCHAR(15) NOT NULL,
	salary NUMERIC(8, 2) DEFAULT 15000.00,
	designation designation_enum NOT NULL,
	email_id VARCHAR(255) NOT NULL,
	married BOOLEAN NOT NULL,
	joining_date DATE DEFAULT CURRENT_DATE,
	manager_id int,
	CONSTRAINT fk_manager FOREIGN KEY (manager_id) REFERENCES employees (eid),
	CONSTRAINT check_email_id CHECK(email_id ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
	CONSTRAINT check_age CHECK(age>=21 AND age<=60),
	CONSTRAINT check_gender CHECK(gender IN ('Male', 'Female'))
);
 
INSERT INTO employees (name, age, gender, salary, designation, email_id, married, joining_date)
VALUES ('Alice Johnson', 35, 'Female', 25000.00, 'Manager', 'alice.johnson@example.com', TRUE, '2025-01-01');
 
INSERT INTO employees (name, age, gender, salary, designation, email_id, married, joining_date, manager_id)
VALUES ('Bob Smith', 28, 'Male', 20000.00, 'Tester', 'bob.smith@example.com', FALSE, '2025-01-10', 1);
 
SELECT * FROM employees;
 
INSERT INTO employees (name, age, gender, salary, designation, email_id, married, joining_date, manager_id)
VALUES
	('Bob Smith', 28, 'Male', 25000.00, 'Programmer', 'bob.smith@example.com', FALSE, CURRENT_DATE, 1),
    ('Charlie Brown', 32, 'Male', 22000.00, 'Tester', 'charlie.brown@example.com', TRUE, CURRENT_DATE, 1),
    ('Diana White', 30, 'Female', 26000.00, 'Programmer', 'diana.white@example.com', FALSE, CURRENT_DATE, 1),
    -- Admin Reporting to Alice
    ('Eve Green', 35, 'Female', 28000.00, 'Admin', 'eve.green@example.com', TRUE, CURRENT_DATE, 1),
    -- New Manager (No Manager Reference)
    ('Frank Black', 45, 'Male', 35000.00, 'Manager', 'frank.black@example.com', TRUE, CURRENT_DATE, NULL),
    -- Employees Reporting to Frank (Manager with eid = 6)
    ('Grace Adams', 27, 'Female', 24000.00, 'Tester', 'grace.adams@example.com', FALSE, CURRENT_DATE, 6),
    ('Henry Ford', 29, 'Male', 23000.00, 'Programmer', 'henry.ford@example.com', FALSE, CURRENT_DATE, 6),
    ('Ivy Nelson', 31, 'Female', 27000.00, 'Admin', 'ivy.nelson@example.com', TRUE, CURRENT_DATE, 6),
    -- Another Tester Reporting to Bob (eid = 2)
    ('Jack Brown', 26, 'Male', 21000.00, 'Tester', 'jack.brown@example.com', FALSE, CURRENT_DATE, 2);
 
 -- 28_01_2025
select * from employees where age between 30 and 35;
select * from employees where name like '%a';
select * from employees where name like 'a%';
select * from employees where name like '%a%';
 
select * from employees limit 5; -- will get me only 5 records, this is limiting(or for pagination kind of emulation)
select * from employees limit 5 offset 3; -- skip first 3
select * from employees offset 3 limit 5; -- offset before limit also fine
 
 
-- String functions
select upper(name), age from employees;
select reverse('Madam') = 'Madam';
 
select reverse('MadaM');
 
select current_date;
 
create table role (designation varchar(20) primary key, max_limit integer, min_salary integer );
 
insert into role values
('Tester', 100, 20000),
('Programmer', 200, 30000),
('Manager', 15, 120000),
('Clerk', 50, 25000);
 
select employees.EID, employees.NAME, employees.DESIGNATION , employees.SALARY, ROLE.MIN_SALARY from employees, ROLE
where employees.designation = ROLE.designation and employees.SALARY<ROLE.MIN_SALARY;

-- delete from employees where eid = 12;

select employees.EID, employees.NAME, employees.DESIGNATION, employees.SALARY, ROLE.MIN_SALARY
from employees
join ROLE on employees.designation = ROLE.designation
where employees.SALARY < ROLE.MIN_SALARY;



select coalesce(designation::text, 'TOTAL') AS designation, sum(salary)
from employees
group by ROLLUP(designation)
order by sum(salary);

create or replace procedure abc()
language plpgsql
as $$
begin

create or replace procedure insert_emp_record(
e_name varchar(20),
e_age int,
e_gender varchar(10),
e_salary int,
e_designation varchar(10),
e_mrg_id int,
e_email_id varchar(20),
e_married boolean
)
language plpgsql
as $$
begin
	insert into employee (NAME, AGE, GENDER, SALARY, DESIGNATION, MRG_ID, EMAIL_ID, MARRIED)
values
(e_name, e_age, e_gender, e_salary, e_designation, e_mrg_id, e_email_id, e_married);
end;
$$

call insert_emp_record('Prasath',25,'MALE',100000,'PROGRAMMER',3,'prasath@xyz.com',FALSE);


create or replace procedure insert_emp_record(
	e_name varchar,
	e_age SMALLINT,
	e_desig designation_enum,
	e_gender VARCHAR,
	e_salary NUMERIC,
	e_email_id VARCHAR,
	e_married BOOLEAN,
	e_joining_date DATE,
	e_manager_id int
)
language plpgsql
as $$
begin
	insert into employees(name, age, gender, salary, designation, email_id, married, joining_date, manager_id)
	VALUES (e_name, e_age, e_gender,e_salary , e_desig,e_email_id , e_married,e_joining_date , e_manager_id);
end;
$$;

call insert_emp_record('Prashant Patil', 33, 'Male', 20000, 'Manager', 'prashant.patil@example.com', FALSE, '2025-01-10', null);


-- Max salary for particular designation as a input parameter
create or replace function max_salary(desig varchar)
returns table(eid int, name varchar, salary decimal)
as $$
begin
	return query
	select e.eid, e.name, e.salary from employees e
	where e.DESIGNATION = desig:: designation_enum
	and e.salary = (select max(e2.salary) from employees e2
	where e2.designation = desig:: designation_enum);
end;
$$ language plpgsql;

select * from max_salary('Programmer');


--- Salary apprialsal with id and amount
create or replace procedure appraisal (
	id int,
	amount int
)
language plpgsql
as $$
begin
	update employees set salary = salary + amount where EID = id;
end;
$$;

call appraisal(1,5000);



SELECT name, salary, SUM(salary) OVER(ORDER BY salary) FROM employees;
-- add row number in a first column
SELECT ROW_NUMBER() OVER(ORDER BY NAME),name, designation FROM employees;
-- rank based on the salary over is also compulsary for the same (skip ranks if two are same)
SELECT name, salary, RANK() OVER(ORDER BY salary DESC) FROM employees;
-- similar to the rank but doesn't skip any rank
SELECT name, salary, DENSE_RANK() OVER(ORDER BY salary DESC) FROM employees;

-- add previous row salary to the new column named lag
	-- first is null
SELECT name, salary, LAG(salary) OVER() FROM employees;

-- add next row salary to the new column named lead
	-- last is null
SELECT name, salary, LEAD(salary) OVER() FROM employees;

-- Employees who's salary is less than avg salary of the that designation
SELECT e1.name, e1.salary, e1.designation
FROM employees e1
WHERE e1.salary > (SELECT AVG(e2.salary) FROM employees e2 WHERE e2.designation=e1.designation);
	-- OR
WITH avg_sal AS (
select designation, AVG(salary) AS avg_salary from employees GROUP BY designation
)
	SELECT e.eid, e.name, e.designation, e.salary, a.avg_salary
	FROM employees e
	JOIN 
		avg_sal a ON e.designation = a.designation
	WHERE
		e.salary > a.avg_salary;

-- Below trigger is calling this function
CREATE OR REPLACE FUNCTION validate_salary()
RETURNS TRIGGER AS $$
BEGIN
	if new.salary < 12000 then
		new.salary = 12000;
	end if;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- TRIGGER
CREATE OR REPLACE TRIGGER before_updating_salary
BEFORE UPDATE ON employees
FOR EACH ROW
EXECUTE FUNCTION validate_salary();

UPDATE employees SET salary = salary-30000;

select * from employees;
