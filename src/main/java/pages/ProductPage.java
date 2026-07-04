package pages;

import base.BasePage;
import components.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    //Components
    private HeaderComponent header;

    //Locators
    private By addToCartButton = By.id("add");

    //Constructor
    public ProductPage(WebDriver driver) {
        super(driver);
        header = new HeaderComponent(driver);
    }

    //Actions
    public void addToCart(int expectedCount){
        driver.findElement(addToCartButton).click();
        String expectedText = String.valueOf(expectedCount);
        header.waitForCartCount(expectedCount);
    }
}
