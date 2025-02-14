// extending thread class to make a class A thread
class A extends Thread {
    // overriding run method of thread class
    public void run() {
        for (int i = 1; i <= 20; i++) {
            // getting the name of the current thread
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                if (i % 2 == 0) {
                    // making the thread sleep for 2 seconds
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

// extending thread class to make a class B thread
class B extends Thread {
    // overriding run method of thread class
    public void run() {
        for (int i = 1; i <= 20; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            if (i % 3 == 0) {
                try {
                    //
                    Thread.sleep(3000);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}

// extending thread class to make a class C thread
class C extends Thread {
    // overriding run method of thread class
    public void run() {
        for (int i = 1; i <= 20; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

public class ThreadDemo {
    public static void main(String[] args) throws Exception {
        // A a1 = new A();
        // B b1 = new B();
        // C c1 = new C();

        // b1.setPriority(8);

        ThreadGroup tg = new ThreadGroup("Demo");

        Thread a1 = new Thread(tg, new A());
        Thread b1 = new Thread(tg, new B());
        Thread c1 = new Thread(tg, new C());

        a1.setName("Anita");
        b1.setName("Babita");
        c1.setName("Cindrella");

        // starting the threads using start method
        a1.start();
        b1.start();
        c1.start();

        for (int i = 1; i <= 20; i++) {
            System.out.println("********Main: " + i + "********");

            /*
             * if(i==5)
             * // suspend is used to pause the thread
             * b1.suspend();
             * // if(i==18)
             * // // resume is used to resume the thread
             * // b1.resume();
             * if(i==10)
             * c1.stop();
             * if(i==12) {
             * // isAlive is used to check whether the thread is alive or not
             * // suspend instance is still alive but it is not running
             * // stop instance is not alive
             * System.out.println(" a1 is alive: " + a1.isAlive());
             * System.out.println(" b1 is alive: " + b1.isAlive());
             * System.out.println(" c1 is alive: " + c1.isAlive());
             * }
             * // if(i==15) {
             * // c1 = new C();
             * // c1.start();
             * // }
             * Thread.sleep(300);
             * }
             * 
             */
            if (i == 5)
                tg.suspend();
            if (i == 10)
                tg.resume();
            if (i == 15)
                tg.stop();
            if (i == 12) {
                System.out.println(" a1 is alive: " + a1.isAlive());
                System.out.println(" b1 is alive: " + b1.isAlive());
                System.out.println(" c1 is alive: " + c1.isAlive());
            }
            Thread.sleep(3000);
        }
        a1.join();
        c1.join();
        System.out.println("**------Main Exit---------**");
    }
}