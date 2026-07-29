package tests.ui;

import base.BaseTest;
import constants.ErrorMessage;
import data.CheckoutDataProvider;
import data.CheckoutFactory;
import data.ProductType;
import models.CheckoutInfo;
import models.CheckoutTestData;
import models.Product;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class CheckoutTest extends BaseTest {

    private CheckoutInformationPage goToCheckoutInformationPage() {
        ProductsPage productsPage = loginAsStandardUser();
        Product backpack = productsPage.getProduct(ProductType.BACKPACK.getName());
        productsPage.addProductToCart(backpack);
        CartPage cartPage = productsPage.openCart();
        return cartPage.checkout();
    }

    @Test
    public void userCanCompleteCheckout() {
        CheckoutInformationPage checkoutInfoPage = goToCheckoutInformationPage();
        CheckoutInfo checkoutInfo = CheckoutFactory.getValidCheckoutInfo();
        checkoutInfoPage.enterCheckoutInfo(checkoutInfo);
        CheckoutOverviewPage overviewPage = checkoutInfoPage.clickContinue();
        CheckoutCompletePage completePage = overviewPage.clickFinish();
        Assert.assertTrue(completePage.isOrderCompleted());
    }

    @Test
    public void userCannotContinueCheckoutWithoutRequiredInformation() {
        CheckoutInformationPage checkoutInfoPage = goToCheckoutInformationPage();
        checkoutInfoPage.clickContinue();
        Assert.assertTrue(checkoutInfoPage.hasErrorMessage(ErrorMessage.FIRST_NAME_REQUIRED));
    }

    @Test(dataProvider = "invalidCheckoutData",
            dataProviderClass = CheckoutDataProvider.class)
    public void userCannotCompleteCheckout(CheckoutTestData testData) {
        CheckoutInformationPage checkoutInfoPage = goToCheckoutInformationPage();
        checkoutInfoPage.checkoutAttempt(testData.getCheckoutInfo());
        Assert.assertTrue(checkoutInfoPage.hasErrorMessage(testData.getErrorMessage()));
    }
}
