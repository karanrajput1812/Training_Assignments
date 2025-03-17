import { Route, Routes } from "react-router-dom"
import Home from "./screens/Home"
import About from "./screens/About"
import Services from "./screens/Services"
import NetBanking from "./screens/NetBanking"
import Contact from "./screens/Contact"
import { Welcome } from "./screens/Welcome"

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/about" element={<About />}></Route>
        <Route path="/netbanking" element={<NetBanking />}></Route>
        <Route path="/services" element={<Services />}></Route>
        <Route path="/login" element={<NetBanking />}></Route>
        <Route path="/contact" element={<Contact />}></Route>
        <Route path='/welcome' element={<Welcome />}></Route>
        <Route path="/demo" element={<h1>Just For Demo</h1>}></Route>
      </Routes>
    </>
  )
}

export default App
