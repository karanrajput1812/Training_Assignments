package mongoDemo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import java.io.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.sql.rowset.*;
import org.bson.Document;
import org.bson.conversions.Bson;

abstract class Emp {
    protected String name;
    protected int id;
    protected int age;
    protected int salary;
    protected int eid;
    protected String designation;
    protected String department;

    Emp(int salary, String designation) {
    	this.id = EIdInput.readEId();
        this.name = NameInput.readName();
        this.age = AgeInput.readAge(20, 60);
        this.salary = salary;
        this.designation = designation;
        this.department = DepartmentInput.readDepartment();
    }

    Emp(String designation) {
    	this.id = EIdInput.readEId();
        this.name = NameInput.readName();
        this.age = AgeInput.readAge(20, 60);
        this.salary = SalaryInput.readSalary();
        this.designation = designation;
        this.department = DepartmentInput.readDepartment();
    }
}

final class Clerk extends Emp {
    Clerk() {
        super("Clerk");
    }

    private static Clerk clerk = null;

    public static Clerk getClerk() {
        clerk = new Clerk();
        return clerk;
    }
}

final class Programmer extends Emp {
    Programmer() {
        super("Programmer");
    }

    private static Programmer programmer = null;

    public static Programmer getProgrammer() {
        programmer = new Programmer();
        return programmer;
    }

}

final class Manager extends Emp {
    Manager() {
        super("Manager");
    }

    private static Manager manager = null;

    public static Manager getManager() {
        manager = new Manager();
        return manager;
    }
}

final class OtherDesignation extends Emp {
    OtherDesignation(String designation) {
        super(designation);
    }

    private static OtherDesignation otherDesignation = null;

    public static OtherDesignation getOtherDesignation() {
        otherDesignation = new OtherDesignation(DesignationInput.readDesignation());
        return otherDesignation;
    }
}

final class MongoDatabaseConnection {
	private static MongoCollection<Document> collection = null;
	private static MongoClient mongoClient = null;
	
	private MongoDatabaseConnection() {
		
	}
	
	public static MongoCollection<Document> getMongoDatabase() {
		if(collection == null) {
			mongoClient = MongoClients.create("mongodb://localhost:27017");
			MongoDatabase database = mongoClient.getDatabase("demodb");
			collection = database.getCollection("Employee");
		}
		return collection;
	} 
	
	public static void closeJdbcConnection() throws SQLException {
		mongoClient.close();
    }
}

final class BufferConn {
    private static BufferedReader br = null;
 
    private BufferConn() {
    }

    public static BufferedReader getBufferConnection() {
        if (br == null) {
        	br = new BufferedReader(new InputStreamReader(System.in));
        }
        return br;
    }

