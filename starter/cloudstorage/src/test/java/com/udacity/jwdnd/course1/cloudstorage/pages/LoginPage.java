package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id = "inputUsername")
    private WebElement usernameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "login-error")
    private WebElement loginError;

    @FindBy(id = "logout")
    private WebElement logout;

    @FindBy(id = "login-logout")
    private WebElement loginLogout;

    private final WebDriver driver;

    public LoginPage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    // https://knowledge.udacity.com/questions/427941
    public void login(String username, String password) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + username + "';", usernameField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + password + "';", passwordField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
    }

    public void logout() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logout);
    }
}
