package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Part2 {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Url() {
		driver.get("http://live.techpanda.org/");
		
		// Click vào My account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		    //driver.findElement(By.cssSelector("div.footer a[title='My Account']"));
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		 
		// Click vào Create an account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_Title() {
		driver.get("http://live.techpanda.org/");
		
		// Click vào My account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		    //driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		sleepInSecond(6);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		 
		// Click vào Create an account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(6);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_03_Navigate() {
		driver.get("http://live.techpanda.org/");
		
		// Click vào My account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		    //driver.findElement(By.cssSelector("div.footer a[title='My Account']"));
		sleepInSecond(6);
		 
		// Click vào Create an account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(6);
		
		// Back lại
		driver.navigate().back();
		sleepInSecond(6);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		// Forward
		driver.navigate().forward();
		sleepInSecond(6);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}   
	
	@Test
	public void TC_04_Page_Source_HTML() {
		driver.get("http://live.techpanda.org/");
		
		// Click vào My account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		    //driver.findElement(By.cssSelector("div.footer a[title='My Account']"));
		sleepInSecond(6);
		
		// Verify page HTML có chứa 1 chuỗi mong muốn 
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		// Click vào Create an account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(6);
		
		// Verify page HTML có chứa 1 chuỗi mong muốn 
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
				
	} 
	
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);// 1000 ms = 1s
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}