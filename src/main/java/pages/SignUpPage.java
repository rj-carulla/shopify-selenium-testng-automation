package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends BasePage {
    //Locators
    private By firstName = By.xpath("//input[@id='first_name']");
    private By lastName = By.xpath("//input[@id='last_name']");
    private By email = By.xpath("//input[@id='email']");
    private By password = By.xpath("//input[@id='password']");
    private By logoutButton = By.xpath("//div[@class='seven columns offset-by-one desktop']//a[@id='customer_logout_link']");
    private By createButton = By.xpath("//input[@value='Create']");

    //Constructor
    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    //Actions

    public void enterFirstName(String fName){
        driver.findElement(firstName).sendKeys(fName);
    }

    public void enterLastName(String lName){
        driver.findElement(lastName).sendKeys(lName);
    }

    public void enterEmail(String testEmail){
        driver.findElement(email).sendKeys(testEmail);
    }

    public void enterPassword(String testPassword){
        driver.findElement(password).sendKeys(testPassword);
    }
    public void createAccount(){
        driver.findElement(createButton).click();
    }
    public boolean checkLogOutButtonExists(){
        return driver.findElement(logoutButton).isDisplayed();
    }
}
