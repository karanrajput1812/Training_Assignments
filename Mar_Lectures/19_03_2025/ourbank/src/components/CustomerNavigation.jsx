import React from "react";
import "./components.css";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

function CustomerNavigation() {
  const isAdmin = useSelector((state) => state.isAuth);
  const navigate = useNavigate();
  useEffect(() => {
    if (!isAdmin) {
      navigate("/net-banking");
    }
    console.log(isAdmin);
  }, [isAdmin]);
  return (
    <aside className="aside">
      <h3>Navigation</h3>
      <ul>
        <li>
          <Link to="/user">Home</Link>
        </li>
        <li>
          <Link to="/about-us">About Us</Link>
        </li>
        <li>
          <Link to="/services">Services</Link>
        </li>
        <li>
          <Link to="/net-banking">Net Banking</Link>
        </li>
        <li>
          <Link to="/contact-us">Contact Us</Link>
        </li>
        <li>
          <Link to="/">Logout</Link>
        </li>
      </ul>
    </aside>
  );
}

export default CustomerNavigation;
