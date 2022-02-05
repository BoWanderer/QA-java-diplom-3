import com.UserOperations;
import com.codeborne.selenide.SelenideElement;
import com.pageobject.LoginPage;
import com.pageobject.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;

public class ConstructorTest {

    @Before
    public void setUp() {
        browser = "chrome";
    }

    @Test
    @DisplayName("Check switching between tabs by authorized user")
    public void checkConstructorTabsWithLogin() {
        UserOperations userOperations = new UserOperations();
        Map<String, String> userData = userOperations.register();

        MainPage mainPage = open(LoginPage.loginPageUrl, LoginPage.class)
                .login(userData.get("email"), userData.get("password"));

        SelenideElement fillingsTab = mainPage.chooseFilings();
        fillingsTab.shouldHave(attribute("class"));

        SelenideElement saucesTab = mainPage.chooseSauces();
        saucesTab.shouldHave(attribute("class"));
        fillingsTab.shouldHave(attribute("class"));

        SelenideElement bunsTab = mainPage.chooseBuns();
        bunsTab.shouldHave(attribute("class"));
        saucesTab.shouldHave(attribute("class"));

        userOperations.delete();
    }

    @Test
    @DisplayName("Check switching between tabs by unauthorized user")
    public void checkConstructorTabsWithoutLogin() {
        MainPage mainPage = open(MainPage.mainPageUrl, MainPage.class);

        SelenideElement fillingsTab = mainPage.chooseFilings();
        fillingsTab.shouldHave(attribute("class"));

        SelenideElement saucesTab = mainPage.chooseSauces();
        saucesTab.shouldHave(attribute("class"));
        fillingsTab.shouldHave(attribute("class"));

        SelenideElement bunsTab = mainPage.chooseBuns();
        bunsTab.shouldHave(attribute("class"));
        saucesTab.shouldHave(attribute("class"));
    }
}