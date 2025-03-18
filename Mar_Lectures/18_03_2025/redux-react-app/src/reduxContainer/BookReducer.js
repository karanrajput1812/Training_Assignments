import { buy_book, sell_book } from "./BookTypes"

const initialState = {
    NumberOfBooks: 2000
}

const BookReducer = (state=initialState, action) => {
  console.log("Action: "  + action.type)

  switch(action.type) {
    case buy_book: return {
        ...state,
        NumberOfBooks: state.NumberOfBooks + 1
    }
    case sell_book: return {
        ...state,
        NumberOfBooks: state.NumberOfBooks - 1
    }
    default: return state
  }
}

export default BookReducer