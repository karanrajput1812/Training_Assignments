class A implements Runnable {
    // synchronized method for thread safety
    // synchronized public void run() {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is waiting for the permission to enter");

        // To sysnchronize the particular block of code
        synchronized (this) {
        System.out.println(Thread.currentThread().getName() + " got the permission to enter");
            for (int i = 1; i <= 20; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}

public class SyncDemo {
    public static void main(String[] args) {
        A a1 = new A();

        // To create a multiple threads from a single object
        Thread t1 = new Thread(a1);
        Thread t2 = new Thread(a1);
        Thread t3 = new Thread(a1);

        // setting the names of the threads
        t1.setName("Aakash");
        t2.setName("Aman");
        t3.setName("Aaditya");

        t2.setDaemon(true);

        // starting the threads
        t1.start();
        t2.start();
        t3.start();

        t2.setDaemon(false);
    }
}

//  