package com.example.demo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.example.model.Emp;

public class SearchEmployee {

    public static void ByDesignation(HashMap<Integer, Emp> emp) {
        System.out.println("Enter the Designation to Search:");
        String designationToSearch = new Scanner(System.in).next();
        boolean found = false;
        Set s = emp.entrySet();
        Iterator i1 = s.iterator();
        while (i1.hasNext()) {
            Map.Entry me = (Map.Entry) i1.next();
            if (designationToSearch.equals(((Emp) me.getValue()).getDesignation())) {
                ((Emp) me.getValue()).display();
                found = true;
            }
        }
        if (!found) {
            System.out.println(designationToSearch + "not found.");
        }
    }

    public static void ById(HashMap<Integer, Emp> emp) {
        System.out.println("Enter the Employee ID to Search:");
        int empIdToSearch = new Scanner(System.in).nextInt();
        boolean found = false;
        Set s = emp.entrySet();
        Iterator i1 = s.iterator();
        while (i1.hasNext()) {
            Map.Entry me = (Map.Entry) i1.next();
            if (empIdToSearch == (Integer) me.getKey()) {
                ((Emp) me.getValue()).display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Employee ID not found.");
        }
    }

    public static void ByName(HashMap<Integer, Emp> emp) {
        System.out.println("Enter the Name to Search:");
        String nameToSearch = new Scanner(System.in).next();
        boolean found = false;

        Set s = emp.entrySet();
        Iterator i1 = s.iterator();
        while (i1.hasNext()) {
            Map.Entry me = (Map.Entry) i1.next();
            if (nameToSearch.equals(((Emp) me.getValue()).getName())) {
                ((Emp) me.getValue()).display();
                found = true;
            }
        }
        if (!found) {
            System.out.println(nameToSearch + " not found.");
        }
    }
}

