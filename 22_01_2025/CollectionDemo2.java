import java.util.*;

public class CollectionDemo2 {
    public static void main(String[] args) {
        List list = new LinkedList();
        list.add("333");
        list.add("222");
        list.add("666");
        list.add("444");
        list.add("111");
        list.add("555");
        list.add("000");
        list.add("777");

        System.out.println(list);

        // Reverse
        Collections.reverse(list);
        System.out.println(list);

        // Sort
        Collections.sort(list);
        System.out.println(list);

        // Search
        System.out.println(Collections.binarySearch(list, "111"));

        // Descending Order
        Collections.reverse(list);
        System.out.println(list);

        // Swap Elements
        Collections.swap(list, 2, 3);
        System.out.println(list);

        // Shuffle in Any order
        Collections.shuffle(list);
        System.out.println(list);


    }
}
