import logo from './logo.svg';
import './App.css';
import Products from './Products/Products';
import Vendors from './Vendors/Vendors';
import Orders from './Orders/Orders';
import { useState } from 'react';
import Inventory from './Inventory/Inventory';
import Stock from './Stock/Stock';

function App() {
  const [activeComponent, setActiveComponent] = useState('Products');
  
  return (
    <div className="products-form">
      <h1 className='header'>E-Commerce Application</h1>  
      <div className='navigation'>
        <button onClick={() => setActiveComponent('Products')} className='submit-btn'>Products</button>
        <button onClick={() => setActiveComponent('Vendors')} className='submit-btn'>Vendors</button>
        <button onClick={() => setActiveComponent('Stock')} className='submit-btn'>Stock</button>
        <button onClick={() => setActiveComponent('Orders')} className='submit-btn'>Orders</button>
        <button onClick={() => setActiveComponent('Inventory')} className='submit-btn'>Inventory</button>
      </div>
      <div className='main'>
        {activeComponent === 'Products' && <Products />}
        {activeComponent === 'Vendors' && <Vendors />}
        {activeComponent === 'Orders' && <Orders />}
        {activeComponent === 'Inventory' && <Inventory />}
        {activeComponent === 'Stock' && <Stock />}
      </div>
    </div>
  );
}

export default App;
