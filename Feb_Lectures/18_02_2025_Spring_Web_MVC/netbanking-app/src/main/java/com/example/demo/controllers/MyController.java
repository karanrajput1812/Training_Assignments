package com.example.demo.controllers;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.database.DatabaseHandler;
import com.example.demo.models.Customer;

@Controller
public class MyController {
	
	DatabaseHandler db = new DatabaseHandler();
	
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
	
	@RequestMapping("/register_emp")
	@ResponseBody
	public String getResponse() {
		return "<h1> Registered successfully </h1>";
	}
	
	@RequestMapping("/signup")
	public ModelAndView storeUser(Customer c) throws SQLException {
	    String signupResult = db.signup(c.cid, c.uname, c.pwd, c.cpwd);
	    ModelAndView mv = new ModelAndView();

	    if (c.pwd.equals(c.cpwd)) {
	    	if (signupResult.equals("Registered Successfully")) {
	    		mv.addObject("error", signupResult);
	    		mv.setViewName("login.jsp");
	    	} else {
	    		mv.addObject("error", signupResult);
	    		mv.setViewName("register.jsp");
	    	}
	    }
	    else {
	    	mv.addObject("error", "Both passwords are not the same!");
	    	mv.setViewName("register.jsp");
	    }

	    return mv;
	}


	
	@RequestMapping("/signin")
	public ModelAndView verifyUser(Customer c) throws SQLException {		
		JdbcRowSet rs = db.signin(c.cid, c.pwd);
		ModelAndView mv = new ModelAndView();
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
