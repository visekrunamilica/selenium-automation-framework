package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutOverviewPage extends BasePage {

    private final By pageTitle = By.cssSelector("[data-test='title']");
    private final By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        return isPageTitle(pageTitle, "Checkout: Overview");
    }

    public CheckoutCompletePage clickFinish() {
        click(finishButton);
        waitForUrlContains("checkout-complete");
        return new CheckoutCompletePage(driver);
    }

    public void waitUntilOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
    }
}
