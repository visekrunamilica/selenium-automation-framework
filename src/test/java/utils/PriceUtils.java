package utils;

public class PriceUtils {
    public static double parsePrice(String priceText) {
        return Double.parseDouble(priceText.replace("$", ""));
    }
}
