import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import Navbar from '../components/Navbar';


function ProductDetail() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    const productId = window.location.pathname.split("/").pop();

    axios
      .get(`http://localhost:4000/products/${productId}`)
      .then((res) => setProducts(res.data));
  });

  return (
    <div className="products-form">
        <Navbar />
      <h1>Product Details</h1>
      <table>
        <thead>
          <tr>
            <th>Product ID</th>
            <th>Title</th>
            <th>Price</th>
            <th>Vendor ID</th>
            <th>Description</th>
            <th>Category</th>
            <th>Rating</th>
          </tr>
        </thead>
        <tbody>
            <tr>
              <td>{products.id}</td>
              <td>{products.title}</td>
              <td>{products.price}</td>
              <td>
                <Link to={`/vendorDetails/${products.v_id}`}>{products.v_id}</Link>
              </td>
              <td>{products.description}</td>
              <td>{products.category}</td>
              <td>{products.rating}</td>
            </tr>
        </tbody>
      </table>
    </div>
  );
}

export default ProductDetail;
