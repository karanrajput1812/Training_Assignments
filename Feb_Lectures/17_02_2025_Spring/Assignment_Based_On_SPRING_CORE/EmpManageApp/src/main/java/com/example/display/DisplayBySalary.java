package com.example.display;

import java.util.Comparator;

import com.example.model.Emp;

public class DisplayBySalary implements Comparator<Emp> {
    public int compare(Emp s1, Emp s2) {
        return Float.compare(s1.getSalary(), s2.getSalary());
    }
}