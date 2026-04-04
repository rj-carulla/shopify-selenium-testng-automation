package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignUpPage;

public class SignUpPageTest extends BaseTest {
    @Test
    public void verifySignUpPage(){
        HomePage homePage = new HomePage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        homePage.goToSignUpPage();
        String title = signUpPage.getPageTitle();
        Assert.assertEquals(title, "Create Account – Sauce Demo");
    }

    //Can't run test due to hCaptcha

    @Test
    public void successfulSignUp() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        String email = "test" + System.currentTimeMillis() + "@yopmail.com";
        homePage.goToSignUpPage();
        signUpPage.enterFirstName("Tom");
        signUpPage.enterLastName("Jones");
        signUpPage.enterEmail(email);
        signUpPage.enterPassword("Jones@123");
        signUpPage.createAccount();
        Thread.sleep(1000);
        boolean logOutButtonExists = signUpPage.checkLogOutButtonExists();
        Assert.assertTrue(logOutButtonExists);
    }
}
