class A {
    public void print1to10() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Range : " + i);
        }
    }
}

class B {
    public void evenTill50() {
        for (int i = 0; i <= 50; i += 2) {
            System.out.println("Even : " + i);
        }
    }
}

class C {
    public void fibonacciFrom1to1000() {
        int a = 0, b = 1, c = 0;
        for (int i = 1; i <= 15; i++) {
            c = a + b;
            a = b;
            b = c;
            System.out.println("Fibonacci : " + c);
        }
    }
}

class MultiThreading {
    public static void main(String args[])
	{
        Runnable r1 = () -> new A().print1to10();;
        Runnable r2 = () -> new B().evenTill50();;
        Runnable r3 = () -> new C().fibonacciFrom1to1000();;

        Thread a1 = new Thread(r1);
        Thread b1 = new Thread(r2);
        Thread c1 = new Thread(r3);

        a1.start();
        b1.start();
        c1.start();

        // OR

        // new Thread(new A()::print1to10).start();
        // new Thread(new B()::evenTill50).start();
        // new Thread(new C()::fibonacciFrom1to1000).start();

        // |^| more accurate for assignment
	}
}