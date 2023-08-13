package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Functions;

public class Topic_26_Fluent_Wait {
	private static final Class<? extends Throwable> NoSuchElementException = null;
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
	
	long allTime = 22; // second
	long pollingTime = 100; // milisecond
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
			
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}


	public void TC_01_Fluent() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		findElement("//div[@id='start']/button").click();
		
		Assert.assertEquals(findElement("//div[@id='finish']/h4").getText(),"Hello World!");
	}
	

	@Test
	public void TC_02_Fluent_Countdown() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countdownTime = findElement("//div[@id='javascript_countdown_time']");
		
		fluentElement = new FluentWait<WebElement>(countdownTime);
		
		fluentElement.withTimeout(Duration.ofSeconds(allTime))
		.pollingEvery(Duration.ofMillis(pollingTime))
		.ignoring(NoSuchElementException.class);
		
		fluentElement.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement element) {
				String text = element.getText();
				System.out.println(text);
				return text.endsWith("00");
			}
			
		});
	}

	public WebElement findElement(String xpathLocator) {
		fluentDriver = new FluentWait<WebDriver>(driver);
		
		// Set tổng thời gian và tần suất
		fluentDriver.withTimeout(Duration.ofSeconds(allTime))
			// 1/10 giây check lại 1 lần
			.pollingEvery(Duration.ofMillis(pollingTime)).ignoring(NoSuchElementException.class);
		
		// Apply điều kiện
		return fluentDriver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(By.xpath(xpathLocator));
			}
		});
	}


	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}