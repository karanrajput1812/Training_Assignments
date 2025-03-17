import React from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import Title from "../components/Title";

function About() {
  return (
    <div>
      <Header />
      <Title msg="About Our Bank" />
      <div>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Ea, modi
        ratione. Sapiente voluptate enim, aut dolorum ex quibusdam tenetur fuga
        dicta tempore laboriosam magni inventore ratione, dolore voluptas
        perferendis esse quas quae. Quae inventore numquam autem quidem
        laudantium harum? Corrupti quas ratione quam explicabo iure aliquam
        expedita reprehenderit perspiciatis accusantium!
      </div>
      <Footer />
    </div>
  );
}

export default About;
