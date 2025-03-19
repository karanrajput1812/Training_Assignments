import React, { useEffect } from "react";
import Navigation from "../components/Navigation";
import './pages.css'
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
function Home() {
  return (
    <div>
      <div class="container">
        <Navigation />
        <main class="main">
          <h2>Welcome to Our Bank</h2>
          <p>
            At Our Bank, we are committed to providing you with the best banking
            experience possible. Our innovative solutions and customer-centric
            approach ensure that your financial needs are met with utmost care
            and efficiency. Discover the convenience of banking at your doorstep
            with our wide range of services tailored to suit your lifestyle.
          </p>
          <br></br>
          <br></br>
          <h2>Latest Offers</h2>
          <p>
            Limited Time Offer: Open a new savings account and receive a 2%
            bonus on your initial deposit. Apply now to take advantage of this
            exclusive offer!
          </p>
          <br></br>
          <br />
          <Link to="/net-banking" class="submit-btn">
            Open an Account
          </Link>
        </main>
      </div>
    </div>
  );
}

export default Home;
