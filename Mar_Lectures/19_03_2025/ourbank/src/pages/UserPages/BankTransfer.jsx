import React, { use, useState } from 'react'
import CustomerNavigation from '../../components/CustomerNavigation'
import axios from 'axios';
import { useSelector } from "react-redux";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

function BankTransfer() {
  const [msg, setMsg] = useState('');
  const [btn, setBtn] = useState(false);
  const [receiver , setReceiver] = useState('');
  const [receiverDetails, setReceiverDetails] = useState([]);
  const [transactions, setTransactions] = useState({
    receiver_id: "",
    amount: ""
  })
  const User = useSelector((state) => state.user);
//   useEffect(() => {
//     console.log(new Date().toISOString());
//   })
  const assignData = (e) => {
    setTransactions({ ...transactions, [e.target.name]: e.target.value});
  }

  const checkReciver = () => {
    axios.get('http://localhost:8080/checkReceiver/' + transactions.receiver_id)
            .then((res) => {
                setReceiver(res.data.fname);
                setReceiverDetails(res.data);
                console.log(res.data);
            })
            .catch((err) => {
                console.log(err);
                // setMsg('Failed to Register User');
            });
  }

  const confirmRecieverName = () => {
    setBtn(true);
    alert(`Confirmed Receiver: ${receiver}`);
  }

  const bankTransfer = (e) => {
    e.preventDefault();
    let transaction = {
      sender: User[0],
      receiver: receiverDetails,
      amount: transactions.amount,
      timeStamp: parseInt(new Date()),
    }

    axios.get('http://localhost:8080/checkBalance/' + User[0].id + '/' + transactions.amount)
            .then((res) => {
                if (res.data==true) {
                    axios.post('http://localhost:8080/bankTransfer', transaction)
                    .then((res) => {
                        console.log(res.data);
                        setMsg('');
                        alert("Transaction Successfull");
                        
                    })
                }
                else {
                    setMsg('Insufficient Balance');
                }
            })
    console.log(transaction);
  }

return (
    <div class="container">
    <CustomerNavigation />
        <main class="main">
            <h2>Bank Transfer</h2>

            <div className="form-container">
                            </div>
                            <div className="contact-form">
                                    <form action="">
                                            <h3>Enter the below Details For Transfer </h3>
                                            <div className="form-group">
                                                    <label for="receiver_id">Receiver Id</label>
                                                    <input type="text" id="receiver_id" value={transactions.receiver_id} onChange={assignData} onMouseLeave={checkReciver} name="receiver_id" placeholder="Enter Receiver Id" required />
                                            </div>
                                            {
                                                    receiver && receiver.length > 0 &&
                                                    <div className="form-group">
                                                    <label for="receiver_fname">Receiver Name</label>
                                                    <input type="text" id="receiver_fname" value={receiver} readOnly name="receiver_fname" placeholder="Receiver Name" />
                                                    <br />
                                                    <button type="radio" className="confirm-btn" onClick={confirmRecieverName}>Confirm</button>
                                                    </div>
                                            }
                                            <div className="form-group">
                                                    <label for="amount">Amount</label>
                                                    <input type="number" id="amount" value={transactions.amount} onChange={assignData} name="amount" placeholder="Enter Amount" required />
                                            </div>
                                            <button disabled={!btn ? true : false} type="submit" className="submit-btn" onClick={bankTransfer}>Transfer</button>
                                            <br />
                                            <br />
                                            <p>Note : Check Reciver Name before performing transfer</p>
                                            <br />
                                            <h1 className='error'>{msg}</h1>
                                    </form>
                            </div>
        </main>
    </div>
)
}

export default BankTransfer