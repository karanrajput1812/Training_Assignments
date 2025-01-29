import java.io.*;
import java.sql.*;

public class JdbcDemo2 {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // Class.forName("org.postgresql.Driver"); // not required after java 8
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tiger");
            // PreparedStatement is used for parameterized queries better than Statement
            PreparedStatement pstmt = con.prepareStatement("insert into employee values(?,?)");
            System.out.println("Enter name: ");
            String name = br.readLine();
            System.out.println("Enter age: ");
            int age = Integer.parseInt(br.readLine());
            pstmt.setString(1, name); // 1st ? in query
            pstmt.setInt(2, age); // 2nd ? in query
            pstmt.execute();
            pstmt.close();

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
