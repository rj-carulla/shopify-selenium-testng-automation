package tests;

import base.BaseTest;
import components.HeaderComponent;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CatalogPage;
import pages.ProductPage;

public class AddToCartTest extends BaseTest {
    @Test
    public void addSingleProductToCartFromCatalogPage() {
        CatalogPage catalogPage = navigateToCatalogPage();
        catalogPage.clickProduct("Grey jacket");
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();
        HeaderComponent headerComponent = new HeaderComponent(driver);
        int count = headerComponent.getCartCount();
        Assert.assertEquals(count, 1);
    }
}
