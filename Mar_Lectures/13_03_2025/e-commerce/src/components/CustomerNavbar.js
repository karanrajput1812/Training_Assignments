import React from 'react'
import {Link} from 'react-router-dom'

function CustomerNavbar() {
  return (
    <div>
    <h1 className="header">E-Commerce Application</h1>
    <div className="navbar">
      <Link to="/customer" className="submit-btn">
        Products
      </Link>
      <Link to="/cart" className="submit-btn">
        Cart
      </Link>
      <Link to="/myorders" className="submit-btn">
        My Orders
      </Link>
      <Link to="/" className="submit-btn">
        Admin
      </Link>
    </div>
    <br></br>
    <br></br>

  </div>
  )
}

export default CustomerNavbar