class Emp
{
    protected name:string
    protected age:number
    public constructor(name:string, age:number) {
        this.name = name;
        this.age = age;
    }
    public display() {
        console.log("Name: " + this.name);
        console.log("Age: " + this.age);
    }
}
export class Clerk extends Emp {
    salary:number;
    designation:string;
    constructor(name:string, age:number, salary:number, designation:string) {
        super(name, age);
        this.salary = salary;
        this.designation = designation;
    }
    displayAll() {
        super.display();
        console.log("Salary: " + this.salary);
        console.log("Designation: " + this.designation);
    }
}

var c1 = new Clerk("Raju", 25, 20000, "Clerk");
var c2 = new Clerk("Ravi", 33, 30000, "Clerk");
// var e2 = new Emp("Ravi", 33);
// c1.name = "Ravi";
c1.displayAll();
console.log("-----------------------------------------------");
c2.display();
console.log("-----------------------------------------------");

class A {
    constructor() {
        console.log("A()");
    }
}

class B extends A {
    constructor() {
        super();     // call parent constructor, compulsory to add super keyword
        console.log("B()");
    }
}

var a1 = new B();

console.log("-----------------------------------------------");

interface Car {
    speed:number;
    acclerate(): void;
    brake(): void;
}

class Hatchback implements Car {
    speed:number = 0;
    acclerate(): void {
        console.log("Acclerator : " + ++this.speed);
    }
    brake(): void {
        console.log("Decclerator : " + --this.speed);
    }
}

let h1 = new Hatchback();
h1.acclerate();
h1.acclerate();
h1.acclerate();
h1.brake();

interface CarProperties {
    company:string;
    model:string;
    price:number;
    gear?:boolean;      // optional things to add
}

let m1:CarProperties = {"company": "Maruti","model":"Brezza","price":1825000, "gear":true};
let t1:CarProperties = {"company": "TATA", "model":"Nexon", "price":1200000};
let m2:CarProperties = {"company": "M&M", "model":"BE6","price":2300000};
console.log(m1);
console.log(t1);
console.log(m2);

console.log("-------------------------------------");

let arr1:string[] = ["hi","hello"];
let arr2:Array<String> = ["hi","hello"];

console.log(arr1);
console.log(arr2);

console.log("-----------------------------------------------");


var a = 10;
var b = 20;

console.log("The sum of " +a+ " and " +b+ " is: " + (a+b));
console.log(`The sum of ${a} and ${b} is: + ${a+b}`);

