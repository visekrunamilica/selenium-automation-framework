package tests.ui;

import base.BaseTest;
import data.ProductDataProvider;
import data.ProductType;
import models.Product;
import models.ProductTestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductsPage;

public class CartTest extends BaseTest {

    private ProductsPage productsPage;

    private void assertProductDetailsMatch(Product expected, Product actual) {
        Assert.assertEquals(actual.getName(), expected.getName());
        Assert.assertEquals(actual.getDescription(), expected.getDescription());
        Assert.assertEquals(actual.getPrice(), expected.getPrice(), 0.01);
    }

    @BeforeMethod
    public void login() {
        productsPage = loginAsStandardUser();
    }

    @Test(dataProvider = "products", dataProviderClass = ProductDataProvider.class)
    public void userCanAddProductToCart(ProductTestData testData) {
        Product product = productsPage.getProduct(testData.getProductType().getName());
        productsPage.addProductToCart(product);
        CartPage cartPage = productsPage.openCart();
        Assert.assertTrue(cartPage.isProductInCart(product));
    }

    @Test
    public void removeProductFromCart() {
        Product backpack = productsPage.getProduct(ProductType.BACKPACK.getName());
        productsPage.addProductToCart(backpack);
        CartPage cartPage = productsPage.openCart();
        Assert.assertTrue(cartPage.isProductInCart(backpack));
        cartPage.removeProductFromCart(backpack);
        Assert.assertTrue(cartPage.isProductNotInCart(backpack));
    }

    @Test(dataProvider = "products", dataProviderClass = ProductDataProvider.class)
    public void productDetailsAreCorrectInCart(ProductTestData testData) {
        Product product = productsPage.getProduct(testData.getProductType().getName());
        productsPage.addProductToCart(product);
        CartPage cartPage = productsPage.openCart();
        Product productFromCart = cartPage.getProduct(testData.getProductType().getName());
        assertProductDetailsMatch(product, productFromCart);
    }

}
