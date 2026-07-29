package tests.ui;

import base.BaseTest;
import data.LoginDataProvider;
import data.UserFactory;
import models.LoginTestData;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductsPage;


public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {
        User user = UserFactory.getStandardUser();
        ProductsPage productsPage = loginPage.login(user);
        Assert.assertTrue(productsPage.isPageOpened());
    }

    @Test(
            dataProvider = "invalidLoginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void userCannotLogin(LoginTestData testData) {
        loginPage.loginAttempt(testData.getUser());

        Assert.assertTrue(loginPage.hasErrorMessage(testData.getErrorMessage()));
    }
}