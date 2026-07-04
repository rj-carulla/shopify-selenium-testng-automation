package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPage extends BasePage {

    //Locators

    //Constructor
    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    //Actions
    public void clickProduct(String productName){
        By product = By.xpath("//h3[normalize-space()='" + productName + "']");
        waitForElement(product);
        driver.findElement(product).click();

    }

}
