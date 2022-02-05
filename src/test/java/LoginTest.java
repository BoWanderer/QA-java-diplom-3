import com.UserOperations;
import com.pageobject.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    MainPage mainPage;
    UserOperations userOperations;
    Map<String, String> userData;
    String userPassword;
    String userEmail;
    String userName;

    @Before
    public void setUp() {
        userOperations = new UserOperations();
        userData = userOperations.register();
        userPassword = userData.get("password");
        userEmail = userData.get("email");
        userName = userData.get("name");
        browser = "chrome";
        mainPage = open(MainPage.mainPageUrl, MainPage.class);
    }

    @After
    public void tearDown() {
        userOperations.delete();
    }

    @Test
    @DisplayName("Check login via the button on the main page")
    public void checkLoginViaMainPage() {
        ProfilePage profilePage = mainPage
                .login()
                .login(userEmail, userPassword)
                .goToProfilePage();

        Assert.assertEquals("Name is not the same",
                userName, profilePage.getName());
        Assert.assertEquals("Email is not the same",
                userEmail, profilePage.getEmail());
    }

    @Test
    @DisplayName("Check login via the profile button")
    public void checkLoginViaProfilePage() {
        ProfilePage profilePage = mainPage
                .loginViaProfilePage()
                .login(userEmail, userPassword)
                .goToProfilePage();

        Assert.assertEquals("Name is not the same",
                userName, profilePage.getName());
        Assert.assertEquals("Email is not the same",
                userEmail, profilePage.getEmail());
    }

    @Test
    @DisplayName("Check login via the button on the registration page")
    public void checkLoginViaRegisterPage() {
        LoginPage loginPage = mainPage
                .login()
                .signUp()
                .login();

        ProfilePage profilePage = loginPage
                .login(userEmail, userPassword)
                .goToProfilePage();

        Assert.assertEquals("Name is not the same",
                userName, profilePage.getName());
        Assert.assertEquals("Email is not the same",
                userEmail, profilePage.getEmail());
    }

    @Test
    @DisplayName("Check login via the button on the forgot password page")
    public void checkLoginViaForgotPasswordPage() {
        LoginPage loginPage = mainPage
                .login()
                .restorePassword()
                .login();

        ProfilePage profilePage = loginPage
                .login(userEmail, userPassword)
                .goToProfilePage();

        Assert.assertEquals("Name is not the same",
                userName, profilePage.getName());
        Assert.assertEquals("Email is not the same",
                userEmail, profilePage.getEmail());
    }
}