/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.st10512031.part1;

/**
 *
 * @author Student
 */
public class Login {

    // ==================== FIELDS ====================

    // These store the registered user's details
    private String registeredUsername;
    private String registeredPassword;
    private String registeredCellPhone;
    private String userFirstName;
    private String userLastName;

    // Regex for a South African international cell phone number
    // Starts with +27, followed by 9 digits
    // Example: +27821234567
    private final String CELL_PHONE_REGEX = "^\\+27[0-9]{9}$";

    // ==================== METHODS ====================

    /**
     * Checks if the username contains an underscore
     * and is no more than 5 characters long.
     *
     * @param username The username to validate
     * @return true if valid, false otherwise
     */
    public boolean checkUserName(String username) {

        if (username.contains("_") && username.length() <= 5) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the password meets all complexity requirements:
     * - At least 8 characters long
     * - Contains at least one capital letter
     * - Contains at least one number
     * - Contains at least one special character
     *
     * @param password The password to validate
     * @return true if valid, false otherwise
     */
    public boolean checkPasswordComplexity(String password) {

        if (password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {

            char currentChar = password.charAt(i);

            // Check if the character is an uppercase letter
            if (Character.isUpperCase(currentChar)) {
                hasCapital = true;
            }

            // Check if the character is a number
            else if (Character.isDigit(currentChar)) {
                hasNumber = true;
            }

            // Check if the character is a special character
            else if (!Character.isLetterOrDigit(currentChar)) {
                hasSpecial = true;
            }
        }

        if (hasCapital && hasNumber && hasSpecial) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the cell phone number is correctly formatted.
     *
     * @param cellPhone The cell phone number to validate
     * @return true if valid, false otherwise
     */
    public boolean checkCellPhoneNumber(String cellPhone) {

        if (cellPhone.matches(CELL_PHONE_REGEX)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Registers the user by validating all fields
     * and storing the details.
     *
     * @param username The user's chosen username
     * @param password The user's chosen password
     * @param cellPhone The user's cell phone number
     * @param firstName The user's first name
     * @param lastName The user's last name
     * @return A message indicating success or failure
     */
    public String registerUser(String username, String password,
                               String cellPhone, String firstName,
                               String lastName) {

        // Validate username
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure "
                    + "that your username contains an underscore and is "
                    + "no more than five characters in length.";
        }

        // Validate password
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure "
                    + "that the password contains at least eight characters, "
                    + "a capital letter, a number, and a special character.";
        }

        // Validate cell phone number
        if (!checkCellPhoneNumber(cellPhone)) {
            return "Cell phone number incorrectly formatted or does not "
                    + "contain international code.";
        }

        // Store the registration details
        this.registeredUsername = username;
        this.registeredPassword = password;
        this.registeredCellPhone = cellPhone;
        this.userFirstName = firstName;
        this.userLastName = lastName;

        // Return successful registration message
        return "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell phone number successfully added.\n"
                + "User registered successfully.";
    }

    /**
     * Verifies that the login details match
     * the registered details.
     *
     * @param enteredUsername The username entered during login
     * @param enteredPassword The password entered during login
     * @return true if credentials match, false otherwise
     */
    public boolean loginUser(String enteredUsername,
                             String enteredPassword) {

        // Check if the entered username matches
        // the registered username
        if (!enteredUsername.equals(this.registeredUsername)) {
            return false;
        }

        // Check if the entered password matches
        // the registered password
        if (!enteredPassword.equals(this.registeredPassword)) {
            return false;
        }

        // Both username and password match
        return true;
    }

    /**
     * Returns the appropriate login status message.
     *
     * @param loginSuccess Whether the login was successful
     * @return A message for the user
     */
    public String returnLoginStatus(boolean loginSuccess) {

        // Login was successful
        if (loginSuccess) {
            return "Welcome " + this.userFirstName + ", "
                    + this.userLastName
                    + " it is great to see you again.";
        } else {
            // Login was unsuccessful
            return "Username or password incorrect, please try again.";
        }
    }
}