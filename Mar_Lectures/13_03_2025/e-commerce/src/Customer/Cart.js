import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import Navbar from "../components/CustomerNavbar";
import { useDispatch, useSelector } from "react-redux";
import EccommerceReducer from "../reduxContainer/EccommerceReducer";
import { delete_from_cart } from "../reduxContainer/EcommerceAction";

function Cart() {
  const [stock, setStock] = useState([]);
  const [orders, setOrder] = useState([]);
  const cart_orders = useSelector((state) => state.cart);
  console.log(cart_orders);

  const dispatch = useDispatch();

  useEffect(() => {
    fetchStock();
    fetchOrders();
  }, []);

  const fetchStock = () => {
    axios.get("http://localhost:4000/stock").then((res) => setStock(res.data));
  };

  const fetchOrders = () => {
    axios.get("http://localhost:4000/orders").then((res) => setOrder(res.data));
  };

  const removeProduct = (productId) => {
    console.log("fff")
    dispatch(delete_from_cart(productId));
  }

  const handleUpdateOrderStatus = (productId) => {
    const productStock = stock.find((item) => item.productId === productId);
    axios.put(`http://localhost:4000/stock/${productStock.id}`, {
      ...productStock,
      quantity: productStock.quantity - 1,
    });

    if (productStock && productStock.quantity > 0) {
      const order = {
        product_id: cart_orders[0].product_id,
        quantity: cart_orders[0].quantity,
        total: cart_orders[0].total,
        v_id: cart_orders[0].v_id,
        status: cart_orders[0].status,
        date: new Date().toISOString().split("T")[0],
      };

      axios
        .post(`http://localhost:4000/orders`, order)
        .then(() => {
          alert("Payment successful!");
          dispatch(delete_from_cart(cart_orders[0].product_id));
          fetchOrders();
        })
        .catch((err) => {
          console.error("Error updating order status:", err);
        });
    } else {
      alert("Product is out of stock!");
    }
  };

  return (
    <div className="products-form">
      <Navbar />
      <h1>Products In Cart</h1>
      <table>
        <thead>
          <tr>
            {/* <th>Order ID</th> */}
            <th>Product ID</th>
            <th>Quantity</th>
            <th>Total</th>
            <th>Status</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {cart_orders.map((order) => (
            <tr key={order.id}>
              {/* <td>{order.id}</td> */}
              <td>{order.product_id}</td>
              <td>{order.quantity}</td>
              <td>{order.total}</td>
              <td>Unpaid</td>
              <td>
                <button
                  className="submit-btn"
                  onClick={() => handleUpdateOrderStatus()}
                >
                  Pay Now
                </button>
                <button
                  className="delete-btn"
                  onClick={() => removeProduct(order.product_id)}
                >
                  Remove From Cart
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Cart;
