import java.util.*;
import java.util.stream.*;

public class StreamDemo3 {
    public static void main(String[] args) {
        List<Emp> list = new ArrayList<Emp>();

        list.add(new Emp("Ramesh", 32, 20000, "Manager"));
        list.add(new Emp("Suresh", 25, 30000, "Clerk"));
        list.add(new Emp("Mohan", 27, 45000, "Manager"));
        list.add(new Emp("Raju", 22, 25000, "Clerk"));
        list.add(new Emp("Rajeev", 30, 38000, "Admin"));
        list.add(new Emp("Rajesh", 29, 35000, "Clerk"));
        list.add(new Emp("Dinesh", 35, 28000, "Tester"));
        list.add(new Emp("Amit", 45, 45656, "Programmer"));
        list.add(new Emp("Jignesh", 39, 53000, "Clerk"));
        list.add(new Emp("Abhishek", 45, 43400, "Programmer"));
        list.add(new Emp("Rohan", 29, 33550, "Tester"));
        list.add(new Emp("Vikram", 45, 30000, "Programmer"));
        list.add(new Emp("Rahul", 37, 23575, "Tester"));
        list.add(new Emp("Pewan", 30, 35000, "Admin"));

        System.out.println("-------------------------------------------------------");

        Map<Boolean, List<Emp>> m1 = list.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 30));
        System.out.println("****JUNIOR EMPLOYEES****");
        System.out.println(m1.get(true));
        System.out.println("****SENIOR EMPLOYEES****");
        System.out.println(m1.get(false));

        System.out.println("-------------------------------------------------------");

        // Collectors.counting() method is used to count the number of elements
        Map<Boolean, Long> m2 = list.stream()
                .collect(Collectors.partitioningBy(e -> e.getAge() > 30, Collectors.counting()));
        System.out.println("****JUNIOR EMPLOYEES****");
        System.out.println(m2.get(true));
        System.out.println("****SENIOR EMPLOYEES****");
        System.out.println(m2.get(false));

        System.out.println("-------------------------------------------------------");

        Map<Boolean, List<Emp>> m3 = list.stream().collect(Collectors.groupingBy((e -> e.getDesig()== "Manager")));
        System.out.println(m3);

        System.out.println("-------------------------------------------------------");

        Map<String, Long> m4 = list.stream().collect(Collectors.groupingBy(Emp::getDesig, Collectors.counting()));
        System.out.println(m4);

        System.out.println("-------------------------------------------------------");

        Map<String, List<String>> m5 = list.stream().collect(Collectors.groupingBy(e->e.getDesig(), Collectors.mapping(e->e.getName(), Collectors.toList())));
        System.out.println(m5);

    }
}

class Emp {
    private String name;
    private int age;
    private int salary;
    private String desig;

    public Emp(String n, int a, int s, String d) {
        name = n;
        age = a;
        salary = s;
        desig = d;
    }
    public String getDesig() {
        return desig;
    }

    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public String toString() {
        return "(" + name + ", " + age + ", " + salary + ", " + desig + ")";
    }
}