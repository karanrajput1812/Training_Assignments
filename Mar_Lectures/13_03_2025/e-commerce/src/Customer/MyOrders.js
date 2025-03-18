import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import Navbar from "../components/CustomerNavbar";
import { useDispatch, useSelector } from "react-redux";
import EccommerceReducer from "../reduxContainer/EccommerceReducer";
import { delete_from_cart } from "../reduxContainer/EcommerceAction";

function MyOrders() {
  const [orders, setOrder] = useState([]);
  const cart_orders = useSelector((state) => state.cart);
  console.log(cart_orders);

  useEffect(() => {
    fetchOrders();
  }, []);


  const fetchOrders = () => {
    axios.get("http://localhost:4000/orders").then((res) => setOrder(res.data));
  };

  return (
    <div className="products-form">
      <Navbar />
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
          {orders
            .map((order) => (
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

export default MyOrders;
