package components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderComponent extends BasePage {
    //Locators
    private By cartCount = By.xpath("//span[@class='count cart-target']");
    private By cartButton = By.xpath("//a[@class='toggle-drawer cart desktop ']");
    private By cartDrawer = By.xpath("//div[@id='drawer']");

    //Constructor
    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    //Actions
    public int getCartCount(){
        try {
            String cartCountValue = driver.findElement(cartCount).getText();
            cartCountValue = cartCountValue.replace("(", "").replace(")", "").trim();
            return Integer.parseInt(cartCountValue);
        } catch (Exception e) {
            return 0;
        }
    }

    public void waitForCartCount(int expectedCount) {
        String expectedText = String.valueOf(expectedCount);
        waitForText(cartCount, expectedText);
    }


    public CartDropdownComponent openCart(){
        driver.navigate().refresh();
        driver.findElement(cartButton).click();
        waitForElement(cartDrawer);
        return new CartDropdownComponent(driver);
    }

}
