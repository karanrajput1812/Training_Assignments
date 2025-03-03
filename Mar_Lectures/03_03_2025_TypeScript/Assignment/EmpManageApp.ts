class Emp {
    protected name: string;
    protected age: number;
    protected salary: number;
    protected eid: number;
    protected designation: String;
    protected department: String;

    constructor(name:String, age:Number, salary:Number,designation:String, department:String) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.eid = empArray.length + 1;
        this.designation = designation;
        this.department = department;
    }
    public displayAll() {
        console.log(this.name);
        console.log(this.age);
        console.log(this.salary);
        console.log(this.department);
        console.log(this.designation);
    }
}


var empArray:Array<Emp> = [];


function addEmployee(event) {
    event?.preventDefault();
   const name = document.getElementById("name").value;
   const age = document.getElementById("age").value;
   const salary = document.getElementById("salary").value;
   const designation = document.getElementById("designation").value;
   const department = document.getElementById("department").value;
    
   let emp: Emp = new Emp(name,age,salary,designation,department); 
   empArray.push(emp);
   console.log(name, age, salary, designation, department);
   document.getElementById("msg")?.innerHTML = "Employee Added Successfully";
}
function displayEmployee() {
    const tableBody = document.getElementById("employeeTableBody");
    tableBody.innerHTML = "";

    empArray.forEach(emp => {
        const row = document.createElement("tr");

        const nameCell = document.createElement("td");
        nameCell.textContent = emp.name;
        row.appendChild(nameCell);

        const ageCell = document.createElement("td");
        ageCell.textContent = emp.age.toString();
        row.appendChild(ageCell);

        const salaryCell = document.createElement("td");
        salaryCell.textContent = emp.salary.toString();
        row.appendChild(salaryCell);

        const designationCell = document.createElement("td");
        designationCell.textContent = emp.designation;
        row.appendChild(designationCell);

        const departmentCell = document.createElement("td");
        departmentCell.textContent = emp.department;
        row.appendChild(departmentCell);

        tableBody.appendChild(row);
    });
}
function deleteEmployee(event) {
    event?.preventDefault();
    const id = parseInt((document.getElementById("id") as HTMLInputElement).value);
    const index = empArray.findIndex(emp => emp.eid === id);

    if (index !== -1) {
        empArray.splice(index, 1);
        document.getElementById("msg")?.innerHTML = "Employee Removed Successfully";
    } else {
        document.getElementById("msg")?.innerHTML = "Employee Not Found";
    }
}

function updateEmployeSalary(event) {
    event?.preventDefault();
    const id = parseInt((document.getElementById("id") as HTMLInputElement).value);
    const salary = parseInt((document.getElementById("salary") as HTMLInputElement).value);
    const index = empArray.findIndex(emp => emp.eid === id);
    console.log("index",index);
    if (index !== -1) {
        empArray[index].salary = salary;
        document.getElementById("msg")?.innerHTML = "Employee Salary Updated Successfully"; 
    }
}

// var c1 = new Clerk();
// c1.displayAll();


