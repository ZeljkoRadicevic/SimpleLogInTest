package hr.company;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogIn {
	public WebDriver driver;
	public String baseUrl;
	private String username;
	private String password;
	public Logger log;
	public String title;
	private WebElement element;

	@BeforeClass
	public void setUpLogIn() throws Exception {
		/**
		 * configure log4j using properties file
		 */
		PropertyConfigurator.configure("log4j.properties");
		/**
		 * Create logger instance and pass Class name for which we want to
		 * create log file
		 */
		log = Logger.getLogger(getClass().getName());
		/**
		 * Create new Firefox Browser
		 */
		driver = new FirefoxDriver();
		log.info("Created new Firefox Browser");
		/**
		 * URL of web interface
		 */
		baseUrl = "http://192.168.234.1:8000";
		/**
		 * Username and pasword for root user
		 */
		username = "username";
		password = "password";
	}

	/**
	 * Open Mozilla Firefox and navigate to login page
	 */
	@Test(description = "Open web browser", priority = 1)
	public void openWebBrowser() throws Exception {
		driver.get(baseUrl + "/login");
		log.info("Browser Opened");
		title = driver.getTitle();
		log.info("Browser title = " + title);
		Assert.assertTrue(title.contains("P5 Login"));
	}

	/**
	 * Find username field and type username in
	 */
	@Test(description = "Type username in", priority = 2)
	public void typeInUsername() throws Exception {
		element = driver.findElement(By.name("uname"));
		Assert.assertEquals(true, element.isDisplayed());
		element.clear();
		log.info("Username field found");
		element.sendKeys(username);
		log.info("User name typed in");
	}

	/**
	 * Find password field and type password in
	 */
	@Test(description = "Type password in", priority = 3)
	public void typeInPassword() throws Exception {
		element = driver.findElement(By.name("upass"));
		Assert.assertEquals(true, element.isDisplayed());
		element.clear();
		log.info("Password field found");
		element.sendKeys(password);
		log.info("Password typed in");

	}

	/**
	 * Click on log in button
	 */
	@Test(description = "Click Log In Button", priority = 4)
	public void clickLogIn() throws Exception {
		element = driver.findElement(By.xpath("//a[@onclick='update()']"));
		Assert.assertEquals(true, element.isDisplayed());
		element.click();
		log.info("Clicked on the log in button");
	}

	/**
	 * Check if user is logged in
	 */
	@Test(description = "Check if user is loged in", priority = 5)
	public void checkLogin() throws Exception {
		title = driver.getTitle();
		log.info("Browser title = " + title);
		Assert.assertTrue(title.contains("Application Server"));
		log.info("User " + username + " loged in successfully");
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		log.info("Browser closed");
	}
}

