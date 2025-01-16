import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.*;

abstract class Emp {
    private String name;
    private int age;
    protected float salary;
    private int eid;
    private String designation;

    private static int[] employees = new int[1000];
    private static int countEmp = 0;

    Emp(float salary, String designation) {
        // Scanner sc = new Scanner(System.in);
        eid = IdInput.readId();
        ;
        if (isUnique(employees, countEmp, eid)) {
            this.eid = eid;
            name = NameInput.readName();
            employees[countEmp] = eid;
            age = AgeInput.readAge(20, 60);
            this.salary = salary;
            this.designation = designation; 
            countEmp++;
        } else {
            System.out.println("Employee Id already exists!!");
        }
    }

    public final void display() {
        System.out.println("Name: " + name);
        System.out.println("Employee Id: " + eid);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
        System.out.println("Designation: " + designation);
        System.out.println();
    }

    public abstract void raiseSalary();

    private static boolean isUnique(int[] employees, int count, int eid) {
        for (int i = 0; i < count; i++) {
            if (employees[i] == eid) {
                return false;
            }
        }
        return true;
    }

    public static int getCountEmp() {
        return countEmp;
    }

    public static void decrementCountEmp() {
        countEmp--;
    }
}

final class Clerk extends Emp {
    Clerk() {
        super(20000, "Clerk");
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

    @Override
    public void raiseSalary() {
        salary += 5000;
    }
}

final class Manager extends Emp {
    Manager() {
        super(100000, "Manager");
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

    public static CEO getCEO() {
            if (ceo == null) {
                isCEO = true;
                ceo = new CEO();
            }
        return ceo;
    }
    @Override
    public void raiseSalary() {
        salary += 150000;
    }
}

// Factory
class EmpFactory {
    public static Emp createEmp(String type) {
        switch (type) {
            case "CEO":
                return CEO.getCEO();
            case "Clerk":
                return new Clerk();
            case "Programmer":
                return new Programmer();
            case "Manager":
                return new Manager();
            default:
                throw new IllegalArgumentException("Unknown employee type");
        }
    }
}

public class EmpManageApp {
    public static void main(String[] args) {
        int ch1 = 0, ch2 = 0;
        Emp[] emp = new Emp[100];
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("-------------------------------------");
            System.out.println("1. Create Employee");
            System.out.println("2. Display");
            System.out.println("3. Raise Salary");
            System.out.println("4. Remove");
            System.out.println("5. Exit");
            System.out.println("-------------------------------------");
            ch1 = Menu.readChoice(5);
            switch (ch1) {
                case 1:
                    do {
                        if (CEO.isCEO == true) {
                            System.out.println("---------------------------------------------");
                            System.out.println("1. Create Clerk");
                            System.out.println("2. Create Programmer");
                            System.out.println("3. Create Manager");
                            System.out.println("4. Back");
                            System.out.println("--------------------------------------------");
                            ch2 = Menu.readChoice(4);
                            switch (ch2) {
                                case 1:
                                    emp[Emp.getCountEmp()] = EmpFactory.createEmp("Clerk");
                                    break;
                                case 2:
                                    emp[Emp.getCountEmp()] = EmpFactory.createEmp("Programmer");
                                    break;
                                case 3:
                                    emp[Emp.getCountEmp()] = EmpFactory.createEmp("Manager");
                                    break;
                                case 4:
                                    break;
                            }
                        } else {
                            System.out.println("---------------------------------------------");
                            System.out.println("1. Create CEO");
                            System.out.println("2. Back");
                            System.out.println("--------------------------------------------");
                            ch2 = Menu.readChoice(1);
                            switch (ch2) {
                                case 1:
                                    emp[Emp.getCountEmp()] = EmpFactory.createEmp("CEO");
                                    break;
                                case 2:
                                    ch2 = 4;
                            }

                        }
                    } while (ch2 != 4);
                    break;

                case 2:
                    if (Emp.getCountEmp() == 0) {
                        System.out.println("No Employee Present to Display");
                    } else {
                        for (int i = 0; i < Emp.getCountEmp(); i++) {
                            emp[i].display();
                        }
                    }
                    break;

                case 3:
                    if (Emp.getCountEmp() == 0) {
                        System.out.println("No Employee Present to Raise Salary");
                    } else {
                        for (int i = 0; i < Emp.getCountEmp(); i++) {
                            emp[i].raiseSalary();
                        }
                    }
                    break;

                case 4:
                    if (Emp.getCountEmp() == 0) {
                        System.out.println("No Employee Present to Delete");
                    } else {
                        System.out.println("Enter the Employee id for deletion");
                        int empid = sc.nextInt();
                        if (empid >= 0 && empid < Emp.getCountEmp()) {
                            System.out.print("Do we really want to delete the record (Y/N)? ");
                            String c = sc.next();
                            if (c.equalsIgnoreCase("Y")) {
                                emp[empid].display();
                                for (int i = empid; i < Emp.getCountEmp() - 1; i++) {
                                    emp[i] = emp[i + 1];
                                }
                                emp[Emp.getCountEmp() - 1] = null;
                                Emp.decrementCountEmp();
                                System.out.println("Employee record deleted successfully");
                            }
                        } else {
                            System.out.println("Invalid Employee ID");
                        }
                    }
                    break;
            }
        } while (ch1 != 5);
        sc.close();
        System.out.println("Total Employees Present in the Company: " + Emp.getCountEmp());
    }
}

class Menu {
    private static int maxChoice;

