package tests;

import base.BaseTest;
import components.HeaderComponent;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CatalogPage;
import pages.ProductPage;

public class AddToCartTest extends BaseTest {
    @Test
    public void addOneToCartFromCatalogPage() {
        CatalogPage catalogPage = navigateToCatalogPage();
        catalogPage.clickProduct("Grey jacket");
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart(1);
        HeaderComponent headerComponent = new HeaderComponent(driver);
        int count = headerComponent.getCartCount();
        Assert.assertEquals(count, 1);
    }

    @Test
    public void addTwoToCartFromCatalogPage() {
        CatalogPage catalogPage = navigateToCatalogPage();
        catalogPage.clickProduct("Grey jacket");
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart(1);
        productPage.goBack();
        catalogPage.clickProduct("Bronze sandals");
        productPage.addToCart(2);
        HeaderComponent headerComponent = new HeaderComponent(driver);
        int count = headerComponent.getCartCount();
        Assert.assertEquals(count, 2);
    }
}
