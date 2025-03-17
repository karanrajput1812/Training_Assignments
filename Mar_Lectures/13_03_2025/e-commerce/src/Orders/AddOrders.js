import React, { useState } from 'react';
import axios from 'axios';

function AddOrders() {
    const [msg, setMsg] = useState('');
    const [orders, setOrders] = useState({
        id: '',
        product_id: '',
        quantity: '',
        total: '',
        v_id: '',
        status: '',
        date: '',
    });

    const assignData = (e) => {
        setOrders({ ...orders, [e.target.name]: e.target.value });
    };

    const insertOrder = (e) => {
        e.preventDefault();
        axios
            .post('http://localhost:4000/orders', orders)
            .then((res) => {
                console.log('Order Added Successfully', res.data);
                setMsg('Order Added Successfully');
            })
            .catch((err) => {
                console.log(err);
                setMsg('Failed to Add Order');
            });
    };

    return (
        <div className="products-form">
            <h1>Add Order</h1>
            <h3>Enter the details below to add an order</h3>
            <label htmlFor="id">Order ID</label>
            <input
                name="id"
                value={orders.id}
                onChange={assignData}
                type="text"
                placeholder="Enter Order ID"
            />
            <br />
            <br />
            <label htmlFor="product_id">Product ID</label>
            <input
                name="product_id"
                value={orders.product_id}
                onChange={assignData}
                type="text"
                placeholder="Enter Product ID"
            />
            <br />
            <br />
            <label htmlFor="quantity">Quantity</label>
            <input
                name="quantity"
                value={orders.quantity}
                onChange={assignData}
                type="number"
                placeholder="Enter Quantity"
            />
            <br />
            <br />
            <label htmlFor="total">Total</label>
            <input
                name="total"
                value={orders.total}
                onChange={assignData}
                type="number"
                placeholder="Enter Total Amount"
            />
            <br />
            <br />
            <label htmlFor="v_id">Vendor ID</label>
            <input
                name="v_id"
                value={orders.v_id}
                onChange={assignData}
                type="text"
                placeholder="Enter Vendor ID"
            />
            <br />
            <br />
            <label htmlFor="status">Status</label>
            <input
                name="status"
                value={orders.status}
                onChange={assignData}
                type="text"
                placeholder="Enter Status"
            />
            <br />
            <br />
            <label htmlFor="date">Date</label>
            <input
                name="date"
                value={orders.date}
                onChange={assignData}
                type="date"
            />
            <br />
            <br />
            <button type="submit" onClick={insertOrder} className="submit-btn">
                SUBMIT
            </button>
            <h1>{msg}</h1>
        </div>
    );
}

export default AddOrders;