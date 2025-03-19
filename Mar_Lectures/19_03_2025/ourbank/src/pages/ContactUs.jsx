import React from "react";
import Navigation from "../components/Navigation";

function ContactUs() {
  return (
    <div class="container">
      <Navigation />
      <main class="main">
        <h2>Contact Us</h2>
        <p>
          We're here to help with any questions you might have about our
          services. Feel free to reach out to us through any of the channels
          below.
        </p>

        <div class="contact-container">
          <div class="contact-card">
            <h3>Corporate Headquarters</h3>
            <div class="contact-info">
              <p>
                <strong>Address:</strong> Times Square
              </p>
              <p>
                <strong>City:</strong> Andheri
              </p>
              <p>
                <strong>State:</strong> MH
              </p>
              <p>
                <strong>Zip:</strong> 400050
              </p>
              <p>
                <strong>Phone:</strong> 742 300 9901
              </p>
              <p>
                <strong>Email:</strong> infoourbank.com
              </p>
              <p>
                <strong>Hours:</strong> Mon-Fri: 9:00 AM - 5:00 PM
              </p>
            </div>
          </div>

          <div class="contact-card">
            <h3>Customer Support</h3>
            <div class="contact-info">
              <p>
                <strong>Toll-Free:</strong> 1812 1218 22
              </p>
              <p>
                <strong>Phone:</strong> 742 200 9901
              </p>
              <p>
                <strong>Email:</strong> supportourbank.com
              </p>
              <p>
                <strong>Hours:</strong> 24/7 Support Available
              </p>
              <br />
              <p>For lost/stolen cards, please call our emergency line:</p>
              <p>
                <strong>Emergency:</strong> 1-888-CARD-911
              </p>
            </div>
          </div>
        </div>

        <h2>Our Branches</h2>
        <div class="branch-info">
          <div class="branch">
            <h4>Andheri Branch</h4>
            <p>Times Square</p>
            <p>Mumbai, 400050</p>
            <p>Phone: 933 300 8201</p>
            <p>Hours: Mon-Fri: 9:00 AM - 5:00 PM</p>
          </div>
          <div class="branch">
            <h4>Thane Branch</h4>
            <p>Hiranandani Estate</p>
            <p>Thane, 400606</p>
            <p>Phone: 833 370 8771</p>
            <p>Hours: Mon-Fri: 9:00 AM - 5:00 PM</p>
          </div>
        </div>
      </main>
    </div>
  );
}

export default ContactUs;
