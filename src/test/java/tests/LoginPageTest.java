package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginPageTest extends BaseTest {
    @Test
    public void verifyLoginPage(){
        LoginPage loginPage = navigateToLoginPage();
        String title = loginPage.getPageTitle();
        Assert.assertEquals(title, "Account – Sauce Demo");
    }

    // Disabled: hCaptcha prevents automation in this public environment.
    // In real-world scenarios, CAPTCHA is disabled or bypassed in test environments.

    @Test(enabled = false)
    public void ValidSignIn(){
        LoginPage loginPage = navigateToLoginPage();
        loginPage.enterEmail("TestMine@yopmail.com");
        loginPage.enterpassword("Test@123");
        loginPage.signIn();
        boolean logOutButtonExists = loginPage.checkLogOutButtonExists();
        Assert.assertTrue(logOutButtonExists);
    }

    @Test(enabled = false)
    public void InvalidSignIn(){
        LoginPage loginPage = navigateToLoginPage();
        loginPage.enterEmail("Test");
        loginPage.enterpassword("Test123");
        loginPage.signIn();
        boolean logOutButtonExists = loginPage.checkLogOutButtonExists();
        Assert.assertFalse(logOutButtonExists);
    }
}
