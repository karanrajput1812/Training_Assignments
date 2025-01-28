import java.util.*;

class Animal {
    void speak() {
        System.out.println("Animal speaks");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog barks");
    }
}

class GermanShepherd extends Dog {

}

public class Variant {
    public static void main(String[] args) {

        // Covariant
        List<? extends Animal> animals1 = List.of(new Dog(), new GermanShepherd());

        // You can read from the list
        Animal a = animals1.get(1);
        a.speak(); // Allowed

        // animals1.add(new Animal()); // NOT ALLOWED
        // animals1.add(new Dog()); // NOT ALLOWED

        // Invariant
        List<Animal> animals2 = new ArrayList<>();
        animals2.add(new Animal());
        animals2.add(new Dog()); // Allowed because Dog is a subtype of Animal

        // List<Dog> dogs = animals2; // NOT ALLOWED
        // List<Animal> animals2 = new ArrayList<Dog>(); // NOT ALLOWED

        // Contravariant
        List<? super GermanShepherd> animals3 = List.of(new Animal(), new Dog());
        // You can only read as Object
        Object obj = animals3.get(0); // Allowed but it's just an Object
    }
}