package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.GetAllWindowHandles;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Window_Tab {
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
	public void TC_01_ID() {
		// Parent page
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Lấy ra được ID của tab hiện tại
		String basicFormID = driver.getWindowHandle();
		System.out.println(basicFormID);
		
		// Click vào google link để bật ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		
		// Nếu như các ID nào mà khác với ID của parent thì switch
		
		switchToWindowByID(basicFormID);
		
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.google.com.vn/");
		
		String googleWindowID = driver.getWindowHandle();
		
		switchToWindowByID(googleWindowID);
		
		Assert.assertEquals(driver.getCurrentUrl(),"https://automationfc.github.io/basic-form/index.html");
	}	
	
	
		// TODO Auto-generated method stub
		

	//@Test
	public void TC_02_Title() {
		// Parent page
		driver.get("https://automationfc.github.io/basic-form/index.html");;
		String parentID = driver.getWindowHandle();
		
		// Click vào google link để bật ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		
		switchToWindowByPageTitle("Google");
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.google.com.vn/");
		driver.findElement(By.name("q")).sendKeys("Selenium");
		
		switchToWindowByPageTitle("Selenium WebDriver");
		
		// Click vào facebook link để bật ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);
		
		switchToWindowByPageTitle("Facebook – log in or sign up");
		driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.cssSelector("input#pass")).sendKeys("Password123");

		switchToWindowByPageTitle("Selenium WebDriver");
		Assert.assertEquals(driver.getCurrentUrl(),"https://automationfc.github.io/basic-form/index.html");

		// Click vào tiki link để bật ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(2);
		
		switchToWindowByPageTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		driver.findElement(By.cssSelector("input[class^='FormSearchStyle']")).sendKeys("Macbook");
	
		closeAllWindowWithoutParent(parentID);
		sleepInSecond(3);
	}
	
	
	
	@Test
	public void TC_03_Live_Guru() {
		
		driver.get("http://live.techpanda.org/");
		
		String parentID = driver.getWindowHandle();
		
		// Click vào Mobile
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		// Click vào Samsung Galaxy - Add to compare
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		  
		
		// Click vào Sony Xperia - Add to compare
		driver.findElement(By.xpath("//a[@title='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Sony Xperia has been added to comparison list.");

		driver.findElement(By.cssSelector("button[title='Compare']")).click();
		
		switchToWindowByPageTitle("Products Comparison List - Magento Commerce");
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());
		
		//driver.findElement(By.cssSelector("button[title='Close Window']")).click();
		closeAllWindowWithoutParent(parentID);
		sleepInSecond(3);
		
		//switchToWindowByPageTitle("Mobile");
		
		// click vào iphone
		driver.findElement(By.xpath("//a[@title='IPhone']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product IPhone has been added to comparison list.");

	}
	// Dùng được cho duy nhất 2 window/tab
	public void switchToWindowByID(String otherID) {
		// Lấy hết tất cả các ID ra
		Set<String> allWindowIDs = driver.getWindowHandles();
		System.out.println(allWindowIDs);
		
		// Sau đó dùng vòng lặp duyệt qua và kiểm tra
		for (String id : allWindowIDs) {
			if (!id.equals(otherID)) {
				driver.switchTo().window(id);
				sleepInSecond(2);
			}
		}
	}
	
	// dùng cho từ 2 window/tab trở lên
	public void switchToWindowByPageTitle(String expectedPageTitle) {
		
		// Lấy hết tất cả các ID ra
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		
		// Sau đó dùng vòng lặp duyệt qua và kiểm tra
		for (String id : allWindowIDs) {
			// Switch từng ID trước
			driver.switchTo().window(id);
		
			
			// Lấy ra title của page này
			String actualPageTitle = driver.getTitle();

			
			if (actualPageTitle.equals(expectedPageTitle)) {
				sleepInSecond(1);
				break;
				
			}
		}
	}
		
	public void closeAllWindowWithoutParent(String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
				sleepInSecond(2);
			}
		}
		driver.switchTo().window(parentID);
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