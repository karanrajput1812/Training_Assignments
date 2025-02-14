import java.util.concurrent.Semaphore;

class Shared {
    static int count = 0;
}

class IncThread extends Thread {
    String name;
    Semaphore sem;

    IncThread(String name, Semaphore sem) {
        this.name = name;
        this.sem = sem;
        new Thread(this).start();
    }

    public void run() {
        try {
            System.out.println(name + " is waiting for the permission to enter...|||");
            sem.acquire();
            System.out.println("" + name + " got the permission to enter...|||"); 
            for (int i = 0; i < 5; i++) {
                System.out.println(name + " : " + ++Shared.count);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(name + " released the permission to enter...|||");
        sem.release();
    }
}

class DecThread extends Thread {
    String name;
    Semaphore sem;

    DecThread(String name, Semaphore sem) {
        this.name = name;
        this.sem = sem;
        new Thread(this).start();
    }

    public void run() {
        try {
            System.out.println(".|.|.|." + name + " is waiting for the permission to enter.|.|.|.");
            sem.acquire();
            System.out.println(".|.|.|." + name + " got the permission to enter..|.|.|."); 
            for (int i = 0; i < 5; i++) {
                System.out.println(name + " : " + --Shared.count);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(".|.|.|." + name + " released the permission to enter.|.|.|.");
        sem.release();
    }
}


public class ConcurrencyDemo {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(2);

        new DecThread("Akil", sem);
        new IncThread("Rajesh", sem);
        new IncThread("Suresh", sem);
        new DecThread("Anil", sem);
        new IncThread("Ramesh", sem);
        new IncThread("Dinesh", sem);
        new DecThread("Sunil", sem);
        new DecThread("Daniel", sem);
        new IncThread("Ganesh", sem);
        new DecThread("Nikhil", sem);
    }
}
