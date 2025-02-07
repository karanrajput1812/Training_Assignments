import java.util.stream.*;
import java.util.*;
import java.util.function.*;

public class StreamDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(22);
        list.add(33);
        list.add(44);
        list.add(55);
        list.add(66);
        list.add(77);
        list.add(88);
        list.add(99);
        list.add(34);
        list.add(56);
        list.add(24);
        list.add(68);
        list.add(46);
        list.add(98);
        list.add(58);

        System.out.println(list);

        Predicate<Integer> p = (x) -> x % 2 == 0;

        Stream s = list.stream();
        Stream en = s.filter(p);
        List<Integer> enList = (List<Integer>) en.collect(Collectors.toList());
        System.out.println(enList);


        List<Integer> odList = (List<Integer>) list.stream().filter(p.negate()).collect(Collectors.toList());
        System.out.println(odList);

        List<Integer> dList = (List<Integer>) list.stream().map(x -> x + x).collect(Collectors.toList());
        System.out.println(dList);

        List<Integer> lsort = (List<Integer>) list.stream().sorted().collect(Collectors.toList());

        // .limit(5).skip(3);  --> Limit 5 and skip first 3
        System.out.println(lsort);

        Random random = new Random();
        random.ints().filter(x -> x >= 0).limit(10).forEach(System.out::println);
        System.out.println("OOOOOOOOOOOOOOORRRRRRRRRRRRRRR");
        random.ints().map(x->Math.abs(x)).limit(10).forEach(System.out::println);

        IntStream is = IntStream.range(1, 11); // 1 to 10, 11 is not included
        is = IntStream.rangeClosed(1, 10);  // 1 to 10
        is.forEach(System.out::println);

        OptionalInt result1 =  IntStream.range(1, 6).reduce((a, b) -> a + b);
        System.out.println("Sum : " + result1.getAsInt());

        int res1 = Stream.of(1,2,3).reduce(10, (a,b) -> a+b);
        System.out.println("Sum of 1, 2, 3 and 10 : " + res1);

        // Parallel Stream used to process data in parallel
        int res2 = Arrays.asList(1,2,3).parallelStream().reduce(10, (a,b) -> a+b, (a,b) -> a+b);
        System.out.println(res2);


        IntSummaryStatistics stats = IntStream.range(1, 10).summaryStatistics();
        // stats returns the summary statistics of the stream like min, max, avg, sum and count
        System.out.println("Min : " + stats.getMin());
        System.out.println("Max : " + stats.getMax());
        System.out.println("Avg : " + stats.getAverage());
        System.out.println("Sum : " + stats.getSum());
        System.out.println("Count : " + stats.getCount());
    }
}
