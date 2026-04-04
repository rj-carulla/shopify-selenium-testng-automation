package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    //Locators
    private By email = By.xpath("//input[@id='customer_email']");
    private By password = By.xpath("//input[@id='customer_password']");
    private By signInButton = By.xpath("//input[@value='Sign In']");
    private By logoutButton = By.xpath("//div[@class='seven columns offset-by-one desktop']//a[@id='customer_logout_link']");

    //Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Actions
    public void enterEmail(String testEmail){
        driver.findElement(email).sendKeys(testEmail);
    }

    public void enterpassword(String testPassword){
        driver.findElement(password).sendKeys(testPassword);
    }

    public void signIn(){
        driver.findElement(signInButton).click();
    }

    public boolean checkLogOutButtonExists(){
        return driver.findElement(logoutButton).isDisplayed();
    }
}
