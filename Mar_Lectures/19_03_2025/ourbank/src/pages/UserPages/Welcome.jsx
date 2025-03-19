import React, { useEffect } from "react";
import "../pages.css";
import CustomerNavigation from "../../components/CustomerNavigation";
import { Link } from "react-router-dom";

function Welcome() {
  return (
    <div class="container">
      <CustomerNavigation />
      <main class="main">
        <h2>Welcome to Our Bank</h2>

        <div class="contact-container">
          <div class="contact-card">
            <h3>Balance Enquiry</h3>
            <div class="contact-info">
              <p>
                Check your account balance with just a few clicks.{" "}
                <Link to="./balance-enquiry">Click Here</Link>
              </p>
            </div>
          </div>

          <div class="contact-card">
            <h3>Mini Statement</h3>
            <div class="contact-info">
              <p>
                Get a summary of your recent transactions.{" "}
                <Link to="./mini-statement">Click Here</Link>
              </p>
            </div>
          </div>

          <div class="contact-card">
            <h3>Bank Transfer</h3>
            <div class="contact-info">
              <p>
                Transfer funds to another account.{" "}
                <Link to="./bank-transfer">Click Here</Link>
              </p>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
}

export default Welcome;
