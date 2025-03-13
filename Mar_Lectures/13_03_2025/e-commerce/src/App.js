import logo from './logo.svg';
import './App.css';
import Products from './Products/Products';
import Vendors from './Vendors/Vendors';

function App() {
  
  return (
    <div className="App">
      <h1 className='header'>E-Commerce Application</h1>
      <div className='main'>
        <Products />
        <Vendors />
      </div>

    </div>
  );
}

export default App;
