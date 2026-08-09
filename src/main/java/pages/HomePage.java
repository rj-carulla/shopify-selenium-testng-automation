package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    //Locators
    private By signUpButton = By.id("customer_register_link");
    private By loginButton = By.id("customer_login_link");
    private By CatalogButton = By.linkText("Catalog");

    //Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Actions
    public ProductPage clickProduct(String productName){
        By product = By.xpath("//h3[normalize-space()='" + productName + "']");
        waitForElement(product);
        driver.findElement(product).click();
        return new ProductPage(driver);
    }

    public void goToSignUpPage(){
        driver.findElement(signUpButton).click();
    }

    public void goToLoginPage(){
        driver.findElement(loginButton).click();
    }

    public void goToCatalogPage(){
        driver.findElement(CatalogButton).click();
    }
}
