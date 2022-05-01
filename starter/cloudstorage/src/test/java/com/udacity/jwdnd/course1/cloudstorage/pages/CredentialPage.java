package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialPage {
    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredential;

    @FindBy(id = "add-credential-button")
    private WebElement addCredentialButton;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "save-credential-button")
    private WebElement saveCredentialButton;

    @FindBy(id = "edit-credential-button")
    private WebElement editCredentialButton;

    @FindBy(id = "delete-credential-button")
    private WebElement deleteCredential;

    @FindBy(id = "success-message3")
    private WebElement successMessage;

    private final WebDriver driver;

    public CredentialPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCredentialsTab(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(navCredential));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", navCredential);
    }

    // https://knowledge.udacity.com/questions/427941
    public void addCredential(String url, String username, String password){
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.elementToBeClickable(addCredentialButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addCredentialButton);

        wait.until(ExpectedConditions.elementToBeClickable(credentialUrl));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + url + "';", this.credentialUrl);

        wait.until(ExpectedConditions.elementToBeClickable(credentialUsername));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + username + "';", this.credentialUsername);

        wait.until(ExpectedConditions.elementToBeClickable(credentialPassword));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + password + "';", this.credentialPassword);

        wait.until(ExpectedConditions.elementToBeClickable(saveCredentialButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveCredentialButton);
    }

    // https://knowledge.udacity.com/questions/427941
    public void editCredential(String newUrl, String newUsername, String newPassword){
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.elementToBeClickable(editCredentialButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editCredentialButton);

        wait.until(ExpectedConditions.elementToBeClickable(credentialUrl)).clear();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + newUrl + "';", this.credentialUrl);

        wait.until(ExpectedConditions.elementToBeClickable(credentialUsername)).clear();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + newUsername + "';", this.credentialUsername);

        wait.until(ExpectedConditions.elementToBeClickable(credentialPassword)).clear();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + newPassword + "';", this.credentialPassword);

        wait.until(ExpectedConditions.elementToBeClickable(saveCredentialButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveCredentialButton);
    }

    // https://knowledge.udacity.com/questions/427941
    public void deleteCredential(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(deleteCredential));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteCredential);
    }

    public boolean getSuccessMessage() {
        return this.successMessage.isDisplayed();
    }


}
