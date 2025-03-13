import Calculators from "./Calculators";
import Counter from "./Counter";
import DepositCalculator from "./DepositCalculator";
import Effect from "./Effect";
import Greet from "./Greet";
import JsxDemo from "./JsxDemo";
import LoanCalculator from "./LoanCalculator";
import Welcome from "./Welcome";

function App() {
  const a = 20;
  let b = 20;
  return (
    <div>
      <h1>This is my first React Application</h1>
      <JsxDemo />
      <hr></hr>
      <br></br>
      <h2> The sum of {a} and {b} is : {a+b} </h2>
      <hr></hr>

      {a>10 ? "Good Morning" :"Good Evening"}
      <Greet day="Morning" name="Pooja"/>
      <Greet day="Afternoon" name="Amit"/>
      <Greet day="Evening" name="Sir"/>
      <hr></hr>
      <Counter />
      <hr></hr>
      <Welcome />
      <hr></hr>
      <Effect />



      {/* <Calculators /> */}
    </div>
  );
}

export default App;