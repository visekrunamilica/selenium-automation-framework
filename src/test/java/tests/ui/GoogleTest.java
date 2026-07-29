package tests.ui;

import base.BaseTest;
import base.DriverFactory;
import config.ConfigReader;
import org.testng.annotations.Test;

public class GoogleTest extends BaseTest {

    @Test
    public void openGoogle() {
        DriverFactory.getDriver()
                .get(ConfigReader.get("baseUrl"));

    }
}

