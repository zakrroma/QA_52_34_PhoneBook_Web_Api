package ui_tests;

import dto.UserData;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.UserFactory;

import static utils.PropertiesReader.getProperty;

public class LoginTests extends AppManager {
    LoginPage loginPage;

    @BeforeMethod
    public void goToRegistrationLoginPage() {
        new HomePage(getDriver()).clickNavLinkLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTest() {
        UserData user = UserData.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();

        loginPage.fillLoginRegistrationForm(user);
        loginPage.clickBtnLogin();

        Assert.assertTrue(new ContactsPage(getDriver())
                .validateTextInTitleNoContactsHere("No Contacts here!"));
    }

    @Test
    public void loginUnregisteredUserNegativeTest() {
        UserData user = UserFactory.positiveUser();

        loginPage.fillLoginRegistrationForm(user);
        loginPage.clickBtnLogin();

        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password"));
    }
}
