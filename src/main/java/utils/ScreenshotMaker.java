package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotMaker {
    private static String createFileName() {
        SimpleDateFormat formater =
                new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date date = new Date(System.currentTimeMillis());
        String currentDate = formater.format(date);
        String fileName =
                "src/test/resources/screenshots/screenshot_"
                        + currentDate + ".png";
        return fileName;
    }

    public static void takeScreenshot(TakesScreenshot ts) {
        String fileName = createFileName();
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screenshot.toPath(), new File(fileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
