/*
 * LoginTest.java
 * Unit tests for the Login class.
 * Author: Student
 * Student Number: ST10512031
 * Date: 2026
 */

package com.mycompany.st10512031.part1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    Login login = new Login();

    @Test
    public void testCheckUserName_Valid() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserName_NoUnderscore() {
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }

    @Test
    public void testCheckUserName_TooLong() {
        assertFalse(login.checkUserName("kyle_12345"));
    }

    @Test
    public void testCheckPasswordComplexity_Valid() {
        assertTrue(login.checkPasswordComplexity("Ch&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexity_TooShort() {
        assertFalse(login.checkPasswordComplexity("Pass1!"));
    }

    @Test
    public void testCheckPasswordComplexity_NoCapital() {
        assertFalse(login.checkPasswordComplexity("p@ssword1"));
    }

    @Test
    public void testCheckPasswordComplexity_NoNumber() {
        assertFalse(login.checkPasswordComplexity("Password!"));
    }

    @Test
    public void testCheckPasswordComplexity_NoSpecial() {
        assertFalse(login.checkPasswordComplexity("Password1"));
    }

    @Test
    public void testCheckCellPhoneNumber_Valid() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumber_NoInternationalCode() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testCheckCellPhoneNumber_TooLong() {
        assertFalse(login.checkCellPhoneNumber("+27838968976123"));
    }

    @Test
    public void testLoginUser_Success() {
        login.registerUser(
                "kyl_1",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(login.loginUser("kyl_1", "Ch&sec@ke99!"));
    }

    @Test
    public void testLoginUser_WrongPassword() {
        login.registerUser(
                "kyl_1",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertFalse(login.loginUser("kyl_1", "WrongPassword1!"));
    }

    @Test
    public void testLoginUser_WrongUsername() {
        login.registerUser(
                "kyl_1",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertFalse(login.loginUser("wrong_user", "Ch&sec@ke99!"));
    }

    @Test
    public void testRegisterUser_Success() {
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
    public void testRegisterUser_BadUsername() {
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
    public void testRegisterUser_BadPassword() {
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
    public void testRegisterUser_BadCellPhone() {
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
    public void testReturnLoginStatus_Success() {
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
    }

    @Test
    public void testReturnLoginStatus_Failure() {
        assertEquals(
                "Username or password incorrect, please try again.",
                login.returnLoginStatus(false)
        );
    }

    @Test
    public void testGetRegisteredUsername() {
        login.registerUser(
                "kyl_1",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals("kyl_1", login.getRegisteredUsername());
    }
}