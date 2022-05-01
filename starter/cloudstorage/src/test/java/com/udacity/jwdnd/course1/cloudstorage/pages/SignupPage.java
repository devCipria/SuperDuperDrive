package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(id = "inputFirstName")
    private WebElement firstNameField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameField;

    @FindBy(id = "inputUsername")
    private WebElement usernameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    private final WebDriver driver;

    public SignupPage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    // https://knowledge.udacity.com/questions/427941
    public void signup(String firstName, String lastName, String username, String password) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + firstName + "';", firstNameField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + lastName + "';", lastNameField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + username + "';", usernameField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + password + "';", passwordField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
    }
}
