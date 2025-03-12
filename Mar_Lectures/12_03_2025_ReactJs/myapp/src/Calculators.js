import React, { useState } from "react";
import LoanCalculator from "./LoanCalculator";
import DepositCalculator from "./DepositCalculator";

function Calculators() {
  const [calculator, setCalculator] = useState(true);

  function changeCalculator() {
    setCalculator(!calculator);
  }

  return (
    <section class="main" id="main">

      <div class="">
        {calculator == false ? (
            <button class="submit-btn" onClick={changeCalculator}>
              Switch to Loan Calculator
            </button>
        ) : (
          ""
        )}

        {calculator == true ? (
          <button class="submit-btn" onClick={changeCalculator}>
            Switch to Deposit Calculator
          </button>
        ) : (
          ""
        )}
      </div>
      {calculator ? <LoanCalculator /> : <DepositCalculator />}

    </section>
  );
}

export default Calculators;
