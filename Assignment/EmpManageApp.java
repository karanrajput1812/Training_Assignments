package Assignment;

import java.util.*;
import java.io.*;

abstract class Emp {
    protected String name;
    protected int age;
    protected float salary;
    protected int eid;
    protected String designation;

    protected static int countEmp = 0;
    protected static int nextEid = 1;

    Emp(float salary, String designation) {
        // this.eid = nextEid++;
        this.name = NameInput.readName();
        this.age = AgeInput.readAge(20, 60);
        this.salary = salary;
        this.designation = designation;
        System.out.println("Record created successfully");
        countEmp++;
    }

    Emp(int id, String name, int age, float salary, String designation) {
        this.eid = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.designation = designation;
        countEmp++;
    }

    public final void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
        System.out.println("Designation: " + designation);
        System.out.println();
    }

    public int getEid() {
        return eid;
    }

    public abstract void raiseSalary();

    public String getDesignation() {
        return designation;
    }

    public String getName() {
        return name;
    }

    public static int getCountEmp() {
        return countEmp;
    }

    public static void decrementCountEmp() {
        countEmp--;
    }

    public String toCSV() {
        return name + "," + age + "," + salary + "," + designation;
    }

}

final class Clerk extends Emp {
    Clerk() {
        super(20000, "Clerk");
    }

    Clerk(int id, String name, int age, float salary, String designation) {
        super(id, name, age, salary, designation);
    }

    private static Clerk clerk = null;

    public static Clerk getClerk() {
        clerk = new Clerk();
        return clerk;
    }

    public static Clerk getClerk(int id, String name, int age, float salary, String designation) {
        if (clerk == null) {
            clerk = new Clerk(id, name, age, salary, designation);
        }
        return clerk;
    }

    @Override
    public void raiseSalary() {
        salary += 2000;
    }
}

final class Programmer extends Emp {
    Programmer() {
        super(30000, "Programmer");
    }

    Programmer(int id, String name, int age, float salary, String designation) {
        super(id, name, age, salary, designation);
    }

    private static Programmer programmer = null;

    public static Programmer getProgrammer() {
        programmer = new Programmer();
        return programmer;
    }

    public static Programmer getProgrammer(int id, String name, int age, float salary, String designation) {
        if (programmer == null) {
            programmer = new Programmer(id, name, age, salary, designation);
        }
        return programmer;
    }

    @Override
    public void raiseSalary() {
        salary += 5000;
    }
}

final class Manager extends Emp {
    Manager() {
        super(100000, "Manager");
    }

    Manager(int id, String name, int age, float salary, String designation) {
        super(id, name, age, salary, designation);
    }

    private static Manager manager = null;

    public static Manager getManager() {
        manager = new Manager();
        return manager;
    }

    public static Manager getManager(int id, String name, int age, float salary, String designation) {
        if (manager == null) {
            manager = new Manager(id, name, age, salary, designation);
        }
        return manager;
    }

    @Override
    public void raiseSalary() {
        salary += 15000;
    }
}

// Singleton class
final class CEO extends Emp {
    private static CEO ceo = null;
    protected static boolean isCEO = false;

    private CEO() {
        super(2000000, "CEO");
    }

    private CEO(int id, String name, int age, float salary, String designation) {
        super(id, name, age, salary, designation);
    }

    public static CEO getCEO() {
        if (ceo == null) {
            isCEO = true;
            ceo = new CEO();
        }
        return ceo;
    }

    public static CEO getCEO(int id, String name, int age, float salary, String designation) {
        if (ceo == null) {
            isCEO = true;
            ceo = new CEO(id, name, age, salary, designation);
        }
        return ceo;
    }

    public static void resetCEO() {
        ceo = null;
        isCEO = false;
    }

    @Override
    public void raiseSalary() {
        salary += 150000;
    }
}

// Abstract Factory
class EmpFactory {
    public static Emp createEmp(String type) {
        switch (type) {
            case "CEO":
                return CEO.getCEO();
            case "Clerk":
                return Clerk.getClerk();
            case "Programmer":
                return Programmer.getProgrammer();
            case "Manager":
                return Manager.getManager();
            default:
                throw new IllegalArgumentException("Unknown employee type");
        }
    }
}

