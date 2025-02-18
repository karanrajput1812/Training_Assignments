package com.example.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AgeInput {
    public static int readAge(int minAge, int maxAge) {
        while (true) {
            System.out.println("Enter your age:");
            try {
                int age = new Scanner(System.in).nextInt();
                if (age < minAge || age > maxAge) {
                    throw new InvalidAgeException();
                }
                return age;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number only.");
            } catch (InvalidAgeException e) {
                e.displayMessage(minAge, maxAge);
            }
        }
    }
}

class InvalidAgeException extends RuntimeException {
    public void displayMessage(int minAge, int maxAge) {
        System.out.println("Please enter an age between " + minAge + " and " + maxAge);
    }
}