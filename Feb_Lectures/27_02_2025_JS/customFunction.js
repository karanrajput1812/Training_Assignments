function add(a,b) {
    console.log("Addition is: " + (a+b));
}

var sum = add;

var sub = function(a,b) {
    console.log("Subtraction is: " + (a-b));
};

var mul = (a,b) => {
    console.log("Multiplication is: " + (a*b));
};

var div = new Function("a", "b", "console.log('Division is: ' + (a/b));");

add(10,20);
sum(30,40);
sub(50,60);
mul(70,80);
div(90,100);

console.log(typeof add);
console.log(typeof sub);
console.log(typeof mul);
console.log(typeof div);
