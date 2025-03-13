import React, { use, useState } from 'react'

function AddVendor() {
    const [msg, setMsg] = useState("");
    const [vendors, setVendors] = useState({
        "name": "",
        "age":"",
        "registration_no":"",
        "address":"",
        "phone_no": "",
        "email": ""
    })
  return (
    <div className='products-form'>
        <h1>Add Vendor</h1>
        <h3> Enter the below details to add vendor</h3> 
        <label for="name">Enter Name</label>
        <input name='name' value={vendors.name} onChange={assignData} type='text' placeholder='Enter Name'></input><br></br><br></br>
        <label for="age">Enter Age</label>
        <input name='age' value={vendors.age} onChange={assignData} type='number' placeholder='Enter Age'></input><br></br><br></br>
        <label for="registration_no">Enter Registration No</label>
        <input name='registration_no' value={vendors.registration_no} onChange={assignData} type='text' placeholder='Enter Registration No'></input><br></br><br></br>
        <label for="address">Enter Address</label>
        <input name='address' onChange={assignData} value={vendors.address} type='text' placeholder='Enter Address'></input><br></br><br></br>
        <label for="phone_no">Enter Vendor ID</label>
        <input name='phone_no' onChange={assignData} value={vendors.v_id} type='text' placeholder='Enter Vendor ID'></input><br></br><br></br>
        <br></br>
        <button type="submit" onClick={insertProduct} className='submit-btn'>SUBMIT</button>
        <h1>{msg}</h1>
    </div>
  )
}

export default AddVendor