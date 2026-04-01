package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends BasePage {
    //Locators
    private By signUpButton = By.id("customer_register_link");
    private By firstName = By.xpath("//input[@id='first_name']");
    private By lastName = By.xpath("//input[@id='last_name']");
    private By email = By.xpath("//input[@id='email']");
    private By password = By.xpath("//input[@id='password']");
    private By logoutButton = By.id("customer_logout_link");
    private By createButton = By.xpath("//input[@type='submit']");

    //Constructor
    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    //Actions
    public void goToSignUpPage(){
        driver.findElement(signUpButton).click();
    }

    public void enterFirstName(){
        driver.findElement(firstName).sendKeys("Tom1");
    }

    public void enterLastName(){
        driver.findElement(lastName).sendKeys("Jones1");
    }

    public void enterEmail(){
        driver.findElement(email).sendKeys("TJones@yopmail.com");
    }

    public void enterPassword(){
        driver.findElement(password).sendKeys("Jones@123");
    }
    public void createAccount(){
        driver.findElement(createButton).click();
    }
    public boolean checkLogOutButtonExists(){
        return driver.findElement(logoutButton).isDisplayed();
    }
}
