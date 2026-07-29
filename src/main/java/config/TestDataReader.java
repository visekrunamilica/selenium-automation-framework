package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestDataReader {

    private static Properties properties;

    static {
        try {
            FileInputStream file =
                    new FileInputStream("src/main/resources/testdata.properties");

            properties = new Properties();
            properties.load(file);

        } catch (IOException e) {
            throw new RuntimeException("Test Data file not found");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
