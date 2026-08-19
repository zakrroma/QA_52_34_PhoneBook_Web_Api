package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    static WebDriver driver;

    public void setDriver(WebDriver wd) {
        driver = wd;
    }

    public boolean isTextInElementPresent(
            WebElement element, String text) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions
                            .textToBePresentInElement(element, text));
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("created exception");
        }
        return false;
    }

    public boolean isUrlContainsText(String text) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.urlContains(text));
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("created exception");
        }
        return false;
    }

    public String closeAlert() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}