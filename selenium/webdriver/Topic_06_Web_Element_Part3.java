package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Part3 {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress, firstName, lastName, password, fullName;

		
		@BeforeClass
		public void beforeClass() {
			if (osName.contains("Windows")) {
				System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			} else {
				System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
			}
			
			
		rand = new Random();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		emailAddress = "automation" + rand.nextInt(9999)+"@gmail.com";
		firstName = "Automation";
		lastName = "FC";
		fullName = firstName + " " + lastName;
		password = "12345678";
	}

	@Test
	public void TC_01_Login_Empty_Email_And_Password() {
		
		driver.get("http://live.techpanda.org/");
		
		// Click vào My account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),"This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),"This is a required field.");
	
	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		driver.get("http://live.techpanda.org/");
		
		// Click vào My account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.id("email")).sendKeys("123456.1234566@123334");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
		
	
	}

	@Test
	public void TC_03_Login_Password_Less_6charater() {
		driver.get("http://live.techpanda.org/");
		
		// Click vào My account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
}
		
	@Test
	public void TC_04_Login_Incorrect_Email_And_Password() {
		driver.get("http://live.techpanda.org/");
			
		// Click vào My account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
			
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys("123123123");
			
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2);
			
		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),"Invalid login or password.");
			
	}   

	
	@Test
	public void TC_05_Create_Account() {
		// Click vào My account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "Thank you for registering with Main Website Store.");
		
		String contactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']//parent::div/following-sibling::div/p")).getText();
		System.out.println(contactInformationText);
		
		Assert.assertTrue(contactInformationText.contains(fullName));
		Assert.assertTrue(contactInformationText.contains(emailAddress));
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
		
	}
	
	@Test
	public void TC_06_Login_Valid_Infor() {
		// Click vào My account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys(password);
			
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2);
		
		String contactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']//parent::div/following-sibling::div/p")).getText();
		System.out.println(contactInformationText);
		
		Assert.assertTrue(contactInformationText.contains(fullName));
		Assert.assertTrue(contactInformationText.contains(emailAddress));
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
		driver.quit();
	}
}