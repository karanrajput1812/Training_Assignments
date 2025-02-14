import java.sql.*;
import java.util.*;

public class JdbcDemo {
    public static void main(String[] args) {
        try {
            // Class.forName("org.postgresql.Driver"); // not required after java 8
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "tiger");
            Statement stmt = con.createStatement();
            System.out.println("Enter name: ");
            String name = new Scanner(System.in).next();
            System.out.println("Enter age: ");
            int age = new Scanner(System.in).nextInt();
            stmt.executeUpdate("insert into employee values('" + name + "','" + age + "')");
            ResultSet rs = stmt.executeQuery("select * from employee");
            while (rs.next()) {
                // getString for is available for all the data types
                System.out.println(rs.getString(1) + " " + rs.getInt("age"));
                System.out.println();
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
