import java.sql.*;
public class JdbcDemo3 {
    public static void main(String[] args) {
        try {
            // Class.forName("org.postgresql.Driver"); // not required after java 8
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "tiger");
            CallableStatement cstmt = con.prepareCall("CALL dummy_record()");
            cstmt.execute();

            cstmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
