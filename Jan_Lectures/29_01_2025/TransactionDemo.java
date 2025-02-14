import java.io.*;
import java.sql.*;

public class TransactionDemo {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "tiger");
            con.setAutoCommit(false);   // with the help of this we can create a transaction
            PreparedStatement pstmt = con.prepareStatement("insert into employee values(?,?)");

            for (int i = 1; i <= 10; i++) {
                System.out.println("Enter name: ");
                String name = br.readLine();
                System.out.println("Enter age: ");
                int age = Integer.parseInt(br.readLine());
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                pstmt.execute();

                if(i==5)
                    con.rollback();
                if(i==10)
                    con.commit();
            }
            pstmt.close();

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
