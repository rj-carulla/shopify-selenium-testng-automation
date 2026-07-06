package tests;

import base.BaseTest;
import components.CartDropdownComponent;
import components.HeaderComponent;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CatalogPage;
import pages.ProductPage;

public class CartDropownTest extends BaseTest {
    @Test
    public void verifyCartEmpty(){
        HeaderComponent header = new HeaderComponent(driver);
        CartDropdownComponent cartDropdown = header.openCart();
        Assert.assertEquals(cartDropdown.getEmptyCartMessage(),"Your cart is empty.");
    }
    @Test
    public void verifyDisplayOneProductInCart(){
        CatalogPage catalogPage = navigateToCatalogPage();
        ProductPage productPage = catalogPage.clickProduct("Grey jacket");
        productPage.addToCart(1);

        HeaderComponent header = new HeaderComponent(driver);
        CartDropdownComponent cartDropdown = header.openCart();
        Assert.assertTrue(cartDropdown.isProductDisplayed("Grey Jacket"));
        Assert.assertEquals(header.getCartCount(), 1);
    }

    @Test
    public void verifyDisplayMultipleProductsInCart(){
        CatalogPage catalogPage = navigateToCatalogPage();
        ProductPage productPage = catalogPage.clickProduct("Grey jacket");
        productPage.addToCart(1);

        productPage.goBack();

        productPage = catalogPage.clickProduct("Bronze sandals");
        productPage.addToCart(2);

        HeaderComponent header = new HeaderComponent(driver);
        CartDropdownComponent cartDropdown = header.openCart();
        Assert.assertTrue(cartDropdown.isProductDisplayed("Grey Jacket"));
        Assert.assertTrue(cartDropdown.isProductDisplayed("Bronze sandals"));
        Assert.assertEquals(header.getCartCount(), 2);
    }

}
