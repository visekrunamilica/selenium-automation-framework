package utils;

public class StringUtils {

    public static String formatProductName(String productName) {
        return productName.toLowerCase().replace(" ", "-");
    }
}
