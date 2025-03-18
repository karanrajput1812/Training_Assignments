import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from "react-router-dom";
import Navbar from '../components/CustomerNavbar';

function Cart() {
    const [stock, setStock] = useState([]);
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        fetchStock();
        fetchOrders();
    }, []);

    const fetchStock = () => {
        axios.get("http://localhost:4000/stock")
            .then((res) => setStock(res.data));
    };

    const fetchOrders = () => {
        axios.get("http://localhost:4000/orders")
            .then((res) => setOrders(res.data));
    };

    const handleUpdateOrderStatus = (orderId) => {
        const order = orders.find(o => o.id === orderId);
        if (order) {
            axios.put(`http://localhost:4000/orders/${orderId}`, {
                ...order,
                status: "Processing"
            }).then(() => {
                alert("Payment successfull!");
                fetchOrders();
            }).catch(err => {
                console.error("Error updating order status:", err);
            });
        }
    };

    return (
        <div className='products-form'>
            <Navbar />
            <h1>Products In Cart</h1>
            <table>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Product ID</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {orders.filter(order => order.status === "In cart").map((order) => (
                        <tr key={order.id}>
                            <td>{order.id}</td>
                            <td>{order.product_id}</td>
                            <td>{order.quantity}</td>
                            <td>{order.total}</td>
                            <td>{order.status}</td>
                            <td>
                                <button
                                    className='submit-btn'
                                    onClick={() => handleUpdateOrderStatus(order.id)}
                                >
                                    Pay Now
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <h1>My Orders</h1>
            <table>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Product ID</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    {orders.filter(order => order.status === "Delivered").map((order) => (
                        <tr key={order.id}>
                            <td>{order.id}</td>
                            <td>{order.product_id}</td>
                            <td>{order.quantity}</td>
                            <td>{order.total}</td>
                            <td>{order.status}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default Cart;