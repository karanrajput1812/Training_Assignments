import java.sql.*;
public class ResultSetDemo {

    public static void main(String[] args) {

        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tiger");
            // TYPE_SCROLL_SENSITIVE and CONCUR_UPDATABLE MAKING THE CURSOR MOVABLE AND UPDATABLE
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from employee");
            while (rs.next()) {  // Move the cursor to the next row
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Age: " + rs.getInt(3));
                System.out.println();
            }
            System.out.println("--------------------------------------------");
            
            while(rs.previous()) { // Move the cursor to the previous row
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Age: " + rs.getInt(3));
                System.out.println();
            }
            System.out.println("--------------------------------------------");
            
            rs.absolute(3); // Move the cursor to the 3rd row
            {
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Age: " + rs.getInt(3));
                System.out.println();
            }
            System.out.println("--------------------------------------------");
            
            rs.relative(-2); // Move the cursor to the 2nd row
            {
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Age: " + rs.getInt(3));
                System.out.println();
            }
            System.out.println("--------------------------------------------");
            
            // rs.afterLast(); Move the cursor to the last row
            // rs.isLast(); Check if the cursor is at the last row
            // rs.isBeforeLast(); Check if the cursor is before the last row
            
            rs.last(); // Move the cursor to the last row
            {
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Age: " + rs.getInt(3));
                System.out.println();
            }
            System.out.println("--------------------------------------------");
           
            // rs.beforeFirst(); Move the cursor to the first row
            // rs.isFirst(); Check if the cursor is at the first row
            // rs.isAfterLast(); Check if the cursor is after the last row

            rs.first();  // Move the cursor to the first row
            {
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Age: " + rs.getInt(3));
                System.out.println();
            }
            System.out.println("--------------------------------------------");
            
            rs.close();
            stmt.close();
            con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}


