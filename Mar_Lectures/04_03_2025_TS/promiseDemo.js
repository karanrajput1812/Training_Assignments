let s1 = "Wissen";
let s2 = "Technology";

var promise = new Promise( 
    function(resolved, rejected) {
        if ( (s1+s2) ==="WissenTechnology" )
            resolved("Employee Object");
        else
            rejected();
    }
);

promise
        .then(
            (val) => {
                console.log("Best Place to work 1");
                console.log("Got : " + val);
                return "Staff Feedback";   // return value to next then
            },
            () => {
                console.log("There is some spelling mistake");
                throw new Error("'o' is missing in 'Technology'")
            }
        )
        .then(
            (val) => {
                console.log("Best Place to work 2");
                console.log("Got : " + val);

            },
            () => {
                console.log("There is some spelling mistake");
                throw new Error("'o' is missing in 'Technology'")
            }
        )
        .catch(
            (err) => {
                console.log("Error handling done here.....");
                console.log("Reason: " + err.message)
            }
        )
        .finally(
            () => {
                console.log("Thank You");
            }
        )
// For multiple promises
// NOTES::
// Promise.all(promise1, promise2, promise3)     // waits for all the promises to complete
// Promise.allSettled(promise1, promise2, promise3)   // waits for all the promises to complete or reject
// Promise.race(promise1, promise2, promise3)      // waits for any one of the promises to complete