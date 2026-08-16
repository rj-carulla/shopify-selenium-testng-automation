package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CheckoutPage extends BasePage {
    //Locators
    private By productsContainer = By.xpath("//div[@class='_4QenE']//aside");
    private By productTotal = By.xpath(".//span[contains(normalize-space(), '£')]");
    private By countryDropdown = By.xpath("//select[@name='countryCode']");

    private By shippingFeeLeft = By.xpath("//*[normalize-space()='Standard Shipping']" +
            "/ancestor::div[.//strong[normalize-space()='Standard Shipping']][1]" +
            "//*[contains(@id, '-secondary')]//strong");

    private By firstNameField = By.xpath("//input[@name='firstName']");
    private By lastNameField = By.xpath("//input[@name='lastName']");
    private By companyField = By.xpath("//input[@name='company']");
    private By addressField = By.xpath("//input[@id='shipping-address1']");
    private By cityField = By.xpath("//input[@name='city']");
    private By postcodeField = By.xpath("//input[@name='postalCode']");

    //Constructor
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

//    public double getTotal(){
//        WebElement container = driver.findElement(productsContainer);
//        WebElement TotalRow = container.findElement(
//                By.xpath(
//                        ".//*[@role='row'][.//*[@role='rowheader'][contains(normalize-space(), 'Total')]]"
//                )
//        );
//
//        String total = TotalRow
//                .findElement(By.xpath(".//*[@role='cell']//span[contains(normalize-space(), '£')]"))
//                .getText();
//        System.out.println(total);
//        return Double.parseDouble(total.replace("£", ""));
//    }
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
}
