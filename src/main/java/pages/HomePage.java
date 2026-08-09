package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        setDriver(driver);
        driver.get("https://telranedu.web.app/home");
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//a[text()='LOGIN']")
    WebElement navLinkLogin;
    @FindBy(xpath = "//form/input[1]")
    WebElement inputEmail;

    public void clickNavLinkLogin() {
        navLinkLogin.click();
    }

    public void method() {
        WebElement login = driver.findElement(By
                .xpath("//a[text()='LOGIN']"));
        login.click();
        WebElement inputEmail = driver.findElement(By
                .xpath("//form/input[1]"));
        inputEmail.sendKeys("qwerty@qwer.ty");
    }
}