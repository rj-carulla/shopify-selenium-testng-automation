package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage extends BasePage {
    //Locators
    private By confirmationMessage = By.xpath("//h2[contains(text(),'Your order is confirmed')]");

    //Constructor
    public ConfirmationPage(WebDriver driver){
        super(driver);
        waitForElement(confirmationMessage);
    }

    //Actions
    public boolean isConfirmationMessageDisplayed(){
        return !driver.findElements(confirmationMessage).isEmpty();
    }
}
