package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    //Locators
    private By addToCartButton = By.id("add");

    //Constructor
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    //Actions
    public void addToCart(){
        driver.findElement(addToCartButton).click();
    }
}
