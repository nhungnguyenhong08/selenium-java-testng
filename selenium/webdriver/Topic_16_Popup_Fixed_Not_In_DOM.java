package webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_Fixed_Not_In_DOM {
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
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		
		driver = new ChromeDriver(options);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Fixed_Not_In_DOM_TIKI() {
		driver.get("https://tiki.vn/");
		
		// Sử dụng biến By: Nó chưa đi tìm element
		By loginPopup = By.cssSelector("div.ReactModal__Content>div");
		
		// Verify nó chưa hiển thị pop up khi chưa click vào login button
		Assert.assertEquals(driver.findElements(loginPopup).size(),0);
		
				
		// Click cho bật pop up login lên
		driver.findElement(By.cssSelector("div[data-view-id=header_header_account_container]")).click();
		sleepInSecond(3);
		
		// Verify nó đã hiển thị pop up khi click vào login button
		// cach 1: 
		Assert.assertEquals(driver.findElements(loginPopup).size(),1);
		// Cách 2: 
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		// Click "Đăng nhập bằng email"
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSecond(2);
		
		// Click nút đăng nhập 
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		
		// Verify hiển thị thông báo khi bỏ trống trường bắt buộc
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).isDisplayed());
		sleepInSecond(2);
		
		// Nhập 1 sdt
		//driver.findElement(By.cssSelector("input[name='tel']")).sendKeys("0969254111");
		//sleepInSecond(2);
		
		// Đóng pop up
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(2);
		
		// Verify không còn hiển thị pop up login button
		Assert.assertEquals(driver.findElements(loginPopup).size(),0);
				
		}

	//@Test
	public void TC_02_Fixed_In_DOM_Facebook() {
    driver.get("https://www.facebook.com/");
		
		// Sử dụng biến By: Nó chưa đi tìm element
		By createAccountPopup = By.xpath("//div[text()='Sign Up']//parent::div//parent::div[@class='_8ien']");
		
		// Verify nó chưa hiển thị pop up khi chưa click vào Create new account button
		Assert.assertEquals(driver.findElements(createAccountPopup).size(),0);
		
		// Click buton Create new account
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		
		// Verify nó đã hiển thị pop up khi chưa click vào Create new account button
		Assert.assertEquals(driver.findElements(createAccountPopup).size(),1);
				
		// nhập liệu
		driver.findElement(By.name("firstname")).sendKeys("Automaiton");
		driver.findElement(By.name("lastname")).sendKeys("FC");
		driver.findElement(By.name("reg_email__")).sendKeys("0962145874");
		driver.findElement(By.name("reg_passwd__")).sendKeys("123456789");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("18");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("Aug");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1999");
		driver.findElement(By.xpath("//label[text()='Female']//following-sibling::input")).click();
		sleepInSecond(2);

		// Close pop up
		driver.findElement(By.xpath("//div[text()='Sign Up']//parent::div//preceding-sibling::img")).click();
		sleepInSecond(2);
		
		// Verify không còn hiển thị pop up 
		Assert.assertEquals(driver.findElements(createAccountPopup).size(),0);
		
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