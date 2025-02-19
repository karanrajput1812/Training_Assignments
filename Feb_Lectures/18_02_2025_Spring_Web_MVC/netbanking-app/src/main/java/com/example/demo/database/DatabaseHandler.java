package com.example.demo.database;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

import org.springframework.web.servlet.ModelAndView;

public class DatabaseHandler {
	
	public JdbcRowSet signin(int cid, String pwd) throws SQLException{
		JdbcRowSet rs = null;
		try {
			rs = RowSetProvider.newFactory().createJdbcRowSet();
            rs.setUrl("jdbc:postgresql://localhost:5432/postgres");
            rs.setUsername("postgres");
            rs.setPassword("tiger");
		} catch (SQLException e) {
			e.printStackTrace();
		}
			rs.setCommand("SELECT * FROM customer WHERE cid = ? And pwd = ?");
            rs.setInt(1, cid);
            rs.setString(2, pwd);
            rs.execute();
		return rs;
	}
	
	
	public String signup(int cid, String uname, String pwd, String cpwd) throws SQLException {
	    JdbcRowSet rsCheck = null;
	    JdbcRowSet rsInsert = null;
	    String result = "";

	    try {
	        rsCheck = RowSetProvider.newFactory().createJdbcRowSet();
	        rsCheck.setUrl("jdbc:postgresql://localhost:5432/postgres");
	        rsCheck.setUsername("postgres");
	        rsCheck.setPassword("tiger");

	        rsCheck.setCommand("SELECT * FROM customer WHERE cid = ?");
	        rsCheck.setInt(1, cid);
	        rsCheck.execute();

	        if (rsCheck.next()) {
	            result = "Customer ID already exists.";
	        } else {
	                rsInsert = RowSetProvider.newFactory().createJdbcRowSet();
	                rsInsert.setUrl("jdbc:postgresql://localhost:5432/postgres");
	                rsInsert.setUsername("postgres");
	                rsInsert.setPassword("tiger");

	                rsInsert.setCommand("SELECT * FROM customer WHERE 1=0");
	                rsInsert.execute();
	                rsInsert.moveToInsertRow();
	                rsInsert.updateInt(1, cid);
	                rsInsert.updateString(2, uname);
	                rsInsert.updateString(3, pwd);
	                rsInsert.insertRow();

	                result = "Registered Successfully";
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        result = "Database error: " + e.getMessage();
	    } finally {
	        if (rsCheck != null) rsCheck.close();
	        if (rsInsert != null) rsInsert.close();
	    }
	    return result;
	}


}
