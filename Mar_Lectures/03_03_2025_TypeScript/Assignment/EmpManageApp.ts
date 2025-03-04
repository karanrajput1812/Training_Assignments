abstract class Emp {
    protected name: string;
    protected age: number;
    protected salary: number;
    protected eid: number;
    protected designation: string;
    protected department: string;

    protected static countEmp: number = 0;
    protected static nextEid: number = 1;

    constructor(name: string, age: number, salary: number, designation: string, department: string) {
        this.eid = Emp.nextEid++;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.designation = designation;
        this.department = department;
        Emp.countEmp++;
    }

    public display(): void {
        console.log("Name: " + this.name);
        console.log("Age: " + this.age);
        console.log("Salary: " + this.salary);
        console.log("Designation: " + this.designation);
        console.log("Department: " + this.department);
        console.log();
    }

    public getEid(): number {
        return this.eid;
    }

    public abstract raiseSalary(): void;

    public getDesignation(): string {
        return this.designation;
    }

    public getName(): string {
        return this.name;
    }

    public getAge(): number {
        return this.age;
    }

    public getSalary(): number {
        return this.salary;
    }

    public getDepartment(): string {
        return this.department;
    }

    public setSalary(salary: number): void {
        this.salary = salary;
    }

    public static getCountEmp(): number {
        return Emp.countEmp;
    }

    public static decrementCountEmp(): void {
        Emp.countEmp--;
    }
}

class Clerk extends Emp {
    constructor(name: string, age: number, salary: number, department: string) {
        super(name, age, salary, "Clerk", department);
    }

    public override raiseSalary(): void {
        this.salary += 2000;
    }
}

class Programmer extends Emp {
    constructor(name: string, age: number, salary: number, department: string) {
        super(name, age, salary, "Programmer", department);
    }

    public override raiseSalary(): void {
        this.salary += 5000;
    }
}

class Manager extends Emp {
    constructor(name: string, age: number, salary: number, department: string) {
        super(name, age, salary, "Manager", department);
    }

    public override raiseSalary(): void {
        this.salary += 15000;
    }
}

class CEO extends Emp {
    protected static isCEO: boolean = false;

    constructor(name: string, age: number, salary: number, department: string) {
        super(name, age, salary, "CEO", department);
        CEO.isCEO = true;
    }

    public static resetCEO(): void {
        CEO.isCEO = false;
    }

    public override raiseSalary(): void {
        this.salary += 150000;
    }
}

class EmpFactory {
    public static createEmp(type: string, name: string, age: number, salary: number, department: string): Emp {
        switch (type) {
            case "CEO":
                return new CEO(name, age, salary, department);
            case "Clerk":
                return new Clerk(name, age, salary, department);
            case "Programmer":
                return new Programmer(name, age, salary, department);
            case "Manager":
                return new Manager(name, age, salary, department);
            default:
                return new Clerk(name, age, salary, department);
        }
    }
}

const employees: Map<number, Emp> = new Map<number, Emp>();

function updateEmployeeCount(): void {
    const countElement = document.getElementById('employeeCount');
    if (countElement) {
        countElement.textContent = Emp.getCountEmp().toString();
    }
}

function addEmployee(event: Event): void {
    event.preventDefault();
    
    const nameInput = document.getElementById('name') as HTMLInputElement;
    const ageInput = document.getElementById('age') as HTMLInputElement;
    const salaryInput = document.getElementById('salary') as HTMLInputElement;
    const designationInput = document.getElementById('designation') as HTMLSelectElement;
    const departmentInput = document.getElementById('department') as HTMLInputElement;
    const msgElement = document.getElementById('msg');
    
    const name = nameInput.value.trim();
    const age = parseInt(ageInput.value);
    const salary = parseInt(salaryInput.value);
    const designation = designationInput.value.trim();
    const department = departmentInput.value.trim();
    
    // Validation
    if (!name || !age || !salary || !designation || !department) {
        if (msgElement) msgElement.textContent = "All fields are required!";
        return;
    }
    
    if (name.split(' ').length < 2 || !(/^[A-Z]/.test(name))) {
        if (msgElement) msgElement.textContent = "Name must start with a capital letter and contain at least two words";
        return;
    }
    
    if (age < 20 || age > 60) {
        if (msgElement) msgElement.textContent = "Age must be between 20 and 60";
        return;
    }
    
    // Create the employee
    try {
        const newEmployee = EmpFactory.createEmp(designation, name, age, salary, department);
        
        employees.set(newEmployee.getEid(), newEmployee);
        
        // Reset form and show success message
        (document.getElementById('addEmpForm') as HTMLFormElement).reset();
        if (msgElement) msgElement.textContent = "Employee added successfully!";
        
        // Update employee count
        updateEmployeeCount();
        
        // Refresh the table if we're on the display section
        if (document.getElementById('employeeTableBody')) {
            refreshEmployeeTable();
        }
    } catch (error) {
        if (msgElement) msgElement.textContent = "Error adding employee: " + error;
    }
}

