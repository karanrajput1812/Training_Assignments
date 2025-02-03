interface I {
    public void abc();
    public default void xyz()
    {
        System.out.println("From xyz() method");
        demo();
    }
    public static void atoz()
    {
        System.out.println("From atoz() method");
    }
    private void demo()
    {
        System.out.println("From demo() method");
    }
}
class A implements I {
    @Override
    public void abc(){
        System.out.println("From A class abc() method");
    }
}

interface X {
    public default void hello()
    {
        System.out.println("From X interface hello() method");
    }
}

interface Y {
    public  default void hello()
    {
        System.out.println("From Y interface hello() method");
    }
}

class B implements X,Y{
    public void hello(){
        System.out.println("From B class hello() method");
        X.super.hello();
        Y.super.hello();
    }
}

public interface LatestInterface {
    public static void main(String[] args) {
        I.atoz();
        A a1 = new A();
        a1.xyz();
        a1.abc();

        B  b1 = new B();
        b1.hello();
    }
}
