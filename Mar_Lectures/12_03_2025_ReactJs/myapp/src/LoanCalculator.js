import React, { useState } from "react";

function LoanCalculator() {
  let [name, setName] = useState("");
  let [type, setType] = useState("");
  let [amount, setAmount] = useState(0);
  let [tenure, setTenure] = useState(0);
  let [interest, setInterest] = useState(0);
  let [emi, setEmi] = useState(0);
  let [error, setError] = useState("");


  function changeInterest() {
    if (type === "home") {
      setInterest(9);
    } else if (type === "car") {
      setInterest(11);
    } else if (type === "personal") {
      setInterest(15);
    }
  }

  function CalculateEmi() {
    if (type === "home") {
      if (tenure > 30) {
        setError("Tenure for home loan should be less than or equal to 30 years.");
        return;
      }
      if (amount < 500000 || amount > 10000000) {
        setError("Amount for home loan should be between 5 lakh to 1 crore.");
        return;
      }
    } else if (type == "car") {
      if (tenure > 7) {
        setError("Tenure for car loan should be less than or equal to 7 years.");
        return;
      }
      if (amount < 100000 || amount > 1500000) {
        setError("Amount for car loan should be between 1 lakh to 15 lakh.");
        return;
      }
    } else if (type == "personal") {
      if (tenure > 5) {
        tenure = tenure * 12;
        setError(
          "Tenure for personal loan should be less than or equal to 7 years."
        );
        return;
      }
      if (amount < 10000 || amount > 500000) {
        setError(
          "Amount for personal loan should be between 10 Thousand to 5 lakh."
        );
        return;
      }
    }
    interest = interest / 1200;
    tenure = tenure * 12;
    setEmi(
      (emi =
        (amount * interest * Math.pow(1 + interest, tenure)) /
        (Math.pow(1 + interest, tenure) - 1))
    );
  }

  return (
    <>
      <section class="main" id="loan_calculator">
        <h2>Loan Calculator</h2>
        <br />
        <br />
        <br />
        <div class="contact-form">
          <h3>Enter the below details</h3>
          <div class="form-group">
            <label for="name">Applicant</label>
            <input
              type="text"
              id="name"
              name="name"
              placeholder="Enter applicant name"
              required
              onChange={(e) => setName(e.target.value)}
            />
          </div>
          <div class="form-group">
            <label for="type">Type</label>
            <select
              id="type"
              name="type"
              onChange={(e) => setType(e.target.value)}
              onMouseLeave={changeInterest}
            >
              <option value="">Select a type</option>
              <option value="home">Home</option>
              <option value="car">Car</option>
              <option value="personal">Personal</option>
            </select>
          </div>
          <div class="form-group">
            <label for="interest">Interest</label>
            <input
              type="number"
              id="interest"
              name="interest"
              value={interest}
              placeholder="Enter Interest"
              required
              disabled
              onChange={(e) => setInterest(e.target.value)}
            />
          </div>
          <div class="form-group">
            <label for="tenure">Tenure</label>
            <input
              type="number"
              id="tenure"
              name="tenure"
              placeholder="Enter Tenure"
              required
              onChange={(e) => setTenure(e.target.value)}
            />
          </div>
          <div class="form-group">
            <label for="amount">Amount</label>
            <input
              type="number"
              id="amount"
              name="amount"
              placeholder="Enter Amount"
              required
              onChange={(e) => setAmount(e.target.value)}
            />
          </div>
          <button type="submit" class="submit-btn" onClick={CalculateEmi}>
            Calculate EMI
          </button>
          <br />
          <h1 class="emi">{emi==0?"":emi}</h1>
          <p>{error}</p>
          <br />
          <br />
        </div>
      </section>
    </>
  );
}

export default LoanCalculator;
