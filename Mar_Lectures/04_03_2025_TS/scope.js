function abc() {
    a = 10;
    var b = 20;
    let c = 30;
    console.log("From inside abc() - a: " + a);
    console.log("From inside abc() - b: " + b);
    console.log("From inside abc() - c: " + c);
}   

abc();
console.log("Outside abc() - a: " + a);
// console.log("Outside abc() - b: " + b);
// console.log("Outside abc() - c: " + c);


// lexical scope 