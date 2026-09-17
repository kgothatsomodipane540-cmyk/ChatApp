/*
 * LoginTest.java
 * Unit tests for the Login class.
 * Author: Student
 * Student Number: ST10512031
 */

package com.mycompany.st10512031.part1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    Login login = new Login();

    @Test
    public void testCheckUserName() {
        assertTrue(login.checkUserName("kyl_1"));
        assertFalse(login.checkUserName("kyle!!!!!!"));
        assertFalse(login.checkUserName("kyle_12345"));
    }

    @Test
    public void testCheckPasswordComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&sec@ke99!"));
        assertFalse(login.checkPasswordComplexity("Pass1!"));
        assertFalse(login.checkPasswordComplexity("p@ssword1"));
        assertFalse(login.checkPasswordComplexity("Password!"));
        assertFalse(login.checkPasswordComplexity("Password1"));
    }

    @Test
    public void testCheckCellPhoneNumber() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
        assertFalse(login.checkCellPhoneNumber("08966553"));
        assertFalse(login.checkCellPhoneNumber("+27838968976123"));
    }

    @Test
    public void testRegisterUser() {
        String result = login.registerUser(
                "kyl_1",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(result.contains("successfully"));
    }

    @Test
    public void testRegisterUserInvalidUsername() {
        String result = login.registerUser(
                "kyle!!!!!!",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals(
                "Username is not correctly formatted; please ensure that your "
                + "username contains an underscore and is no more than five "
                + "characters in length.",
                result
        );
    }

    @Test
    public void testRegisterUserInvalidPassword() {
        String result = login.registerUser(
                "kyl_1",
                "password",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals(
                "Password is not correctly formatted; please ensure that the "
                + "password contains at least eight characters, a capital letter, "
                + "a number, and a special character.",
                result
        );
    }

    @Test
    public void testRegisterUserInvalidCellPhone() {
        String result = login.registerUser(
                "kyl_1",
                "Ch&sec@ke99!",
                "08966553",
                "Kyle",
                "Smith"
        );

        assertEquals(
                "Cell phone number incorrectly formatted or does not contain "
                + "international code.",
                result
        );
    }

    @Test
    public void testLoginUser() {
        login.registerUser(
                "kyl_1",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(login.loginUser("kyl_1", "Ch&sec@ke99!"));
        assertFalse(login.loginUser("kyl_1", "WrongPassword1!"));
        assertFalse(login.loginUser("wrong_user", "Ch&sec@ke99!"));
    }

    @Test
    public void testReturnLoginStatus() {
        login.registerUser(
                "kyl_1",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals(
                "Welcome Kyle, Smith it is great to see you again.",
                login.returnLoginStatus(true)
        );

        assertEquals(
                "Username or password incorrect, please try again.",
                login.returnLoginStatus(false)
        );
    }
}