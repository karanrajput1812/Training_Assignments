import React from "react";
import Title from "../components/Title";
import Header from "../components/Header";
import Footer from "../components/Footer";
import { useNavigate } from "react-router-dom";

function Home() {
  let flag = true;
  const navigate = useNavigate();
  function goTo() {
    if (flag) {
      navigate("/welcome");
    } else { 
      navigate("/login");
    }
  }
  return (
    <div>
      <Header />
      <Title msg="Welcome to our National Bank" />
      <p>
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Tempora
        aliquid doloribus, rem explicabo, earum dicta ut illum dolorum labore,
        fugiat delectus? Dolorum animi culpa accusantium repellat delectus
        laudantium vero. Error?
      </p>
      <button onClick={goTo}>CLICK HERE</button>
      <Footer />
    </div>
  );
}

export default Home;