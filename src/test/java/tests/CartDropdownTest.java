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
  public void verifyCartEmpty() {
    HeaderComponent header = new HeaderComponent(driver);
    CartDropdownComponent cartDropdown = header.openCart();
    Assert.assertEquals(cartDropdown.getEmptyCartMessage(), "Your cart is empty.");
    Assert.assertFalse(cartDropdown.isCheckoutButtonDisplayed());
  }

  @Test
  public void verifyDisplayOneProductInCart() {
    CatalogPage catalogPage = navigateToCatalogPage();
    ProductPage productPage = catalogPage.clickProduct("Grey jacket");
    productPage.addToCart(1);

    HeaderComponent header = new HeaderComponent(driver);
    CartDropdownComponent cartDropdown = header.openCart();
    Assert.assertTrue(cartDropdown.isProductDisplayed("Grey Jacket"));
    Assert.assertEquals(header.getCartCount(), 1);
  }

  @Test
  public void verifyDisplayMultipleProductsInCart() {
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

  @Test
  public void verifyRemoveProduct() {
    CatalogPage catalogPage = navigateToCatalogPage();
    ProductPage productPage = catalogPage.clickProduct("Grey jacket");
    productPage.addToCart(1);

    HeaderComponent header = new HeaderComponent(driver);
    CartDropdownComponent cartDropdown = header.openCart();

    cartDropdown.removeProduct("Grey jacket");

    Assert.assertEquals(cartDropdown.getEmptyCartMessage(), "Your cart is empty.");
    Assert.assertEquals(header.getCartCount(), 0);
  }

  @Test
  public void verifyRemoveOnlySelectedProduct() {
    CatalogPage catalogPage = navigateToCatalogPage();
    ProductPage productPage = catalogPage.clickProduct("Grey jacket");
    productPage.addToCart(1);

    productPage.goBack();

    productPage = catalogPage.clickProduct("Bronze sandals");
    productPage.addToCart(2);

    productPage.goBack();

    productPage = catalogPage.clickProduct("Striped top");
    productPage.addToCart(3);

    HeaderComponent header = new HeaderComponent(driver);
    CartDropdownComponent cartDropdown = header.openCart();

    cartDropdown.removeProduct("Grey jacket");

    Assert.assertFalse(cartDropdown.isProductDisplayed("Grey jacket"));
    Assert.assertEquals(header.getCartCount(), 2);
  }

  @Test
  public void verifyEditProductQuantity() {
    CatalogPage catalogPage = navigateToCatalogPage();
    ProductPage productPage = catalogPage.clickProduct("Grey jacket");
    productPage.addToCart(1);

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
    ProductPage productPage = catalogPage.clickProduct("Grey jacket");
    productPage.addToCart(1);

    HeaderComponent header = new HeaderComponent(driver);
    CartDropdownComponent cartDropdown = header.openCart();
    CartPage cartPage = cartDropdown.goToCheckoutPage();

    Assert.assertEquals(cartPage.getPageTitle(), "Your Shopping Cart – Sauce Demo");
  }

}
