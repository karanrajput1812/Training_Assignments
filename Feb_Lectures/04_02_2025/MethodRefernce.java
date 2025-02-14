interface I {
    void abc();
}

interface J {
    Object getObject();
}

class A {
    public A() {
        System.out.println("From A class constructor");
    }
    public static void classMethod() {
        System.out.println("From static class method");
    }

    public void instanceMethod() {
        System.out.println("From instance method");
    }
}

class B {
    public static void demo()
    {
        System.out.println("From B class demo() method");
    }
}

class C {
    C()
    {
        System.out.println("From C class constructor");
    }
    public String toString()
    {
        System.out.println("From C class toString() method");
        return "";
    }

}

public class MethodRefernce {
    public static void main(String[] args) {
        I i1 = A::classMethod;      // method reference
        i1.abc();

        I i2 = new A()::instanceMethod;     // method reference by creating object of class A
        i2.abc();

        I i3 = A::new;          // constructor reference
        i3.abc();

        I i4 = B::demo;         // static method reference from class B
        i4.abc();

        J j1 = C::new;          // constructor reference
        Object obj = j1.getObject();
        System.out.println(obj);

        J j2 = Thread::new;     // Thread class constructor reference
        System.out.println(j2.getObject());

    }    
}