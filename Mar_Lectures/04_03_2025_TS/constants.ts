class Emp
{
    name: String;
    age: Number;
    readonly company: String = "Wissen"


    constructor(name, age) {
        this.name = name;
        this.age = age;
    }
    display() {
        console.log("Name : " + this.name);
        console.log("Age : " + this.age);
        console.log("Company : " + this.company); 
    }
}

let e1 = new Emp("Raju",25);
e1.display();
// e1.company = "Infosys";
e1.display();