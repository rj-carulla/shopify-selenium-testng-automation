package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignUpPage;

public class SignUpPageTest extends BaseTest {
    @Test
    public void verifySignUpPage(){
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.goToSignUpPage();
        String title = signUpPage.getPageTitle();
        Assert.assertEquals(title, "Create Account – Sauce Demo");
    }

    @Test
    public void successfulSignUp() throws InterruptedException {
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.goToSignUpPage();
        signUpPage.enterFirstName();
        signUpPage.enterLastName();
        signUpPage.enterEmail();
        signUpPage.enterPassword();
        signUpPage.createAccount();
        boolean logOutButtonExists = signUpPage.checkLogOutButtonExists();
        Assert.assertTrue(logOutButtonExists);
//        Thread.sleep(1000);
    }
}
