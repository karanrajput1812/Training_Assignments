import React from "react";
import { Link } from "react-router-dom";

function Header() {
  return (
    <div>
      <h1>OUR NATIONAL BANK</h1>
      <h2>Getting Banking Done Easier</h2>
      <hr></hr>
      <Link to="/"> Home </Link>
      <Link to="/about"> About Us </Link>
      <Link to="/services"> Services </Link>
      <Link to="/netbanking"> Netbanking </Link>
      <Link to="/contact"> Contact Us </Link>
    </div>
  );
}

export default Header;
