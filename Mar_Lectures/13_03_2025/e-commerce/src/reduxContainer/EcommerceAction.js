export const add_to_cart = (productDetails) => {
  console.log("Added To Cart:", productDetails);
  return {
    type: "ADD_TO_CART",
    payload: productDetails
  };
};

export const delete_from_cart = (productDetailsID) => {
    console.log("Deleted From Cart:", productDetailsID);
    return {
        type: "DELETE_FROM_CART",
        payload: productDetailsID
    }
}

export const isAdminAuthenticated = (status) => {
    return {
        type: "IS_ADMIN_AUTHENTICATED",
        payload: status
    }
}
