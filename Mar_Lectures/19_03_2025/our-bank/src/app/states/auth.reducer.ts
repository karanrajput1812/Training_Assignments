import { createReducer, on } from "@ngrx/store"
import { login } from "./auth.action"
export interface AuthState {
    auth: boolean,
    user: any
}

export const initialState: AuthState = {
    auth: false,
    user: []
}


export const authReducer = createReducer(
    initialState,
    on(login, (state) => {
    return { ...state, auth: true, user: [...state.user] };
})
)