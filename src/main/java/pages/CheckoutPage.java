package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

import java.security.KeyStore;
import java.util.List;

public class CheckoutPage extends BasePage {
    //Locators
    private By productsContainer = By.xpath("//div[@class='_4QenE']//aside");
    private By productTotal = By.xpath(".//span[contains(normalize-space(), '£')]");
    private By countryDropdown = By.xpath("//select[@name='countryCode']");

    private By shippingFeeLeft = By.xpath("//*[normalize-space()='Standard Shipping']" +
            "/ancestor::div[.//strong[normalize-space()='Standard Shipping']][1]" +
            "//*[contains(@id, '-secondary')]//strong");

    private By infoContainer = By.xpath("//main[@id='checkout-main']");
    private By emailField = By.xpath("//input[@name='email']");
    private By firstNameField = By.xpath("//input[@name='firstName']");
    private By lastNameField = By.xpath("//input[@name='lastName']");
    private By companyField = By.xpath("//input[@name='company']");
    private By addressField = By.xpath("//input[@id='shipping-address1']");
    private By cityField = By.xpath("//input[@name='city']");
    private By postcodeField = By.xpath("//input[@name='postalCode']");

    private By lastNameError = By.xpath("//div[@id='error-for-TextFieldP0-47']");
    private By emailError = By.xpath("//div[@id='error-for-email']");
    private By addressError = By.id("error-for-shipping-address1");
    private By cityError = By.xpath(
        "//*[@id=//input[@name='city' and not(@aria-hidden='true')]/@aria-describedby]"
    );
    private By zipCodeError = By.xpath(
        "//*[@id=//input[@name='postalCode' and not(@aria-hidden='true')]/@aria-describedby]"
    );
    private By cardNumberError = By.xpath("//div[@id='error-for-number']");
    private By cardExpiryError = By.xpath("//div[@id='error-for-expiry']");
    private By cardCVVError = By.xpath("//div[@id='error-for-verification_value']");
    private By cardNameError = By.xpath("//div[@id='error-for-name']");

    private By invalidEmailError = By.xpath("//div[@id='error-for-email' and contains(normalize-space(), 'Enter a valid email')]");

    private By payNowBtn = By.xpath("//button[@id='checkout-pay-button']");
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    //Actions
    public boolean isProductDisplayed(String productName) {
        By product = By.xpath(
                ".//p[normalize-space()='" + productName + "']"
        );
        WebElement container = driver.findElement(productsContainer);
        return !container.findElements(product).isEmpty();
    }

    public double getProductTotal(String productName){
        WebElement row = getRow(productName);
        String total = row.findElement(productTotal).getText();
        return Double.parseDouble(total.replace("£", ""));
    }

    public double getSubtotal(){
        WebElement container = driver.findElement(productsContainer);
        WebElement subtotalRow = container.findElement(
                By.xpath(
                        ".//*[@role='row'][.//*[@role='rowheader'][contains(normalize-space(), 'Subtotal')]]"
                )
        );

        String subtotal = subtotalRow
                .findElement(By.xpath(".//*[@role='cell']//span[contains(normalize-space(), '£')]"))
                .getText();
        return Double.parseDouble(subtotal.replace("£", ""));
    }

    public double getShippingFeeRight(String expectedShipping) {
        WebElement container = driver.findElement(productsContainer);

        WebElement shippingRow = container.findElement(
                By.xpath(
                        ".//*[@role='row'][.//*[@role='rowheader'][contains(normalize-space(), 'Shipping')]]"
                )
        );

        waitForText(shippingRow, expectedShipping);

        WebElement shippingElement = shippingRow.findElement(
            By.xpath(".//*[@role='cell']/span[last()]")
        );

        String shipping = shippingElement.getText();

        return Double.parseDouble(shipping.replace("£", "").trim());
    }

    public double getShippingFeeLeft(){
        String shippingfee = driver.findElement(shippingFeeLeft).getText();
        return Double.parseDouble(shippingfee.replace("£", ""));
    }

    public double getTotal() {
        WebElement container = driver.findElement(productsContainer);

        WebElement totalRow = container.findElement(
                By.xpath(
                        ".//*[@role='row'][.//*[@role='rowheader'][contains(normalize-space(), 'Total')]]"
                )
        );

        WebElement totalElement = totalRow.findElement(
                By.xpath(".//*[@role='cell']//strong[last()]")
        );

        String total = totalElement.getText();

        return Double.parseDouble(total.replace("£", "").trim());
    }
    public WebElement getRow(String productName) {
        WebElement container = driver.findElement(productsContainer);
        return container.findElement(
                By.xpath(
                        ".//div[@role='row'][.//p[normalize-space()='"
                                + productName + "']]"
                )
        );
    }

    public boolean isFieldErrorDisplayed(By errorLocator){
        WebElement container = driver.findElement(infoContainer);
        return !container.findElements(errorLocator).isEmpty();
    }

    public boolean isEmailErrorDisplayed(){
        return isFieldErrorDisplayed(emailError);
    }

    public boolean isLastNameErrorDisplayed(){
        return isFieldErrorDisplayed(lastNameError);
    }

    public boolean isAddressErrorDisplayed(){
        return isFieldErrorDisplayed(addressError);
    }

    public boolean isCityErrorDisplayed(){
        return isFieldErrorDisplayed(cityError);
    }

    public boolean isZipCodeErrorDisplayed(){
        return isFieldErrorDisplayed(zipCodeError);
    }

    public boolean isCardNumberErrorDisplayed(){
        return isFieldErrorDisplayed(cardNumberError);
    }

    public boolean isCardExpiryErrorDisplayed(){
        return isFieldErrorDisplayed(cardExpiryError);
    }

    public boolean isCardCVVErrorDisplayed(){
        return isFieldErrorDisplayed(cardCVVError);
    }

    public boolean isCardNameErrorDisplayed(){
        return isFieldErrorDisplayed(cardNameError);
    }

    public boolean isInvalidEmailErrorDisplayed(){
        return isFieldErrorDisplayed(invalidEmailError);
    }

    public void placeOrder() {
        WebElement payNow = driver.findElement(payNowBtn);
        waitForElementClick(payNowBtn);
        payNow.click();
        waitForElementClick(payNowBtn);
    }

    // Checkout information helper methods

    public void selectCountry(String country){
        WebElement dropdown = driver.findElement(countryDropdown);
        Select select = new Select(dropdown);
       select.selectByVisibleText(country);
    }

    public void enterFirstName(String firstName){
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterCompany(String company){
        driver.findElement(companyField).sendKeys(company);
    }

    public void enterAddress(String address){
        driver.findElement(addressField).sendKeys(address);
    }

    public void enterCity(String city){
        driver.findElement(cityField).sendKeys(city);
    }

    public void enterPostalCode(String postcode){
        driver.findElement(postcodeField).sendKeys(postcode);
    }

    public void enterInvalidEmail(String invalidEmail){
        driver.findElement(emailField).sendKeys(invalidEmail);
    }
}
