package components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CartPage;

import java.util.Locale;

public class CartDropdownComponent extends BasePage {

    //Locators
    private By emptyCartMessage = By.xpath("//p[@class='empty']");
    private By cartDrawer = By.xpath("//div[@id='drawer']");
    private By checkoutButton = By.xpath("(//input[@value='Check Out'])[1]");

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
        WebElement row = getRow(productName);
        row.findElement(By.className("removeLine")).click();
        waitForDelete(row);
    }

    public CartPage updateQuantity(int quantity, String productName){
        WebElement row = getRow(productName);
        WebElement quantityInput = row.findElement(By.tagName("input"));

        String quantitySTR = Integer.toString(quantity);

        quantityInput.clear();
        quantityInput.sendKeys(quantitySTR);
        quantityInput.sendKeys(Keys.ENTER);


        return new CartPage(driver);
    }

    private String getSlug(String productName){
        return productName.toLowerCase().replace(" ", "-");
    }

    private WebElement getRow(String productName){
        WebElement cart = driver.findElement(cartDrawer);
        By rowInCart = By.xpath(".//div[contains(@class,'row')][.//a[contains(@href,'" + getSlug(productName) + "')]]");
        return cart.findElement(rowInCart);
    }

    public int getProductQuantity(String productName){
        WebElement row = getRow(productName);
        WebElement quantityInput = row.findElement(By.name("updates[]"));
        return Integer.parseInt(quantityInput.getAttribute("value"));
    }

    public double getProductTotal(String productName){
        WebElement row = getRow(productName);
        String productTotalText = row.findElement(By.cssSelector(".total")).getText();
        return Double.parseDouble(productTotalText.replace("£",""));
    }
    public double getProductPrice(String productName){
        WebElement row = getRow(productName);
        String productPriceText = row.findElement(By.cssSelector(".price")).getText();
        return Double.parseDouble(productPriceText.replace("£",""));
    }

    public CartPage goToCheckoutPage(){
        driver.findElement(checkoutButton).click();
        return new CartPage(driver);
    }

    public boolean isCheckoutButtonDisplayed() {
        return !driver.findElements(checkoutButton).isEmpty();
    }
}
