import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { isAdminAuthenticated } from "../reduxContainer/EcommerceAction";
import { useEffect } from "react";

function Navbar() {
  const dispatch = useDispatch();
  const isAdmin = useSelector((state) => state.isAdminAuthenticated);
  const navigate = useNavigate();

  useEffect(() => {
    if (!isAdmin) {
      let password = prompt("Enter admin password:");
      if (password === "admin") {
        dispatch(isAdminAuthenticated(true));
      }
      else {
        navigate('/customer');
        alert("Wrong Admin Password")
      }
    }
  });
  
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

