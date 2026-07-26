package tests;

import base.BaseTest;
import components.CartDropdownComponent;
import components.HeaderComponent;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CatalogPage;
import pages.HomePage;
import pages.ProductPage;

public class CartDropdownTest extends BaseTest {
  @Test
  public void verifyEmptyCart() {
    HeaderComponent header = new HeaderComponent(driver);
    CartDropdownComponent cartDropdown = header.openCart();
    Assert.assertEquals(cartDropdown.getEmptyCartMessage(), "Your cart is empty.");
    Assert.assertFalse(cartDropdown.isCheckoutButtonDisplayed());
  }

  @Test
  public void verifyDisplayOneProductInCart() {
    CatalogPage catalogPage = navigateToCatalogPage();
    addProductsToCart("Grey jacket");

    HeaderComponent header = new HeaderComponent(driver);
    CartDropdownComponent cartDropdown = header.openCart();
    Assert.assertTrue(cartDropdown.isProductDisplayed("Grey Jacket"));
    Assert.assertEquals(header.getCartCount(), 1);
  }

  @Test
  public void verifyDisplayMultipleProductsInCart() {
    CatalogPage catalogPage = navigateToCatalogPage();

    addProductsToCart("Grey jacket", "Bronze sandals");

    HeaderComponent header = new HeaderComponent(driver);
    CartDropdownComponent cartDropdown = header.openCart();
    Assert.assertTrue(cartDropdown.isProductDisplayed("Grey jacket"));
    Assert.assertTrue(cartDropdown.isProductDisplayed("Bronze sandals"));
    Assert.assertEquals(header.getCartCount(), 2);
  }

  @Test
  public void verifyRemoveProduct() {
    CatalogPage catalogPage = navigateToCatalogPage();

    addProductsToCart("Grey jacket");

    HeaderComponent header = new HeaderComponent(driver);
    CartDropdownComponent cartDropdown = header.openCart();

    cartDropdown.removeProduct("Grey jacket");

    Assert.assertEquals(cartDropdown.getEmptyCartMessage(), "Your cart is empty.");
    Assert.assertEquals(header.getCartCount(), 0);
  }

  @Test
  public void verifyRemoveOnlySelectedProduct() {
    CatalogPage catalogPage = navigateToCatalogPage();

    addProductsToCart("Grey jacket","Bronze sandals","Striped top"  );

    HeaderComponent header = new HeaderComponent(driver);
    CartDropdownComponent cartDropdown = header.openCart();

    cartDropdown.removeProduct("Grey jacket");

    Assert.assertFalse(cartDropdown.isProductDisplayed("Grey jacket"));
    Assert.assertEquals(header.getCartCount(), 2);
  }

  @Test
  public void verifyEditProductQuantity() {
    CatalogPage catalogPage = navigateToCatalogPage();

    addProductsToCart("Grey jacket");

    HeaderComponent header = new HeaderComponent(driver);
    CartDropdownComponent cartDropdown = header.openCart();

    CartPage cartPage = cartDropdown.updateQuantity(3, "Grey jacket");

    cartDropdown = header.openCart();
    double total = cartDropdown.getProductTotal("Grey jacket");
    int quantity = cartDropdown.getProductQuantity("Grey jacket");
    double price = cartDropdown.getProductPrice("Grey jacket");

    Assert.assertEquals(cartPage.getPageTitle(), "Your Shopping Cart – Sauce Demo");
    Assert.assertEquals(header.getCartCount(), 3);
    Assert.assertEquals(total, quantity * price);
  }

  @Test
  public void verifyCheckoutNavigation(){
    CatalogPage catalogPage = navigateToCatalogPage();

    addProductsToCart("Grey jacket");

    HeaderComponent header = new HeaderComponent(driver);
    CartDropdownComponent cartDropdown = header.openCart();
    CartPage cartPage = cartDropdown.goToCheckoutPage();

    Assert.assertEquals(cartPage.getPageTitle(), "Your Shopping Cart – Sauce Demo");
  }

}
