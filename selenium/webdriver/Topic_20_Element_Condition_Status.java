package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Element_Condition_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;

		
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
			
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 10);
	}
	// UI:   1.có trên UI,    2.không có trên UI
	// HTML: 1.có trong HTML, 2.không có trong HTML
	

	public void TC_01_Visible_Displayed_Visibility() {
		driver.get("https://www.facebook.com/");
		
		// 1. Có trên UI (bắt buộc)
		// 1. Có trong HTML (bắt buộc)
		
		// Wait cho email address textbox hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		
		}
	
	public void TC_02_Invisible_Undisplay_Unvisibility_I() {
		
	// 2.không có trên UI (bắt buộc)
	// 1.có trong HTML
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		
	// Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
		
	
	}
	
	public void TC_02_Invisible_Undisplay_Unvisibility_II() {
		
		// 2.không có trên UI (bắt buộc)
		// 2.không có trong HTML
		
	 driver.get("https://www.facebook.com/");
		
	// Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
		
	}   
	

	public void TC_03_Presence_I() {
		driver.get("https://www.facebook.com/");
		
		// 1. Có trên UI (bắt buộc)
		// 1. Có trong HTML (bắt buộc)
		
		// Chờ cho email address textbox presence trong HTML trong vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));

	}


	public void TC_03_Presence_II() {
		// 2. không có trên UI 
		// 1. Có trong HTML (bắt buộc)
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		
	// Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
		
		
	}
	
	@Test
	public void TC_04_Staleness() {
		
		// 2.không có trên UI (bắt buộc)
		// 2.không có trong HTML
		
	 driver.get("https://www.facebook.com/");
	 
	 driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

	 // Phase 1: Element có trong cây HTML
	 WebElement reEnterEmailAddressTextbox = driver.findElement(By.name("reg_email_confirmation__"));
		
	 
	 // Thao tác với element .., làm cho element re-enter không còn trong DOM nữa
	 // ....
	 
	 // Close pop up đi
	 driver.findElement(By.cssSelector("img._8idr.img")).click();
	 
	// Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
	explicitWait.until(ExpectedConditions.stalenessOf(reEnterEmailAddressTextbox));
		
		
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