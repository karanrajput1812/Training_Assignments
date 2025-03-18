import React from 'react';
import { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from "react-router-dom";
import Navbar from '../components/CustomerNavbar';

function Customer() {
    const [products, setProducts] = useState([]);
    const [stock, setStock] = useState([]);

    useEffect(() => {
        fetchProducts();
        fetchStock();
    }, []);

    const fetchProducts = () => {
        axios.get("http://localhost:4000/products")
            .then((res) => setProducts(res.data));
    };

    const fetchStock = () => {
        axios.get("http://localhost:4000/stock")
            .then((res) => setStock(res.data));
    };
    const handleAddToCart = (productId) => {
        const productStock = stock.find(item => item.product_id === productId);
        const productDetails = products.find(item => item.id === productId);
    
        if (productStock && productStock.quantity > 0) {
            // Update stock
            axios.put(`http://localhost:4000/stock/${productStock.id}`, {
                ...productStock,
                quantity: productStock.quantity - 1
            }).then(() => {
                // Add order
                axios.post("http://localhost:4000/orders", {
                    product_id: productId,
                    quantity: 1,
                    total: productDetails.price,
                    v_id: productDetails.v_id,
                    status: "In cart",
                    date: new Date().toISOString().split('T')[0] // Current date in YYYY-MM-DD format
                }).then(() => {
                    alert(`Product added to cart!`);
                    fetchStock();
                }).catch(err => {
                    console.error("Error adding order:", err);
                });
            }).catch(err => {
                console.error("Error updating stock:", err);
            });
        } else {
            alert("Product is out of stock!");
        }
    };

    return (
        <div className='products-form'>
            <Navbar />
            <h1>All Products</h1>
            <table>
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Price</th>
                        <th>Vendor ID</th>
                        <th>Description</th>
                        <th>Category</th>
                        <th>Rating</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {products.map((prod) => (
                        <tr key={prod.id}>
                            <td>{prod.title}</td>
                            <td>{prod.price}</td>
                            <td><Link to={`/vendorDetails/${prod.v_id}`}>{prod.v_id}</Link></td>
                            <td>{prod.description}</td>
                            <td>{prod.category}</td>
                            <td>{prod.rating}</td>
                            <td>
                                <button
                                    className='submit-btn'
                                    onClick={() => handleAddToCart(prod.id)}
                                >
                                    Place Order
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default Customer;