import Calculators from "./Calculators";
import Counter from "./Counter";
import DepositCalculator from "./DepositCalculator";
import Greet from "./Greet";
import JsxDemo from "./JsxDemo";
import LoanCalculator from "./LoanCalculator";
import Welcome from "./Welcome";

function App() {
  const a = 20;
  let b = 20;
  return (
    <div>
      {/* <h1>This is my first React Application</h1>
      <JsxDemo />
      <br></br>
      <h2> The sum of {a} and {b} is : {a+b} </h2>
      {a>10 ? "Good Morning" :"Good Evening"}
      <Greet day="Morning" name="Pooja"/>
      <Greet day="Afternoon" name="Amit"/>
      <Greet day="Evening" name="Sir"/>

      <Counter />
      <Welcome /> */}
      <Calculators />
    </div>
  );
}

export default App;