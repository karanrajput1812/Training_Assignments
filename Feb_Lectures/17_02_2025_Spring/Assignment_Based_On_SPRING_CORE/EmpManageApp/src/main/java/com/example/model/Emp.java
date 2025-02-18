package com.example.model;

import java.io.Serializable;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import com.example.demo.AgeInput;
import com.example.demo.EmpManageAppApplication;
import com.example.demo.NameInput;


public abstract class Emp implements Serializable {
	
    protected String name;
    public int age;
    private float salary;
    protected int eid;
    protected String designation;
    @Autowired
    protected Address address;

    public static int countEmp = 0;
    protected static int nextEid = 1;

    Emp(float salary, String designation, Address address) {
        this.name = NameInput.readName();
        this.age = AgeInput.readAge(20, 60);
        this.setSalary(salary);
        this.designation = designation;
        this.address = address;
        countEmp++;
    }

    Emp(int id, String name, int age, float salary, String designation) {
        this.eid = id;
        this.name = name;
        this.age = age;
        this.setSalary(salary);
        this.designation = designation;
        countEmp++;
    }

    public final void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + getSalary());
        System.out.println("Designation: " + designation);
        System.out.println(address);
        System.out.println();
    }

    public int getEid() {
        return eid;
    }

    public abstract void raiseSalary();

    public String getDesignation() {
        return designation;
    }

    public String getName() {
        return name;
    }

    public static int getCountEmp() {
        return countEmp;
    }

    public static void decrementCountEmp() {
        countEmp--;
    }

    public String toCSV() {
        return name + "," + age + "," + getSalary() + "," + designation;
    }

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

}
