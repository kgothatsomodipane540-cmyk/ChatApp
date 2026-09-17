/*
 * Main.java
 * This is the main entry point for the QuickChat registration and login application.
 * Author: Student
 * Student Number: ST10512031
 */

package com.mycompany.st10512031.part1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Create a Scanner object to read input from the keyboard
        Scanner scanner = new Scanner(System.in);

        // Create a Login object to handle registration and login
        Login loginSystem = new Login();

        System.out.println("========================================");
        System.out.println("   Welcome to QuickChat Registration    ");
        System.out.println("========================================");
        System.out.println();

        // ==================== REGISTRATION ====================

        System.out.println("--- REGISTRATION ---");

        // Prompt for first name
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        // Prompt for last name
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        // Prompt for username
        System.out.print("Enter a username "
                + "(must contain '_' and be no more than 5 characters): ");
        String username = scanner.nextLine();

        // Prompt for password
        System.out.print("Enter a password "
                + "(min 8 chars, 1 capital, 1 number, 1 special char): ");
        String password = scanner.nextLine();

        // Prompt for cell phone number
        System.out.print("Enter your cell phone number "
                + "(e.g., +27831234567): ");
        String cellPhone = scanner.nextLine();

        // Register the user
        String registrationResult = loginSystem.registerUser(
                username,
                password,
                cellPhone,
                firstName,
                lastName
        );

        // Display registration result
        System.out.println();
        System.out.println(registrationResult);
        System.out.println();

        // ==================== LOGIN ====================

        // Only proceed to login if registration was successful
        if (registrationResult.contains("User registered successfully")) {

            System.out.println("--- LOGIN ---");

            // Prompt for username
            System.out.print("Enter your username: ");
            String loginUsername = scanner.nextLine();

            // Prompt for password
            System.out.print("Enter your password: ");
            String loginPassword = scanner.nextLine();

            // Verify login details
            boolean loginSuccess = loginSystem.loginUser(
                    loginUsername,
                    loginPassword
            );

            // Display login status
            String statusMessage = loginSystem.returnLoginStatus(loginSuccess);

            System.out.println();
            System.out.println(statusMessage);

        } else {

            System.out.println("Registration failed. Please try again.");
        }

        // Close the Scanner
        scanner.close();
    }
}