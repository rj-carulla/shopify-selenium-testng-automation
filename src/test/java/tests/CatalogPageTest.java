package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CatalogPage;

public class CatalogPageTest extends BaseTest {
    @Test
    public void verifyCatalogPage(){
        CatalogPage catalogPage = navigateToCatalogPage();
        String title = catalogPage.getPageTitle();
        Assert.assertEquals(title, "Products – Sauce Demo");
    }
}
