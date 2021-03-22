package test;

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

import pages.NewTodo;

public class TodoSiteTest {
	
	public static final String URL = "file:///C:/Users/nikos/eclipse-workspace/To-Do-List-Web-Application/final-todo-proejct/home.html";
	
	public static WebDriver driver;

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
		driver.get(NewTodo.URL);
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
	
	@Test
	public void homePage() {
		Actions action = new Actions(driver);
		WebElement startButton = driver.findElement(By.cssSelector("body > div > form > button"));
		action.moveToElement(startButton).perform();
		action.click();

	}


}