    public static void closeBufferConnection(){
        try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

class EmpFactory {
    public static Emp createEmp(String type) {
        switch (type) {
            case "Clerk":
                return Clerk.getClerk();
            case "Programmer":
                return Programmer.getProgrammer();
            case "Manager":
                return Manager.getManager();
            case "Others":
                return OtherDesignation.getOtherDesignation();
            default:
                throw new IllegalArgumentException("Unknown employee type");
        }
    }
}

interface EmpDAO {
    abstract void storeEmployee(Emp e);

    abstract void displayEmployee(String type);

    abstract void raiseEmployeeSalary(int eid);

    abstract void deleteEmployee(int eid);

    abstract void searchEmployeeBasedOnId(int eid);

    abstract void searchEmployee(String type, String value);
}

class MainMenu implements EmpDAO {
    @Override
    public void storeEmployee(Emp emp) {
        try {
            MongoCollection<Document> collection = MongoDatabaseConnection.getMongoDatabase();
            
            collection.insertOne(new Document().append("id", emp.id).append("name", emp.name).append("age",emp.age).append("salary", emp.salary).append("designation", emp.designation).append("department", emp.department));
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void displayEmployee(String type) {
        try {
        	MongoCollection<Document> collection = MongoDatabaseConnection.getMongoDatabase();
            Bson asort;
            Bson projection = Projections.excludeId();
            switch (type) {
                case "Designation":
                	asort = Sorts.ascending("designation","id");
                	break;
                case "ID":
                	asort = Sorts.ascending("id");
            		break;
                case "Name":
                	asort = Sorts.ascending("name","id");
                	break;
                case "Age":
                	asort = Sorts.ascending("age","id");
            		break;
                case "Salary":
                	asort = Sorts.ascending("salary","id");
            		break;
                case "Department":
                	asort = Sorts.ascending("department","id");
            		break;
                default :
                    throw new IllegalArgumentException("Unknown employee type");
            }
            FindIterable<Document> empList = collection.find().projection(projection).sort(asort);
            Display.displayEmployeeFunction(empList);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void raiseEmployeeSalary(int eid) {
        try {
        	MongoCollection<Document> collection = MongoDatabaseConnection.getMongoDatabase();
        	BufferedReader br = BufferConn.getBufferConnection();
            Bson filter = Filters.eq("id", eid);
    		Document doc = collection.find(filter).first();
    		if (doc == null) {
                System.out.println("No Employee Present with this eid");
            } else {
            	System.out.println("------------------------------------------------");
    			System.out.println("Id: " + doc.getInteger("id"));
    			System.out.println("Name: " + doc.getString("name"));
    			System.out.println("Age: " +doc.getInteger("age"));
    			System.out.println("Salary: " + doc.getInteger("salary"));
    			System.out.println("Designation: " + doc.getString("designation"));
    			System.out.println("Department: " + doc.getString("department"));
    			System.out.println("------------------------------------------------");
            	
                System.out.print("Do you really want to raise salary of the above record (Y/N)? ");
                
                String confirm = br.readLine();
                
                if (confirm.equalsIgnoreCase("Y")) {
                    System.out.println("Enter the amount:");
                    
                    int amount = Integer.parseInt(br.readLine());
                    int salary = doc.getInteger("salary");
                    Bson update = Updates.set("salary", salary + amount);
                    collection.updateOne(filter, update);
                    System.out.println("Employee with eid: " + eid + " salary raised successfully");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteEmployee(int eid) {
        try {
        	MongoCollection<Document> collection = MongoDatabaseConnection.getMongoDatabase();
        	BufferedReader br = BufferConn.getBufferConnection();
        	
            Bson filter = Filters.eq("id", eid);
            Bson projection = Projections.excludeId();
    		Document doc = collection.find(filter).projection(projection).first();
        	
            if (doc.isEmpty()) {
                System.out.println("No Employee Present with this eid");
            } else {
            	System.out.println("------------------------------------------------");
    			System.out.println("Id: " + doc.getInteger("id"));
    			System.out.println("Name: " + doc.getString("name"));
    			System.out.println("Age: " +doc.getInteger("age"));
    			System.out.println("Salary: " + doc.getInteger("salary"));
    			System.out.println("Designation: " + doc.getString("designation"));
    			System.out.println("Department: " + doc.getString("department"));
    			System.out.println("------------------------------------------------");
    			
                System.out.print("Do you really want to delete the above record (Y/N)? ");
                String confirm = br.readLine();
                if (confirm.equalsIgnoreCase("Y")) {
                	collection.deleteOne(filter);
                    System.out.println("Employee with eid: " + eid + " deleted successfully");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void searchEmployeeBasedOnId(int searchEid) {
        try {
        	MongoCollection<Document> collection = MongoDatabaseConnection.getMongoDatabase();
            Bson filter = Filters.eq("id", searchEid);
            Bson projection = Projections.excludeId();
            FindIterable<Document> empList = collection.find(filter).projection(projection);
            Display.displayEmployeeFunction(empList);
    		if(getDocumentSize(empList) == 0) {
    			System.out.println("No Employee Present with this eid");
    		}
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void searchEmployee(String type, String value) {
        try {
 
        	MongoCollection<Document> collection = MongoDatabaseConnection.getMongoDatabase();
            Bson filter = Filters.eq(type, value);
            Bson projection = Projections.excludeId();
            FindIterable<Document> empList = collection.find(filter).projection(projection);
            Display.displayEmployeeFunction(empList);
    		if(getDocumentSize(empList) == 0) {
    			System.out.println("No Employee Present with this eid");
    		}
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static long getDocumentSize(FindIterable<Document> i) {
		long count = 0;
		for(Document doc : i) {
			count++;
		}
		return count;
    }
}

class Display {
    public static void displayEmployeeFunction(FindIterable<Document> i ) {
        try {
        	for(Document d : i) {
    			System.out.println("------------------------------------------------");
    			System.out.println("Id: " + d.getInteger("id"));
    			System.out.println("Name: " + d.getString("name"));
    			System.out.println("Age: " + d.getInteger("age"));
    			System.out.println("Salary: " + d.getInteger("salary"));
    			System.out.println("Designation: " + d.getString("designation"));
    			System.out.println("Department: " + d.getString("department"));
    			System.out.println("------------------------------------------------");
    		}
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

public class EmpManageApp {
    public static void main(String[] args) throws SQLException {
        int ch1 = 0, ch2 = 0, ch3 = 0, ch4 = 0;
        MainMenu employeeOperations = new MainMenu();
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
                                employeeOperations.storeEmployee(EmpFactory.createEmp("Clerk"));
                                break;
                            case 2:
                                employeeOperations.storeEmployee(EmpFactory.createEmp("Programmer"));
                                break;
                            case 3:
                                employeeOperations.storeEmployee(EmpFactory.createEmp("Manager"));
                                break;
                            case 4:
                                employeeOperations.storeEmployee(EmpFactory.createEmp("Others"));
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
                            case 1 : 
                            	employeeOperations.displayEmployee("Designation"); 
                            	break;
                            case 2 : 
                            	employeeOperations.displayEmployee("ID");
                            	break;
                            case 3 : 
                            	employeeOperations.displayEmployee("Name");
                            	break;
                            case 4 : 
                            	employeeOperations.displayEmployee("Age");
                            	break;
                            case 5 : 
                            	employeeOperations.displayEmployee("Salary");
                            	break;
                            case 6 : 
                            	employeeOperations.displayEmployee("Department");
                            	break;
                            case 7 : 
                            	ch3 = 7;
                            	break;
                        }
                    } while (ch3 != 7);

                    break;

                case 3:
                    int eid = IdInput.readId();
                    employeeOperations.raiseEmployeeSalary(eid);
                    break;

                case 4:
                    System.out.println("Enter the Employee ID to delete:");
                    int empIdToRemove = IdInput.readId();
                    employeeOperations.deleteEmployee(empIdToRemove);
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
                                employeeOperations.searchEmployee("designation", designation);
                                break;
                            case 2:
                                int searchEid = IdInput.readId();
                                employeeOperations.searchEmployeeBasedOnId(searchEid);
                                break;
                            case 3:
                                String Name = NameInput.readName();
                                employeeOperations.searchEmployee("name", Name);
                                break;
                            case 4:
                                String Department = DepartmentInput.readDepartment();
                                employeeOperations.searchEmployee("department", Department);
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
        
        MongoDatabaseConnection.closeJdbcConnection();
        BufferConn.closeBufferConnection();
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
            MongoCollection<Document> collection = MongoDatabaseConnection.getMongoDatabase();
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

class EIdInput {
    public static int readEId() {
        while (true) {
            System.out.println("Enter your id:");
            MongoCollection<Document> collection = MongoDatabaseConnection.getMongoDatabase();
            try {
                int id = new Scanner(System.in).nextInt();
                Bson filter = Filters.eq("id", id);
        		Document doc = collection.find(filter).first();
        		if (doc != null && !doc.isEmpty()) {
                    throw new InvalidIDException("Id Already Exists....");
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
