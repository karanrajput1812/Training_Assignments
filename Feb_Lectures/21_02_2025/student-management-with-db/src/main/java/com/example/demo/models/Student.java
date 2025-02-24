package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    private int univRegNo;
    private int rollNo;
    private String name;
    private int standard;
    private String school;
    private float percentage;

    @ManyToOne
    @JoinColumn(name = "standard", referencedColumnName = "std")
    private ClassTeacher classTeacher;

    public int getUnivRegNo() {
        return univRegNo;
    }

    public void setUnivRegNo(int univRegNo) {
        this.univRegNo = univRegNo;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public ClassTeacher getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(ClassTeacher classTeacher) {
        this.classTeacher = classTeacher;
    }
}