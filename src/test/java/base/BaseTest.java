package base;

import config.ConfigReader;
import data.UserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.ProductsPage;

public class BaseTest {

    protected LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initializeDriver();
        WebDriver driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);
    }

    protected ProductsPage loginAsStandardUser() {
        return loginPage.login(UserFactory.getStandardUser());
    }


    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}