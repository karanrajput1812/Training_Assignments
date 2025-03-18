import React, { useEffect, useState } from "react";
import axios from "axios";
import {Link} from 'react-router-dom';
import Navbar from '../components/Navbar';

function GetOrders() {
    const [orders, setOrders] = useState([]);
    const [editingOrder, setEditingOrder] = useState(null);
    const [updatedOrder, setUpdatedOrder] = useState({});

    useEffect(() => {
        axios.get("http://localhost:4000/orders")
            .then((res) => setOrders(res.data))
            .catch((err) => console.error(err));
    }, []);

    function deleteOrders(id) {
        axios.delete("http://localhost:4000/orders/" + id)
            .then((res) => {
                console.log(`Order Deleted Successfully`, res.data);
                setOrders(orders.filter(order => order.id !== id));
            })
            .catch((err) => {
                console.error(err);
            });
    }

    function updateOrder(id) {
        const order = orders.find(order => order.id === id);
        setEditingOrder(order);
        setUpdatedOrder(order);
    }

    function handleUpdateChange(e) {
        const { name, value } = e.target;
        setUpdatedOrder({ ...updatedOrder, [name]: value });
    }

    function saveUpdatedOrder() {
        axios.put("http://localhost:4000/orders/" + updatedOrder.id, updatedOrder)
            .then((res) => {
                console.log(`Order Updated Successfully`, res.data);
                setOrders(orders.map(order => order.id === updatedOrder.id ? updatedOrder : order));
                setEditingOrder(null);
            })
            .catch((err) => {
                console.error(err);
            });
    }

    return (
        <div className='products-form'>
            <Navbar />
            <h1>All Orders</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Product Id</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Vendor Id</th>
                        <th>Status</th>
                        <th>Date</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {orders.map((order) => (
                        <tr key={order.id}>
                            <td>{order.id}</td>
                            <td><Link to={`/ProductDetails/${order.product_id}`}>{order.product_id}</Link></td>
                            <td>{order.quantity}</td>
                            <td>{order.total}</td>
                            <td><Link to={`/vendorDetails/${order.v_id}`}>{order.v_id}</Link></td>
                            <td>{order.status}</td>
                            <td>{order.date}</td>
                            <td>
                                <button type="button" onClick={() => deleteOrders(order.id)} className='delete-btn'>Delete</button>
                                <button type="button" onClick={() => updateOrder(order.id)} className='update-btn'>Update</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            {editingOrder && (
                <div className='update-form'>
                    <h2>Update Order</h2>
                    <form>
                        <label>
                            Product ID:
                            <input type="text" name="product_id" value={updatedOrder.product_id || ''} onChange={handleUpdateChange} />
                        </label>
                        <label>
                            Quantity:
                            <input type="text" name="quantity" value={updatedOrder.quantity || ''} onChange={handleUpdateChange} />
                        </label>
                        <label>
                            Total:
                            <input type="text" name="total" value={updatedOrder.total || ''} onChange={handleUpdateChange} />
                        </label>
                        <label>
                            Vendor ID:
                            <input type="text" name="v_id" value={updatedOrder.v_id || ''} onChange={handleUpdateChange} />
                        </label>
                        <label>
                            Status:
                            <select name="status" value={updatedOrder.status || ''} onChange={handleUpdateChange}>
                                <option value="Processing">Processing</option>
                                <option value="Delivered">Delivered</option>
                                <option value="Cancelled">Cancelled</option>
                                <option value="Shipped">Shipped</option>
                            </select>
                        </label>
                        <label>
                            Date:
                            <input type="text" name="date" value={updatedOrder.date || ''} onChange={handleUpdateChange} />
                        </label>
                        <button type="button" onClick={saveUpdatedOrder} className='submit-btn'>Save</button>
                    </form>
                </div>
            )}
        </div>
    );
}

export default GetOrders;
