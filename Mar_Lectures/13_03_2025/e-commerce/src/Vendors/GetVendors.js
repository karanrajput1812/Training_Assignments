import React from 'react'
import { useState, useEffect } from 'react';
import axios from 'axios';

function GetVendors() {
    const [vendors, setVendors] = useState([]);
//   const [editingVendor, setEditingVendor] = useState(null);
//   const [updatedVendor, setUpdatedVendor] = useState({});

  useEffect(() => {
    axios.get("http://localhost:4000/vendors")
    .then((res) => setVendors(res.data));
  });

  function deleteProducts(id) {
    axios.delete("http://localhost:4000/vendors/" + id)
    .then((res) => {
      console.log(`Vendor Deleted Successfully`, res.data);
    })
    .catch((err) => {
      console.log(err);
    });
  }

//   function updateVendors(id) {
//     const vendor = vendors.find(prod => prod.id === id);
//     setEditingVendor(vendor);
//     setUpdatedVendor(vendor);
//   }

//   function handleUpdateChange(e) {
//     const { name, value } = e.target;
//     setUpdatedVendor({ ...updatedProduct, [name]: value });
//   }

//   function saveUpdatedProduct() {
//     axios.put("http://localhost:4000/products/" + updatedProduct.id, updatedProduct)
//     .then((res) => {
//       console.log(`Product Updated Successfully`, res.data);
//       setProducts(products.map(product => product.id === updatedProduct.id ? updatedProduct : product));
//       setEditingProduct(null);
//     })
//     .catch((err) => {
//       console.log(err);
//     });
//   }
  return (
    <div className='products-form'>
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
              <button type="button" onClick={() => deleteProducts(vendor.id)} className='delete-btn'>Delete</button>
              {/* <button type="button" onClick={() => updateProducts(prod.id)} className='update-btn'>Update</button> */}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default GetVendors