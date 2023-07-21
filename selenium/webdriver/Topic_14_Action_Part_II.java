package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Action_Part_II {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

		
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
			
		
		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Click_And_Hold_Block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> listNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		// Đang chứa 12 số/items trong list này
		
		// 1 - Click vào số 1 // 2 - Vẫn giữ chuột/ chưa nhả ra
			action.clickAndHold(listNumbers.get(0))
		
		// 3 - Di chuột tới số (target)
			.moveToElement(listNumbers.get(7))
		
		// 4 - Nhả chuột trái ra
		
			.release()
		
		// Execute
			.perform();
		
		sleepInSecond(2);
		
		// Verify
		List<WebElement> listSelectednumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));

		Assert.assertEquals(listSelectednumber.size(), 8);
		
		}

	@Test
	public void TC_02_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// Chạy được cho cả windows/ mac
		Keys key = null;
		if (osName.contains("Windows")) {
			key = Keys.CONTROL;
		}else {
			key = Keys.COMMAND;
		}
		
		List<WebElement> listNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		// Đang chứa 12 số/items trong list này
		
		// Nhấn Ctrl 
		action.keyDown(Keys.CONTROL).perform();
		
		// Click chọn các số random
		action.click(listNumbers.get(0))
		.click(listNumbers.get(3))
		.click(listNumbers.get(5))
		.click(listNumbers.get(10)).perform();
		
		// Nhả phím Ctrl ra
		action.keyUp(key).perform();
		
		sleepInSecond(3);
		
		List<WebElement> listSelectednumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));

		Assert.assertEquals(listSelectednumber.size(), 4);
		
	}

	@Test
	public void TC_03_() {
	}   

	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000); // 1000 ms = 1s
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}