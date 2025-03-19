import React, { useState } from "react";
import { Link } from "react-router-dom";
import CustomerNavigation from "../../components/CustomerNavigation";
import { useSelector } from "react-redux";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function BalanceEnquiry() {
    const User = useSelector((state) => state.user);
    const [balance, setBalance] = useState(0);

    useEffect(() => {
        if (User && User.length > 0) {
            axios
                .get("http://localhost:8080/checkBalance/" + User[0].id)
                .then((res) => {
                    setBalance(res.data);
                    console.log(res.data);
                })
                .catch((err) => {
                    console.log(err);
                });
        }
    }, [User]);

    return (
        <div className="container">
            <CustomerNavigation />
            <main className="main">
                <h2>Bank Balance</h2>

                <div className="contact-container">
                    <div className="contact-card">
                        <h3>Welcome, {User && User.length > 0 ? User[0].fname : "User"}</h3>
                        <div className="contact-info">
                            <p>Your current balance is:</p>
                            <h4>₹ {balance}</h4>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    );
}

export default BalanceEnquiry;
