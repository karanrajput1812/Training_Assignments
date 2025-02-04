package json_assignment;

import java.sql.*;
import java.util.*;
import javax.sql.rowset.*;

import org.json.JSONObject;

public class Assignment {
	  
    public static void main(String[] args) throws SQLException {
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("name", "Ramesh");
        jsonInfo.put("email", "Ramesh.rajput@gmail.com");
        jsonInfo.put("age", 21);
        jsonInfo.put("active", false);

//        JsonData.insertUser("Ramesh", jsonInfo);
        JsonData.readUser("Ramesh");
//        JsonData.deleteUser("Karan");
        JsonData.updateUser("Ramesh");
        
        
        
        JdbcConnection.closeJdbcConnection();
        DatabaseConnection.closeDatabaseConnection();
    }
}
class DatabaseConnection{
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
    public static void insertUser(String name, JSONObject info) throws SQLException {
        String sql = "INSERT INTO users (name, info) VALUES (?, ?::jsonb)";

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, name);
        pstmt.setString(2, info.toString());
        pstmt.executeUpdate();

        System.out.println("Data inserted successfully.");
    }
    
    public static void readUser(String name) throws SQLException {
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
    
    public static void deleteUser(String name) throws SQLException {
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
    
    public static void updateUser(String name) throws SQLException {
    	String updateSql = "UPDATE Users SET info = info || ?::jsonb WHERE info->>'name' = ?";
        PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(updateSql);
        pstmt.setString(1, "{\"active\": true}");
        pstmt.setString(2, name);
        pstmt.executeUpdate();     
        System.out.println("Status updated successfully");
     }       
}