public class EmpManageApp {
    public static void main(String[] args) {
        // Emp[] emp = new Emp[100];
        HashMap<Integer, Emp> emp = new HashMap<Integer, Emp>();
        Scanner sc = new Scanner(System.in);
        loadFromFile(emp);
        int ch1 = 0, ch2 = 0, ch3 = 0, ch4 = 0;
        do {
            System.out.println("-------------------------------------");
            System.out.println("1. Create Employee");
            System.out.println("2. Display");
            System.out.println("3. Raise Salary");
            System.out.println("4. Remove");
            System.out.println("5. Search");
            System.out.println("6. Exit");
            System.out.println("-------------------------------------");
            ch1 = Menu.readChoice(6);
            List<Emp> list = new ArrayList<Emp>(emp.values());
            switch (ch1) {
                case 1:
                    do {
                        if (CEO.isCEO) {
                            System.out.println("---------------------------------------------");
                            System.out.println("1. Create Clerk");
                            System.out.println("2. Create Programmer");
                            System.out.println("3. Create Manager");
                            System.out.println("4. Back");
                            System.out.println("---------------------------------------------");
                            ch2 = Menu.readChoice(4);
                            switch (ch2) {
                                case 1 -> emp.put(IdInput.readId(emp), EmpFactory.createEmp("Clerk"));
                                case 2 -> emp.put(IdInput.readId(emp), EmpFactory.createEmp("Programmer"));
                                case 3 -> emp.put(IdInput.readId(emp), EmpFactory.createEmp("Manager"));
                                case 4 -> ch2 = 4;
                            }
                        } else {
                            System.out.println("---------------------------------------------");
                            System.out.println("1. Create CEO");
                            System.out.println("2. Back");
                            System.out.println("---------------------------------------------");
                            ch2 = Menu.readChoice(2);
                            switch (ch2) {
                                case 1:
                                    emp.put(IdInput.readId(emp), EmpFactory.createEmp("CEO"));
                                    break;
                                case 2:
                                    ch2 = 4;
                                    break;
                            }
                        }
                    } while (ch2 != 4);
                    saveToFile(emp);
                    break;

                case 2:
                    if (Emp.getCountEmp() == 0) {
                        System.out.println("No Employee Present to Display");
                    } else {
                        do {
                            System.out.println("---------------------------------------------");

                            System.out.println("Sorted Based On the");
                            System.out.println("1. Designation");
                            System.out.println("2. ID");
                            System.out.println("3. Name");
                            System.out.println("4. Age");
                            System.out.println("5. Salary");
                            System.out.println("6. Exit");
                            System.out.println("---------------------------------------------");
                            ch3 = Menu.readChoice(6);
                            switch (ch3) {
                                case 1 -> list.sort(new DisplayByDesignation());
                                case 2 -> list.sort(new DisplayByEmployeeId());
                                case 3 -> list.sort(new DisplayByName());
                                case 4 -> list.sort(new DisplayByAge());
                                case 5 -> list.sort(new DisplayBySalary());
                                case 6 -> ch3 = 6;
                            }
                            if (ch3 != 6) {
                                for (int i = 0; i < list.size(); i++) {
                                    list.get(i).display();
                                }
                            }
                        } while (ch3 != 6);
                    }
                    break;

                case 3:
                    if (Emp.getCountEmp() == 0) {
                        System.out.println("No Employee Present to Raise Salary");
                    } else {
                        Set s = emp.entrySet();
                        Iterator i1 = s.iterator();
                        while (i1.hasNext()) {
                            Map.Entry me = (Map.Entry) i1.next();
                            System.out.println("ID: " + me.getKey());
                            ((Emp) me.getValue()).raiseSalary();
                        }
                        System.out.println("Salary raised successfully");
                        saveToFile(emp);
                    }
                    break;

                case 4:
                    if (Emp.getCountEmp() == 0) {
                        System.out.println("No Employee Present to Remove");
                    } else {
                        System.out.println("Enter the Employee ID to delete:");
                        int empIdToRemove = sc.nextInt();
                        boolean found = false;

                        if (emp.containsKey(empIdToRemove)) {
                            Emp employeeToRemove = (Emp) emp.get(empIdToRemove);
                            employeeToRemove.display();

                            System.out.print("Do you really want to delete the record (Y/N)? ");
                            String confirm = sc.next();

                            if (confirm.equalsIgnoreCase("Y")) {
                                if (employeeToRemove instanceof CEO) {
                                    CEO.resetCEO();
                                    emp.clear();
                                } else {
                                    emp.remove(empIdToRemove);
                                }
                                Emp.decrementCountEmp();
                                System.out.println("Employee record deleted successfully.");
                                found = true;
                            }
                            saveToFile(emp);
                        }

                        if (!found) {
                            System.out.println("Employee ID not found.");
                        }
                    }
                    break;

                case 5:
                    if (Emp.getCountEmp() == 0) {
                        System.out.println("No Employee Present to Search");
                    } else {

                        do {
                            System.out.println("---------------------------------------------");

                            System.out.println("Search Based On the");
                            System.out.println("1. Designation");
                            System.out.println("2. ID");
                            System.out.println("3. Name");
                            System.out.println("4. Exit");
                            System.out.println("---------------------------------------------");
                            ch4 = Menu.readChoice(4);
                            switch (ch4) {
                                case 1 -> SearchEmployee.ByDesignation(emp);
                                case 2 -> SearchEmployee.ById(emp);
                                case 3 -> SearchEmployee.ByName(emp);
                                case 4 -> ch4 = 4;
                            }
                        } while (ch4 != 4);
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (ch1 != 6);

        sc.close();
        System.out.println("Total Employees Present in the Company: " + Emp.getCountEmp());
    }

    private static void loadFromFile(HashMap<Integer, Emp> emp) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("employees.csv"));
            String line;
            while ((line = reader.readLine()) != null) {

                StringTokenizer st = new StringTokenizer(line, ",");

                try {
                    int id = Integer.parseInt(st.nextToken().trim());
                    String name = st.nextToken().trim();
                    int age = Integer.parseInt(st.nextToken().trim());
                    float salary = Float.parseFloat(st.nextToken().trim());
                    String designation = st.nextToken().trim();


                    Emp e = switch (designation) {
                        case "CEO" -> CEO.getCEO(id, name, age, salary, designation);
                        case "Clerk" -> Clerk.getClerk(id, name, age, salary, designation);
                        case "Programmer" -> Programmer.getProgrammer(id, name, age, salary, designation);
                        case "Manager" -> Manager.getManager(id, name, age, salary, designation);
                        default -> throw new IllegalArgumentException("Unknown designation: " + designation);
                    };
                    if (e != null) {
                        emp.put(id, e);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Skipping line: " + line);
                }
            }
            reader.close();
            System.out.println("Data loaded from file successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void saveToFile(HashMap<Integer, Emp> emp) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("employees.csv"));
            Iterator<Map.Entry<Integer, Emp>> iterator = emp.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Emp> entry = iterator.next();
                Emp e = entry.getValue();
                writer.write(entry.getKey() + "," + e.toCSV());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data saved to file successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class Menu {
    public static int readChoice(int maxChoice) {
        while (true) {
            System.out.println("Enter your choice:");
            try {
                int choice = new Scanner(System.in).nextInt();
                if (choice < 1 || choice > maxChoice) {
                    throw new InvalidChoiceException();
                }
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number only.");
            } catch (InvalidChoiceException e) {
                e.displayMessage(maxChoice);
            }
        }
    }
}

class AgeInput {
    public static int readAge(int minAge, int maxAge) {
        while (true) {
            System.out.println("Enter your age:");
            try {
                int age = new Scanner(System.in).nextInt();
                if (age < minAge || age > maxAge) {
                    throw new InvalidAgeException();
                }
                return age;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number only.");
            } catch (InvalidAgeException e) {
                e.displayMessage(minAge, maxAge);
            }
        }
    }
}

class IdInput {
    public static int readId(HashMap<Integer, Emp> emp) {
        while (true) {
            System.out.println("Enter your id:");
            try {
                int id = new Scanner(System.in).nextInt();
                if (emp.containsKey(id)) {
                    throw new InvalidIDException("Id already exists..");
                }
                return id;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number only.");
            } catch (InvalidIDException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

class NameInput {
    public static String readName() {
        while (true) {
            System.out.println("Enter your name:");
            try {
                String name = new Scanner(System.in).nextLine();
                if (!name.matches("^[A-Z][a-z]*\\s[A-Z][a-z]*$")) {
                    throw new InvalidNameException();
                }
                return name;
            } catch (InvalidNameException e) {
                e.displayMessage();
            }
        }
    }
}

class SearchEmployee {

    public static void ByDesignation(HashMap<Integer, Emp> emp) {
        System.out.println("Enter the Designation to Search:");
        String designationToSearch = new Scanner(System.in).next();
        boolean found = false;
        Set s = emp.entrySet();
        Iterator i1 = s.iterator();
        while (i1.hasNext()) {
            Map.Entry me = (Map.Entry) i1.next();
            if (designationToSearch.equals(((Emp) me.getValue()).getDesignation())) {
                ((Emp) me.getValue()).display();
                found = true;
            }
        }
        if (!found) {
            System.out.println(designationToSearch + "not found.");
        }
    }

    public static void ById(HashMap<Integer, Emp> emp) {
        System.out.println("Enter the Employee ID to Search:");
        int empIdToSearch = new Scanner(System.in).nextInt();
        boolean found = false;
        Set s = emp.entrySet();
        Iterator i1 = s.iterator();
        while (i1.hasNext()) {
            Map.Entry me = (Map.Entry) i1.next();
            if (empIdToSearch == (Integer) me.getKey()) {
                ((Emp) me.getValue()).display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Employee ID not found.");
        }
    }

    public static void ByName(HashMap<Integer, Emp> emp) {
        System.out.println("Enter the Name to Search:");
        String nameToSearch = new Scanner(System.in).next();
        boolean found = false;

        Set s = emp.entrySet();
        Iterator i1 = s.iterator();
        while (i1.hasNext()) {
            Map.Entry me = (Map.Entry) i1.next();
            if (nameToSearch.equals(((Emp) me.getValue()).getName())) {
                ((Emp) me.getValue()).display();
                found = true;
            }
        }
        if (!found) {
            System.out.println(nameToSearch + " not found.");
        }
    }
}

class DisplayByEmployeeId implements Comparator<Emp> {
    public int compare(Emp s1, Emp s2) {
        return Integer.compare(s1.getEid(), s2.getEid());
    }
}

class DisplayByDesignation implements Comparator<Emp> {
    public int compare(Emp s1, Emp s2) {
        return s1.designation.compareTo(s2.designation);
    }
}

class DisplayByName implements Comparator<Emp> {
    public int compare(Emp s1, Emp s2) {
        return s1.name.compareTo(s2.name);
    }
}

class DisplayBySalary implements Comparator<Emp> {
    public int compare(Emp s1, Emp s2) {
        return Float.compare(s1.salary, s2.salary);
    }
}

class DisplayByAge implements Comparator<Emp> {
    public int compare(Emp s1, Emp s2) {
        return Integer.compare(s1.age, s2.age);
    }
}

class InvalidChoiceException extends RuntimeException {
    public void displayMessage(int maxChoice) {
        System.out.println("Please enter a choice between 1 and " + maxChoice);
    }
}

class InvalidAgeException extends RuntimeException {
    public void displayMessage(int minAge, int maxAge) {
        System.out.println("Please enter an age between " + minAge + " and " + maxAge);
    }
}

class InvalidNameException extends RuntimeException {
    public void displayMessage() {
        System.out.println("Invalid name. Name must start with a capital letter and contain two words.");
    }
}

class InvalidIDException extends RuntimeException {
    InvalidIDException(String msg) {
        super(msg);
    }

    public void displayMessage() {
        System.out.println("Please enter a number only");
    }
}
