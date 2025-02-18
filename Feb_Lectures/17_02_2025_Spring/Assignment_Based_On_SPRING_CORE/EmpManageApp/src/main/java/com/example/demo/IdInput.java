package com.example.demo;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.example.model.Emp;

public class IdInput {
    public static int readId(HashMap<Integer, Emp> emp) {
        while (true) {
            System.out.println("Enter your id:");
            try {
                int id = new Scanner(System.in).nextInt();
                if (emp.containsKey(id)) {
                    throw new InvalidIDException("Id already exists..");
                }
                return id;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number only.");
            } catch (InvalidIDException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

class InvalidIDException extends RuntimeException {
    InvalidIDException(String msg) {
        super(msg);
    }

    public void displayMessage() {
        System.out.println("Please enter a number only");
    }
}
