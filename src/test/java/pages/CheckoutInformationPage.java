package pages;

import constants.ErrorMessage;
import models.CheckoutInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInformationPage extends BasePage {

    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        type(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameField, lastName);
    }

    public void enterPostalCode(String postalCode) {
        type(postalCodeField, postalCode);
    }

    public CheckoutInformationPage enterCheckoutInfo(CheckoutInfo info) {
        type(firstNameField, info.getFirstName());
        type(lastNameField, info.getLastName());
        type(postalCodeField, info.getPostalCode());
        return this;
    }

    public CheckoutOverviewPage clickContinue() {
        click(continueButton);
        return new CheckoutOverviewPage(driver);
    }

    public void checkoutAttempt(CheckoutInfo info) {
        enterCheckoutInfo(info);
        click(continueButton);
    }

    public boolean hasErrorMessage(ErrorMessage message) {
        return getText(errorMessage).contains(message.getMessage());
    }
}
