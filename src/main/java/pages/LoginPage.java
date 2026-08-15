package pages;

import dto.UserData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//form/input[1]")
    WebElement inputEmail;

    @FindBy(xpath = "//form/input[2]")
    WebElement inputPassword;

    @FindBy(xpath = "//form/button[1]")
    WebElement btnLogin;

    @FindBy(xpath = "//form/button[2]")
    WebElement btnRegistration;

    public void fillLoginRegistrationForm(UserData user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickBtnLogin() {
        btnLogin.click();
    }

    public void clickBtnRegistration() {
        btnRegistration.click();
    }
}