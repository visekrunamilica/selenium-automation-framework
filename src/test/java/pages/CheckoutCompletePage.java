package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {
    private final By pageTitle = By.cssSelector("[data-test='title']");
    private final By completeMessage = By.cssSelector("[data-test='complete-header']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public boolean isPageOpened() {
        return getPageTitle().equals("Checkout: Complete");
    }

    public boolean isOrderCompleted() {
        return getText(completeMessage).contains("Thank you for your order!");
    }
}
