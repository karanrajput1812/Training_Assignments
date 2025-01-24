// import java.io.*;

// class Employee
// {
//     private String name;
//     private int age;
//     private int salary;
//     private String designation;
//     public Employee()
//     {
//         readDetails();
//     }

//     public void readDetails() {
//         try {
//         BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

//         System.out.println("Enter Name: ");
//         name = br.readLine();
//         System.out.println("Enter Age: ");
//         age = Integer.parseInt(br.readLine());
//         System.out.println("Enter Salary: ");
//         salary = Integer.parseInt(br.readLine());
//         System.out.println("Enter Designation: ");
//         designation = br.readLine();
//         br.close();
//         }
//         catch (Exception e)
//         {
//             System.out.println(e);
//         }
//     }
//     public void writeToFile()
//     {
//         PrintWriter pw;
//         try {
//             pw = new PrintWriter(new FileOutputStream("employees.csv",true));
//             pw.println(name+","+age+","+","+salary+","+designation);
//             pw.flush();
//             pw.close();
//         } catch (Exception e) {
//             System.out.println(e);
//         }
//     }
//     public static void display() throws Exception
//     {
//         BufferedReader fr = new BufferedReader(new FileReader("employees.csv"));
//         String line;
//         while()
//     }
// }

// public class ReadWriteFile {
//     public static void main(String[] args) throws IOException {    
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         int ch = 0;
//         do
//         {
//             System.out.println("--------------------------");
//             System.out.println("1. Create ");
//             System.out.println("2. Display ");
//             System.out.println("3. Exit ");
//             System.out.println("--------------------------");
//             System.out.println("Enter the choice");
//             ch = Integer.parseInt(br.readLine());
//             switch (ch) {
//                 case 1 -> new Employee().writeToFile();
//                 case 2 -> Employee.display();

//             }
//         }while (ch!=3);
//     }
// }


class Person {
    int age;
    String name;
}
