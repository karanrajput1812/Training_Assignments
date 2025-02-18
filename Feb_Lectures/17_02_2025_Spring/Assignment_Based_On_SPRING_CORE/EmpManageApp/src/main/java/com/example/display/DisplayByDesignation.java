package com.example.display;

import java.util.Comparator;

import com.example.model.Emp;

public class DisplayByDesignation implements Comparator<Emp> {
    public int compare(Emp s1, Emp s2) {
        return s1.getDesignation().compareTo(s2.getDesignation());
    }
}