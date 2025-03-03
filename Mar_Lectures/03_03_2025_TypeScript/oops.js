"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
Object.defineProperty(exports, "__esModule", { value: true });
exports.Clerk = void 0;
var Emp = /** @class */ (function () {
    function Emp(name, age) {
        this.name = name;
        this.age = age;
    }
    Emp.prototype.display = function () {
        console.log("Name: " + this.name);
        console.log("Age: " + this.age);
    };
    return Emp;
}());
var Clerk = /** @class */ (function (_super) {
    __extends(Clerk, _super);
    function Clerk(name, age, salary, designation) {
        var _this = _super.call(this, name, age) || this;
        _this.salary = salary;
        _this.designation = designation;
        return _this;
    }
    Clerk.prototype.displayAll = function () {
        _super.prototype.display.call(this);
        console.log("Salary: " + this.salary);
        console.log("Designation: " + this.designation);
    };
    return Clerk;
}(Emp));
exports.Clerk = Clerk;
var c1 = new Clerk("Raju", 25, 20000, "Clerk");
var c2 = new Clerk("Ravi", 33, 30000, "Clerk");
// var e2 = new Emp("Ravi", 33);
// c1.name = "Ravi";
c1.displayAll();
console.log("-----------------------------------------------");
c2.display();
console.log("-----------------------------------------------");
var A = /** @class */ (function () {
    function A() {
        console.log("A()");
    }
    return A;
}());
var B = /** @class */ (function (_super) {
    __extends(B, _super);
    function B() {
        var _this = _super.call(this) || this; // call parent constructor, compulsory to add super keyword
        console.log("B()");
        return _this;
    }
    return B;
}(A));
var a1 = new B();
console.log("-----------------------------------------------");
var Hatchback = /** @class */ (function () {
    function Hatchback() {
        this.speed = 0;
    }
    Hatchback.prototype.acclerate = function () {
        console.log("Acclerator : " + ++this.speed);
    };
    Hatchback.prototype.brake = function () {
        console.log("Decclerator : " + --this.speed);
    };
    return Hatchback;
}());
var h1 = new Hatchback();
h1.acclerate();
h1.acclerate();
h1.acclerate();
h1.brake();
var m1 = { "company": "Maruti", "model": "Brezza", "price": 1825000, "gear": true };
var t1 = { "company": "TATA", "model": "Nexon", "price": 1200000 };
var m2 = { "company": "M&M", "model": "BE6", "price": 2300000 };
console.log(m1);
console.log(t1);
console.log(m2);
console.log("-------------------------------------");
var arr1 = ["hi", "hello"];
var arr2 = ["hi", "hello"];
console.log(arr1);
console.log(arr2);
console.log("-----------------------------------------------");
var a = 10;
var b = 20;
console.log("The sum of" + a + " and " + b + " is: " + (a + b));
console.log("The sum of ".concat(a, " and ").concat(b, " is: + ").concat(a + b));
