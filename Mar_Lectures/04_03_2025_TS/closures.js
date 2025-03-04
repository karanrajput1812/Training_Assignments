// var a = 0;
var increment = function incr() {
    var a = 0;
    var plus = function() {
        a = a + 1;
        console.log(a);
    }
    return plus;
}();

increment();
increment();
increment();
increment();
increment(); 

