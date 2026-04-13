package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.CatalogPage;

public class AddToCartTest extends BaseTest {
    @Test
    public void addSingleProductToCartFromCatalogPage(){
        CatalogPage catalogPage = navigateToCatalogPage();
        catalogPage.clickProduct("Grey jacket");
    }
}
