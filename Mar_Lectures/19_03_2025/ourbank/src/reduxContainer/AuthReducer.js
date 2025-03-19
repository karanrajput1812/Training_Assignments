const initialState = {
    isAuth : false,
    user: []
}

export const AuthReducer = (state=initialState, action) => {
  console.log("Action: "  + action.type)

  switch(action.type) {
    case 'LOGIN_USER': return {
        ...state,
        isAuth: true,
        user: [...state.user, action.payload]
    }
    default: return state
  }
}
