package pages;

import constants.ErrorMessage;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessageLocator = By.cssSelector("[data-test='error']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public LoginPage clickLogin() {
        click(loginButton);
        return this;
    }

    public ProductsPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        ProductsPage productsPage=new ProductsPage(driver);
        productsPage.waitUntilOpened();
        return new ProductsPage(driver);
    }

    public ProductsPage login(User user) {
        return login(user.getUsername(), user.getPassword());
    }

    public void loginAttempt(User user) {
        enterUsername(user.getUsername());
        enterPassword(user.getPassword());
        clickLogin();
    }

    public boolean hasErrorMessage(ErrorMessage errorMessage) {
        return getText(errorMessageLocator)
                .contains(errorMessage.getMessage());
    }

}
