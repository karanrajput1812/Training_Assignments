@FunctionalInterface
interface I {
    public void abc();

    public boolean equals(Object obj);

    public int hashCode();
}

class A implements I {
    public void abc() {
        System.out.println("From A class abc() method");
    }
}

class B {
    public void demo() {
        System.out.println("Hi Everybody,,..");
    }
}

public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        I i1 = new A();
        i1.abc();

        I i2 = new I() {
            public void abc() {
                System.out.println("Anonymous class abc() method");
            }
        };
        i2.abc();
        B b1 = new B() {
            public void demo() {
                System.out.println("Anonymous class demo() method");
            }
        };
        b1.demo();

        I i3 = () -> System.out.println("From Lambda exprssion abc() method");
        i3.abc();
    }
}