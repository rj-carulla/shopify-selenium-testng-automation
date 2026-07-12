package components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Locale;

public class CartDropdownComponent extends BasePage {

    //Locators
    private By emptyCartMessage = By.xpath("//p[@class='empty']");

    //Constructor
    public CartDropdownComponent(WebDriver driver) {
        super(driver);
    }

    //Actions

    public String getEmptyCartMessage() {
        waitForElement(emptyCartMessage);
        return driver.findElement(emptyCartMessage).getText();
    }

    public boolean isProductDisplayed(String productName) {
        By product = By.xpath(".//a[contains(@href,'" + getSlug(productName) + "')]");
        By cartContainer = By.xpath("//div[@id='drawer']");
        WebElement cart = driver.findElement(cartContainer);
        return !cart.findElements(product).isEmpty();
    }

    public void removeProduct(String productName){
        By rowInCart = By.xpath("//div[contains(@class,'row')][.//a[contains(@href,'" + getSlug(productName) + "')]]");
        WebElement row = driver.findElement(rowInCart);
        row.findElement(By.className("removeLine")).click();
        waitForDelete(row);
    }

    private String getSlug(String productName){
        return productName.toLowerCase().replace(" ", "-");
    }
}
