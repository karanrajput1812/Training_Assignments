var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g = Object.create((typeof Iterator === "function" ? Iterator : Object).prototype);
    return g.next = verb(0), g["throw"] = verb(1), g["return"] = verb(2), typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (g && (g = 0, op[0] && (_ = 0)), _) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
function addEmployee(event) {
    return __awaiter(this, void 0, void 0, function () {
        var nameInput, ageInput, salaryInput, designationInput, departmentInput, msgElement, name, age, salary, designation, department, result, error_1;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    event.preventDefault();
                    nameInput = document.getElementById('name');
                    ageInput = document.getElementById('age');
                    salaryInput = document.getElementById('salary');
                    designationInput = document.getElementById('designation');
                    departmentInput = document.getElementById('department');
                    msgElement = document.getElementById('msg');
                    name = nameInput.value.trim();
                    age = parseInt(ageInput.value);
                    salary = parseInt(salaryInput.value);
                    designation = designationInput.value.trim();
                    department = departmentInput.value.trim();
                    // Validation
                    if (!name || !age || !salary || !designation || !department) {
                        if (msgElement)
                            msgElement.textContent = "All fields are required!";
                        return [2 /*return*/];
                    }
                    if (name.split(' ').length < 2 || !(/^[A-Z]/.test(name))) {
                        if (msgElement)
                            msgElement.textContent = "Name must start with a capital letter and contain at least two words";
                        return [2 /*return*/];
                    }
                    if (age < 20 || age > 60) {
                        if (msgElement)
                            msgElement.textContent = "Age must be between 20 and 60";
                        return [2 /*return*/];
                    }
                    _a.label = 1;
                case 1:
                    _a.trys.push([1, 3, , 4]);
                    return [4 /*yield*/, fetch("http://localhost:8181/saveEmployee", {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json"
                            },
                            body: JSON.stringify({ name: name, age: age, salary: salary, designation: designation, department: department })
                        })];
                case 2:
                    result = _a.sent();
                    if (result.ok) {
                        if (msgElement)
                            msgElement.textContent = "Employee added successfully!";
                    }
                    else {
                        if (msgElement)
                            msgElement.textContent = "Error adding employee: " + result.statusText;
                    }
                    document.getElementById('addEmpForm').reset();
                    getEmployeeCount();
                    return [3 /*break*/, 4];
                case 3:
                    error_1 = _a.sent();
                    if (msgElement)
                        msgElement.textContent = "Error adding employee: " + error_1;
                    return [3 /*break*/, 4];
                case 4: return [2 /*return*/];
            }
        });
    });
}
function getEmployeeCount() {
    return __awaiter(this, void 0, void 0, function () {
        var response, count, countElement, error_2;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    _a.trys.push([0, 3, , 4]);
                    return [4 /*yield*/, fetch("http://localhost:8181/employeeCount")];
                case 1:
                    response = _a.sent();
                    if (!response.ok) {
                        throw new Error("Error: ".concat(response.statusText));
                    }
                    return [4 /*yield*/, response.json()];
                case 2:
                    count = _a.sent();
                    countElement = document.getElementById('employeeCount');
                    if (countElement) {
                        countElement.textContent = count.toString();
                    }
                    return [3 /*break*/, 4];
                case 3:
                    error_2 = _a.sent();
                    console.error("Error fetching employee count:", error_2);
                    return [3 /*break*/, 4];
                case 4: return [2 /*return*/];
            }
        });
    });
}
function searchEmployees(event) {
    return __awaiter(this, void 0, void 0, function () {
        var searchTypeSelect, searchValueInput, resultsDiv, searchType, searchValue, url, response, foundEmployees, html_1, error_3;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    event.preventDefault();
                    searchTypeSelect = document.getElementById('searchType');
                    searchValueInput = document.getElementById('searchValue');
                    resultsDiv = document.getElementById('searchResults');
                    if (!resultsDiv)
                        return [2 /*return*/];
                    searchType = searchTypeSelect.value;
                    searchValue = searchValueInput.value.trim();
                    if (!searchValue) {
                        resultsDiv.innerHTML = '<p class="error">Please enter a search value</p>';
                        return [2 /*return*/];
                    }
                    url = "http://localhost:8181/getBy".concat(searchType, "/").concat(searchValue);
                    _a.label = 1;
                case 1:
                    _a.trys.push([1, 4, , 5]);
                    return [4 /*yield*/, fetch(url)];
                case 2:
                    response = _a.sent();
                    if (!response.ok) {
                        resultsDiv.innerHTML = "<p class=\"error\">Error: ".concat(response.statusText, "</p>");
                        return [2 /*return*/];
                    }
                    return [4 /*yield*/, response.json()];
                case 3:
                    foundEmployees = _a.sent();
                    if (foundEmployees.length === 0) {
                        resultsDiv.innerHTML = '<p>No employees found matching your search criteria</p>';
                    }
                    else {
                        html_1 = "\n                <h3>Search Results (".concat(foundEmployees.length, " found)</h3>\n                <table>\n                    <thead>\n                        <tr>\n                            <th>EID</th>\n                            <th>Name</th>\n                            <th>Age</th>\n                            <th>Salary</th>\n                            <th>Designation</th>\n                            <th>Department</th>\n                        </tr>\n                    </thead>\n                    <tbody>\n            ");
                        foundEmployees.forEach(function (emp) {
                            html_1 += "\n                    <tr>\n                        <td>".concat(emp.eid, "</td>\n                        <td>").concat(emp.name, "</td>\n                        <td>").concat(emp.age, "</td>\n                        <td>").concat(emp.salary, "</td>\n                        <td>").concat(emp.designation, "</td>\n                        <td>").concat(emp.department, "</td>\n                    </tr>\n                ");
                        });
                        html_1 += "\n                    </tbody>\n                </table>\n            ";
                        resultsDiv.innerHTML = html_1;
                    }
                    return [3 /*break*/, 5];
                case 4:
                    error_3 = _a.sent();
                    resultsDiv.innerHTML = "<p class=\"error\">Error: ".concat(error_3, "</p>");
                    return [3 /*break*/, 5];
                case 5: return [2 /*return*/];
            }
        });
    });
}
function getAllEmployees() {
    return __awaiter(this, void 0, void 0, function () {
        var response, result, tableBody, row, cell;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, fetch("http://localhost:8181/allEmployees")];
                case 1:
                    response = _a.sent();
                    console.log(response);
                    return [4 /*yield*/, response.json()];
                case 2:
                    result = _a.sent();
                    tableBody = document.getElementById("employeeTableBody");
                    if (!tableBody)
                        return [2 /*return*/];
                    // Clear the table
                    tableBody.innerHTML = '';
                    if (result.length === 0) {
                        row = document.createElement('tr');
                        cell = document.createElement('td');
                        cell.colSpan = 6; // Updated for EID column
                        cell.textContent = "No employees found";
                        cell.style.textAlign = "center";
                        row.appendChild(cell);
                        tableBody.appendChild(row);
                        return [2 /*return*/];
                    }
                    result.forEach(function (employee) {
                        var row = document.createElement("tr");
                        row.innerHTML = "\n            <td>".concat(employee.eid, "</td>\n            <td>").concat(employee.name, "</td>\n            <td>").concat(employee.age, "</td>\n            <td>").concat(employee.salary, "</td>\n            <td>").concat(employee.designation, "</td>\n            <td>").concat(employee.department, "</td>\n        ");
                        tableBody.appendChild(row);
                    });
                    return [2 /*return*/];
            }
        });
    });
}
function deleteEmployee(event) {
    return __awaiter(this, void 0, void 0, function () {
        var idInput, msgElement, confirmation, result, error_4;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    event.preventDefault();
                    idInput = document.getElementById('removeId');
                    msgElement = document.getElementById('removeMsg');
                    confirmation = confirm("Are you sure you want to delete employee with ID ".concat(idInput.value, "?"));
                    if (!confirmation) {
                        return [2 /*return*/];
                    }
                    _a.label = 1;
                case 1:
                    _a.trys.push([1, 3, , 4]);
                    return [4 /*yield*/, fetch("http://localhost:8181/deleteEmployee/".concat(idInput.value), {
                            method: "DELETE"
                        })];
                case 2:
                    result = _a.sent();
                    if (result.ok) {
                        if (msgElement)
                            msgElement.textContent = "Employee deleted successfully!";
                    }
                    else {
                        if (msgElement)
                            msgElement.textContent = "Error deleting employee: " + result.statusText;
                    }
                    // Reset form and show success message
                    document.getElementById('deleteEmployeeForm').reset();
                    // Update employee count
                    getEmployeeCount();
                    return [3 /*break*/, 4];
                case 3:
                    error_4 = _a.sent();
                    if (msgElement)
                        msgElement.textContent = "Error deleting employee: " + error_4;
                    return [3 /*break*/, 4];
                case 4: return [2 /*return*/];
            }
        });
    });
}
function updateEmployeeSalary(event) {
    return __awaiter(this, void 0, void 0, function () {
        var idInput, salaryInput, msgElement, id, salary, result, error_5;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    event.preventDefault();
                    idInput = document.getElementById('updateId');
                    salaryInput = document.getElementById('updateSalary');
                    msgElement = document.getElementById('updateMsg');
                    id = parseInt(idInput.value);
                    salary = parseFloat(salaryInput.value);
                    _a.label = 1;
                case 1:
                    _a.trys.push([1, 3, , 4]);
                    return [4 /*yield*/, fetch("http://localhost:8181/updateSalary?eid=".concat(id, "&salary=").concat(salary), {
                            method: "PATCH",
                            headers: {
                                "Content-Type": "application/json"
                            }
                        })];
                case 2:
                    result = _a.sent();
                    if (result.ok) {
                        if (msgElement)
                            msgElement.textContent = "Employee salary updated successfully!";
                    }
                    else {
                        if (msgElement)
                            msgElement.textContent = "Error updating employee salary: " + result.statusText;
                    }
                    // Reset form and show success message
                    document.getElementById('updateEmployeeForm').reset();
                    // Update employee count
                    getEmployeeCount();
                    return [3 /*break*/, 4];
                case 3:
                    error_5 = _a.sent();
                    if (msgElement)
                        msgElement.textContent = "Error updating employee salary: " + error_5;
                    return [3 /*break*/, 4];
                case 4: return [2 /*return*/];
            }
        });
    });
}
document.addEventListener('DOMContentLoaded', function () {
    getEmployeeCount();
});
function renderSection(section) {
    var main = document.getElementById('main');
    var templates = {
        add_employee: "\n                <h2>Add Employee</h2>\n        <div class=\"card\">\n            <div class=\"contact-form\">\n                <h3>Enter Employee Details</h3>\n                <form id=\"addEmpForm\">\n                    <div class=\"form-group\">\n                        <label for=\"name\">Employee Name</label>\n                        <input type=\"text\" id=\"name\" name=\"name\" placeholder=\"First Last\" required>\n                        <small>Must start with capital letter and contain at least two words</small>\n                    </div>\n                    <div class=\"form-group\">\n                        <label for=\"age\">Age</label>\n                        <input type=\"number\" id=\"age\" name=\"age\" min=\"20\" max=\"60\" placeholder=\"Age (20-60)\" required>\n                    </div>\n                    <div class=\"form-group\">\n                        <label for=\"salary\">Salary</label>\n                        <input type=\"number\" id=\"salary\" name=\"salary\" placeholder=\"Salary Amount\" required>\n                    </div>\n                    <div class=\"form-group\">\n                        <label for=\"designation\">Designation</label>\n                        <select id=\"designation\" name=\"designation\" required>\n                            <option value=\"\">Select Designation</option>\n                            <option value=\"CEO\">CEO</option>\n                            <option value=\"Manager\">Manager</option>\n                            <option value=\"Programmer\">Programmer</option>\n                            <option value=\"Clerk\">Clerk</option>\n                        </select>\n                    </div>\n                    <div class=\"form-group\">\n                        <label for=\"department\">Department</label>\n                        <input type=\"text\" id=\"department\" name=\"department\" placeholder=\"Department\" required>\n                    </div>\n                    <button type=\"submit\" class=\"submit-btn\" onClick=\"addEmployee(event)\">Add Employee</button>\n                </form>\n                <div class=\"message\" id=\"msg\"></div>\n            </div>\n        </div>",
        display_employee: "\n                <h2>All Employees</h2>\n        <div class=\"card\">\n            <div class=\"employee-table\">\n                <table>\n                    <thead>\n                        <tr>\n                            <th>EID</th>\n                            <th>Name</th>\n                            <th>Age</th>\n                            <th>Salary</th>\n                            <th>Designation</th>\n                            <th>Department</th>\n                        </tr>\n                    </thead>\n                    <tbody id=\"employeeTableBody\">\n                        <!-- Employee rows will be dynamically added here -->\n                    </tbody>\n                </table>\n            </div>\n        </div>",
        search_employee: "\n                <h2>Search Employees</h2>\n        <div class=\"card\">\n            <div class=\"contact-form\">\n                <h3>Search by Criteria</h3>\n                <form id=\"searchEmployeeForm\">\n                    <div class=\"form-group\">\n                        <label for=\"searchType\">Search By</label>\n                        <select id=\"searchType\" name=\"searchType\" required>\n                            <option value=\"Id\">Employee ID</option>\n                            <option value=\"Name\">Name</option>\n                            <option value=\"Designation\">Designation</option>\n                            <option value=\"Separtment\">Department</option>\n                        </select>\n                    </div>\n                    <div class=\"form-group\">\n                        <label for=\"searchValue\">Search Value</label>\n                        <input type=\"text\" id=\"searchValue\" name=\"searchValue\" placeholder=\"Enter search value\" required>\n                    </div>\n                    <button type=\"submit\" class=\"submit-btn\" onClick=\"searchEmployees(event)\">Search</button>\n                </form>\n                <div id=\"searchResults\" class=\"search-results mt-4\">\n                    <!-- Search results will appear here -->\n                </div>\n            </div>\n        </div>",
        update_employee: "\n                <h2>Update Employee Salary</h2>\n        <div class=\"card\">\n            <div class=\"contact-form\">\n                <h3>Update Salary</h3>\n                <form id=\"updateEmployeeForm\">\n                    <div class=\"form-group\">\n                        <label for=\"updateId\">Employee ID</label>\n                        <input type=\"number\" id=\"updateId\" name=\"updateId\" placeholder=\"Enter Employee ID\" required>\n                    </div>\n                    <div class=\"form-group\">\n                        <label for=\"updateSalary\">New Salary</label>\n                        <input type=\"number\" id=\"updateSalary\" name=\"updateSalary\" placeholder=\"Enter New Salary\" required>\n                    </div>\n                    <button type=\"submit\" class=\"submit-btn\" onClick=\"updateEmployeeSalary(event)\">Update Salary</button>\n                </form>\n                <div class=\"message\" id=\"updateMsg\"></div>\n            </div>\n        </div>",
        remove_employee: "\n                <h2>Remove Employee</h2>\n        <div class=\"card\">\n            <div class=\"contact-form\">\n                <h3>Enter Employee ID to Remove</h3>\n                <form id=\"deleteEmployeeForm\">\n                    <div class=\"form-group\">\n                        <label for=\"removeId\">Employee ID</label>\n                        <input type=\"number\" id=\"removeId\" name=\"removeId\" placeholder=\"Enter Employee ID\" required>\n                    </div>\n                    <button type=\"submit\" class=\"submit-btn danger\" onClick=\"deleteEmployee(event)\">Remove Employee</button>\n                </form>\n                <div class=\"message\" id=\"removeMsg\"></div>\n            </div>\n        </div>"
    };
    main.innerHTML = templates[section] || "<h2>Invalid Section</h2>";
}
