import java.util.function.*;
import java.util.*;

class A implements Consumer<String> {
    public void accept(String s) {
        System.out.println("Good Name is : " + s);
    }
}

class B {
    public void writeToFile(String s) {
        System.out.println("Written to csv file : " + s);
    }
}

public class ForEachMethod {
    public static void main(String[] args) {
        List <String> employees = Arrays.asList("Suman","Raman","Chaman","Baman");

        employees.forEach(new A());
        employees.forEach(new B()::writeToFile);
        employees.forEach((arg) -> System.out.println("Printing : " + arg));
        employees.forEach(System.out::println);
    }
}
