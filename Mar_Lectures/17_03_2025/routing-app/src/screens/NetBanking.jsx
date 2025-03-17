import React from 'react'
import Header from '../components/Header'
import Footer from '../components/Footer'
function NetBanking() {
  return (
    <div>
        <Header />
        <h2>NET BANKING</h2>
        <h3>Please Login</h3>
        <form>
            Username : <input></input> <br></br>
            Password : <input></input> <br></br>
            <br></br>
            <button>Login</button>
            <button>Reset</button>
        </form>
        <Footer />
    </div>
  )
}

export default NetBanking