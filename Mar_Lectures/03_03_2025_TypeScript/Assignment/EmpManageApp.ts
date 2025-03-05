async function addEmployee(event: Event) {
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
        let result = await fetch("http://localhost:8181/saveEmployee", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ name, age, salary, designation, department })
        });
        
        if (result.ok) {
            if (msgElement) msgElement.textContent = "Employee added successfully!";
        } else {
            if (msgElement) msgElement.textContent = "Error adding employee: " + result.statusText;
        }
        (document.getElementById('addEmpForm') as HTMLFormElement).reset();
        getEmployeeCount();
        
    } catch (error) {
        if (msgElement) msgElement.textContent = "Error adding employee: " + error;
    }
}
async function getEmployeeCount() {
    try {
        let response = await fetch("http://localhost:8181/employeeCount");
        if (!response.ok) {
            throw new Error(`Error: ${response.statusText}`);
        }
        let count = await response.json();
        const countElement = document.getElementById('employeeCount');
        if (countElement) {
            countElement.textContent = count.toString();
        }
    } catch (error) {
        console.error("Error fetching employee count:", error);
    }
}

async function searchEmployees(event: Event) {
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
    
    let url = `http://localhost:8181/getBy${searchType}/${searchValue}`;
    
    try {
        let response = await fetch(url);
        if (!response.ok) {
            resultsDiv.innerHTML = `<p class="error">Error: ${response.statusText}</p>`;
            return;
        }
        
        let foundEmployees = await response.json();
        
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
            
            foundEmployees.forEach((emp: any) => {
                html += `
                    <tr>
                        <td>${emp.eid}</td>
                        <td>${emp.name}</td>
                        <td>${emp.age}</td>
                        <td>${emp.salary}</td>
                        <td>${emp.designation}</td>
                        <td>${emp.department}</td>
                    </tr>
                `;
            });
            
            html += `
                    </tbody>
                </table>
            `;
            
            resultsDiv.innerHTML = html;
        }
    } catch (error) {
        resultsDiv.innerHTML = `<p class="error">Error: ${error}</p>`;
    }
}



async function getAllEmployees() {
    let response = await fetch("http://localhost:8181/allEmployees");
    console.log(response);
    let result = await response.json();
    let tableBody = document.getElementById("employeeTableBody");

    if (!tableBody) return;

    // Clear the table
    tableBody.innerHTML = '';
    
    if (result.length === 0) {
        const row = document.createElement('tr');
        const cell = document.createElement('td');
        cell.colSpan = 6; // Updated for EID column
        cell.textContent = "No employees found";
        cell.style.textAlign = "center";
        row.appendChild(cell);
        tableBody.appendChild(row);
        return;
    }

    result.forEach((employee: { eid: number; name: string; age: number; salary: number; designation: string; department: string; }) => {
        let row = document.createElement("tr");
        row.innerHTML = `
            <td>${employee.eid}</td>
            <td>${employee.name}</td>
            <td>${employee.age}</td>
            <td>${employee.salary}</td>
            <td>${employee.designation}</td>
            <td>${employee.department}</td>
        `;
        tableBody.appendChild(row);
    });
}



async function deleteEmployee(event: Event) {
    event.preventDefault();
    
    const idInput = document.getElementById('removeId') as HTMLInputElement;
    const msgElement = document.getElementById('removeMsg');
    
    const confirmation = confirm(`Are you sure you want to delete employee with ID ${idInput.value}?`);
    if (!confirmation) {
        return;
    }

    try {
        let result = await fetch(`http://localhost:8181/deleteEmployee/${idInput.value}`, {
            method: "DELETE"
        });
        if (result.ok) {
            if (msgElement) msgElement.textContent = "Employee deleted successfully!";
        } else {
            if (msgElement) msgElement.textContent = "Error deleting employee: " + result.statusText;
        }
        // Reset form and show success message
        (document.getElementById('deleteEmployeeForm') as HTMLFormElement).reset();
        
        // Update employee count
        getEmployeeCount();
    } catch (error) {
        if (msgElement) msgElement.textContent = "Error deleting employee: " + error;
    }
}

async function updateEmployeeSalary(event: Event) {
    event.preventDefault();
    
    const idInput = document.getElementById('updateId') as HTMLInputElement;
    const salaryInput = document.getElementById('updateSalary') as HTMLInputElement;
    const msgElement = document.getElementById('updateMsg');
    
    const id = parseInt(idInput.value);
    const salary = parseFloat(salaryInput.value);

    try {
        let result = await fetch(`http://localhost:8181/updateSalary?eid=${id}&salary=${salary}`, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json"
            }
        });
        if (result.ok) {
            if (msgElement) msgElement.textContent = "Employee salary updated successfully!";
        } else {
            if (msgElement) msgElement.textContent = "Error updating employee salary: " + result.statusText;
        }
        // Reset form and show success message
        (document.getElementById('updateEmployeeForm') as HTMLFormElement).reset();
        
        // Update employee count
        getEmployeeCount();
    } catch (error) {
        if (msgElement) msgElement.textContent = "Error updating employee salary: " + error;
    }        
}

document.addEventListener('DOMContentLoaded', () => {
    getEmployeeCount();
});

function renderSection(section: String) {
    let main = document.getElementById('main');
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
                            <option value="Id">Employee ID</option>
                            <option value="Name">Name</option>
                            <option value="Designation">Designation</option>
                            <option value="Separtment">Department</option>
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