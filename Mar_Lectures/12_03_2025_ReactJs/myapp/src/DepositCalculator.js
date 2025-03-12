import React, { useState } from 'react'

function DepositCalculator() {
    const [amount, setAmount] = useState(0);
    const [tenure, setTenure] = useState(0);
    const [error, setError] = useState("");
    const [maturity, setMaturity] = useState(0);

    function calculateMaturity() {
        if (tenure > 7) {
            setError("Tenure for deposit should be less than or equal to 7 years.");
            return;
        }
        setMaturity(amount * Math.pow(1 + 7 / 100, tenure));
        setError("");
    }


  return (
    <section class="main" id="deposit_calculator">
            <h2>Deposit Calculator</h2>
            <div class="contact-form">
            <h3>Enter the below details</h3>
                    <div class="form-group">
                        <label for="amount">Principal Amount</label>
                        <input type="number" id="amount" name="amount" placeholder="Enter Principal Amount" required onChange={(e) => setAmount(e.target.value)}/>
                    </div>
                    <div class="form-group">
                        <label for="tenure">Tenure</label>
                        <input type="number" id="tenure" name="tenure" placeholder="Enter Tenure" required onChange={(e) => setTenure(e.target.value)}/>
                    </div>
                    <div class="form-group">
                        <label for="interest">Interest</label>
                        <input type="number" id="interest" name="interest" placeholder="Enter Interest" disabled value="7.0"/>
                    </div>
                    <button type="submit" class="submit-btn" onClick={calculateMaturity}>Calculate Maturity</button>
                    <br/>
                    <br/>
                    <h1 class="maturity">{maturity==0?"":maturity}  </h1>
                    <p>{error}</p>
                    <br/>
                </div>
        </section>
  )
}

export default DepositCalculator