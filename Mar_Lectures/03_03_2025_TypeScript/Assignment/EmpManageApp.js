var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
// Abstract Employee class
var Emp = /** @class */ (function () {
    function Emp(name, age, salary, designation, department) {
        this.eid = Emp.nextEid++;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.designation = designation;
        this.department = department;
        Emp.countEmp++;
    }
    Emp.prototype.display = function () {
        console.log("Name: " + this.name);
        console.log("Age: " + this.age);
        console.log("Salary: " + this.salary);
        console.log("Designation: " + this.designation);
        console.log("Department: " + this.department);
        console.log();
    };
    Emp.prototype.getEid = function () {
        return this.eid;
    };
    Emp.prototype.getDesignation = function () {
        return this.designation;
    };
    Emp.prototype.getName = function () {
        return this.name;
    };
    Emp.prototype.getAge = function () {
        return this.age;
    };
    Emp.prototype.getSalary = function () {
        return this.salary;
    };
    Emp.prototype.getDepartment = function () {
        return this.department;
    };
    Emp.prototype.setSalary = function (salary) {
        this.salary = salary;
    };
    Emp.getCountEmp = function () {
        return Emp.countEmp;
    };
    Emp.decrementCountEmp = function () {
        Emp.countEmp--;
    };
    Emp.countEmp = 0;
    Emp.nextEid = 1;
    return Emp;
}());
var Clerk = /** @class */ (function (_super) {
    __extends(Clerk, _super);
    function Clerk(name, age, salary, department) {
        return _super.call(this, name, age, salary, "Clerk", department) || this;
    }
    Clerk.prototype.raiseSalary = function () {
        this.salary += 2000;
    };
    return Clerk;
}(Emp));
var Programmer = /** @class */ (function (_super) {
    __extends(Programmer, _super);
    function Programmer(name, age, salary, department) {
        return _super.call(this, name, age, salary, "Programmer", department) || this;
    }
    Programmer.prototype.raiseSalary = function () {
        this.salary += 5000;
    };
    return Programmer;
}(Emp));
var Manager = /** @class */ (function (_super) {
    __extends(Manager, _super);
    function Manager(name, age, salary, department) {
        return _super.call(this, name, age, salary, "Manager", department) || this;
    }
    Manager.prototype.raiseSalary = function () {
        this.salary += 15000;
    };
    return Manager;
}(Emp));
var CEO = /** @class */ (function (_super) {
    __extends(CEO, _super);
    function CEO(name, age, salary, department) {
        var _this = _super.call(this, name, age, salary, "CEO", department) || this;
        CEO.isCEO = true;
        return _this;
    }
    CEO.resetCEO = function () {
        CEO.isCEO = false;
    };
    CEO.prototype.raiseSalary = function () {
        this.salary += 150000;
    };
    CEO.isCEO = false;
    return CEO;
}(Emp));
var EmpFactory = /** @class */ (function () {
    function EmpFactory() {
    }
    EmpFactory.createEmp = function (type, name, age, salary, department) {
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
    };
    return EmpFactory;
}());
var employees = new Map();
function updateEmployeeCount() {
    var countElement = document.getElementById('employeeCount');
    if (countElement) {
        countElement.textContent = Emp.getCountEmp().toString();
    }
}
function addEmployee(event) {
    event.preventDefault();
    var nameInput = document.getElementById('name');
    var ageInput = document.getElementById('age');
    var salaryInput = document.getElementById('salary');
    var designationInput = document.getElementById('designation');
    var departmentInput = document.getElementById('department');
    var msgElement = document.getElementById('msg');
    var name = nameInput.value.trim();
    var age = parseInt(ageInput.value);
    var salary = parseInt(salaryInput.value);
    var designation = designationInput.value.trim();
    var department = departmentInput.value.trim();
    // Validation
    if (!name || !age || !salary || !designation || !department) {
        if (msgElement)
            msgElement.textContent = "All fields are required!";
        return;
    }
    if (name.split(' ').length < 2 || !(/^[A-Z]/.test(name))) {
        if (msgElement)
            msgElement.textContent = "Name must start with a capital letter and contain at least two words";
        return;
    }
    if (age < 20 || age > 60) {
        if (msgElement)
            msgElement.textContent = "Age must be between 20 and 60";
        return;
    }
    // Create the employee
    try {
        var newEmployee = EmpFactory.createEmp(designation, name, age, salary, department);
        employees.set(newEmployee.getEid(), newEmployee);
        // Reset form and show success message
        document.getElementById('addEmpForm').reset();
        if (msgElement)
            msgElement.textContent = "Employee added successfully!";
        // Update employee count
        updateEmployeeCount();
        // Refresh the table if we're on the display section
        if (document.getElementById('employeeTableBody')) {
            refreshEmployeeTable();
        }
    }
    catch (error) {
        if (msgElement)
            msgElement.textContent = "Error adding employee: " + error;
    }
}
function refreshEmployeeTable() {
    var tableBody = document.getElementById('employeeTableBody');
    if (!tableBody)
        return;
    // Clear the table
    tableBody.innerHTML = '';
    if (employees.size === 0) {
        var row = document.createElement('tr');
        var cell = document.createElement('td');
        cell.colSpan = 6; // Updated for EID column
        cell.textContent = "No employees found";
        cell.style.textAlign = "center";
        row.appendChild(cell);
        tableBody.appendChild(row);
        return;
    }
    // Add all employees to the table
    employees.forEach(function (employee) {
        var row = document.createElement('tr');
        var eidCell = document.createElement('td');
        eidCell.textContent = employee.getEid().toString();
        row.appendChild(eidCell);
        var nameCell = document.createElement('td');
        nameCell.textContent = employee.getName();
        row.appendChild(nameCell);
        var ageCell = document.createElement('td');
        ageCell.textContent = employee.getAge().toString();
        row.appendChild(ageCell);
        var salaryCell = document.createElement('td');
        salaryCell.textContent = employee.getSalary().toString();
        row.appendChild(salaryCell);
        var designationCell = document.createElement('td');
        designationCell.textContent = employee.getDesignation();
        row.appendChild(designationCell);
        var departmentCell = document.createElement('td');
        departmentCell.textContent = employee.getDepartment();
        row.appendChild(departmentCell);
        tableBody.appendChild(row);
    });
}
function deleteEmployee(event) {
    event.preventDefault();
    var idInput = document.getElementById('removeId');
    var msgElement = document.getElementById('removeMsg');
    var id = parseInt(idInput.value);
    if (isNaN(id)) {
        if (msgElement)
            msgElement.textContent = "Please enter a valid ID";
        return;
    }
    if (!employees.has(id)) {
        if (msgElement)
            msgElement.textContent = "Employee with ID " + id + " not found";
        return;
    }
    // Handle CEO deletion specially
    var employeeToDelete = employees.get(id);
    if (employeeToDelete instanceof CEO) {
        CEO.resetCEO();
    }
    employees.delete(id);
    Emp.decrementCountEmp();
    if (msgElement)
        msgElement.textContent = "Employee deleted successfully";
    // Update employee count
    updateEmployeeCount();
    // Reset form
    document.getElementById('deleteEmployeeForm').reset();
    // Refresh table if on display page
    if (document.getElementById('employeeTableBody')) {
        refreshEmployeeTable();
    }
}
function updateEmployeeSalary(event) {
    event.preventDefault();
    var idInput = document.getElementById('updateId');
    var salaryInput = document.getElementById('updateSalary');
    var msgElement = document.getElementById('updateMsg');
    var id = parseInt(idInput.value);
    var salary = parseInt(salaryInput.value);
    if (isNaN(id) || isNaN(salary)) {
        if (msgElement)
            msgElement.textContent = "Please enter valid values";
        return;
    }
    if (!employees.has(id)) {
        if (msgElement)
            msgElement.textContent = "Employee with ID " + id + " not found";
        return;
    }
    var employee = employees.get(id);
    if (employee) {
        employee.setSalary(salary);
        if (msgElement)
            msgElement.textContent = "Salary updated successfully";
        document.getElementById('updateEmployeeForm').reset();
        if (document.getElementById('employeeTableBody')) {
            refreshEmployeeTable();
        }
    }
}
function searchEmployees(event) {
    event.preventDefault();
    var searchTypeSelect = document.getElementById('searchType');
    var searchValueInput = document.getElementById('searchValue');
    var resultsDiv = document.getElementById('searchResults');
    if (!resultsDiv)
        return;
    var searchType = searchTypeSelect.value;
    var searchValue = searchValueInput.value.trim();
    if (!searchValue) {
        resultsDiv.innerHTML = '<p class="error">Please enter a search value</p>';
        return;
    }
    var foundEmployees = [];
    employees.forEach(function (employee) {
        var match = false;
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
    }
    else {
        var html_1 = "\n            <h3>Search Results (".concat(foundEmployees.length, " found)</h3>\n            <table>\n                <thead>\n                    <tr>\n                        <th>EID</th>\n                        <th>Name</th>\n                        <th>Age</th>\n                        <th>Salary</th>\n                        <th>Designation</th>\n                        <th>Department</th>\n                    </tr>\n                </thead>\n                <tbody>\n        ");
        foundEmployees.forEach(function (emp) {
            html_1 += "\n                <tr>\n                    <td>".concat(emp.getEid(), "</td>\n                    <td>").concat(emp.getName(), "</td>\n                    <td>").concat(emp.getAge(), "</td>\n                    <td>").concat(emp.getSalary(), "</td>\n                    <td>").concat(emp.getDesignation(), "</td>\n                    <td>").concat(emp.getDepartment(), "</td>\n                </tr>\n            ");
        });
        html_1 += "\n                </tbody>\n            </table>\n        ";
        resultsDiv.innerHTML = html_1;
    }
}
document.addEventListener('DOMContentLoaded', function () {
    updateEmployeeCount();
});
function renderSection(section) {
    var main = document.getElementById('main');
    console.log(main);
    var templates = {
        add_employee: "\n                <h2>Add Employee</h2>\n        <div class=\"card\">\n            <div class=\"contact-form\">\n                <h3>Enter Employee Details</h3>\n                <form id=\"addEmpForm\">\n                    <div class=\"form-group\">\n                        <label for=\"name\">Employee Name</label>\n                        <input type=\"text\" id=\"name\" name=\"name\" placeholder=\"First Last\" required>\n                        <small>Must start with capital letter and contain at least two words</small>\n                    </div>\n                    <div class=\"form-group\">\n                        <label for=\"age\">Age</label>\n                        <input type=\"number\" id=\"age\" name=\"age\" min=\"20\" max=\"60\" placeholder=\"Age (20-60)\" required>\n                    </div>\n                    <div class=\"form-group\">\n                        <label for=\"salary\">Salary</label>\n                        <input type=\"number\" id=\"salary\" name=\"salary\" placeholder=\"Salary Amount\" required>\n                    </div>\n                    <div class=\"form-group\">\n                        <label for=\"designation\">Designation</label>\n                        <select id=\"designation\" name=\"designation\" required>\n                            <option value=\"\">Select Designation</option>\n                            <option value=\"CEO\">CEO</option>\n                            <option value=\"Manager\">Manager</option>\n                            <option value=\"Programmer\">Programmer</option>\n                            <option value=\"Clerk\">Clerk</option>\n                        </select>\n                    </div>\n                    <div class=\"form-group\">\n                        <label for=\"department\">Department</label>\n                        <input type=\"text\" id=\"department\" name=\"department\" placeholder=\"Department\" required>\n                    </div>\n                    <button type=\"submit\" class=\"submit-btn\" onClick=\"addEmployee(event)\">Add Employee</button>\n                </form>\n                <div class=\"message\" id=\"msg\"></div>\n            </div>\n        </div>",
        display_employee: "\n                <h2>All Employees</h2>\n        <div class=\"card\">\n            <div class=\"employee-table\">\n                <table>\n                    <thead>\n                        <tr>\n                            <th>EID</th>\n                            <th>Name</th>\n                            <th>Age</th>\n                            <th>Salary</th>\n                            <th>Designation</th>\n                            <th>Department</th>\n                        </tr>\n                    </thead>\n                    <tbody id=\"employeeTableBody\">\n                        <!-- Employee rows will be dynamically added here -->\n                    </tbody>\n                </table>\n            </div>\n        </div>",
        search_employee: "\n                <h2>Search Employees</h2>\n        <div class=\"card\">\n            <div class=\"contact-form\">\n                <h3>Search by Criteria</h3>\n                <form id=\"searchEmployeeForm\">\n                    <div class=\"form-group\">\n                        <label for=\"searchType\">Search By</label>\n                        <select id=\"searchType\" name=\"searchType\" required>\n                            <option value=\"eid\">Employee ID</option>\n                            <option value=\"name\">Name</option>\n                            <option value=\"designation\">Designation</option>\n                            <option value=\"department\">Department</option>\n                        </select>\n                    </div>\n                    <div class=\"form-group\">\n                        <label for=\"searchValue\">Search Value</label>\n                        <input type=\"text\" id=\"searchValue\" name=\"searchValue\" placeholder=\"Enter search value\" required>\n                    </div>\n                    <button type=\"submit\" class=\"submit-btn\" onClick=\"searchEmployees(event)\">Search</button>\n                </form>\n                <div id=\"searchResults\" class=\"search-results mt-4\">\n                    <!-- Search results will appear here -->\n                </div>\n            </div>\n        </div>",
        update_employee: "\n                <h2>Update Employee Salary</h2>\n        <div class=\"card\">\n            <div class=\"contact-form\">\n                <h3>Update Salary</h3>\n                <form id=\"updateEmployeeForm\">\n                    <div class=\"form-group\">\n                        <label for=\"updateId\">Employee ID</label>\n                        <input type=\"number\" id=\"updateId\" name=\"updateId\" placeholder=\"Enter Employee ID\" required>\n                    </div>\n                    <div class=\"form-group\">\n                        <label for=\"updateSalary\">New Salary</label>\n                        <input type=\"number\" id=\"updateSalary\" name=\"updateSalary\" placeholder=\"Enter New Salary\" required>\n                    </div>\n                    <button type=\"submit\" class=\"submit-btn\" onClick=\"updateEmployeeSalary(event)\">Update Salary</button>\n                </form>\n                <div class=\"message\" id=\"updateMsg\"></div>\n            </div>\n        </div>",
        remove_employee: "\n                <h2>Remove Employee</h2>\n        <div class=\"card\">\n            <div class=\"contact-form\">\n                <h3>Enter Employee ID to Remove</h3>\n                <form id=\"deleteEmployeeForm\">\n                    <div class=\"form-group\">\n                        <label for=\"removeId\">Employee ID</label>\n                        <input type=\"number\" id=\"removeId\" name=\"removeId\" placeholder=\"Enter Employee ID\" required>\n                    </div>\n                    <button type=\"submit\" class=\"submit-btn danger\" onClick=\"deleteEmployee(event)\">Remove Employee</button>\n                </form>\n                <div class=\"message\" id=\"removeMsg\"></div>\n            </div>\n        </div>"
    };
    main.innerHTML = templates[section] || "<h2>Invalid Section</h2>";
}
