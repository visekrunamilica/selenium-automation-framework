package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import utils.LoggerUtils;

public class BasePage {

    private static final Logger log = LoggerUtils.getLogger(BasePage.class);
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void click(By locator) {
        log.info("Clicking element: {}", locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        log.info("Typing '{}' into element: {}", text, locator);
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        log.info("Getting text from element: {}",locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    protected boolean isVisible(By locator) {
        log.info("Checking visibility of element: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }

    protected boolean isPageTitle(By pageTitle, String expectedTitle) {
        return getText(pageTitle).equals(expectedTitle);
    }

    protected void waitUntilVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForUrlContains(String value) {
        wait.until(ExpectedConditions.urlContains(value));
    }

    protected void waitForTitleContains(String value) {
        wait.until(ExpectedConditions.titleContains(value));
    }

    protected List<String> getTexts(By locator) {
        log.info("Getting texts from elements: {}", locator);
        return getElements(locator)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    protected List<WebElement> getElements(By locator) {
        log.info("Getting elements: {}", locator);
        return driver.findElements(locator);
    }
}
