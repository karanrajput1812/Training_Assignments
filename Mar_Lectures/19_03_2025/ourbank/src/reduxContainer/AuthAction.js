export const login_User = (userDetails) => {
    console.log("User Login Action Called");
    return {
        type: "LOGIN_USER",
        payload: userDetails
    }
}