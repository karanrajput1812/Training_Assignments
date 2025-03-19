import React from "react";
import "./pages.css";
import Navigation from "../components/Navigation";
function AboutUs() {
  return (
    <div class="container">
      <Navigation />
      <section class="main">
        <h2>About Us</h2>
        <div>
          <p>
            Our Bank is a leading private sector bank in India. The Bank’s
            consolidated total assets stood at Rs. 14.76 trillion at September
            30, 2024. Our Bank has a network of 5,608 branches and 16,087 ATMs
            across India. Our Bank offers a wide range of banking products and
            financial services to corporate and retail customers through a
            variety of delivery channels and through its specialised
            subsidiaries in the areas of investment banking, life and non-life
            insurance, venture capital and asset management.
          </p>
        </div>
      </section>
    </div>
  );
}

export default AboutUs;
