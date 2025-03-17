import React from "react";
import {Link} from "react-router-dom";

function Menu() {
  return (
    <div>
      <Link to="/">Home</Link>
      <Link to="/">About Us</Link>
      <Link to="/">Services</Link>
      <Link to="/">Contact Us</Link>
      <Link to="/">Home</Link>
    </div>
  );
}

export default Menu;