    public static int readChoice(int mc) {
        maxChoice = mc;
        while (true) {
            System.out.println("Enter the choice:- ");
            try {
                int choice = new Scanner(System.in).nextInt();
                if (choice < 1 || choice > maxChoice) {
                    throw new InvalidChoiceException();
                }
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Please enter number only");
            } catch (InvalidChoiceException e) {
                e.displayMessage(maxChoice);
            }
        }
    }
}

class AgeInput {
    public static int readAge(int min_age, int max_age) {
        while (true) {
            System.out.println("Enter your age");
            try {
                int age = new Scanner(System.in).nextInt();
                if (age < min_age || age > max_age) {
                    throw new InvalidAgeException();
                }
                return age;
            } catch (InputMismatchException e) {
                System.out.println("Please enter number only");
            } catch (InvalidAgeException e) {
                e.displayMessage(min_age, max_age);
            }
        }
    }
}

class NameInput {
    public static String readName() {
        while (true) {
            System.out.println("Enter your Name");
            try {
                String name = new Scanner(System.in).nextLine();
                String checker = "^[A-Z][a-z]*\\s[A-Z][a-z]*$";
                Pattern p1 = Pattern.compile(checker);
                Matcher m1 = p1.matcher(name);

                if (!m1.matches()) {
                    throw new InvalidNameException();
                }
                return name;
            } catch (InvalidNameException e) {
                e.displayMessage();
            }
        }
    }
}

class IdInput {
    public static int readId() {
        while (true) {
            System.out.println("Enter your Employee Id");
            try {
                int choice = new Scanner(System.in).nextInt();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Please enter number only");
            }
        }
    }
}

class InvalidChoiceException extends RuntimeException {
    InvalidChoiceException() {
        super();
    }

    public void displayMessage(int maxChoice) {
        System.out.println("Please enter the choice between 1 to " + maxChoice);
    }
}

class InvalidAgeException extends RuntimeException {
    InvalidAgeException() {
        super();
    }

    public void displayMessage(int min_age, int max_age) {
        System.out.println("Please enter the age between " + min_age + " to " + max_age);
    }
}

class InvalidNameException extends RuntimeException {
    InvalidNameException() {
        super();
    }

    public void displayMessage() {
        System.out.println(
                "Please enter the valid name.\n(Name starts with capital letter, contain two words only and not contain any characters....)");
    }
}

class CEOAlreadyExists extends RuntimeException {
    public CEOAlreadyExists() {
        super();
    }

    public CEOAlreadyExists(String msg) {
        super(msg);
    }
}