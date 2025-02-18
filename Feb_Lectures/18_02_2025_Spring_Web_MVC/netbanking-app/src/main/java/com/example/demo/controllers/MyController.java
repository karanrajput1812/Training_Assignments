package com.example.demo.controllers;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
	

	@RequestMapping("/")
	public String getFirstPage()
	{
		return "home.html";
	}
	
	@RequestMapping("/netbanking_home")
	public String getNetBankingHome() {
		return "netbanking.html";
	}
	
	@RequestMapping("/login")
	public String getLogin() {
		return "login.jsp";
	}
	
	@RequestMapping("/register")
	public String getRegister() {
		return "register.jsp";
	}
	
	@RequestMapping("/signup")
	public ModelAndView storeUser(int cid, String uname, String pwd, String cpwd) throws SQLException {

		ModelAndView mv = new ModelAndView();
		JdbcRowSet rs = null;
		try {
			rs = RowSetProvider.newFactory().createJdbcRowSet();
            rs.setUrl("jdbc:postgresql://localhost:5432/postgres");
            rs.setUsername("postgres");
            rs.setPassword("tiger");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(pwd.equals(cpwd)) {
			rs.setCommand("SELECT * FROM customer WHERE 1=0");
            rs.execute();
            rs.moveToInsertRow();
            rs.updateInt(1, cid);
            rs.updateString(2, uname);
            rs.updateString(3, pwd);
            rs.insertRow();
            if(!rs.rowInserted())
            	  System.out.println("Data inserted successfully.");
            else
            	  System.out.println("Data insert failed");
            mv.addObject("error", "Registered Successfully");
        	mv.setViewName("login.jsp"); 
		}
		else {
			mv.addObject("error", "Both passwords are not same!!!");
			mv.setViewName("register.jsp"); 
		}
		return mv;
	}
	@RequestMapping("/signin")
	public ModelAndView verifyUser(int cid, String pwd) throws SQLException {
		JdbcRowSet rs = null;
		try {
			rs = RowSetProvider.newFactory().createJdbcRowSet();
            rs.setUrl("jdbc:postgresql://localhost:5432/postgres");
            rs.setUsername("postgres");
            rs.setPassword("tiger");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView();
			rs.setCommand("SELECT * FROM customer WHERE cid = ? And pwd = ?");
            rs.setInt(1, cid);
            rs.setString(2, pwd);
            rs.execute();
            if (!rs.next()) {
            	mv.addObject("error", "Wrong CID And Password");
    			mv.setViewName("login.jsp"); 
            }
            else {
            	mv.addObject("uname",rs.getString("uname"));
            	mv.setViewName("welcome.jsp"); 
            }
		return mv;
	}
}
