import java.util.*;

class Biker extends Thread {
    private int distance = 1000;
    public static boolean startRace = false;
    String name;
    long startTime;
    long endTime;
    long timeTaken;

    public Biker(String name) {
        this.name = name;
    }

    public void run() {
        try {
            while (!startRace) {
                Thread.yield();
            }
            //used distance in below
            startTime = System.currentTimeMillis();
            long randomSpeed = 50 + (long) (Math.random() * 100);
            while (distance > 0) {
                Thread.sleep(1000);
                distance -= randomSpeed;

                if(distance < 0) {
                    distance = 0;
                }
                System.out.println(name + " has covered " + (1000 - distance) + "m.");
                Thread.sleep(randomSpeed);
            }
            endTime = System.currentTimeMillis();
            timeTaken = endTime - startTime;

        } catch (InterruptedException e) {
            System.out.println(name + " was interrupted.");
        }
    }
}

public class Bike implements Comparator<Biker>{
    public int compare(Biker b1, Biker b2) {
        return (int) (b1.timeTaken - b2.timeTaken);
    }
    public static void main(String[] args) {
        Biker[] bikers = new Biker[10];

        String [] names = {"Karan", "Madhav", "Johnathon", "Crystal", "Sanat", "Vrushank", "Aditya", "John", "Harsh", "Keefe"};

        // Create 10 bikers with random names
        for (int i = 0; i < 10; i++) {
            bikers[i] = new Biker("Biker-" + (i + 1));
            bikers[i].name = names[i];
        }

        System.out.println("Race Countdown:");
        for (int i = 10; i > 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        System.out.println("GO!");

        for (Biker biker : bikers) {
            biker.start();
        }
        Biker.startRace = true;

        for (Biker biker : bikers) {
            try {
                biker.join();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        Arrays.sort(bikers, new Bike());

        System.out.println("--------------------------------------");
        for (int i = 0; i < 10; i++) {
            System.out.println("Rank: " + (i + 1) + ". " + bikers[i].name + " finished in " + bikers[i].startTime + "ms");
        }
        System.out.println("--------------------------------------");

    }
}