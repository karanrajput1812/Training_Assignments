package com.example.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static int readChoice(int maxChoice) {
        while (true) {
            System.out.println("Enter your choice:");
            try {
                int choice = new Scanner(System.in).nextInt();
                if (choice < 1 || choice > maxChoice) {
                    throw new InvalidChoiceException();
                }
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number only.");
            } catch (InvalidChoiceException e) {
                e.displayMessage(maxChoice);
            }
        }
    }
}


class InvalidChoiceException extends RuntimeException {
    public void displayMessage(int maxChoice) {
        System.out.println("Please enter a choice between 1 and " + maxChoice);
    }
}