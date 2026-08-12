package base;

import config.ConfigReader;
import data.UserFactory;
import listeners.TestListener;
import models.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import pages.ProductsPage;

@Listeners(TestListener.class)
public class BaseTest {

    protected LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initializeDriver();
        WebDriver driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);
    }

    protected ProductsPage login(User user) {
        return loginPage.login(user);
    }

    protected ProductsPage loginAsStandardUser() {
        return login(UserFactory.getStandardUser());
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}