CREATE TABLE EmployeeSalaries(
	employee_id INT,
	department VARCHAR(20) NOT NULL,
	quarter VARCHAR(20) NOT NULL,
	salary INT NOT NULL,
	bonus INT,
	tax INT
);

INSERT INTO EmployeeSalaries(employee_id, department, quarter, salary, bonus, tax) VALUES
(101, 'HR', 'Q1', 5000, 500, 100),
(101, 'HR', 'Q2', 5200, 520, 104),
(101, 'HR', 'Q3', 5300, 530, 106),
(102, 'IT', 'Q1', 6000, 600, 120),
(102, 'IT', 'Q2', 6100, 610, 122),
(102, 'IT', 'Q3', 6200, 620, 124),
(103, 'FINANCE', 'Q1', 7000, 700, 140),
(103, 'FINANCE', 'Q2', 7100, 710, 142),
(103, 'FINANCE', 'Q3', 7200, 720, 144);

-- Pivot the quarterly salary data for each employee
SELECT * FROM CROSSTAB(
	'SELECT employee_id, quarter, salary
	FROM EmployeeSalaries
	ORDER BY employee_id, quarter'
) AS ct(employee_id INT, Q1_SALARY INT, Q2_SALARY INT, Q3_SALARY INT);

SELECT * FROM CROSSTAB(
	'SELECT employee_id, quarter, salary
	FROM EmployeeSalaries
	ORDER BY employee_id, quarter'
) AS EmployeeQuarterlySalaries(employee_id INT, Q1_SALARY INT, Q2_SALARY INT, Q3_SALARY INT);



SELECT employee_id, 'Q1' AS Quarter, Q1_SALARY AS SALARY FROM EmploeePivot
UNION ALL
SELECT employee_id, 'Q2' AS Quarter, Q2_SALARY AS SALARY FROM EmploeePivot
UNION ALL
SELECT employee_id, 'Q3' AS Quarter, Q3_SALARY AS SALARY FROM EmploeePivot
ORDER BY employee_id, Quarter;




SELECT * FROM EmployeeSalaries;