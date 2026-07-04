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
    public void addToCart(){
        driver.findElement(addToCartButton).click();
        header.waitForCartCount(1);
    }
}
