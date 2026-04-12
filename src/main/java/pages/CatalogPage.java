package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPage extends BasePage {

    //Locators
    private By email = By.xpath("//input[@id='customer_email']");

    //Constructor
    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    //Actions
    public void enterEmail(String testEmail){
        driver.findElement(email).sendKeys(testEmail);
    }

}
