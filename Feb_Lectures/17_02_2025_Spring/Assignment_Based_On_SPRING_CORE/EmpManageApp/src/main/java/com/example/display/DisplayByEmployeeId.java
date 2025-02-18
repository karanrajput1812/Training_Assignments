package com.example.display;

import java.util.Comparator;

import com.example.model.Emp;

public class DisplayByEmployeeId implements Comparator<Emp> {
    public int compare(Emp s1, Emp s2) {
        return Integer.compare(s1.getEid(), s2.getEid());
    }
}