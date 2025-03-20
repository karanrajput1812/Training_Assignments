import { createSelector } from "@ngrx/store";
import { AppState } from "./app.state"

export const selectAuthsState = (state: AppState) => state.auth;

export const selectAuth = createSelector(
    selectAuthsState,
    (state) => state.auth,
    (state) => state.user
)