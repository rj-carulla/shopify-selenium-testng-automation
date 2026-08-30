package tests;

import base.BaseTest;
import components.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;

import java.util.List;

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

        enterShippingInformation(checkoutPage);

        double shippingFeeLeft = checkoutPage.getShippingFeeLeft();
        double shippingFeeRight = checkoutPage.getShippingFeeRight("£10.00");

        Assert.assertEquals(shippingFeeLeft, shippingFeeRight);
        Assert.assertEquals(shippingFeeRight, 10.0);
    }

    @Test
    public void verifyCheckoutTotal(){
        addProductsToCart("Grey jacket", "Bronze sandals", "Striped top");

        HeaderComponent header = new HeaderComponent(driver);
        CartPage cartPage = header.navigateToCartPage();
        CheckoutPage checkoutPage = cartPage.navigateToCheckout();

        enterShippingInformation(checkoutPage);

        double subtotal = checkoutPage.getSubtotal();
        double shippingFeeRight = checkoutPage.getShippingFeeRight("£10.00");
        double total  = checkoutPage.getTotal();

        Assert.assertEquals(subtotal + shippingFeeRight, total);
    }

    @Test
    public void verifyRequiredFields(){
        addProductsToCart("Grey jacket");

        HeaderComponent header = new HeaderComponent(driver);
        CartPage cartPage = header.navigateToCartPage();
        CheckoutPage checkoutPage = cartPage.navigateToCheckout();

        checkoutPage.selectCountry("United Kingdom");
        checkoutPage.placeOrder();

        Assert.assertTrue(checkoutPage.isEmailErrorDisplayed(), "Email error is not displayed");
        Assert.assertTrue(checkoutPage.isLastNameErrorDisplayed(), "Last name error is not displayed");
        Assert.assertTrue(checkoutPage.isAddressErrorDisplayed(), "Address error is not displayed");
        Assert.assertTrue(checkoutPage.isCityErrorDisplayed(), "City error is not displayed");
        Assert.assertTrue(checkoutPage.isZipCodeErrorDisplayed(), "Zip Code error is not displayed");
        Assert.assertTrue(checkoutPage.isCardNumberErrorDisplayed(), "Card Number error is not displayed");
        Assert.assertTrue(checkoutPage.isCardExpiryErrorDisplayed(), "Card Expiry error is not displayed");
        Assert.assertTrue(checkoutPage.isCardCVVErrorDisplayed(), "Card CVV error is not displayed");
        Assert.assertTrue(checkoutPage.isCardNameErrorDisplayed(), "Card Name error is not displayed");
    }

    @Test
    public void verifyInvalidEmail(){
        addProductsToCart("Grey jacket");

        HeaderComponent header = new HeaderComponent(driver);
        CartPage cartPage = header.navigateToCartPage();
        CheckoutPage checkoutPage = cartPage.navigateToCheckout();

        checkoutPage.selectCountry("United Kingdom");
        checkoutPage.enterInvalidEmail("Test@invalid.");
        checkoutPage.placeOrder();

        Assert.assertTrue(checkoutPage.isInvalidEmailErrorDisplayed(), "Invalid Email error is not displayed");
    }


}
