import java.util.*;
public class EMP_MANAGE_APP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

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
}
