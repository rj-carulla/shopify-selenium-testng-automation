package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageTest extends BaseTest {
    @Test
    public void verifyHomePageTitle(){
        HomePage homePage = new HomePage(driver);
        String title = homePage.getPageTitle();
        Assert.assertEquals(title, "Sauce Demo");
    }
    @Test
    public void verifyProductPageTitle(){
        HomePage homePage = new HomePage(driver);
        homePage.clickGreyJacket();
        String titleP = homePage.getPageTitle();
        Assert.assertEquals(titleP, "Grey jacket – Sauce Demo");
    }
}
