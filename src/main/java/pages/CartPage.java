package pages;

import base.BasePage;
import components.CartDropdownComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {

    //Locators
    private By cartHeading = By.xpath("//h1[normalize-space()='My Cart']");
    private By cartContainer = By.xpath("//section[@id='cart']");
    private By eMessage = By.xpath("//div[@id='main']//p[1]");
    private By removeButton = By.linkText("x");

    //Constructor
    public CartPage(WebDriver driver) {
        super(driver);
        waitForElement(cartHeading);
    }

    //Actions
    public String getEmptyCartMessage(){
        WebElement cart = driver.findElement(cartContainer);
        return cart.findElement(eMessage).getText();
    }

    public boolean isProductDisplayed(String productName) {
        By product = By.xpath(".//a[contains(@href,'" + getSlug(productName) + "')]");
        WebElement cart = driver.findElement(cartContainer);
        return !cart.findElements(product).isEmpty();
    }

    private String getSlug(String productName){
        return productName.toLowerCase().replace(" ", "-");
    }

    public double getProductPrice(String productName){
        WebElement row = getRow(productName);
        String productPriceText = row.findElement(By.cssSelector(".price")).getText();
        return Double.parseDouble(productPriceText.replace("£", ""));
    }

    public double getProductTotal(String productName){
        WebElement row = getRow(productName);
        String productTotalText = row.findElement(By.cssSelector(".total")).getText();
        return Double.parseDouble(productTotalText.replace("£", ""));
    }


    public int getProductQuantity(String productName){
        WebElement row = getRow(productName);
        WebElement productQuantityInput = row.findElement(By.name("updates[]"));
        return Integer.parseInt(productQuantityInput.getAttribute("value"));
    }

    public void updateProductQuantity(int quantity, String productName){
        WebElement row = getRow(productName);
        WebElement productQuantityInput = row.findElement(By.name("updates[]"));

        String quantityText = Integer.toString(quantity);

        productQuantityInput.clear();
        productQuantityInput.sendKeys(quantityText);
        productQuantityInput.sendKeys(Keys.ENTER);

        double expectedTotal = getProductPrice(productName) * quantity;

        waitForProductTotalUpdate(productName, expectedTotal);
        //added this wait cloudflare
        waitForProductQuantityUpdate(productName, quantity);

    }

    public void removeProduct(String productName){
        WebElement row = getRow(productName);
        row.findElement(removeButton).click();
        waitForDelete(row);
    }

    protected void waitForProductTotalUpdate(String productName, double expectedTotal) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver ->
                Double.compare(getProductTotal(productName), expectedTotal) == 0
        );
    }

    protected void waitForProductQuantityUpdate(String productName, int quantity) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver ->
                getProductQuantity(productName) == quantity
        );
    }

    public WebElement getRow(String productName){
        WebElement cart = driver.findElement(cartContainer);
        By rowInCart = By.xpath(".//div[contains(@class,'row')][.//a[contains(@href,'" + getSlug(productName) + "')]]");
        return cart.findElement(rowInCart);

    }
}

