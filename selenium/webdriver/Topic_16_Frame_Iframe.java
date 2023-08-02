package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Frame_Iframe {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;
	JavascriptExecutor jsExecutor;

		
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
			
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Kyna_Iframe() {
		driver.get("https://skills.kynaenglish.vn/");
		
		// Verify FB iframe hiển thị
		// Facebook iframe vẫn là 1 element của trang kyna
		Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")).isDisplayed());
		
		// Verify số lượng like của fb hiển thị
		// Cần phải switch vào đúng thẻ ifram chưa element đó
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")));
		
		String facebookFollowers = driver.findElement(By.xpath("//a[text()='Kyna.vn']//parent::div//following-sibling::div")).getText();
		
		System.out.println(facebookFollowers);
		
		Assert.assertEquals(facebookFollowers,"166K followers");
		
		// Cần switch về main page
		driver.switchTo().defaultContent();
		
		// Từ main page switch qua iframe chat
		driver.switchTo().frame("cs_chat_iframe");
	
		
		// Click vào Chat để show lên chat support
		driver.findElement(By.cssSelector("div.button_bar")).click();
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("John Kennedy");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0969254712");
		new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.name("message")).sendKeys("Tư vấn khóa học Excel");
		sleepInSecond(3);
		
		// Cần switch về main page
		driver.switchTo().defaultContent();
		
		// Search với từ khóa Excel
		driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List <WebElement> courseName = driver.findElements(By.cssSelector("ul.k-box-card-list>li"));
		
		for (WebElement course : courseName) {
			Assert.assertTrue(course.getText().contains("Excel"));
		}
	}

	@Test
	public void TC_02_HDFC_Bank_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		// switch qua frame chứa login textbox
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.name("fldLoginUserId")).sendKeys("john2022");
		driver.findElement(By.cssSelector("a.login-btn")).click();
	
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