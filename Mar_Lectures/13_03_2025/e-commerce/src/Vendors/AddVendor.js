import React, { useState } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';


function AddVendor() {
  const [msg, setMsg] = useState("");
  const [vendors, setVendors] = useState({
    "name": "",
    "age": "",
    "registration_no": "",
    "address": "",
    "phone_no": "",
    "email": ""
  });

  const assignData = (e) => {
    setVendors({ ...vendors, [e.target.name]: e.target.value });
  };

  const insertVendor = (e) => {
    e.preventDefault();
    axios.post("http://localhost:4000/vendors", vendors)
      .then((res) => {
        console.log(`Vendor Added Successfully`, res.data);
        setMsg("Vendor Added Successfully");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div className='products-form'>
      <Navbar />
      <h1>Add Vendor</h1>
      <h3>Enter the below details to add vendor</h3>
      <label htmlFor="name">Enter Name</label>
      <input name='name' value={vendors.name} onChange={assignData} type='text' placeholder='Enter Name'></input><br></br><br></br>
      <label htmlFor="age">Enter Age</label>
      <input name='age' value={vendors.age} onChange={assignData} type='number' placeholder='Enter Age'></input><br></br><br></br>
      <label htmlFor="registration_no">Enter Registration No</label>
      <input name='registration_no' value={vendors.registration_no} onChange={assignData} type='text' placeholder='Enter Registration No'></input><br></br><br></br>
      <label htmlFor="address">Enter Address</label>
      <input name='address' value={vendors.address} onChange={assignData} type='text' placeholder='Enter Address'></input><br></br><br></br>
      <label htmlFor="phone_no">Enter Phone No</label>
      <input name='phone_no' value={vendors.phone_no} onChange={assignData} type='text' placeholder='Enter Phone No'></input><br></br><br></br>
      <label htmlFor="email">Enter Email</label>
      <input name='email' value={vendors.email} onChange={assignData} type='email' placeholder='Enter Email'></input><br></br><br></br>
      <button type="submit" onClick={insertVendor} className='submit-btn'>SUBMIT</button>
      <h1>{msg}</h1>
    </div>
  );
}

export default AddVendor;