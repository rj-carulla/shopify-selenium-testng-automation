package base;

import components.HeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pages.*;

public class BaseTest {
    protected WebDriver driver;
    @BeforeMethod
    public void setup(){
//        driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.get("https://sauce-demo.myshopify.com/");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

     protected LoginPage navigateToLoginPage(){
        HomePage homePage = new HomePage(driver);
        homePage.goToLoginPage();
        return new LoginPage(driver);
    };

     protected SignUpPage navigateToSignUpPage(){
        HomePage homePage = new HomePage(driver);
        homePage.goToSignUpPage();
        return new SignUpPage(driver);
    };

    protected CatalogPage navigateToCatalogPage(){
        HomePage homePage = new HomePage(driver);
        homePage.goToCatalogPage();
        return new CatalogPage(driver);
    };

//    protected CartPage addProductsToCart(String... productNames) { CatalogPage catalogPage = navigateToCatalogPage();
//        ProductPage productPage;
//
//        for (int i = 0; i < productNames.length; i++ ){
//            productPage = catalogPage.clickProduct(productNames[i]);
//            productPage.addToCart(i + 1);
//            if (i < productNames.length - 1){
//                productPage.goBack();
//            }
//
//        }
//
//        return new CartPage(driver);
//    }

    protected void addProductsToCart(String... productNames) {
        CatalogPage catalogPage = navigateToCatalogPage();
        ProductPage productPage;

        for (int i = 0; i < productNames.length; i++ ){
            productPage = catalogPage.clickProduct(productNames[i]);
            productPage.addToCart(i + 1);
            if (i < productNames.length - 1){
                productPage.goBack();
            }

        }
    }
}
