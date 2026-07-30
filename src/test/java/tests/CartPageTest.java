package tests;

import base.BaseTest;
import components.HeaderComponent;
import org.openqa.selenium.bidi.network.Header;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;

import java.sql.Driver;

public class CartPageTest extends BaseTest {

    // Display Tests

    @Test
    public void verifyEmptyCart(){
        HeaderComponent header = new HeaderComponent(driver);
        CartPage cartPage = header.navigateToCartPage();
        Assert.assertEquals(cartPage.getEmptyCartMessage(), "It appears that your cart is currently empty! Continue Shopping.");
    }


    @Test
    public void verifyDisplayOneProduct(){
        addProductsToCart("Grey jacket");

        HeaderComponent header = new HeaderComponent(driver);
        CartPage cartPage = header.navigateToCartPage();
        double price = cartPage.getProductPrice("Grey jacket");
        int quantity = cartPage.getProductQuantity("Grey jacket");
        double total = cartPage.getProductTotal("Grey jacket");

        Assert.assertTrue(cartPage.isProductDisplayed("Grey Jacket"));
        Assert.assertEquals(price, 55.00);
        Assert.assertEquals(quantity, 1);
        Assert.assertEquals(price * quantity, total);
    }

    @Test
    public void verifyDisplayMultipleProducts(){
        addProductsToCart("Grey jacket", "Bronze sandals", "Striped top");

        HeaderComponent header = new HeaderComponent(driver);
        CartPage cartPage = header.navigateToCartPage();

        double price1 = cartPage.getProductPrice("Grey jacket");
        int quantity1 = cartPage.getProductQuantity("Grey jacket");
        double total1 = cartPage.getProductTotal("Grey jacket");

        double price2 = cartPage.getProductPrice("Bronze sandals");
        int quantity2 = cartPage.getProductQuantity("Bronze sandals");
        double total2 = cartPage.getProductTotal("Bronze sandals");

        double price3 = cartPage.getProductPrice("Striped top");
        int quantity3 = cartPage.getProductQuantity("Striped top");
        double total3 = cartPage.getProductTotal("Striped top");

        Assert.assertTrue(cartPage.isProductDisplayed("Grey Jacket"));
        Assert.assertEquals(price1, 55.00);
        Assert.assertEquals(quantity1, 1);
        Assert.assertEquals(price1 * quantity1, total1);

        Assert.assertTrue(cartPage.isProductDisplayed("Bronze sandals"));
        Assert.assertEquals(price2, 39.99);
        Assert.assertEquals(quantity2, 1);
        Assert.assertEquals(price2 * quantity2, total2);


        Assert.assertTrue(cartPage.isProductDisplayed("Striped top"));
        Assert.assertEquals(price3, 50.00);
        Assert.assertEquals(quantity3, 1);
        Assert.assertEquals(price3 * quantity3, total3);

    }

    //Quantity Tests

    @Test
    public void verifyIncreaseProductQuantity(){
        addProductsToCart("Grey jacket");

        HeaderComponent header = new HeaderComponent(driver);
        CartPage cartPage = header.navigateToCartPage();

        cartPage.updateProductQuantity(3, "Grey jacket");

        double price = cartPage.getProductPrice("Grey jacket");
        int quantity = cartPage.getProductQuantity("Grey jacket");
        double total = cartPage.getProductTotal("Grey jacket");

        Assert.assertTrue(cartPage.isProductDisplayed("Grey Jacket"));
        Assert.assertEquals(price, 55);
        Assert.assertEquals(quantity, 3);
        Assert.assertEquals(price * quantity, total);
    }

    @Test
    public void verifyDecreaseProductQuantity(){

        addSameProductToCart("Bronze sandals", 3);

        HeaderComponent header = new HeaderComponent(driver);
        CartPage cartPage = header.navigateToCartPage();

        cartPage.updateProductQuantity(2, "Bronze sandals");

        double price = cartPage.getProductPrice("Bronze sandals");
        int quantity = cartPage.getProductQuantity("Bronze sandals");
        double total = cartPage.getProductTotal("Bronze sandals");

        Assert.assertTrue(cartPage.isProductDisplayed("Bronze sandals"));
        Assert.assertEquals(price, 39.99);
        Assert.assertEquals(quantity, 2);
        Assert.assertEquals(price * quantity, total);
    }

    @Test
    public void verifyUpdateMultipleProductQuantities(){

        addProductsToCart("Grey jacket");
        addSameProductToCart("Bronze sandals", 3);

        HeaderComponent header = new HeaderComponent(driver);
        CartPage cartPage = header.navigateToCartPage();

        cartPage.updateProductQuantity(3, "Grey jacket");
        cartPage.updateProductQuantity(2, "Bronze sandals");

        double price1 = cartPage.getProductPrice("Grey jacket");
        int quantity1 = cartPage.getProductQuantity("Grey jacket");
        double total1 = cartPage.getProductTotal("Grey jacket");

        double price2 = cartPage.getProductPrice("Bronze sandals");
        int quantity2 = cartPage.getProductQuantity("Bronze sandals");
        double total2 = cartPage.getProductTotal("Bronze sandals");

        Assert.assertTrue(cartPage.isProductDisplayed("Grey jacket"));
        Assert.assertEquals(price1, 55);
        Assert.assertEquals(quantity1, 3);
        Assert.assertEquals(price1 * quantity1, total1);

        Assert.assertTrue(cartPage.isProductDisplayed("Bronze sandals"));
        Assert.assertEquals(price2, 39.99);
        Assert.assertEquals(quantity2, 2);
        Assert.assertEquals(price2 * quantity2, total2);
    }
}
