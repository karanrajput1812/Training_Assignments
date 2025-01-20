import java.util.*;

class Bike extends Thread {
    public static final Object lock = new Object();
    private static boolean raceStarted = false;
    private int distance = RacingDetails.distance;
    String name;
    long startTime;
    long endTime;
    long timeTaken;

    Bike(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(name+" is ready to race.....");
        synchronized (lock) {
            while (!raceStarted) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    System.out.println(name + " interrupted while waiting to start the race.");
                }
            }
        }

        startTime = System.currentTimeMillis();
        for (int i = 1; i <= RacingDetails.distance; i++) {
            try {
                long randomSpeed = 50 + (long) (Math.random() * 100);
            while (distance > 0) {
                distance -= randomSpeed;

                if(distance < 0) {
                    distance = 0;
                }
                System.out.println(name + " has covered " + (RacingDetails.distance - distance) + "m.");
                Thread.sleep(randomSpeed);
            }
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        endTime = System.currentTimeMillis();
        timeTaken = endTime - startTime;
    }

    public static void startRace() {
        synchronized (lock) {
            raceStarted = true;
            lock.notifyAll();
        }
    }
}

class RacingDetails {
    static int noOfBikers;
    static int distance;
    static String unit = "Meters";
}


class Dashboard {
    public static void displayRankings(Bike[] bikers) {
        Arrays.sort(bikers, Comparator.comparingLong(b -> b.timeTaken));

        System.out.println("\nRankings: ");
        System.out.printf("%-6s %-10s %-10s %-10s %-10s\n", "Rank", "Name","Start Time","End Time", "Time (ms)");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < RacingDetails.noOfBikers; i++) {
            System.out.printf("%-6d %-10s %-10s %-10s %-10d\n", (i + 1), bikers[i].name,bikers[i].startTime,bikers[i].endTime, bikers[i].timeTaken);
        }
    }
}

public class BikeRacing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of bikers: ");
        RacingDetails.noOfBikers = scanner.nextInt();
        System.out.println("Enter the distance of the race: ");
        RacingDetails.distance = scanner.nextInt();

        Bike[] bikers = new Bike[RacingDetails.noOfBikers];

        for (int i = 0; i < RacingDetails.noOfBikers; i++) {
            System.out.println("Enter the name of biker " + (i + 1) + ": ");
            String name = scanner.next();
            bikers[i] = new Bike(name);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println();
        for (int i = 10; i > 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        System.out.println("Go....");

        for (Bike biker : bikers) {
            biker.start(); 
        }

        Bike.startRace();

        for (Bike biker : bikers) {
            try {
                biker.join(); 
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }

        Dashboard.displayRankings(bikers);

        scanner.close();
    }
}
