import java.sql.*;

public class JdbcDemo4 {
    public static void main(String[] args) {
        try {
            // Class.forName("org.postgresql.Driver"); // not required after java 8
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "tiger");
            Statement stmt = con.createStatement();
            stmt.addBatch("insert into employee values('Aman', 25)");
            stmt.addBatch("insert into employee values('Rohit', 35)");
            stmt.addBatch("insert into employee values('Rahul', 45)");
            stmt.addBatch("insert into employee values('Raj', 55)");
            stmt.addBatch("insert into employee values('Virat', 40)");

            System.out.println("Wait For 20 Seconds to see the whole batch execution");
            Thread.sleep(20000);

            stmt.executeBatch();
            System.out.println("Yes, You can find now all the records");


            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
