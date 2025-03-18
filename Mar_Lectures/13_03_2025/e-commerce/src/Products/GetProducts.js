import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import Navbar from "../components/Navbar";
function GetProducts() {
  const [products, setProducts] = useState([]);
  const [editingProduct, setEditingProduct] = useState(null);
  const [updatedProduct, setUpdatedProduct] = useState({});

  useEffect(() => {
    axios
      .get("http://localhost:4000/products")
      .then((res) => setProducts(res.data));
  });

  function deleteProducts(id) {
    axios
      .delete("http://localhost:4000/products/" + id)
      .then((res) => {
        console.log(`Product Deleted Successfully`, res.data);
        setProducts(products.filter((product) => product.id !== id));
      })
      .catch((err) => {
        console.log(err);
      });
  }

  function updateProducts(id) {
    const product = products.find((prod) => prod.id === id);
    setEditingProduct(product);
    setUpdatedProduct(product);
  }

  function handleUpdateChange(e) {
    const { name, value } = e.target;
    setUpdatedProduct({ ...updatedProduct, [name]: value });
  }

  function saveUpdatedProduct() {
    axios
      .put(
        "http://localhost:4000/products/" + updatedProduct.id,
        updatedProduct
      )
      .then((res) => {
        console.log(`Product Updated Successfully`, res.data);
        setProducts(
          products.map((product) =>
            product.id === updatedProduct.id ? updatedProduct : product
          )
        );
        setEditingProduct(null);
      })
      .catch((err) => {
        console.log(err);
      });
  }

  return (
    <div className="products-form">
      <Navbar />

      <h1>All Products</h1>
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
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {products.map((prod) => (
            <tr key={prod.id}>
              <td>{prod.id}</td>
              <td>{prod.title}</td>
              <td>{prod.price}</td>
              <td>
                <Link to={`/vendorDetails/${prod.v_id}`}>{prod.v_id}</Link>
              </td>
              <td>{prod.description}</td>
              <td>{prod.category}</td>
              <td>{prod.rating}</td>
              <td>
                <button
                  type="button"
                  onClick={() => deleteProducts(prod.id)}
                  className="delete-btn"
                >
                  Delete
                </button>
                <button
                  type="button"
                  onClick={() => updateProducts(prod.id)}
                  className="update-btn"
                >
                  Update
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <br></br>
      <br></br>
      <Link to="./addProducts" className="submit-btn">
        Switch To Add Product
      </Link>
      {editingProduct && (
        <div className="update-form">
          <h2>Update Product</h2>
          <form onSubmit={saveUpdatedProduct}>
            <label>
              Title:
              <input
                type="text"
                name="title"
                value={updatedProduct.title}
                onChange={handleUpdateChange}
              />
            </label>
            <label>
              Price:
              <input
                type="text"
                name="price"
                value={updatedProduct.price}
                onChange={handleUpdateChange}
              />
            </label>
            <label>
              Description:
              <input
                type="text"
                name="description"
                value={updatedProduct.description}
                onChange={handleUpdateChange}
              />
            </label>
            <label>
              Category:
              <input
                type="text"
                name="category"
                value={updatedProduct.category}
                onChange={handleUpdateChange}
              />
            </label>
            <label>
              Rating:
              <input
                type="text"
                name="rating"
                value={updatedProduct.rating}
                onChange={handleUpdateChange}
              />
            </label>
            <button
              type="button"
              onClick={saveUpdatedProduct}
              className="submit-btn"
            >
              Save
            </button>
          </form>
        </div>
      )}
    </div>
  );
}

export default GetProducts;
