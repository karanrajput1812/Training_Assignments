import React from "react";
import { Link } from "react-router-dom";

function Navbar() {
  return (
    <div>
      <h1 className="header">E-Commerce Application Admin</h1>
      <div className="navbar">
        <Link to="/products" className="submit-btn">
          Products
        </Link>
        <Link to="/vendors" className="submit-btn">
          Vendors
        </Link>
        <Link to="/stock" className="submit-btn">
          Stock
        </Link>
        <Link to="/orders" className="submit-btn">
          Orders
        </Link>
        <Link to="/inventory" className="submit-btn">
          Inventory
        </Link>
      </div>
    </div>
  );
}

export default Navbar;

