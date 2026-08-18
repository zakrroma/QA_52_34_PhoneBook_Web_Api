package ui_tests;

import data_providers.UserDataProvider;
import dto.UserData;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

import static utils.PropertiesReader.*;
import static utils.UserFactory.*;

import java.util.Random;

public class RegistrationTests extends AppManager {
    LoginPage loginPage;

    @BeforeMethod
    public void goToRegistrationLoginPage() {
        new HomePage(getDriver()).clickNavLinkLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000);
        UserData user = UserData.builder()
                .username("username" + i + "@qwer.ty")
                .password(getProperty("base.properties", "password"))
                .build();

        loginPage.fillLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();

        Assert.assertTrue(new ContactsPage(getDriver())
                .validateTextInTitleNoContactsHere("No Contacts here!"));
    }

    @Test
    public void registrationPositiveTest2() {
        UserData user = positiveUser();
        System.out.println(user);

        loginPage.fillLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();

        Assert.assertTrue(new ContactsPage(getDriver())
                .validateTextInTitleNoContactsHere("No Contacts here!"));
    }

    @Test
    public void registrationEmptyFieldsNegativeTest() {
        loginPage.clickBtnRegistration();

        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password format"));
    }

    @Test
    public void registrationEmptyEmailFieldNegativeTest() {
        UserData user = positiveUser();
        user.setUsername("");
        loginPage.fillLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password format"));
    }

    @Test
    public void registrationEmptyPasswordFieldNegativeTest() {
        UserData user = positiveUser();
        user.setPassword("");
        loginPage.fillLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password format"));
    }

    @Test(dataProvider = "wrongEmailPasswordProvider",
            dataProviderClass = UserDataProvider.class)
    public void registrationWrongPasswordFieldNegativeTest(UserData user) {
        loginPage.fillLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password format"));
    }
}