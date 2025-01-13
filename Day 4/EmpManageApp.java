package emp.assignment;

import java.util.Scanner;

abstract class Emp{
    String name;
    int age;
    float salary;
    int eid;
    String designation;
	
    static int[] employees = new int[1000];

    static int countEmp = 0;
    Emp(float salary, String designation) {
        Scanner sc = new Scanner(System.in);
	System.out.println("Enter Employee Id");
	eid = sc.nextInt();
	if(isunique(employees,countEmp, eid))
        {
		this.eid = eid;	
		System.out.println("Enter your name");
        	name = sc.next();
		employees[countEmp] = eid;
		System.out.println("Enter your age");
        	age = sc.nextInt();
        	this.salary = salary;
        	this.designation = designation;
        	countEmp += 1;
	}else
 	{
		System.out.println("Employee Id already exists!!");
	}
    }

    final public void display() {
        System.out.println("Name: "+name);
	System.out.println("Employee Id: "+eid);
        System.out.println("Age: "+age);
        System.out.println("Salary: "+salary);
        System.out.println("Designation: "+designation);
        System.out.println();
    }

    public abstract void raiseSalary();

    public static boolean isunique(int[] employees, int count, int eid) {
	for(int i=0; i<count; i++)
	{
		if(employees[i]==eid) {
			return false;
		}
	}
	return true;
    }
}

final class Clerk extends Emp{
    Clerk(){
        super(20000, "Clerk");
    }
    public void raiseSalary(){
        salary += 2000;
    }
}

final class Programmer extends Emp{
    Programmer(){
        super(30000, "Programmer");
    }
    public void raiseSalary(){
        salary += 5000;
    }
}

final class Manager extends Emp{
    Manager(){
        super(100000, "Manager");
    }
    public void raiseSalary(){
        salary += 15000;
    }
}


public class EmpManageApp {
    public static void main(String[] args) {
        int ch1 = 0, ch2 = 0;
        Emp emp[] = new Emp[100];
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("-------------------------------------");
            System.out.println("1. Create Employee");
            System.out.println("2. Display");
            System.out.println("3. Raise Salary");
	    System.out.println("4. Remove");
            System.out.println("5. Exit");
            System.out.println("-------------------------------------");
            System.out.print("Enter your choice: ");
            ch1 = sc.nextInt();
            switch(ch1){
                case 1:
                do{
                    System.out.println("---------------------------------------------");
                    System.out.println("1. Create Clerk");
                    System.out.println("2. Create Programmer");
                    System.out.println("3. Create Manager");
                    System.out.println("4. Back");
                    System.out.println("--------------------------------------------");
                    System.out.print("Enter your choice: ");
                    ch2 = sc.nextInt();
                    switch(ch2){
                        case 1:
                        emp[Emp.countEmp] = new Clerk();
                        break;
                        case 2:
                        emp[Emp.countEmp] = new Programmer();
                        break;
                        case 3:
                        emp[Emp.countEmp] = new Manager();
                        break;
                    }
                }while(ch2 != 4);

                case 2:
                if (Emp.countEmp == 0){
                    System.out.println("No Employee Present to Display");
                }
                for (int i = 0; i < Emp.countEmp; i++){
                    emp[i].display();
                }
                break;

		case 3:
                if (Emp.countEmp == 0){
                    System.out.println("No Employee Present to Raise Salary");
                }
                for (int i = 0; i < Emp.countEmp; i++){
                    emp[i].raiseSalary();
                }
                break;

                case 4:
            	if (Emp.countEmp == 0) {
                	System.out.println("No Employee Present to Delete");
            	} else {
                	System.out.println("Enter the Employee id for deletion");
                	int empid = sc.nextInt();
                	if (empid >= 0 && empid < Emp.countEmp) {
                    	System.out.print("Do we really want to delete the record (Y/N)? ");
                    	String c = sc.next();
                    	if (c.equalsIgnoreCase("Y")) {
                        	emp[empid].display();
                        	for (int i = empid; i < Emp.countEmp - 1; i++) {
                            	emp[i] = emp[i + 1];
                        }
                        emp[Emp.countEmp - 1] = null;
                        Emp.countEmp--;
                        System.out.println("Employee record deleted successfully");
                    	}
                } else {
                    System.out.println("Invalid Employee ID");
                }
            }
            break;

            }
        }while(ch1 != 5);
        sc.close();
        System.out.println("Total Employees Present in the Company: " + Emp.countEmp);
    }
}