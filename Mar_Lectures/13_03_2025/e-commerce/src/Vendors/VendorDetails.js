import React from 'react'
import { useState, useEffect } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';

function VendorDetails() {
     const [vendors, setVendors] = useState([]);
     useEffect(() => {
        const vendorId = window.location.pathname.split("/").pop();
        
        axios.get(`http://localhost:4000/vendors/${vendorId}`)
        .then((res) => setVendors(res.data));
      });
  return (
    <div className='products-form'>
        <Navbar />
       <h1>Vendor Detail</h1>
      <table>
        <thead>
          <tr>
            <th>Vendor ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Registration No</th>
            <th>Address</th>
            <th>Phone No</th>
            <th>Email</th>
          </tr>
        </thead>
        <tbody>
            <tr>
              <td>{vendors.id}</td>
              <td>{vendors.name}</td>
              <td>{vendors.age}</td>
              <td>{vendors.registration_no}</td>
              <td>{vendors.address}</td>
              <td>{vendors.phone_no}</td>
              <td>{vendors.email}</td>
            </tr>
        </tbody>
      </table>
      
    </div>
  )
}

export default VendorDetails