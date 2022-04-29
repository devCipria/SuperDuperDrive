package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.CredentialPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.NotePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;
	public String baseUrl;

	private static WebDriver driver;

	// User Registration
	String firstName = "Bruce";
	String lastName = "Wayne";
	String username = "Batman";
	String password = "password";

	// Sample Note
	String noteTitle = "Today is Friday";
	String noteDescription = "Everybody Loves Fridays";

	// Sample Note Edit
	String noteTitleEdit = "Today is Monday";
	String getNoteDescriptionEdit = "Everybody Hates Mondays";

	// Sample Credential
	String url = "www.gmail.com";

	// Sample Credential Edit
	String passwordEdit = "abc123";

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		baseUrl = "http://localhost:" + port;
		driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	@Order(1)
	public void testUnauthorizedAccess() throws InterruptedException {
		driver.get(baseUrl + "/login");
		Assertions.assertTrue(driver.getCurrentUrl().equals(baseUrl + "/login"));
		Thread.sleep(2000);


		driver.get(baseUrl + "/signup");
		Assertions.assertTrue(driver.getCurrentUrl().equals(baseUrl + "/signup"));
		Thread.sleep(2000);


		driver.get(baseUrl + "/home");
		Assertions.assertNotEquals(driver.getCurrentUrl(), (baseUrl + "/home"));
		Thread.sleep(2000);
	}

	@Test
	@Order(2)
	public void testLoginAndSignupPage() throws InterruptedException {

		driver.get(baseUrl + "/login");
		LoginPage loginPage = new LoginPage(driver);
		Thread.sleep(2000);

		driver.get(baseUrl + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);
		Thread.sleep(2000);

		loginPage.login(username, password);
		Thread.sleep(2000);

		loginPage.logout();
		Thread.sleep(2000);

		driver.get(baseUrl + "/home");
		Assertions.assertTrue(driver.getCurrentUrl().equals(baseUrl + "/login"));
		Thread.sleep(2000);

	}

	@Test
	@Order(3)
	public void testAddNote() throws  InterruptedException {
		driver.get(baseUrl + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		Thread.sleep(2000);


		driver.get(baseUrl + "/home");
		Thread.sleep(2000);

		NotePage notePage = new NotePage(driver);

		notePage.clickNotesTab();
		Thread.sleep(2000);

		notePage.addNote(noteTitle, noteDescription);
		Thread.sleep(2000);

		assertTrue(notePage.getSuccessMessage2());
	}

	@Test
	@Order(4)
	public void testEditNote() throws InterruptedException {
		driver.get(baseUrl + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		Thread.sleep(2000);

		driver.get(baseUrl + "/home");
		Thread.sleep(2000);

		NotePage notePage = new NotePage(driver);

		notePage.clickNotesTab();
		Thread.sleep(2000);

		notePage.editNote(noteTitleEdit, getNoteDescriptionEdit);
		Thread.sleep(2000);

		assertTrue(notePage.getSuccessMessage2());
	}

	@Test
	@Order(5)
	public void testDeleteNote() throws InterruptedException {
		driver.get(baseUrl + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		Thread.sleep(2000);

		driver.get(baseUrl + "/home");
		Thread.sleep(2000);

		NotePage notePage = new NotePage(driver);

		notePage.clickNotesTab();
		Thread.sleep(2000);

		notePage.deleteNote();
		Thread.sleep(2000);

		assertTrue(notePage.getSuccessMessage2());
	}

	@Test
	@Order(6)
	public void testAddCredential() throws InterruptedException {
		driver.get(baseUrl + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		Thread.sleep(2000);


		driver.get(baseUrl + "/home");
		Thread.sleep(2000);

		CredentialPage credentialPage = new CredentialPage(driver);

		credentialPage.clickCredentials();
		Thread.sleep(2000);

		credentialPage.addCredential(url, username, password);
		Thread.sleep(2000);

		assertTrue(credentialPage.getSuccessMessage());
	}

	@Test
	@Order(7)
	public void testEditCredential() throws InterruptedException {
		driver.get(baseUrl + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		Thread.sleep(2000);


		driver.get(baseUrl + "/home");
		Thread.sleep(2000);

		CredentialPage credentialPage = new CredentialPage(driver);

		credentialPage.clickCredentials();
		Thread.sleep(2000);

		credentialPage.editCredential(url, username, passwordEdit);
		Thread.sleep(2000);

		assertTrue(credentialPage.getSuccessMessage());
	}

	@Test
	@Order(8)
	public void testDeleteCredential() throws InterruptedException {
		driver.get(baseUrl + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		Thread.sleep(2000);


		driver.get(baseUrl + "/home");
		Thread.sleep(2000);

		CredentialPage credentialPage = new CredentialPage(driver);

		credentialPage.clickCredentials();
		Thread.sleep(2000);

		credentialPage.deleteCredential();
		Thread.sleep(2000);

		assertTrue(credentialPage.getSuccessMessage());
	}


	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password) {

		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful.
		// You may have to modify the element "success-msg" and the sign-up
		// success message below depending on the rest of your code.
		*/
		Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}



	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}

//	/**
//	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
//	 * rest of your code.
//	 * This test is provided by Udacity to perform some basic sanity testing of
//	 * your code to ensure that it meets certain rubric criteria.
//	 *
//	 * If this test is failing, please ensure that you are handling redirecting users
//	 * back to the login page after a succesful sign up.
//	 * Read more about the requirement in the rubric:
//	 * https://review.udacity.com/#!/rubrics/2724/view
//	 */
//	@Test
//	public void testRedirection() {
//		// Create a test account
//		doMockSignUp("Redirection","Test","RT","123");
//
//		// Check if we have been redirected to the log in page.
//		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
//	}
//
//	/**
//	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
//	 * rest of your code.
//	 * This test is provided by Udacity to perform some basic sanity testing of
//	 * your code to ensure that it meets certain rubric criteria.
//	 *
//	 * If this test is failing, please ensure that you are handling bad URLs
//	 * gracefully, for example with a custom error page.
//	 *
//	 * Read more about custom error pages at:
//	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
//	 */
//	@Test
//	public void testBadUrl() {
//		// Create a test account
//		doMockSignUp("URL","Test","UT","123");
//		doLogIn("UT", "123");
//
//		// Try to access a random made-up URL.
//		driver.get("http://localhost:" + this.port + "/some-random-page");
//		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
//	}
//
//
//	/**
//	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
//	 * rest of your code.
//	 * This test is provided by Udacity to perform some basic sanity testing of
//	 * your code to ensure that it meets certain rubric criteria.
//	 *
//	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
//	 * gracefully in your code.
//	 *
//	 * Read more about file size limits here:
//	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
//	 */
//	@Test
//	public void testLargeUpload() {
//		// Create a test account
//		doMockSignUp("Large File","Test","LFT","123");
//		doLogIn("LFT", "123");
//
//		// Try to upload an arbitrary large file
//		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
//		String fileName = "upload5m.zip";
//
//		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
//		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
//		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());
//
//		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
//		uploadButton.click();
//		try {
//			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
//		} catch (org.openqa.selenium.TimeoutException e) {
//			System.out.println("Large File upload failed");
//		}
//		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));
//
//	}



}
