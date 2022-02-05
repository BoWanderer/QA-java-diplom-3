package com.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ForgotPasswordPage {

    //Кнопка "Войти"
    @FindBy(how = How.LINK_TEXT,using="Войти")
    private SelenideElement loginButton;

    @Step("Go to login page")
    public LoginPage login() {
        loginButton.click();
        return page(LoginPage.class);
    }
}