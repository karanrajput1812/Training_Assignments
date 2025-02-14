import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.*;

import java.lang.;

abstract class Emp {
    private String name;
    private int age;
    protected float salary;
    private int eid;
    private String designation;

    private static int countEmp = 0;
    private static int nextEid = 1;

    Emp(float salary, String designation) {
        this.eid = nextEid++;
        this.name = NameInput.readName();
        this.age = AgeInput.readAge(20, 60);
        this.salary = salary;
        this.designation = designation;
        System.out.println("Record created successfully with Employee ID: " + eid);
        countEmp++;
    }

    public final void display() {
        System.out.println("Name: " + name);
        System.out.println("Employee Id: " + eid);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
        System.out.println("Designation: " + designation);
        System.out.println();
    }

    public int getEid() {
        return eid;
    }

    public abstract void raiseSalary();

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

    public static void resetCEO() {
        ceo = null;
        isCEO = false;
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
        Emp[] emp = new Emp[100];
        Scanner sc = new Scanner(System.in);

        int ch1 = 0, ch2 = 0;
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
                        if (CEO.isCEO) {
                            System.out.println("---------------------------------------------");
                            System.out.println("1. Create Clerk");
                            System.out.println("2. Create Programmer");
                            System.out.println("3. Create Manager");
                            System.out.println("4. Back");
                            System.out.println("---------------------------------------------");
                            ch2 = Menu.readChoice(4);
                            switch (ch2) {
                                case 1 -> emp[Emp.getCountEmp()] = EmpFactory.createEmp("Clerk");
                                case 2 -> emp[Emp.getCountEmp()] = EmpFactory.createEmp("Programmer");
                                case 3 -> emp[Emp.getCountEmp()] = EmpFactory.createEmp("Manager");
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
                                    emp[Emp.getCountEmp()] = EmpFactory.createEmp("CEO");
                                    break;
                                case 2:
                                    ch2 = 4;
                                    break;
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
                        System.out.println("Salary raised successfully");
                    }
                    break;

                case 4:
                    if (Emp.getCountEmp() == 0) {
                        System.out.println("No Employee Present to Remove");
                    } else {
                        System.out.println("Enter the Employee ID to delete:");
                        int empIdToRemove = sc.nextInt();
                        boolean found = false;

                        for (int i = 0; i < Emp.getCountEmp(); i++) {
                            if (emp[i].getEid() == empIdToRemove) {
                                System.out.print("Do you really want to delete the record (Y/N)? ");
                                String confirm = sc.next();
                                if (confirm.equalsIgnoreCase("Y")) {
                                    if (emp[i] instanceof CEO) {
                                        CEO.isCEO = false;
                                    }
                                    emp[i].display();
                                    for (int j = i; j < Emp.getCountEmp() - 1; j++) {
                                        emp[j] = emp[j + 1];
                                    }
                                    emp[Emp.getCountEmp() - 1] = null;
                                    Emp.decrementCountEmp();
                                    System.out.println("Employee record deleted successfully");
                                    found = true;
                                }
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println("Employee ID not found.");
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (ch1 != 5);

        sc.close();
        System.out.println("Total Employees Present in the Company: " + Emp.getCountEmp());
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
