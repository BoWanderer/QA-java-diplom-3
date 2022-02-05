package com.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    public static String loginPageUrl = "https://stellarburgers.nomoreparties.site/login";

    //Поле ввода email
    @FindBy(how = How.NAME,using="name")
    private SelenideElement emailField;

    //Поле ввода пароля
    @FindBy(how = How.NAME,using="Пароль")
    private SelenideElement passwordField;

    //Кнопка "Войти"
    @FindBy(how = How.XPATH,using=".//button[text()='Войти']")
    private SelenideElement enterButton;

    //Кнопка "Восстановить пароль"
    @FindBy(how = How.LINK_TEXT,using="Восстановить пароль")
    private SelenideElement restorePassword;

    //Кнопка "Зарегистрироваться"
    @FindBy(how = How.LINK_TEXT,using="Зарегистрироваться")
    private SelenideElement signUpButton;

    @Step("Set email")
    public void setEmailField(String email) {
        emailField.setValue(email);
    }

    @Step("Set password")
    public void setPasswordField(String password) {
        passwordField.setValue(password);
    }

    @Step("Confirm login")
    public MainPage confirmLogin() {
        enterButton.click();
        return page(MainPage.class);
    }

    @Step("Commit login")
    public MainPage login(String email, String password) {
        setEmailField(email);
        setPasswordField(password);
        return confirmLogin();
    }

    @Step("Click Enter button")
    public void clickLogin() {
        enterButton.click();
    }

    @Step("Go to register page")
    public SignUpPage signUp() {
        signUpButton.click();
        return page(SignUpPage.class);
    }

    @Step("Go to restore password page")
    public ForgotPasswordPage restorePassword() {
        restorePassword.click();
        return page(ForgotPasswordPage.class);
    }

    @Step("Find login button")
    public SelenideElement getEnterButton() {
        return enterButton;
    }
}