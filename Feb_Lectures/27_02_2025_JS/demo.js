var a = 10;
var b = "10";

console.log(a == b); // true
console.log(a === b); // false

var arr = ["hi", 22, 3.14, new Date(), null, () => {}, false];

for (const key in arr) {
    console.log(key + " value is: " + arr[key]);
}

for (const element of arr) {
    console.log(element);
}