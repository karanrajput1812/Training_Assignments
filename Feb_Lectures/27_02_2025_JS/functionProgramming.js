var a  = 10;
console.log(a);
a = function() {console.log("HELLO");};
console.log(a);
a();

console.log("-------------------------------");
function abc() {
    console.log("From function abc().....")
}

function xyz() {
    console.log("From function xyz().....")
    return abc;
}
function demo1(op) {
    console.log("From function demo() >>>>>>>>>>");
    op();
}
function demo2() {
    console.log("From function demo2() >>>>>>>>>>");
    return xyz;
}

demo1(abc);
demo1(xyz);

console.log("-------------------------------");
var res = demo2();
var res1 = res();
res1();

console.log("-------------------------------");

// Below code is known as currying
demo2();
demo2()();
demo2()()();    // same work as above 3 lines

console.log("-------------------------------");