import './App.css';
import {Route, Routes} from 'react-router-dom';
import AddProducts from './Products/AddProducts';
import VendorDetails from './Vendors/VendorDetails';
import AddInventory from './Inventory/AddInventory';
import AddOrders from './Orders/AddOrders';
import AddVendor from './Vendors/AddVendor';
import AddStock from './Stock/AddStock';
import ProductDetail from './Products/ProductDetail';
import GetProducts from './Products/GetProducts';
import GetVendors from './Vendors/GetVendors';
import GetOrders from './Orders/GetOrders';
import GetInventory from './Inventory/GetInventory';
import GetStock from './Stock/GetStock';
import Customer from './Customer/Customer';
import Cart from './Customer/Cart';

function App() {
  
  return (
      <Routes>
        <Route path='/' element={<GetProducts />} />
        <Route path='/products' element={<GetProducts />} />
        <Route path='/vendors' element={<GetVendors />} />
        <Route path='/orders' element={<GetOrders />} />
        <Route path='/inventory' element={<GetInventory />} />
        <Route path='/stock' element={<GetStock />} />
        
        <Route path='/customer' element={<Customer />} />
 
        <Route path='/products/addProducts' element={<AddProducts />}/>
        <Route path='/inventory/addInventory' element={<AddInventory />}/>
        <Route path='/orders/addOrders' element={<AddOrders />}/>
        <Route path='/vendors/addVendors' element={<AddVendor />}/>
        <Route path='/stock/addStock' element={<AddStock />}/>

        <Route path='/vendorDetails/:id' element={<VendorDetails />} />
        <Route path='/ProductDetails/:id' element={<ProductDetail />} />
        <Route path='/cart' element={<Cart />}/>
       </Routes>
  );
}

export default App;
