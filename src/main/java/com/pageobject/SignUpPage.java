package com.pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class SignUpPage {

    public static String signUpPageUrl = "https://stellarburgers.nomoreparties.site/register";

    //Поле ввода имени/email
    @FindBy(how = How.NAME,using="name")
    ElementsCollection nameAndEmailFields;

    //Поле ввода пароля
    @FindBy(how = How.NAME,using="Пароль")
    private SelenideElement passwordField;

    //Кнопки "Зарегистрироваться"
    @FindBy(how = How.XPATH,using=".//button[text()='Зарегистрироваться']")
    private SelenideElement signupButton;

    //Кнопка "Войти"
    @FindBy(how = How.LINK_TEXT,using="Войти")
    private SelenideElement loginButton;

    //Сообщение об ошибке
    @FindBy(how = How.XPATH,using = ".//p[text()='Некорректный пароль']")
    private SelenideElement passwordError;

    //Кнопка "Личный кабинет"
    @FindBy(how = How.XPATH,using = ".//p[text()='Личный Кабинет']")
    private SelenideElement profilePageButton;

    @Step("Go to the Profile page")
    public ProfilePage goToProfilePage() {
        profilePageButton.click();
        return page(ProfilePage.class);
    }

    @Step("Set name")
    public void setNameField(String name) {
        nameAndEmailFields.get(0).setValue(name);
    }

    @Step("Set email")
    public void setEmailField(String email) {
        nameAndEmailFields.get(1).setValue(email);
    }

    @Step("Set password")
    public void setPasswordField(String password) {
        passwordField.setValue(password);
    }

    @Step("Set name, email, password for sign up")
    public void setRegisterFields(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
    }

    @Step("Submit registration")
    public LoginPage submitRegistration() {
        signupButton.click();
        return page(LoginPage.class);
    }

    @Step("singUp")
    public LoginPage signUp(String name, String email, String password) {
        setRegisterFields(name, email, password);
        return submitRegistration();
    }

    @Step("Press login button")
    public LoginPage login() {
        loginButton.click();
        return page(LoginPage.class);
    }

    @Step("Get text of a password error")
    public String getPasswordError() {
        return passwordError.getText();
    }
}