import React, { useState } from "react";
import { Link } from "react-router-dom";
import CustomerNavigation from "../../components/CustomerNavigation";
import { useSelector } from "react-redux";
import { useEffect } from "react";
import axios from "axios";

function MiniStatement() {
  const User = useSelector((state) => state.user);
  const [ministatement, setMinistatement] = useState([]);
  useEffect(() => {
    // if (User && User[0] && User[0].id) {
      axios.get(`http://localhost:8080/ministatement/` + User[0].id)
      .then((res) => {
          console.log(res.data);
          setMinistatement(res.data);
      })
      .catch((err) => {
          console.error("Error fetching mini statement:", err);
      });
    // }
  }, [User]);
  return (
    <div className="container">
      <CustomerNavigation />
      <main className="main">
        <h2>Mini Statement</h2>

        <div className="contact-container">
          <div className="contact-card">
            <div className="contact-info">
              <table>
                      <thead>
                        <tr>
                          <th>Transaction ID</th>
                          <th>Sender Id</th>
                          <th>Sender Name</th>
                          <th>Receiver Id</th>
                          <th>Receiver Name</th>
                          <th>Amount</th>
                          <th>Time Stamp</th>
                        </tr>
                      </thead>
                      <tbody>
                        {ministatement.map((statement) => (
                          <tr key={statement.t_id}>
                            <td>{statement.t_id}</td>
                            <td>{statement.sender.id}</td>
                            <td>{statement.sender.fname}</td>
                            <td>{statement.receiver.id}</td>
                            <td>{statement.receiver.fname}</td>
                            <td>{statement.amount}</td>
                            <td>{statement.timestamp}</td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
}

export default MiniStatement;
