package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignUpPage;

public class SignUpPageTest extends BaseTest {
    @Test
    public void verifySignUpPage(){
        SignUpPage signUpPage = navigateToSignUpPage();
        String title = signUpPage.getPageTitle();
        Assert.assertEquals(title, "Create Account – Sauce Demo");
    }

    // Disabled: hCaptcha prevents automation in this public environment.
    // In real-world scenarios, CAPTCHA is disabled or bypassed in test environments.

    @Test(enabled = false)
    public void ValidSignUp(){
        SignUpPage signUpPage = navigateToSignUpPage();
        String email = "test" + System.currentTimeMillis() + "@yopmail.com";
        signUpPage.enterFirstName("Tom");
        signUpPage.enterLastName("Jones");
        signUpPage.enterEmail(email);
        signUpPage.enterPassword("Jones@123");
        signUpPage.createAccount();
        boolean logOutButtonExists = signUpPage.checkLogOutButtonExists();
        Assert.assertTrue(logOutButtonExists);
    }

    @Test(enabled = false)
    public void InvalidSignUp(){
        SignUpPage signUpPage = navigateToSignUpPage();
        String email = "test" + System.currentTimeMillis() + "@fakeemail.com";
        signUpPage.enterFirstName("Tom");
        signUpPage.enterLastName("Jones");
        signUpPage.enterEmail(email);
        signUpPage.enterPassword("w");
        signUpPage.createAccount();
        boolean logOutButtonExists = signUpPage.checkLogOutButtonExists();
        Assert.assertFalse(logOutButtonExists);
    }
}
