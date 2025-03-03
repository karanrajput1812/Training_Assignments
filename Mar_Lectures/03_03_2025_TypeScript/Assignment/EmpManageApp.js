var Emp = /** @class */ (function () {
    function Emp(name, age, salary, designation, department) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.eid = empArray.length + 1;
        this.designation = designation;
        this.department = department;
    }
    Emp.prototype.displayAll = function () {
        console.log(this.name);
        console.log(this.age);
        console.log(this.salary);
        console.log(this.department);
        console.log(this.designation);
    };
    return Emp;
}());
var empArray = [];
function addEmployee(event) {
    var _a;
    event === null || event === void 0 ? void 0 : event.preventDefault();
    var name = document.getElementById("name").value;
    var age = document.getElementById("age").value;
    var salary = document.getElementById("salary").value;
    var designation = document.getElementById("designation").value;
    var department = document.getElementById("department").value;
    var emp = new Emp(name, age, salary, designation, department);
    empArray.push(emp);
    console.log(name, age, salary, designation, department);
    (_a = document.getElementById("msg")) === null || _a === void 0 ? void 0 : _a.innerHTML = "Employee Added Successfully";
}
function displayEmployee() {
    var tableBody = document.getElementById("employeeTableBody");
    tableBody.innerHTML = "";
    empArray.forEach(function (emp) {
        var row = document.createElement("tr");
        var nameCell = document.createElement("td");
        nameCell.textContent = emp.name;
        row.appendChild(nameCell);
        var ageCell = document.createElement("td");
        ageCell.textContent = emp.age.toString();
        row.appendChild(ageCell);
        var salaryCell = document.createElement("td");
        salaryCell.textContent = emp.salary.toString();
        row.appendChild(salaryCell);
        var designationCell = document.createElement("td");
        designationCell.textContent = emp.designation;
        row.appendChild(designationCell);
        var departmentCell = document.createElement("td");
        departmentCell.textContent = emp.department;
        row.appendChild(departmentCell);
        tableBody.appendChild(row);
    });
}
function deleteEmployee(event) {
    var _a, _b;
    event === null || event === void 0 ? void 0 : event.preventDefault();
    var id = parseInt(document.getElementById("id").value);
    var index = empArray.findIndex(function (emp) { return emp.eid === id; });
    if (index !== -1) {
        empArray.splice(index, 1);
        (_a = document.getElementById("msg")) === null || _a === void 0 ? void 0 : _a.innerHTML = "Employee Removed Successfully";
    }
    else {
        (_b = document.getElementById("msg")) === null || _b === void 0 ? void 0 : _b.innerHTML = "Employee Not Found";
    }
}
function updateEmployeSalary(event) {
    var _a;
    event === null || event === void 0 ? void 0 : event.preventDefault();
    var id = parseInt(document.getElementById("id").value);
    var salary = parseInt(document.getElementById("salary").value);
    var index = empArray.findIndex(function (emp) { return emp.eid === id; });
    console.log("index", index);
    if (index !== -1) {
        empArray[index].salary = salary;
        (_a = document.getElementById("msg")) === null || _a === void 0 ? void 0 : _a.innerHTML = "Employee Salary Updated Successfully";
    }
}
// var c1 = new Clerk();
// c1.displayAll();
