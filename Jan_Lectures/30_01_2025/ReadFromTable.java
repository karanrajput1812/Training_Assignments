import java.io.*;
import java.sql.*;

public class ReadFromTable {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter table name: ");
            String tableName = br.readLine();
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tiger");
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from " + tableName);
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.println(rsmd.getColumnName(i) + ": " + rs.getString(i));
                }
                System.out.println();
            }
            rs.close();
            br.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
