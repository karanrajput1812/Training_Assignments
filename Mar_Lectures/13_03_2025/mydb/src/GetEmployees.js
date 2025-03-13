import React, { useEffect, useState } from "react";
import axios from "axios";

function GetEmployees() {
  const [employees, setEmployees] = useState([]);
  useEffect(() => {
    axios.get("http://localhost:4000/employees")
      .then((res) => setEmployees(res.data));
  }, []);
  return (
    <>
      <h3>Employee Details</h3>
      <table>
        <tr>
          <th>Emp ID</th>
          <th>Name</th>
          <th>Age</th>
          <th>Salary</th>
          <th>Designation</th>
        </tr>
        {employees.map((emp) => {
          return (
            <tr>
              <td>{emp.id}</td>
              <td>{emp.name}</td>
              <td>{emp.age}</td>
              <td>{emp.salary}</td>
              <td>{emp.designation}</td>
            </tr>
          );
        })}
      </table>
    </>
  );
}

export default GetEmployees;
