import java.util.concurrent.*;
public class ConcurrencyDemo3 {
    public static void main(String[] args) throws Exception{
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(()-> {
            for(int i=1; i<=10; i++) {
                System.out.println("Using supplyAsync for String type : " + i);
            }
            return "Completed the string argument excecution......";
        });
        CompletableFuture.runAsync(() -> {
            for(int i=1; i<=10; i++) {
                System.out.println("Using runAsync : " + i);
            }
        });
        CompletableFuture<Double> cf2 = CompletableFuture.supplyAsync(()-> {
            Double res = 0.0;
            for(int i=1; i<=10; i++) {
                System.out.println("Using supplyAsync for Double type : " + i);
            }
            for(int j=1; j<=100; j++) {
                res = res + j;
            }
            return res;
        });

        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(()-> {
            for(int i=1; i<=10; i++) {
                System.out.println("Using supplyAsync for String type : " + i);
                try {
                    Thread.sleep(10000);
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
            return "Completed the string argument excecution......";
        }).orTimeout(5,TimeUnit.SECONDS).exceptionally(s-> "You delayed to give result");

        // Thread.sleep(10000);
        // System.out.println("Result: " + cf1.get());    // to get the result from cf1 completableFuture

        // CompletableFuture<Void> f = CompletableFuture.allOf( cf1, cf2);
        // f.join();

        System.out.println("Result : " + cf3.get());

        // System.out.println("Result : " + cf1.get());
        // System.out.println("Result from CF1 : " + cf1.getNow("Just For Demo"));

        // cf2.thenApplyAsync((x) -> {
        //     System.out.println("Summing till 100 is : " + x);
        //     return x;
        // });

        System.out.println("Main Exit :) ");
        Thread.sleep(1000);
    }
}
