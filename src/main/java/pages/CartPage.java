package pages;

import base.BasePage;
import components.CartDropdownComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    //Locators
    private By cartHeading = By.xpath("//h1[normalize-space()='My Cart']");

    //Constructor
    public CartPage(WebDriver driver) {
        super(driver);
        waitForElement(cartHeading);
    }

    //Actions

}
