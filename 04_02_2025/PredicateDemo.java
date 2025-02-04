import java.util.function.*;
import java.lang.*;
public class PredicateDemo {
    public static void main(String[] args) {
        int arr[] = {11,22,33,44,55,66,77,88,99};

        // Predicate is a functional interface

        Predicate<Integer> p1 = i -> i%2==0;  // check if number is even
        Predicate<Integer> p2 = (i) -> i>50; // check if number is greater than 50
        process(arr,p1);
        process(arr, p1.negate());  // give all odd numbers

        process(arr, p2);
        process(arr, p2.negate());  // give all numbers less than 50

        process(arr, p1.and(p2));  // give all even numbers less than 50
        process(arr, p1.negate().and(p2).negate());  // give all odd numbers greater than 50
        process(arr, p1.negate().or(p2));  // give all odd numbers or all numbers less than 50
    }

    public static void process(int[] arr, Predicate<Integer> p) {
        for(int x: arr) {
            if(p.test(x)) {
                System.out.println(x);
            }
        }
        System.out.println("--------------------------");
    }
}
