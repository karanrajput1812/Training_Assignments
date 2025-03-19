import React, { useState } from 'react'
import Navigation from '../components/Navigation'
import { Link } from 'react-router-dom'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


function Register() {

  const navigate = useNavigate();
  const [msg, setMsg] = useState('');
  const [user, setUser] = useState({
    fname: '',
    username: '',
    email: '',
    password: '',
    amount: 0
  })
  const assignData = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value});
  }

  const registerUser = (e) => {
    e.preventDefault();
    console.log(user);
    axios.post('http://localhost:8080/register', user)
            .then((res) => {
                console.log('User Register Successfully', res.data);
                setMsg('User Register Successfully');
                navigate('/net-banking');
            })
            .catch((err) => {
                console.log(err);
                setMsg('Failed to Register User');
            });
  }

  return (
    <div className="container">
    <Navigation />
    <section className="main">
        <h2>Net Banking</h2>
        <div className="form-container">
        </div>
        <div className="contact-form">
            <form action="">
                <h3>Register for Net Banking</h3>
                <div className="form-group">
                    <label for="fname">Full Name</label>
                    <input type="text" id="fname" value={user.fname} onChange={assignData} name="fname" placeholder="Enter your full name" required />
                </div>
                <div className="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" value={user.username} onChange={assignData} name="username" placeholder="Enter Username" required />
                </div>
                <div className="form-group">
                    <label for="email">Email Address</label>
                    <input type="email" id="email" value={user.email} onChange={assignData} name="email" placeholder="Enter your email address" required />
                </div>
                <div className="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password"value={user.password} onChange={assignData} name="password" placeholder="Enter your password" required />
                </div>
                <button type="submit" className="submit-btn" onClick={registerUser}>Register</button>
                <br />
                <br />
                <p>Already have an account? <Link to="/net-banking">Login</Link></p>
                <h1 className='error'>{msg}</h1>
            </form>
        </div>
    </section>
</div>
  )
}

export default Register