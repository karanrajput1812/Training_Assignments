import React, { useState } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';

function AddInventory() {
    const [msg, setMsg] = useState('');
    const [inventory, setInventory] = useState({
        id: "",
        product_id: "",
        quantity: "",
        v_id: "",
        date: "",
        status: "",
        price: "",
        discount: ""
    });

    const assignData = (e) => {
        setInventory({ ...inventory, [e.target.name]: e.target.value });
    };

    const insertInventory = (e) => {
        e.preventDefault();
        axios.post("http://localhost:4000/inventory", inventory) // Use inventory instead of vendors
            .then((res) => {
                console.log(`Inventory Added Successfully`, res.data);
                setMsg("Inventory Added Successfully");
            })
            .catch((err) => {
                console.log(err);
            });
    };

    return (
        <div className='products-form'>
             <Navbar />
            <h1>Add Inventory</h1>
            <h3>Enter the below details to add inventory</h3>
            <label htmlFor="id">Enter ID</label>
            <input name='id' value={inventory.id} onChange={assignData} type='text' placeholder='Enter ID'></input><br></br><br></br>
            <label htmlFor="product_id">Enter Product ID</label>
            <input name='product_id' value={inventory.product_id} onChange={assignData} type='text' placeholder='Enter Product ID'></input><br></br><br></br>
            <label htmlFor="quantity">Enter Quantity</label>
            <input name='quantity' value={inventory.quantity} onChange={assignData} type='number' placeholder='Enter Quantity'></input><br></br><br></br>
            <label htmlFor="date">Enter Date</label>
            <input name='date' value={inventory.date} onChange={assignData} type='date' placeholder='Enter Date'></input><br></br><br></br>
            <label htmlFor="status">Enter Status</label>
            <input name='status' value={inventory.status} onChange={assignData} type='text' placeholder='Enter Status'></input><br></br><br></br>
            <label htmlFor="price">Enter Price</label>
            <input name='price' value={inventory.price} onChange={assignData} type='number' placeholder='Enter Price'></input><br></br><br></br>
            <label htmlFor="discount">Enter Discount</label>
            <input name='discount' value={inventory.discount} onChange={assignData} type='number' placeholder='Enter Discount'></input><br></br><br></br>
            <button type="submit" onClick={insertInventory} className='submit-btn'>SUBMIT</button>
            <h1>{msg}</h1>
        </div>
    );
}

export default AddInventory;