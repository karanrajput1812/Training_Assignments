import React from 'react'
import GetProducts from './GetProducts'
import AddProducts from './AddProducts'
import './products.css'
function Products() {
  return (
    <>
        <GetProducts />
        <br></br>
        <AddProducts />
    </>
  )
}

export default Products