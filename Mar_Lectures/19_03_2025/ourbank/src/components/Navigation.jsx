import React from 'react'
import './components.css'
import { Link } from 'react-router-dom'


function Navigation() {
  return (
    <aside class="aside">   
    <h3>Navigation</h3>
    <ul>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/about-us">About Us</Link></li>
        <li><Link to="/services">Services</Link></li>
        <li><Link to="/net-banking">Net Banking</Link></li>
        <li><Link to="/contact-us">Contact Us</Link></li>
    </ul>
</aside>
  )
}

export default Navigation