package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotePage {
    @FindBy(id = "nav-notes-tab")
    private WebElement navNote;

    @FindBy(id = "add-note-button")
    private WebElement addNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "save-note-button")
    private WebElement saveNoteButton;

    @FindBy(id = "edit-note-button")
    private WebElement editNoteButton;

    @FindBy(id = "delete-note-button")
    private WebElement deleteNoteButton;

    @FindBy(id = "success-message2")
    private WebElement successMessage2;

    private final WebDriver driver;

    public NotePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickNotesTab() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(navNote));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", navNote);
    }

    // https://knowledge.udacity.com/questions/427941
    public void addNote(String title, String description) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.elementToBeClickable(addNoteButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addNoteButton);

        wait.until(ExpectedConditions.elementToBeClickable(noteTitle));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + title + "';", this.noteTitle);

        wait.until(ExpectedConditions.elementToBeClickable(noteDescription));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + description + "';", this.noteDescription);

        wait.until(ExpectedConditions.elementToBeClickable(saveNoteButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveNoteButton);
    }

    // https://knowledge.udacity.com/questions/427941
    public void editNote(String titleEdit, String descriptionEdit){
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.elementToBeClickable(editNoteButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editNoteButton);

        wait.until(ExpectedConditions.elementToBeClickable(noteTitle)).clear();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + titleEdit + "';", this.noteTitle);

        wait.until(ExpectedConditions.elementToBeClickable(noteDescription)).clear();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + descriptionEdit + "';", this.noteDescription);

        wait.until(ExpectedConditions.elementToBeClickable(saveNoteButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveNoteButton);
    }

    // https://knowledge.udacity.com/questions/427941
    public void deleteNote(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(deleteNoteButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteNoteButton);
    }

    public boolean getSuccessMessage2() {
        return this.successMessage2.isDisplayed();
    }
}
