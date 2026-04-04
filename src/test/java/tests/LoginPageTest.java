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

    @Test
    public void successfulSignIn(){
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.goToLoginPage();
        loginPage.enterEmail("TestMine@yopmail.com");
        loginPage.enterpassword("Test@123");
        loginPage.signIn();
        boolean logOutButtonExists = loginPage.checkLogOutButtonExists();
        Assert.assertTrue(logOutButtonExists);
    }
}
