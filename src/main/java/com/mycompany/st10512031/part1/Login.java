/*
 * Login.java
 * Part 1: Registration and Login for QuickChat.
 * Author: Student
 * Student Number: ST10512031
 * Date: 2026
 *
 * References:
 *   [1] J. Farrell, Java Programming, 10th ed. Boston, MA, USA:
 *       Cengage Learning, 2023.
 *
 * Code patterns adapted from [1]:
 *   - Class structure and private fields ........ Ch. 4, Sec. 4.3
 *   - Named constants with final ................. Ch. 2, Sec. 2.1
 *   - if...else decisions ........................ Ch. 5, Sec. 5.2
 *   - Relational and equality operators .......... Ch. 2, Sec. 2.3
 *   - for loops .................................. Ch. 6, Sec. 6.4
 *   - char data type and charAt() ................ Ch. 2, Sec. 2.5
 *   - Character class methods .................... Ch. 7, Sec. 7.2
 *   - String methods (contains, length, equals) .. Ch. 7, Sec. 7.4
 *
 * Research-based code:
 *   - Regular expression for cell phone number was researched independently.
 *     Regex pattern: ^\\+27[0-9]{9}$
 *     Source: [Add your source citation here, e.g., Oracle Java
 *             documentation on the Pattern class or a regex tutorial]
 *
 * Note: Unit tests are not covered in [1] and were learned from
 *       the assigned YouTube resources.
 */

package com.mycompany.st10512031.part1;

public class Login {

    // -------- Fields (private per [1], Ch. 4, Sec. 4.3) --------
    private String registeredUsername;
    private String registeredPassword;
    private String userFirstName;
    private String userLastName;

    // -------- Named constants (final, per [1], Ch. 2, Sec. 2.1) --------
    private final int MAX_USERNAME_LENGTH = 5;
    private final int MIN_PASSWORD_LENGTH = 8;

    /*
     * RESEARCH-BASED CONSTANT
     * Regex pattern for a South African cell phone number.
     * Must start with the international code "+27" and be
     * followed by exactly 9 digits.
     *
     * Source: [Add your research source citation here]
     *
     * Escaping the "+" as "\\+" follows the same escape sequence
     * convention taught in [1], Ch. 2, Sec. 2.5.
     */
    private final String CELL_PHONE_PATTERN = "^\\+[0-9]{1,10}$";

    /*
     * Checks if the username contains an underscore and is no more than
     * five characters long.
     *
     * Uses String.contains() and String.length() — [1], Ch. 7, Sec. 7.4.
     * Uses AND operator && and if...else — [1], Ch. 5, Sec. 5.5 and 5.2.
     */
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= MAX_USERNAME_LENGTH;
    }

    /*
     * Checks if the password meets complexity rules:
     * at least 8 characters, contains a capital letter,
     * a number, and a special character.
     *
     * Uses if...else decisions — [1], Ch. 5, Sec. 5.2.
     * Uses for loop iteration — [1], Ch. 6, Sec. 6.4.
     * Uses char data type and String.charAt() — [1], Ch. 2, Sec. 2.5
     *   and Ch. 7, Sec. 7.4.
     * Uses Character class methods — [1], Ch. 7, Sec. 7.2.
     */
    public boolean checkPasswordComplexity(String password) {

        // Check the length first — [1], Ch. 7, Sec. 7.4.
        if (password.length() < MIN_PASSWORD_LENGTH) {
            return false;
        }

        // Boolean flags to track each character type.
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        // Loop through each character — [1], Ch. 6, Sec. 6.4.
        for (int i = 0; i < password.length(); i = i + 1) {

            // Extract each character — [1], Ch. 7, Sec. 7.4.
            char currentChar = password.charAt(i);

            // Character class methods — [1], Ch. 7, Sec. 7.2.
            if (Character.isUpperCase(currentChar)) {
                hasCapital = true;
            }

            if (Character.isDigit(currentChar)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(currentChar)) {
                hasSpecial = true;
            }
        }

        // All three flags must be true — [1], Ch. 5, Sec. 5.5.
        return hasCapital && hasNumber && hasSpecial;
    }

    /*
     * Checks if the cell phone number matches the required format.
     *
     * RESEARCH-BASED METHOD.
     * Uses String.matches() with a regex pattern.
     * The general technique of using String methods is covered in
     * [1], Ch. 7, Sec. 7.4, but regex syntax itself was researched
     * independently.
     */
    public boolean checkCellPhoneNumber(String cellPhone) {
        if (cellPhone.matches(CELL_PHONE_PATTERN)) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Validates all registration details and returns a status message.
     *
     * Uses if...else decisions — [1], Ch. 5, Sec. 5.2.
     * Uses the NOT operator (!) — [1], Ch. 5, Sec. 5.8.
     * Uses String concatenation with + — [1], Ch. 2, Sec. 2.1.
     * Uses the newline escape sequence \n — [1], Ch. 2, Sec. 2.5.
     */
    public String registerUser(String username, String password, String cellPhone,
                               String firstName, String lastName) {

        // Validate username first.
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your "
                    + "username contains an underscore and is no more than five "
                    + "characters in length.";
        }

        // Validate password.
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the "
                    + "password contains at least eight characters, a capital letter, "
                    + "a number, and a special character.";
        }

        // Validate cell phone number.
        if (!checkCellPhoneNumber(cellPhone)) {
            return "Cell phone number incorrectly formatted or does not contain "
                    + "international code.";
        }

        // Store values in fields — [1], Ch. 4, Sec. 4.3.
        registeredUsername = username;
        registeredPassword = password;
        userFirstName = firstName;
        userLastName = lastName;

        // Return success message with concatenation and newlines.
        return """
               Username successfully captured.
               Password successfully captured.
               Cell phone number successfully added.
               User registered successfully.""";
    }

    /*
     * Verifies that the login details match the stored registration details.
     *
     * Uses String.equals() — [1], Ch. 7, Sec. 7.4.
     * Uses if...else and AND operator — [1], Ch. 5, Sec. 5.2 and 5.5.
     */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals(registeredUsername)
                && enteredPassword.equals(registeredPassword);
    }

    /*
     * Returns the correct login message.
     *
     * Uses if...else — [1], Ch. 5, Sec. 5.2.
     * Uses String concatenation with + — [1], Ch. 2, Sec. 2.1.
     */
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + userFirstName + ", " + userLastName
                    + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    /*
     * Accessor method — returns the registered username.
     * Accessor pattern described in [1], Ch. 4, Sec. 4.3.
     */
    public String getRegisteredUsername() {
        return registeredUsername;
    }
}