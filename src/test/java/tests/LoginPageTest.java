package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginPageTest extends BaseTest {
    @Test
    public void verifyLoginPage(){
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.goToLoginPage();
        String title = loginPage.getPageTitle();
        Assert.assertEquals(title, "Account – Sauce Demo");
    }

    //Can't run test due to hCaptcha

    @Test
    public void ValidSignIn(){
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.goToLoginPage();
        loginPage.enterEmail("TestMine@yopmail.com");
        loginPage.enterpassword("Test@123");
        loginPage.signIn();
        boolean logOutButtonExists = loginPage.checkLogOutButtonExists();
        Assert.assertTrue(logOutButtonExists);
    }

    @Test
    public void InvalidSignIn(){
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.goToLoginPage();
        loginPage.enterEmail("Test");
        loginPage.enterpassword("Test123");
        loginPage.signIn();
        boolean logOutButtonExists = loginPage.checkLogOutButtonExists();
        Assert.assertFalse(logOutButtonExists);
    }
}
