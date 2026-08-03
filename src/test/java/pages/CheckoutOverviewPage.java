package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    private final By pageTitle = By.cssSelector("[data-test='title']");
    private final By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public boolean isPageOpened() {
        return getPageTitle().equals("Checkout: Overview");
    }

    public CheckoutCompletePage clickFinish() {
        click(finishButton);
        return new CheckoutCompletePage(driver);
    }
}
