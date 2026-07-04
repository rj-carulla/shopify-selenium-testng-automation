package tests;

import base.BaseTest;
import components.HeaderComponent;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CatalogPage;
import pages.HomePage;
import pages.ProductPage;

public class AddToCartTest extends BaseTest {
    @Test
    public void addOneToCartFromCatalogPage() {
        CatalogPage catalogPage = navigateToCatalogPage();
        ProductPage productPage = catalogPage.clickProduct("Grey jacket");
        productPage.addToCart(1);

        HeaderComponent headerComponent = new HeaderComponent(driver);
        Assert.assertEquals(headerComponent.getCartCount(), 1);
    }

    @Test
    public void addTwoToCartFromCatalogPage() {
        CatalogPage catalogPage = navigateToCatalogPage();
        ProductPage productPage = catalogPage.clickProduct("Grey jacket");
        productPage.addToCart(1);

        productPage.goBack();

        productPage = catalogPage.clickProduct("Bronze sandals");
        productPage.addToCart(2);

        HeaderComponent headerComponent = new HeaderComponent(driver);
        Assert.assertEquals(headerComponent.getCartCount(), 2);
    }
    @Test
    public void addThreeToCartFromCatalogPage() {
        CatalogPage catalogPage = navigateToCatalogPage();
        ProductPage productPage = catalogPage.clickProduct("Grey jacket");
        productPage.addToCart(1);

        productPage.goBack();

        productPage = catalogPage.clickProduct("Bronze sandals");
        productPage.addToCart(2);

        productPage.goBack();

        productPage = catalogPage.clickProduct("Striped top");
        productPage.addToCart(3);

        HeaderComponent headerComponent = new HeaderComponent(driver);
        Assert.assertEquals(headerComponent.getCartCount(), 3);
    }

    @Test
    public void addOneToCartFromHomePage() {
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = homePage.clickProduct("Grey jacket");
        productPage.addToCart(1);

        HeaderComponent headerComponent = new HeaderComponent(driver);
        Assert.assertEquals(headerComponent.getCartCount(), 1);
    }

    @Test
    public void addSameProductTwiceToCart(){
        CatalogPage catalogPage = navigateToCatalogPage();
        ProductPage productPage = catalogPage.clickProduct("Grey jacket");
        productPage.addToCart(1);
        productPage.addToCart(2);

        HeaderComponent headerComponent = new HeaderComponent(driver);
        Assert.assertEquals(headerComponent.getCartCount(), 2);
    }

}

