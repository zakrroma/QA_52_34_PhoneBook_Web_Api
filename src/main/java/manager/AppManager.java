package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class AppManager {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public Logger logger = LoggerFactory.getLogger(AppManager.class);
    static String browser = System.getProperty("browser",
            Browser.CHROME.browserName());

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {
        if (browser.equals(Browser.CHROME.browserName())) {
            driver = new ChromeDriver();
        } else if (browser.equals(Browser.FIREFOX.browserName())) {
            driver = new FirefoxDriver();
        } else if (browser.equals(Browser.EDGE.browserName())) {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        logger.info("Test started with method: " + method.getName());
    }

    @AfterMethod//(enabled = false)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}