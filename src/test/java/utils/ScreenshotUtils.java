package utils;

import base.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    public static void takeScreenshot(String testName) {
        File source = ((TakesScreenshot) DriverFactory.getDriver())
                .getScreenshotAs(OutputType.FILE);

        File destination = new File(
                "screenshots/" + testName + ".png"
        );

        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
