package com.udacity.jwdnd.course1.cloudstorage.pages;

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

    public void clickCredentials(){
        this.navCredential.click();
    }

    public void addCredential(String url, String username, String password){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(addCredentialButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(credentialUrl)).sendKeys(url);
        wait.until(ExpectedConditions.elementToBeClickable(credentialUsername)).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(credentialPassword)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(saveCredentialButton)).click();
    }

    public void editCredential(String newUrl, String newUsername, String newPassword){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(editCredentialButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(credentialUrl)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(credentialUrl)).sendKeys(newUrl);
        wait.until(ExpectedConditions.elementToBeClickable(credentialUsername)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(credentialUsername)).sendKeys(newUsername);
        wait.until(ExpectedConditions.elementToBeClickable(credentialPassword)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(credentialPassword)).sendKeys(newPassword);
        wait.until(ExpectedConditions.elementToBeClickable(saveCredentialButton)).click();
    }

    public void deleteCredential(){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(deleteCredential)).click();
    }

    public boolean getSuccessMessage() {
        return this.successMessage.isDisplayed();
    }


}
