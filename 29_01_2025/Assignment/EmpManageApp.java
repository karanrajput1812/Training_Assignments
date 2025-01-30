import java.util.*;
import java.io.*;
import java.sql.*;


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
            System.out.println("Employee created successfully");
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
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "tiger");
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("select * from employee where eid = " + eid);
            if (!rs.next()) {
                System.out.println("No Employee Present with this eid");
            } else {
                Display.displayEmployeeFunction(rs);
                System.out.print("Do you really want to raise salary of the above record (Y/N)? ");
                String confirm = br.readLine();
                System.out.println("Enter the amount");
                int amount = Integer.parseInt(br.readLine());
                if (confirm.equalsIgnoreCase("Y")) {
                    stmt.executeUpdate("update employee set salary = salary + " + amount + " where EID = " + eid + ";");
                }
                System.out.println("Employee with eid: " + eid + " salary raised successfully");
            }
            br.close();
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteEmployee(int eid) {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "tiger");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("delete from employee where eid = " + eid);
            System.out.println("Employee Deleted Successfully");
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
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
                Display.displayEmployeeFunction(rs);
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
                System.out.println("No Employee Present with this "+ type);
            } else {
                Display.displayEmployeeFunction(rs);
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

class Display {
    public static void displayEmployeeFunction(ResultSet rs) {
        try {
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

public class EmpManageApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
            switch (ch1) {
                case 1:
                    do {
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
                    } while (ch2 != 5);
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
                    } while (ch3 != 7);

                    break;

                case 3:
                    int eid = IdInput.readId();
                    MainMenu.raiseEmployeeSalary(eid);
                    break;

                case 4:
                    System.out.println("Enter the Employee ID to delete:");
                    int empIdToRemove = sc.nextInt();
                    MainMenu.deleteEmployee(empIdToRemove);
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
                        ch4 = Menu.readChoice(5);
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
