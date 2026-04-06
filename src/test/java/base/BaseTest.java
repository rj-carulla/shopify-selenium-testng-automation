package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pages.HomePage;
import pages.LoginPage;
import pages.SignUpPage;

public class BaseTest {
    protected WebDriver driver;
    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.get("https://sauce-demo.myshopify.com/");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    public LoginPage navigateToLoginPage(){
        HomePage homePage = new HomePage(driver);
        homePage.goToLoginPage();
        return new LoginPage(driver);
    };

    public SignUpPage navigateToSignUpPage(){
        HomePage homePage = new HomePage(driver);
        homePage.goToSignUpPage();
        return new SignUpPage(driver);
    };
}
