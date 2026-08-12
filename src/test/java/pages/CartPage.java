package pages;

import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PriceUtils;
import utils.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;

public class CartPage extends BasePage {

    private final By cartProductNames = By.cssSelector("[data-test='inventory-item-name']");
    private final By cartProductCards = By.className("cart_item");
    private final By cartProductNameLocator = By.className("inventory_item_name");
    private final By cartProductDescriptionLocator = By.className("inventory_item_desc");
    private final By cartProductPriceLocator = By.className("inventory_item_price");
    private final By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By removeButton(String productName) {
        return By.id("remove-" + StringUtils.formatProductName(productName));
    }

    public boolean isProductInCart(Product product) {
        return getTexts(cartProductNames)
                .stream()
                .anyMatch(name -> name.equalsIgnoreCase(product.getName()));
    }

    public void removeProductFromCart(Product product) {
        click(removeButton(product.getName()));
    }

    public boolean isCartEmpty() {
        return getTexts(cartProductNames).isEmpty();
    }

    public boolean isProductNotInCart(Product product) {
        return getTexts(cartProductNames)
                .stream()
                .noneMatch(name -> name.equalsIgnoreCase(product.getName()));
    }

    public Product getProduct(String productName) {
        List<WebElement> products = getElements(cartProductCards);
        for (WebElement productCard : products) {
            String name = productCard.findElement(cartProductNameLocator).getText();
            if (name.trim().equalsIgnoreCase(productName)) {
                String description = productCard.findElement(cartProductDescriptionLocator).getText();
                double price = PriceUtils.parsePrice(productCard.findElement(cartProductPriceLocator).getText());
                return new Product(name, description, price);
            }
        }
        throw new NoSuchElementException("Product not found: " + productName);
    }

    public CheckoutInformationPage checkout() {
        click(checkoutButton);
        return new CheckoutInformationPage(driver);
    }
}
