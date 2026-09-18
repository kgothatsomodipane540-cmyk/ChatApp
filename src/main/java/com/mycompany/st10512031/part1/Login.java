package com.mycompany.st10512031.part1;

public class Login {

    private String registeredUsername;
    private String registeredPassword;
    private String registeredCellPhone;
    private String userFirstName;
    private String userLastName;

    private final int MAX_USERNAME_LENGTH = 5;
    private final int MIN_PASSWORD_LENGTH = 8;

    private final String CELL_PHONE_PATTERN = "^\\+27[0-9]{9}$";

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= MAX_USERNAME_LENGTH;
    }

    public boolean checkPasswordComplexity(String password) {

        if (password.length() < MIN_PASSWORD_LENGTH) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {

            char currentChar = password.charAt(i);

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

        return hasCapital && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String cellPhone) {
        return cellPhone.matches(CELL_PHONE_PATTERN);
    }

    public String registerUser(String username, String password, String cellPhone,
                               String firstName, String lastName) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your "
                    + "username contains an underscore and is no more than five "
                    + "characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the "
                    + "password contains at least eight characters, a capital letter, "
                    + "a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellPhone)) {
            return "Cell phone number incorrectly formatted or does not contain "
                    + "international code.";
        }

        registeredUsername = username;
        registeredPassword = password;
        registeredCellPhone = cellPhone;
        userFirstName = firstName;
        userLastName = lastName;

        return """
               Username successfully captured.
               Password successfully captured.
               Cell phone number successfully added.
               User registered successfully.""";
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals(registeredUsername)
                && enteredPassword.equals(registeredPassword);
    }

    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + userFirstName + ", " + userLastName
                    + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    public String getRegisteredUsername() {
        return registeredUsername;
    }

    public String getRegisteredCellPhone() {
        return registeredCellPhone;
    }
}