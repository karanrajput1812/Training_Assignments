var Person = /** @class */ (function () {
    function Person(name) {
        var _this = this;
        console.log("Start of constructor");
        this.name = name;
        setTimeout(function () { return console.log("From within constructor : " + _this.name); }, 5000);
        console.log("End of constructor");
    }
    return Person;
}());
new Person("Ravi");
console.log("This statement is not waiting for anyone");
