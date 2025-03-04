class Person {
    name: string;
    constructor(name: string) {
        console.log("Start of constructor");
        this.name = name;

        setTimeout(() => console.log("From within constructor : " + this.name),5000);
        console.log("End of constructor");
    }
}

new Person("Ravi");
console.log("This statement is not waiting for anyone");