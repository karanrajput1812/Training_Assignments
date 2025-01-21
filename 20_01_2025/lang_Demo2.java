import java.lang.reflect.*;
import java.util.Scanner;

class Employee {
    public void raiseSalary() {
        // Method implementation
    }

    public void display() {

    }
}

class Clerk extends Employee {
    int age;
    String name;

    Clerk() {
        age = 20;
        name = "Karan";
    }

    Clerk(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String toString() {
        return "This is Clerk class object";
    }
}

class Manager extends Employee {
    public String toString() {
        return "This is Manager class object";
    }
}

class Programmer extends Employee {
    public String toString() {
        return "This is Programmer class object";
    }
}

class Tester extends Employee {
    public String toString() {
        return "This is Tester class object";
    }
}

class Admin extends Employee {
    public String toString() {
        return "This is Admin class object";
    }
}

class A {
    int x;

    A(int x) {
        this.x = x;
    }

    public void finalize() {
        System.out.println("Object with x value " + x + " is getting removed......");
    }
}

public class lang_Demo2 extends Thread {
    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        A a1 = new A(10);
        A a2 = new A(20);
        A a3 = new A(30);

        // a2.finalize();

        System.out.println(a1.x);
        System.out.println(a2.x);
        System.out.println(a3.x);

        a2 = null;
        // a1 = null;
        a3 = null;
        System.gc();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Class c1 = a1.getClass();
        System.out.println("Enter the class name: ");
        Class c2 = Class.forName(new Scanner(System.in).next());
        Object obj = c2.newInstance();

        Field fields[] = c2.getFields();
        Method methods[] = c2.getMethods();
        Constructor constructors[] = c2.getConstructors();

        System.out.println("Fields: ");
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        System.out.println("Methods: ");
        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i].getName());
        }
        System.out.println("Constructors: ");
        for (Constructor constructor : constructors) {
            System.out.println(constructor.getName());
        }

    }
}
