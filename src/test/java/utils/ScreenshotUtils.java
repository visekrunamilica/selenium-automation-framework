package utils;

import base.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    private static final String SCREENSHOT_PATH = "screenshots/";

    public static void takeScreenshot(String testName) {

        File source = ((TakesScreenshot) DriverFactory.getDriver())
                .getScreenshotAs(OutputType.FILE);

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        File destination = new File(
                SCREENSHOT_PATH + testName + "_" + timestamp + ".png"
        );

        File parentDirectory = destination.getParentFile();

        if (!parentDirectory.exists()) {
            boolean created = parentDirectory.mkdirs();

            if (!created) {
                throw new RuntimeException("Failed to create screenshot directory");
            }
        }

        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot", e);
        }
    }
}
