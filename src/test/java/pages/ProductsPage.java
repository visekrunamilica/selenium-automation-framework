package pages;

import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PriceUtils;
import utils.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductsPage extends BasePage {

    private final By pageTitle = By.className("title");

    private final By cartIcon = By.cssSelector("[data-test='shopping-cart-link']");

    private final By productCards = By.className("inventory_item");

    private final By productNameLocator = By.className("inventory_item_name");

    private final By productDescriptionLocator = By.className("inventory_item_desc");

    private final By productPriceLocator = By.className("inventory_item_price");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilOpened() {
        waitUntilVisible(pageTitle);
    }

    public boolean isPageOpened() {
        return isPageTitle(pageTitle, "Products");
    }

    public void addProductToCart(Product product) {
        click(addToCartButton(product.getName()));
    }

    private By addToCartButton(String productName) {
        return By.id("add-to-cart-" + StringUtils.formatProductName(productName));
    }

    public CartPage openCart() {
        click(cartIcon);
        return new CartPage(driver);
    }

    public Product getProduct(String productName) {
        List<WebElement> products = getElements(productCards);
        for (WebElement product : products) {
            String name = product.findElement(productNameLocator).getText();
            if (name.trim().equalsIgnoreCase(productName)) {
                String description = product.findElement(productDescriptionLocator).getText();
                double price = PriceUtils.parsePrice(product.findElement(productPriceLocator).getText());
                return new Product(name, description, price);
            }
        }
        throw new NoSuchElementException("Product not found: " + productName);
    }
}