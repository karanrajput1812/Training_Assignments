import axios from 'axios';
import React, { useState } from 'react'
 
function AddEmployees() {
    const [emp, setEmp] = useState({
        "id": "",
        "name": "",
        "age": "",
        "salary":"",
        "designation":""
    })
 
    const assignData = (e) =>{
        setEmp({...emp,[e.target.name] : e.target.value});
    }
 
    const insertEmp=(e)=>{
        e.preventDefault();
        axios.post("http://localhost:4000/employees",emp)
        .then((res)=>{
            console.log(`Employee Added Sucessfully`,res.data);
 
        })
        .catch((err)=>{
            console.log(err);
        })
    }
 
 
    const updateEmp=(e)=>{
        e.preventDefault();
        axios.put("http://localhost:4000/employees/"+emp.id,emp)
        .then((res)=>{
            alert(`Employee Updated Sucessfully`);
           
        })
        .catch((err)=>{
            console.log(err);
        })
    }

    const deleteEmp=(e)=>{
        e.preventDefault();
        axios.delete("http://localhost:4000/employees/"+emp.id)
        .then((res)=>{
            console.log(`Employee Deleted Sucessfully`,res.data);
 
        })
        .catch((err)=>{
            console.log(err);
        })
    }
 
    return (
        <div>
            <h3>
                Please enter employee details
            </h3>
 
            <input name='id' value={emp.id} onChange={assignData} type='text' placeholder='Enter Emp ID'></input><br></br>
            <input name='name' value={emp.name} onChange={assignData}type='text' placeholder='Enter Name'></input><br></br>
            <input name='age' value={emp.age} onChange={assignData}type='number' placeholder='Enter Age'></input><br></br>
            <input name='salary' value={emp.salary} onChange={assignData}type='number' placeholder='Enter salary'></input><br></br>
            <input name='designation' onChange={assignData} value={emp.designation} type='text' placeholder='Enter Designation'></input><br></br>
            <br></br>
            <button type="submit" onClick={insertEmp}>SUBMIT</button>
            <button type="submit" onClick={updateEmp}>UPDATE</button>
            <button type='submit' onClick={deleteEmp}>REMOVE</button>
 
        </div>
    )
}
 
export default AddEmployees