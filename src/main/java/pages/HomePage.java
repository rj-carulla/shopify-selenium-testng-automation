package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    //Locators
    private By greyJacket = By.xpath("//h3[normalize-space()='Grey jacket']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickGreyJacket(){
        driver.findElement(greyJacket).click();
    }

}
