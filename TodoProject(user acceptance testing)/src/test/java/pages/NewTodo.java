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

public class NewTodo {
	public static final String URL = "http://127.0.0.1:5500/final-todo-proejct/createtodo.html";
	
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
		driver.get(NewTodo.URL);
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
		System.out.print(driver.getTitle());
		driver.manage().window().maximize();

	}

	@Test
	public void newTodo() throws InterruptedException {

		WebElement titleContainer = driver.findElement(By.cssSelector("#title"));
		Actions action = new Actions(driver);
		action.moveToElement(titleContainer).perform();
		action.click();

		titleContainer.sendKeys("Bike ride");

		WebElement memoContainer = driver.findElement(By.cssSelector("#memo"));
		action.moveToElement(memoContainer).perform();
		action.click();

		memoContainer.sendKeys("Don't forget to go for a bike ride tomorrow.....");

		WebElement userIdContainer = driver.findElement(By.cssSelector("#user_id"));
		action.moveToElement(userIdContainer).perform();
		action.click();

		action.click().perform();
		userIdContainer.sendKeys("1");

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