function refreshEmployeeTable(): void {
    const tableBody = document.getElementById('employeeTableBody');
    if (!tableBody) return;
    
    // Clear the table
    tableBody.innerHTML = '';
    
    if (employees.size === 0) {
        const row = document.createElement('tr');
        const cell = document.createElement('td');
        cell.colSpan = 6; // Updated for EID column
        cell.textContent = "No employees found";
        cell.style.textAlign = "center";
        row.appendChild(cell);
        tableBody.appendChild(row);
        return;
    }
    
    // Add all employees to the table
    employees.forEach(employee => {
        const row = document.createElement('tr');
        
        const eidCell = document.createElement('td');
        eidCell.textContent = employee.getEid().toString();
        row.appendChild(eidCell);
        
        const nameCell = document.createElement('td');
        nameCell.textContent = employee.getName();
        row.appendChild(nameCell);
        
        const ageCell = document.createElement('td');
        ageCell.textContent = employee.getAge().toString();
        row.appendChild(ageCell);
        
        const salaryCell = document.createElement('td');
        salaryCell.textContent = employee.getSalary().toString();
        row.appendChild(salaryCell);
        
        const designationCell = document.createElement('td');
        designationCell.textContent = employee.getDesignation();
        row.appendChild(designationCell);
        
        const departmentCell = document.createElement('td');
        departmentCell.textContent = employee.getDepartment();
        row.appendChild(departmentCell);
        
        tableBody.appendChild(row);
    });
}

function deleteEmployee(event: Event): void {
    event.preventDefault();
    
    const idInput = document.getElementById('removeId') as HTMLInputElement;
    const msgElement = document.getElementById('removeMsg');
    
    const id = parseInt(idInput.value);
    
    if (isNaN(id)) {
        if (msgElement) msgElement.textContent = "Please enter a valid ID";
        return;
    }
    
    if (!employees.has(id)) {
        if (msgElement) msgElement.textContent = "Employee with ID " + id + " not found";
        return;
    }
    
    // Handle CEO deletion specially
    const employeeToDelete = employees.get(id);
    if (employeeToDelete instanceof CEO) {
        CEO.resetCEO();
    }
    
    employees.delete(id);
    Emp.decrementCountEmp();
    
    if (msgElement) msgElement.textContent = "Employee deleted successfully";
    
    // Update employee count
    updateEmployeeCount();
    
    // Reset form
    (document.getElementById('deleteEmployeeForm') as HTMLFormElement).reset();
    
    // Refresh table if on display page
    if (document.getElementById('employeeTableBody')) {
        refreshEmployeeTable();
    }
}

function updateEmployeeSalary(event: Event): void {
    event.preventDefault();
    
    const idInput = document.getElementById('updateId') as HTMLInputElement;
    const salaryInput = document.getElementById('updateSalary') as HTMLInputElement;
    const msgElement = document.getElementById('updateMsg');
    
    const id = parseInt(idInput.value);
    const salary = parseInt(salaryInput.value);
    
    if (isNaN(id) || isNaN(salary)) {
        if (msgElement) msgElement.textContent = "Please enter valid values";
        return;
    }
    
    if (!employees.has(id)) {
        if (msgElement) msgElement.textContent = "Employee with ID " + id + " not found";
        return;
    }
    
    const employee = employees.get(id);

    if (employee) {
        employee.setSalary(salary);
        if (msgElement) msgElement.textContent = "Salary updated successfully";
        
        (document.getElementById('updateEmployeeForm') as HTMLFormElement).reset();
        
        if (document.getElementById('employeeTableBody')) {
            refreshEmployeeTable();
        }
    }
}

function searchEmployees(event: Event): void {
    event.preventDefault();
    
    const searchTypeSelect = document.getElementById('searchType') as HTMLSelectElement;
    const searchValueInput = document.getElementById('searchValue') as HTMLInputElement;
    const resultsDiv = document.getElementById('searchResults');
    
    if (!resultsDiv) return;
    
    const searchType = searchTypeSelect.value;
    const searchValue = searchValueInput.value.trim();
    
    if (!searchValue) {
        resultsDiv.innerHTML = '<p class="error">Please enter a search value</p>';
        return;
    }
    const foundEmployees: Emp[] = [];
    
    employees.forEach(employee => {
        let match = false;
        
        switch (searchType) {
            case 'eid':
                match = employee.getEid().toString() === searchValue;
                break;
            case 'name':
                match = employee.getName().toLowerCase().includes(searchValue.toLowerCase());
                break;
            case 'designation':
                match = employee.getDesignation().toLowerCase() === searchValue.toLowerCase();
                break;
            case 'department':
                match = employee.getDepartment().toLowerCase().includes(searchValue.toLowerCase());
                break;
        }
        
        if (match) {
            foundEmployees.push(employee);
        }
    });
    
    if (foundEmployees.length === 0) {
        resultsDiv.innerHTML = '<p>No employees found matching your search criteria</p>';
    } else {
       let html = `
            <h3>Search Results (${foundEmployees.length} found)</h3>
            <table>
                <thead>
                    <tr>
                        <th>EID</th>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Salary</th>
                        <th>Designation</th>
                        <th>Department</th>
                    </tr>
                </thead>
                <tbody>
        `;
        
        foundEmployees.forEach(emp => {
            html += `
                <tr>
                    <td>${emp.getEid()}</td>
                    <td>${emp.getName()}</td>
                    <td>${emp.getAge()}</td>
                    <td>${emp.getSalary()}</td>
                    <td>${emp.getDesignation()}</td>
                    <td>${emp.getDepartment()}</td>
                </tr>
            `;
        });
        
        html += `
                </tbody>
            </table>
        `;
        
        resultsDiv.innerHTML = html;
    }
}
document.addEventListener('DOMContentLoaded', () => {
    updateEmployeeCount();
});

