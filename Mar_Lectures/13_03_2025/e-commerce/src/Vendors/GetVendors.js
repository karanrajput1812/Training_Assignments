import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';
import Navbar from '../components/Navbar';


function GetVendors() {
  const [vendors, setVendors] = useState([]);
  const [editingVendor, setEditingVendor] = useState(null);
  const [updatedVendor, setUpdatedVendor] = useState({});

  useEffect(() => {
    axios.get("http://localhost:4000/vendors")
    .then((res) => setVendors(res.data));
  });

  function deleteVendors(id) {
    axios.delete("http://localhost:4000/vendors/" + id)
    .then((res) => {
      console.log(`Vendor Deleted Successfully`, res.data);
      setVendors(vendors.filter(vendor => vendor.id !== id));
    })
    .catch((err) => {
      console.log(err);
    });
  }

  function updateVendors(id) {
    const vendor = vendors.find(vend => vend.id === id);
    setEditingVendor(vendor);
    setUpdatedVendor(vendor);
  }

  function handleUpdateChange(e) {
    const { name, value } = e.target;
    setUpdatedVendor({ ...updatedVendor, [name]: value });
  }

  function saveUpdatedVendor() {
    axios.put("http://localhost:4000/vendors/" + updatedVendor.id, updatedVendor)
    .then((res) => {
      console.log(`Vendor Updated Successfully`, res.data);
      setVendors(vendors.map(vendor => vendor.id === updatedVendor.id ? updatedVendor : vendor));
      setEditingVendor(null);
    })
    .catch((err) => {
      console.log(err);
    });
  }

  return (
    <div className='products-form'>
      <Navbar />
      <h1>All Vendors</h1>
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
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {vendors.map((vendor) => (
            <tr key={vendor.id}>
              <td>{vendor.id}</td>
              <td>{vendor.name}</td>
              <td>{vendor.age}</td>
              <td>{vendor.registration_no}</td>
              <td>{vendor.address}</td>
              <td>{vendor.phone_no}</td>
              <td>{vendor.email}</td>
              <td>
                <button type="button" onClick={() => deleteVendors(vendor.id)} className='delete-btn'>Delete</button>
                <button type="button" onClick={() => updateVendors(vendor.id)} className='update-btn'>Update</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <br></br>
      <br></br>
      <Link to="./addVendors" className="submit-btn">Switch To Add Vendor</Link>
      {editingVendor && (
        <div className='update-form'>
          <h2>Update Vendor</h2>
          <form onSubmit={saveUpdatedVendor}>
            <label>
              Name:
              <input type="text" name="name" value={updatedVendor.name} onChange={handleUpdateChange} />
            </label>
            <label>
              Age:
              <input type="text" name="age" value={updatedVendor.age} onChange={handleUpdateChange} />
            </label>
            <label>
              Registration No:
              <input type="text" name="registration_no" value={updatedVendor.registration_no} onChange={handleUpdateChange} />
            </label>
            <label>
              Address:
              <input type="text" name="address" value={updatedVendor.address} onChange={handleUpdateChange} />
            </label>
            <label>
              Phone No:
              <input type="text" name="phone_no" value={updatedVendor.phone_no} onChange={handleUpdateChange} />
            </label>
            <label>
              Email:
              <input type="text" name="email" value={updatedVendor.email} onChange={handleUpdateChange} />
            </label>
            <button type="button" onClick={saveUpdatedVendor} className='submit-btn'>Save</button>
          </form>
        </div>
      )}
    </div>
  );
}

export default GetVendors;