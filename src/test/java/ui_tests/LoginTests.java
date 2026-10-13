package ui_tests;

import dto.UserDto;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.TestNGListener;
import utils.UserFactory;

import static utils.PropertiesReader.*;

@Listeners(TestNGListener.class)

public class LoginTests extends AppManager {
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod(alwaysRun = true)
    public void goToRegistrationLoginPage() {
        new HomePage(getDriver()).clickNavLinkLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test(groups = {"smoke","user","positive"})
    public void loginPositiveTest() {
        UserDto user = UserDto.builder()
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
        UserDto user = UserFactory.positiveUser();

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

    @Test
    public void loginEmptyEmailFieldNegativeTest() {
        UserDto user = UserDto.builder()
                .username("")
                .password(getProperty("base.properties", "password"))
                .build();

        loginPage.fillLoginRegistrationForm(user);
        loginPage.clickBtnLogin();

        Assert.assertEquals(loginPage.closeAlert(),
                "Wrong email or password");
    }

    @Test
    public void loginEmptyPasswordFieldNegativeTest() {
        UserDto user = UserDto.builder()
                .username(getProperty("base.properties", "email"))
                .password("")
                .build();

        loginPage.fillLoginRegistrationForm(user);
        loginPage.clickBtnLogin();

        Assert.assertEquals(loginPage.closeAlert(),
                "Wrong email or password");
    }

    @Test
    public void loginWrongEmailNegativeTest() {
        UserDto user = UserDto.builder()
                .username("email@qwer.ty")
                .password(getProperty("base.properties", "password"))
                .build();

        loginPage.fillLoginRegistrationForm(user);
        loginPage.clickBtnLogin();

        Assert.assertEquals(loginPage.closeAlert(),
                "Wrong email or password");
    }

    @Test
    public void loginWrongPasswordNegativeTest() {
        UserDto user = UserDto.builder()
                .username(getProperty("base.properties", "email"))
                .password("Pass1234!")
                .build();

        loginPage.fillLoginRegistrationForm(user);
        loginPage.clickBtnLogin();

        Assert.assertEquals(loginPage.closeAlert(),
                "Wrong email or password");
    }
}