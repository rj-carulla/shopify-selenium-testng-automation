package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    private By greyJacket = By.linkText("Grey Jacket");
    public void clickGreyJacket(){
        driver.findElement(greyJacket).click();
    }

}
