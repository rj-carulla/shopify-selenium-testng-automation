package tests;

import base.BaseTest;
import components.HeaderComponent;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;

public class CheckOutTest extends BaseTest {
    @Test
    public void verifyCheckoutPage(){
        addProductsToCart("Grey jacket");

        HeaderComponent header = new HeaderComponent(driver);
        CartPage cartPage = header.navigateToCartPage();
        CheckoutPage checkoutPage = cartPage.navigateToCheckout();

        Assert.assertEquals(checkoutPage.getPageTitle(), "Checkout - Sauce Demo");
    }

    @Test
    public void verifyCheckoutProduct(){
        addProductsToCart("Grey jacket");

        HeaderComponent header = new HeaderComponent(driver);
        CartPage cartPage = header.navigateToCartPage();
        CheckoutPage checkoutPage = cartPage.navigateToCheckout();
        checkoutPage.isProductDisplayed("Grey jacket");

        Assert.assertTrue(checkoutPage.isProductDisplayed("Grey jacket"));
    }

    @Test
    public void verifyCheckoutSubtotal(){
        addProductsToCart("Grey jacket", "Bronze sandals", "Striped top");

        HeaderComponent header = new HeaderComponent(driver);
        CartPage cartPage = header.navigateToCartPage();
        CheckoutPage checkoutPage = cartPage.navigateToCheckout();

        double total1 = checkoutPage.getProductTotal("Bronze sandals");
        double total2 = checkoutPage.getProductTotal("Grey jacket");
        double total3 = checkoutPage.getProductTotal("Striped top");

        double subtotal = total1 + total2 + total3;

        Assert.assertTrue(checkoutPage.isProductDisplayed("Grey jacket"));
        Assert.assertTrue(checkoutPage.isProductDisplayed("Bronze sandals"));
        Assert.assertTrue(checkoutPage.isProductDisplayed("Striped top"));

        Assert.assertEquals(checkoutPage.getSubtotal(), subtotal);
    }

    @Test
    public void verifyShippingFee(){
        addProductsToCart("Grey jacket", "Bronze sandals", "Striped top");

        HeaderComponent header = new HeaderComponent(driver);
        CartPage cartPage = header.navigateToCartPage();
        CheckoutPage checkoutPage = cartPage.navigateToCheckout();

        Assert.assertEquals(checkoutPage.getSubtotal(), subtotal);
    }
}
