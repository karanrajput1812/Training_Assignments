import React, { use } from 'react';
import { useState } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';
import { useDispatch, useSelector } from "react-redux";
import { isAdminAuthenticated } from '../reduxContainer/EcommerceAction';

function AddProducts() {
    const dispatch = useDispatch();
    const isAdmin = useSelector((state) => state.isAdminAuthenticated);
    
    if(!isAdmin){
        let password = parseInt("Enter Admin Password");
        if(password == 'admin') {
            dispatch(isAdminAuthenticated());
        }
    }

    const [msg, setMsg] = useState("");
    const [products, setProducts] = useState({
            "id": "",
            "title": "",
            "price": "",
            "description":"",
            "category":"",
            "rating":5,
            "v_id":"",
        })

        const assignData = (e) =>{
            setProducts({...products,[e.target.name ] : e.target.value});
        }

        const insertProduct=(e)=>{
            e.preventDefault();
            axios.post("http://localhost:4000/products",products)
            .then((res)=>{
                console.log(`Product Added Sucessfully`,res.data);
                setMsg("Product Added Successfully");
     
            })
            .catch((err)=>{
                console.log(err);
            })
        }

  return (
    <div className='products-form'>
             <Navbar />
        <h1>Add Products</h1>
        <h3> Enter the below details to add products in inventory</h3>
            <label for="id">Enter Id</label>
            <input name='id'  value={products.id} onChange={assignData} type='text' placeholder='Enter Product ID'></input><br></br><br></br>
            <label for="title">Enter Title</label>
            <input name='title' value={products.title} onChange={assignData} type='text' placeholder='Enter Product Title'></input><br></br><br></br>
            <label for="price">Enter Price</label>
            <input name='price' value={products.price} onChange={assignData} type='number' placeholder='Enter Price'></input><br></br><br></br>
            <label for="description">Enter Description</label>
            <input name='description' value={products.description} onChange={assignData} type='text' placeholder='Enter Description'></input><br></br><br></br>
            <label for="category">Enter Category</label>
            <input name='category' onChange={assignData} value={products.category} type='text' placeholder='Enter Category'></input><br></br><br></br>
            <label for="v_id">Enter Vendor ID</label>
            <input name='v_id' onChange={assignData} value={products.v_id} type='text' placeholder='Enter Vendor ID'></input><br></br><br></br>
            <br></br>
            <button type="submit" onClick={insertProduct} className='submit-btn'>SUBMIT</button>
            <h1>{msg}</h1>
    </div>
  )
}

export default AddProducts