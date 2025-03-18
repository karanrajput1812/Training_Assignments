
const initialState = {
    cart: [],
    isAdminAuthenticated: false
}
const EccommerceReducer = (state=initialState, action) => {
    console.log("Action: " + action); 
    switch(action.type) {
        case 'ADD_TO_CART': return {
            ...state,
            cart: [...state.cart, action.payload]
        }
        case 'DELETE_FROM_CART': return {
            ...state,
            cart: state.cart.filter((item)=> item.product_id !== action.payload)
        }
        case 'IS_ADMIN_AUTHENTICATED': return {
            ...state,
            isAdminAuthenticated: action.payload
        }
        default: return state
    }
}

export default EccommerceReducer