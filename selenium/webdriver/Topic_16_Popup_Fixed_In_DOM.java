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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_Fixed_In_DOM {
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
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Fixed_In_DOM_NgoaiNgu() {
		driver.get("https://ngoaingu24h.vn/");
		
		By loginPopup = By.xpath("//div[@class='main-banner-panel']//preceding-sibling::div[@id='modal-login-v1']//div[@class='modal-content']");
		 
		// Verify popup is undisplayed
		
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
	
		
		driver.findElement(By.cssSelector("div.modal.fade.in input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("div.modal.fade.in input#password-input")).sendKeys("automationfc");

		driver.findElement(By.cssSelector("div.modal.fade.in button.btn-login-v1")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.modal.fade.in div.error-login-panel")).getText(),"Tài khoản không tồn tại!");

		}

	@Test
	public void TC_02_Fixed_In_DOM_KyNa() {
		driver.get("https://skills.kynaenglish.vn/");
		
		By loginPopup = By.cssSelector("div#k-popup-account-login");
		
		// Verify popup is undisplayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
		// Click vào button đăng nhập
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(2);
		
		// Verify popup is displayed
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456789");

		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		sleepInSecond(2);
		// Verify popup is undisplayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
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
		driver.quit();
	}
}