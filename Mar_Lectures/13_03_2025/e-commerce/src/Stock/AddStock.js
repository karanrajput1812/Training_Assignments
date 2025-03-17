import React, { useState } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';


function AddStock() {
    const [msg, setMsg] = useState("");
    const [stock, setStock] = useState({
        "id": "",
        "product_id": "",
        "quantity": "",
        "v_id": "",
        "date": "",
        "status": "",
        "cost_price": "",
        "discount": "",
        "selling_price": ""
    });

    const assignData = (e) => {
        setStock({ ...stock, [e.target.name]: e.target.value });
    };

    const insertStock = (e) => {
        e.preventDefault();
        axios.post("http://localhost:4000/stock", stock)
            .then((res) => {
                console.log(`Stock Added Successfully`, res.data);
                setMsg("Stock Added Successfully");
            })
            .catch((err) => {
                console.log(err);
            });
    };

    return (
        <div className='products-form'>
             <Navbar />
            <h1>Add Stock</h1>
            <h3>Enter the below details to add stock to inventory</h3>
            <label htmlFor="id">Enter ID</label>
            <input name='id' value={stock.id} onChange={assignData} type='text' placeholder='Enter Stock ID'></input><br /><br />
            <label htmlFor="product_id">Enter Product ID</label>
            <input name='product_id' value={stock.product_id} onChange={assignData} type='text' placeholder='Enter Product ID'></input><br /><br />
            <label htmlFor="quantity">Enter Quantity</label>
            <input name='quantity' value={stock.quantity} onChange={assignData} type='number' placeholder='Enter Quantity'></input><br /><br />
            <label htmlFor="v_id">Enter Vendor ID</label>
            <input name='v_id' value={stock.v_id} onChange={assignData} type='text' placeholder='Enter Vendor ID'></input><br /><br />
            <label htmlFor="date">Enter Date</label>
            <input name='date' value={stock.date} onChange={assignData} type='date' placeholder='Enter Date'></input><br /><br />
            <label htmlFor="status">Enter Status</label>
            <input name='status' value={stock.status} onChange={assignData} type='text' placeholder='Enter Status'></input><br /><br />
            <label htmlFor="cost_price">Enter Cost Price</label>
            <input name='cost_price' value={stock.cost_price} onChange={assignData} type='number' placeholder='Enter Cost Price'></input><br /><br />
            <label htmlFor="discount">Enter Discount</label>
            <input name='discount' value={stock.discount} onChange={assignData} type='text' placeholder='Enter Discount'></input><br /><br />
            <label htmlFor="selling_price">Enter Selling Price</label>
            <input name='selling_price' value={stock.selling_price} onChange={assignData} type='number' placeholder='Enter Selling Price'></input><br /><br />
            <button type="submit" onClick={insertStock} className='submit-btn'>SUBMIT</button>
            <h1>{msg}</h1>
        </div>
    );
}

export default AddStock;