package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends BasePage {
    private final By pageTitle = By.cssSelector("[data-test='title']");
    private final By completeMessage = By.cssSelector("[data-test='complete-header']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        return isPageTitle(pageTitle,"Checkout: Complete");
    }

    public boolean isOrderCompleted() {
        return getText(completeMessage).contains("Thank you for your order!");
    }

    public void waitUntilOpened() {
        wait.until(ExpectedConditions.urlContains("checkout-complete"));
    }
}
