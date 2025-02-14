package json_assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

import org.json.JSONObject;

public class Assignment {
	  
    public static void main(String[] args) throws SQLException {
    	int ch2;
        do {
            System.out.println("---------------------------------------------");
            System.out.println("1. Create User");
            System.out.println("2. Read User");
            System.out.println("3. Delete User");
            System.out.println("4. Update User");
            System.out.println("5. Back");
            System.out.println("---------------------------------------------");
            ch2 = new Scanner(System.in).nextInt();
            switch (ch2) {
                case 1:
                	JsonData.insertUser();
                    break;
                case 2:
                	JsonData.readUser();
                    break;
                case 3:
                	JsonData.deleteUser();
                    break;
                case 4:
                	JsonData.updateUser();
                    break;
                case 5:
                    ch2 = 5;
                    break;
            }
        } while (ch2 != 5);
        
        
        JdbcConnection.closeJdbcConnection();
        DatabaseConnection.closeDatabaseConnection();
    }
}
final class DatabaseConnection{
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "tiger";
    
    private static Connection con = null;

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
    	if (con == null) {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return con;
    }
    
    public static void closeDatabaseConnection() throws SQLException {
        con.close();
    }
}

final class JdbcConnection {
    private static JdbcRowSet rs = null;

    private JdbcConnection() {
    }

    public static JdbcRowSet getJdbcConnection() throws SQLException {
        if (rs == null) {
            rs = RowSetProvider.newFactory().createJdbcRowSet();
            rs.setUrl("jdbc:postgresql://localhost:5432/postgres");
            rs.setUsername("postgres");
            rs.setPassword("tiger");
        }
        return rs;
    }

    public static void closeJdbcConnection() throws SQLException {
        rs.close();
    }
}

class JsonData {
    public static void insertUser() throws SQLException {
    	JSONObject jsonInfo = new JSONObject();
    	System.out.println("Enter user name: ");
    	String name = new Scanner(System.in).nextLine();
    	System.out.println("Enter user email: ");
    	String email = new Scanner(System.in).nextLine();
    	System.out.println("Enter user age: ");
    	int age = new Scanner(System.in).nextInt();
    	
        jsonInfo.put("name", name);
        jsonInfo.put("email", email);
        jsonInfo.put("age", age);
        jsonInfo.put("active", false);
        String sql = "INSERT INTO users (info) VALUES (?)";

        PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql);

        pstmt.setObject(1, jsonInfo.toString(), Types.OTHER);
        pstmt.executeUpdate();
        System.out.println("Data inserted successfully.");
    }
    
    public static void readUser() throws SQLException {
    	System.out.println("Enter user name: ");
    	String name = new Scanner(System.in).nextLine();
    	String sql = "Select info From Users where info->>'name' = ?";
    	JdbcRowSet rs = JdbcConnection.getJdbcConnection();
    	
    	rs.setCommand(sql);
    	rs.setString(1, name);
    	rs.execute();
    	
    	while (rs.next()) {
    		String infoString = rs.getString(1);
    		System.out.println(infoString);
            JSONObject info = new JSONObject(infoString);
            System.out.println("------------------------------------------------");
            System.out.println("Name: " + name);
            System.out.println("Email: " + info.getString("email"));
            System.out.println("Age: " + info.getInt("age"));
            System.out.println("Active: " + info.getBoolean("active"));
            System.out.println("------------------------------------------------");
        }
    	
    }
    
    public static void deleteUser() throws SQLException {
    	System.out.println("Enter user name: ");
    	String name = new Scanner(System.in).nextLine();
    	JdbcRowSet rs = JdbcConnection.getJdbcConnection();
    	   	
    	rs.setCommand("select * from Users where info->>'name' = ?");
        rs.setString(1, name);
        rs.execute();
        if (!rs.next()) {
            System.out.println("No User Present with this name");
        } else {
            rs.deleteRow();
            System.out.println("User Deleted Successfully");
        }
    }
    
    public static void updateUser() throws SQLException {
    	System.out.println("Enter user name: ");
    	String name = new Scanner(System.in).nextLine();
    	String updateSql = "UPDATE Users SET info = info || ?::jsonb WHERE info->>'name' = ?";
        PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(updateSql);
        pstmt.setString(1, "{\"active\": true}");
        pstmt.setString(2, name);
        pstmt.executeUpdate();     
        System.out.println("Status updated successfully");
     }       
}
