package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.Random;

public class RegistrationTests extends AppManager {
    @BeforeMethod
    public void goToRegistrationLoginPage() {
        new HomePage(getDriver()).clickNavLinkLogin();
    }

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000);
        UserLombok user = UserLombok.builder()
                .username("kek" + i + "@qwer.ty")
                .password("Kek1234!")
                .build();

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.fillLoginRegistrationForm(user);

        loginPage.clickBtnRegistration();
    }
}