function renderSection(section) {
    let main = document.getElementById('main');
    console.log(main);
    const templates = {
        add_employee: `
                <h2>Add Employee</h2>
        <div class="card">
            <div class="contact-form">
                <h3>Enter Employee Details</h3>
                <form id="addEmpForm">
                    <div class="form-group">
                        <label for="name">Employee Name</label>
                        <input type="text" id="name" name="name" placeholder="First Last" required>
                        <small>Must start with capital letter and contain at least two words</small>
                    </div>
                    <div class="form-group">
                        <label for="age">Age</label>
                        <input type="number" id="age" name="age" min="20" max="60" placeholder="Age (20-60)" required>
                    </div>
                    <div class="form-group">
                        <label for="salary">Salary</label>
                        <input type="number" id="salary" name="salary" placeholder="Salary Amount" required>
                    </div>
                    <div class="form-group">
                        <label for="designation">Designation</label>
                        <select id="designation" name="designation" required>
                            <option value="">Select Designation</option>
                            <option value="CEO">CEO</option>
                            <option value="Manager">Manager</option>
                            <option value="Programmer">Programmer</option>
                            <option value="Clerk">Clerk</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="department">Department</label>
                        <input type="text" id="department" name="department" placeholder="Department" required>
                    </div>
                    <button type="submit" class="submit-btn" onClick="addEmployee(event)">Add Employee</button>
                </form>
                <div class="message" id="msg"></div>
            </div>
        </div>`,

        display_employee: `
                <h2>All Employees</h2>
        <div class="card">
            <div class="employee-table">
                <table>
                    <thead>
                        <tr>
                            <th>EID</th>
                            <th>Name</th>
                            <th>Age</th>
                            <th>Salary</th>
                            <th>Designation</th>
                            <th>Department</th>
                        </tr>
                    </thead>
                    <tbody id="employeeTableBody">
                        <!-- Employee rows will be dynamically added here -->
                    </tbody>
                </table>
            </div>
        </div>`,

        search_employee: `
                <h2>Search Employees</h2>
        <div class="card">
            <div class="contact-form">
                <h3>Search by Criteria</h3>
                <form id="searchEmployeeForm">
                    <div class="form-group">
                        <label for="searchType">Search By</label>
                        <select id="searchType" name="searchType" required>
                            <option value="eid">Employee ID</option>
                            <option value="name">Name</option>
                            <option value="designation">Designation</option>
                            <option value="department">Department</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="searchValue">Search Value</label>
                        <input type="text" id="searchValue" name="searchValue" placeholder="Enter search value" required>
                    </div>
                    <button type="submit" class="submit-btn" onClick="searchEmployees(event)">Search</button>
                </form>
                <div id="searchResults" class="search-results mt-4">
                    <!-- Search results will appear here -->
                </div>
            </div>
        </div>`,

        update_employee: `
                <h2>Update Employee Salary</h2>
        <div class="card">
            <div class="contact-form">
                <h3>Update Salary</h3>
                <form id="updateEmployeeForm">
                    <div class="form-group">
                        <label for="updateId">Employee ID</label>
                        <input type="number" id="updateId" name="updateId" placeholder="Enter Employee ID" required>
                    </div>
                    <div class="form-group">
                        <label for="updateSalary">New Salary</label>
                        <input type="number" id="updateSalary" name="updateSalary" placeholder="Enter New Salary" required>
                    </div>
                    <button type="submit" class="submit-btn" onClick="updateEmployeeSalary(event)">Update Salary</button>
                </form>
                <div class="message" id="updateMsg"></div>
            </div>
        </div>`,
        remove_employee: `
                <h2>Remove Employee</h2>
        <div class="card">
            <div class="contact-form">
                <h3>Enter Employee ID to Remove</h3>
                <form id="deleteEmployeeForm">
                    <div class="form-group">
                        <label for="removeId">Employee ID</label>
                        <input type="number" id="removeId" name="removeId" placeholder="Enter Employee ID" required>
                    </div>
                    <button type="submit" class="submit-btn danger" onClick="deleteEmployee(event)">Remove Employee</button>
                </form>
                <div class="message" id="removeMsg"></div>
            </div>
        </div>`
    };

    main.innerHTML = templates[section] || "<h2>Invalid Section</h2>";
}
