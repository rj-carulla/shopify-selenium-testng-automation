package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.CartPage;

public class CartPageTest extends BaseTest {
    @Test
    public void verifyEmptyCart(){
//        CartPage cartPage = addProductToCart("Grey jacket");
        CartPage cartPage = addProductsToCart("Grey jacket", "Bronze sandals");

    }
}
