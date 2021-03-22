package pages;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class CreateUser {
	
public static final String URL = "http://127.0.0.1:5500/final-todo-proejct/createuser.html";
	
	private static WebDriver driver;
	
	@BeforeClass
	public static void init() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		ChromeOptions cOptions = new ChromeOptions();
		cOptions.setHeadless(false);

		cOptions.setCapability("profile.default_content_setting_values.cookies", 2);
		cOptions.setCapability("network.cookie.cookieBehavior", 2);
		cOptions.setCapability("profile.block_third_party_cookies", true);
		driver = new ChromeDriver(cOptions);

	}

	@Before
	public void setup() {
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.get(CreateUser.URL);
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
	
	
	@Test
	public void newUser() throws InterruptedException {
		Actions action = new Actions(driver);
		WebElement firstNameContainer = driver.findElement(By.cssSelector("#firstname"));
		action.moveToElement(firstNameContainer).perform();
		action.click();
		
		
		firstNameContainer.sendKeys("Nikos");
		
		
		WebElement lastNameContainer = driver.findElement(By.cssSelector("#lastname"));
		action.moveToElement(lastNameContainer).perform();
		action.click();
		
		
		lastNameContainer.sendKeys("Papado");
	
		
		WebElement userNameContainer = driver.findElement(By.cssSelector("#username"));
		action.moveToElement(userNameContainer).perform();
		action.click();
		
		
		userNameContainer.sendKeys("nikpap1234890");
		
		
		WebElement passwordContainer = driver.findElement(By.cssSelector("#password"));
		action.moveToElement(passwordContainer).perform();
		action.click();
		
		
		passwordContainer.sendKeys("Nqs4812LouPOoK");
		
		
		WebElement saveButton = driver.findElement(By.cssSelector("#savebutton"));
		action.moveToElement(saveButton).perform();
		action.click();
		
		
		
	}
	
	@Test
	public void fetchPosts() throws InterruptedException {
		Actions action = new Actions(driver);
		WebElement fetchPostsButton = driver.findElement(By.cssSelector("#fetch"));
		action.moveToElement(fetchPostsButton).perform();
		action.click();
	}

}
