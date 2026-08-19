package ui_tests;

import dto.UserData;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.UserFactory;

import static utils.PropertiesReader.*;

public class LoginTests extends AppManager {
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

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

        ContactsPage contactsPage = new ContactsPage(getDriver());

        softAssert.assertTrue(contactsPage.isButtonSignOutPresent(),
                "validate button sign out presence");
        softAssert.assertTrue(contactsPage.isUrlContainsText("contacts"),
                "validate url");
        softAssert.assertAll();
    }

    @Test
    public void loginUnregisteredUserNegativeTest() {
        UserData user = UserFactory.positiveUser();

        loginPage.fillLoginRegistrationForm(user);
        loginPage.clickBtnLogin();

        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password"));
    }

    @Test
    public void loginEmptyFieldsNegativeTest() {
        loginPage.clickBtnLogin();

        Assert.assertEquals(loginPage.closeAlert(),
                "Wrong email or password");
//        Assert.assertTrue(loginPage.closeAlert()
//                .contains("Wrong email or password format"));
    }
}
