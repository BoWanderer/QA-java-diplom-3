package com.pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ProfilePage {

    //Кнопка "Конструктор"
    @FindBy(how = How.XPATH,using = ".//p[text()='Конструктор']")
    private SelenideElement constructorButton;

    //Кнопка-логотип для перехода на главную страницу
    @FindBy(how = How.CLASS_NAME,using = "AppHeader_header__logo__2D0X2")
    private SelenideElement stellarBurgersButton;

    //Кнопки "профиль"
    @FindBy(how = How.XPATH,using=".//a[text()='Профиль']")
    private SelenideElement profileButton;

    //Кнопка "Выйти"
    @FindBy(how = How.XPATH,using=".//button[text()='Выход']")
    private SelenideElement logOutButton;

    //Поле ввода имени
    @FindBy(how = How.NAME,using="Name")
    private SelenideElement nameField;

    //Поле ввода email
    @FindBy(how = How.NAME,using="name")
    private SelenideElement emailField;

    @Step("Go to constructor")
    public MainPage goToConstructor() {
        constructorButton.click();
        return page(MainPage.class);
    }

    @Step("Go to main page")
    public MainPage goToMainPage() {
        stellarBurgersButton.click();
        return page(MainPage.class);
    }

    @Step("Press logout button")
    public LoginPage logout() {
        logOutButton.click();
        return page(LoginPage.class);
    }

    @Step("Get username")
    public String getName() {
        return nameField.getValue();
    }

    @Step("Get user email")
    public String getEmail() {
        return emailField.getValue();
    }

    @Step("Find a profile button")
    public SelenideElement getProfileButton() {
        return profileButton;
    }
}