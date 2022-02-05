import com.pageobject.LoginPage;
import com.pageobject.MainPage;
import com.pageobject.ProfilePage;
import com.pageobject.SignUpPage;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.UserOperations.EMAIL_POSTFIX;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationTest {

    SignUpPage signUpPage;
    ProfilePage profilePage;
    String email;
    String password;
    String name;

    @Before
    public void setUp() {
        email = RandomStringUtils.randomAlphabetic(10) + EMAIL_POSTFIX;
        password = RandomStringUtils.randomAlphabetic(10);
        name = RandomStringUtils.randomAlphabetic(10);

        browser = "chrome";
        signUpPage = open(SignUpPage.signUpPageUrl, SignUpPage.class);
    }

    @Test
    @DisplayName("Check signing up")
    public void checkSuccessfulRegistration() {
        LoginPage loginPage = signUpPage.signUp(name, email, password);
        MainPage mainPage = loginPage.login(email, password);

        profilePage = mainPage.goToProfilePage();

        Assert.assertEquals("Name is not the same",
                name, profilePage.getName());
        Assert.assertEquals("Email is not the same",
                email.toLowerCase(), profilePage.getEmail().toLowerCase());

        profilePage.logout();
    }

    @Test
    @DisplayName("Check that a user cannot sign up using a password that is less than 6 symbols")
    public void checkRegistrationWithIncorrectPassword() {
        String incorrectPassword = RandomStringUtils.randomAlphabetic(5);

        signUpPage.signUp(name, email, incorrectPassword);
        String actualError = signUpPage.getPasswordError();
        Assert.assertEquals("Error message is not the same",
                "Некорректный пароль", actualError);
    }
}