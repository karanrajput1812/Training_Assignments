import {createStoreHook} from 'react-redux'
import BookReducer from './BookReducer'
import {createStore} from 'redux'


// const store = createStoreHook(BookReducer)
const store = createStore(BookReducer)


export default store;