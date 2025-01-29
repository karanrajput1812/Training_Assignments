import java.util.*;
import java.io.*;
import java.sql.*;

abstract class Emp implements Serializable {
    protected String name;
    protected int age;
    protected float salary;
    protected int eid;
    protected String designation;
    protected String department;

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

    public static Emp getCEO() {
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
        } else {
            ceo = null;
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

class MainMenu {
    public static void storeEmployee(String inputDesignation) {
        try {
            String name = NameInput.readName();
            int age = AgeInput.readAge(20, 60);
            int salary = SalaryInput.readSalary();
            String designation = inputDesignation;
            String department = DepartmentInput.readDepartment();

            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "tiger");
            PreparedStatement pstmt = con.prepareStatement(
                    "insert into employee (name, age, salary, designation, department) values(?,?,?,?,?)");

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setInt(3, salary);
            pstmt.setString(4, designation);
            pstmt.setString(5, department);
            pstmt.execute();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void displayEmployee(String type) {
        ResultSet rs = null;
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "tiger");
            Statement stmt = con.createStatement();
            switch (type) {
                case "Designation" -> rs = stmt.executeQuery("select * from employee order by Designation");
                case "ID" -> rs = stmt.executeQuery("select * from employee order by EID");
                case "Name" -> rs = stmt.executeQuery("select * from employee order by Name");
                case "Age" -> rs = stmt.executeQuery("select * from employee order by Age");
                case "Salary" -> rs = stmt.executeQuery("select * from employee order by Salary");
                case "Department" -> rs = stmt.executeQuery("select * from employee order by Department");
                default ->
                    throw new IllegalArgumentException("Unknown employee type");
            }
            while (rs.next()) {
                System.out.println("------------------------------------------------");
                System.out.println("ID: " + rs.getInt(1));
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Age: " + rs.getInt(3));
                System.out.println("Salary: " + rs.getInt(4));
                System.out.println("Designation: " + rs.getString(5));
                System.out.println("Department: " + rs.getString(6));
                System.out.println("------------------------------------------------");
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void raiseEmployeeSalary(int eid) {
        ResultSet rs = null;
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "tiger");
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("select * from employee where eid = " + eid);
            if (!rs.next()) {
                System.out.println("No Employee Present with this eid");
            } else {
                do {
                    System.out.println("------------------------------------------------");
                    System.out.println("ID: " + rs.getInt(1));
                    System.out.println("Name: " + rs.getString(2));
                    System.out.println("Age: " + rs.getInt(3));
                    System.out.println("Salary: " + rs.getInt(4));
                    System.out.println("Designation: " + rs.getString(5));
                    System.out.println("Department: " + rs.getString(6));
                    System.out.println("------------------------------------------------");
                } while (rs.next());
                System.out.print("Do you really want to raise salary of the above record (Y/N)? ");
                String confirm = new Scanner(System.in).next();
                System.out.println("Enter the amount");
                int amount = new Scanner(System.in).nextInt();
                if (confirm.equalsIgnoreCase("Y")) {
                    stmt.executeUpdate("update employee set salary = salary + " + amount + " where EID = " + eid + ";");
                }
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void searchEmployeeBasedOnId(int searchEid) {
        ResultSet rs = null;
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "tiger");
            PreparedStatement pstmt = con.prepareStatement("select * from employee where eid = ?");
            pstmt.setInt(1, searchEid);
            rs = pstmt.executeQuery();

            if (!rs.next()) {
                System.out.println("No Employee Present with this eid");
            } else {
                do {
                    System.out.println("------------------------------------------------");
                    System.out.println("ID: " + rs.getInt(1));
                    System.out.println("Name: " + rs.getString(2));
                    System.out.println("Age: " + rs.getInt(3));
                    System.out.println("Salary: " + rs.getInt(4));
                    System.out.println("Designation: " + rs.getString(5));
                    System.out.println("Department: " + rs.getString(6));
                    System.out.println("------------------------------------------------");
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public static void searchEmployee(String type, String value) {
        ResultSet rs = null;
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "tiger");
            String query = "select * from employee where " + type + " = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, value);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                System.out.println("No Employee Present with this eid");
            } else {
                while (rs.next()) {
                    System.out.println("------------------------------------------------");
                    System.out.println("ID: " + rs.getInt(1));
                    System.out.println("Name: " + rs.getString(2));
                    System.out.println("Age: " + rs.getInt(3));
                    System.out.println("Salary: " + rs.getInt(4));
                    System.out.println("Designation: " + rs.getString(5));
                    System.out.println("Department: " + rs.getString(6));
                    System.out.println("------------------------------------------------");
                }
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

public class EmpManageApp {
    public static void main(String[] args) {
        // Emp[] emp = new Emp[100];
        HashMap<Integer, Emp> emp = new HashMap<Integer, Emp>();
        Scanner sc = new Scanner(System.in);
        emp = loadFromFile(emp);
        Emp.countEmp = emp.size();
        System.out.println(emp.size());
        int ch1 = 0, ch2 = 0, ch3 = 0, ch4 = 0;
        do {
            System.out.println("-------------------------------------");
            System.out.println("1. Create Employee");
            System.out.println("2. Display");
            System.out.println("3. Appraisal");
            System.out.println("4. Remove");
            System.out.println("5. Search");
            System.out.println("6. Exit");
            System.out.println("-------------------------------------");
            ch1 = Menu.readChoice(6);
            List<Emp> list = new ArrayList<Emp>(emp.values());

            switch (ch1) {
                case 1:
                    do {
                        if (!CEO.isCEO) {
                            System.out.println("---------------------------------------------");
                            System.out.println("1. Create Clerk");
                            System.out.println("2. Create Programmer");
                            System.out.println("3. Create Manager");
                            System.out.println("4. Others");
                            System.out.println("5. Back");
                            System.out.println("---------------------------------------------");
                            ch2 = Menu.readChoice(5);
                            switch (ch2) {
                                case 1:
                                    MainMenu.storeEmployee("Clerk");
                                    break;
                                case 2:
                                    MainMenu.storeEmployee("Programmer");
                                    break;
                                case 3:
                                    MainMenu.storeEmployee("Manager");
                                    break;
                                case 4:
                                    String designation = DesignationInput.readDesignation();
                                    MainMenu.storeEmployee(designation);
                                    break;
                                case 5:
                                    ch2 = 5;
                                    break;
                            }
                        } else {
                            System.out.println("---------------------------------------------");
                            System.out.println("1. Create CEO");
                            System.out.println("2. Back");
                            System.out.println("---------------------------------------------");
                            ch2 = Menu.readChoice(2);
                            switch (ch2) {
                                case 1 -> MainMenu.storeEmployee("CEO");
                                case 2 -> ch2 = 5;
                            }
                        }
                    } while (ch2 != 5);
                    saveToFile(emp);
                    break;

                case 2:

                    do {
                        System.out.println("---------------------------------------------");
                        System.out.println("Sorted Based On the");
                        System.out.println("1. Designation");
                        System.out.println("2. ID");
                        System.out.println("3. Name");
                        System.out.println("4. Age");
                        System.out.println("5. Salary");
                        System.out.println("6. Department");
                        System.out.println("7. Exit");
                        System.out.println("---------------------------------------------");
                        ch3 = Menu.readChoice(7);
                        switch (ch3) {
                            case 1 -> MainMenu.displayEmployee("Designation");
                            case 2 -> MainMenu.displayEmployee("ID");
                            case 3 -> MainMenu.displayEmployee("Name");
                            case 4 -> MainMenu.displayEmployee("Age");
                            case 5 -> MainMenu.displayEmployee("Salary");
                            case 6 -> MainMenu.displayEmployee("Department");
                            case 7 -> ch3 = 7;
                        }
                        if (ch3 != 6) {
                            for (int i = 0; i < list.size(); i++) {
                                list.get(i).display();
                            }
                        }
                    } while (ch3 != 7);

                    break;

                case 3:
                    int eid = IdInput.readId();
                    MainMenu.raiseEmployeeSalary(eid);
                    break;

                case 4:
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

                        if (!found) {
                            System.out.println("Employee ID not found.");
                        }
                    }
                    break;

                case 5:
                    do {
                        System.out.println("---------------------------------------------");
                        System.out.println("Search Based On the");
                        System.out.println("1. Designation");
                        System.out.println("2. ID");
                        System.out.println("3. Name");
                        System.out.println("4. Department");
                        System.out.println("5. Exit");
                        System.out.println("---------------------------------------------");
                        ch4 = Menu.readChoice(4);
                        switch (ch4) {
                            case 1:
                                String designation = DesignationInput.readDesignation();
                                MainMenu.searchEmployee("Designation", designation);
                                break;
                            case 2:
                                int searchEid = IdInput.readId();
                                MainMenu.searchEmployeeBasedOnId(searchEid);
                                break;
                            case 3:
                                String Name = NameInput.readName();
                                MainMenu.searchEmployee("Name", Name);
                                break;
                            case 4:
                                String Department = DepartmentInput.readDepartment();
                                MainMenu.searchEmployee("Department", Department);
                                break;
                            case 5:
                                ch4 = 5;
                                break;
                        }
                    } while (ch4 != 5);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (ch1 != 6);
        sc.close();
    }

    private static HashMap<Integer, Emp> loadFromFile(HashMap<Integer, Emp> emp) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Emp.ser"));
            HashMap<Integer, Emp> empMap = (HashMap<Integer, Emp>) ois.readObject();
            return empMap;
        } catch (Exception e) {
            System.out.println(e);
        }
        return emp;
    }

    private static void saveToFile(HashMap<Integer, Emp> emp) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Emp.ser"));
            oos.writeObject(emp);
            oos.close();
            System.out.println("Successfully serializable........");
            oos.close();
        } catch (Exception e) {
            System.out.println(e);
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
    public static int readId() {
        while (true) {
            System.out.println("Enter your id:");
            try {
                int id = new Scanner(System.in).nextInt();
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

class DesignationInput {
    public static String readDesignation() {
        while (true) {
            System.out.println("Enter your Designation:");
            try {
                String designation = new Scanner(System.in).nextLine();
                return designation;
            } catch (InvalidNameException e) {
                e.displayMessage();
            }
        }
    }
}

class SalaryInput {
    public static int readSalary() {
        while (true) {
            System.out.println("Enter your salary:");
            try {
                int salary = new Scanner(System.in).nextInt();
                return salary;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number only.");
            }
        }
    }
}

class DepartmentInput {
    public static String readDepartment() {
        while (true) {
            System.out.println("Enter your Department:");
            try {
                String department = new Scanner(System.in).nextLine();
                return department;
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
