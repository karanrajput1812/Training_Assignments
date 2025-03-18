import './App.css';
import BookContainer from './components/BookContainer';
import { Provider } from 'react-redux';
import store from './reduxContainer/Store';

function App() {
  return (
    <Provider store={store}>
    <div className="App">
      <h1>React Redux Demo</h1>
      <hr></hr>
      <BookContainer />
    </div>
    </Provider>
  );
}

export default App;
