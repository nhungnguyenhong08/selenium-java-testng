package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String authenChrome = projectPath + "\\autoIT\\authen_chrome.exe";
	String authenFirefox = projectPath + "\\autoIT\\authen_firefox.exe";
	String username = "admin";
	String password = "admin";

	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
			
		
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);
		
		// Có thể switch qua và tương tác luôn
		alert = driver.switchTo().alert();
		
		// 2 - cần wait trước khi nào nó xuất hiện thìe mới switch qua và tương tác
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Verify alert title đúng như mong đợi
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		// Accept alert này
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
		
		}

	//@Test
	public void TC_02_Confirm_Alert() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	
	driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
	sleepInSecond(3);
	
	// 2 - cần wait trước khi nào nó xuất hiện thìe mới switch qua và tương tác
	alert = explicitWait.until(ExpectedConditions.alertIsPresent());
	
	// Verify alert title đúng như mong đợi
	
	Assert.assertEquals(alert.getText(), "I am a JS Confirm");
	
	// Accept alert này
	alert.dismiss();
	
	Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	
	}
	
	//@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(3);
		
		// 2 - cần wait trước khi nào nó xuất hiện thì mới switch qua và tương tác
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Verify alert title đúng như mong đợi
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		// Nhập text vào alert
		String courseName = "Fullstack Selenium Java";
		
		alert.sendKeys(courseName);
		sleepInSecond(3);
		
		// Accept alert này
		alert.accept();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered:" + " " + courseName);

	}   

	//@Test
	public void TC_04_Authentication_Alert() {
		
		// Cách 1: Truyền trực tiếp Username/ Password vào trong chính Url này -> tự động signIn luôn
		// http:// + Username : Password @ the-internet.herokuapp.com/basic_auth
		// driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
		
		// Dùng hàm
		driver.get(passUserAndPassToUrl("https://the-internet.herokuapp.com/basic_auth", "admin", "admin"));

		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
		// Ví dụ: từ url trang A - trang B mới hiển thị authentication alert
		
		driver.get("https://the-internet.herokuapp.com/");
		
		String authenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		driver.get(passUserAndPassToUrl(authenUrl, "admin", "admin"));

		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());	
	}  
	
	
	@Test
	public void TC_04_Authentication_Alert_II() throws IOException {
		// Dùng AutoIT khi k còn cách nào khác vì
		// Chỉ dùng cho Windows (k dùng cho mac/linux)
		// Không chạy ở trên các CI khác được (headless - k bật lên giao diện)
		
		if (driver.toString().contains("firefox")) {
			//Runtime.getRuntime().exec: Thực thi 1 file exe trong code java
			Runtime.getRuntime().exec(new String[] {authenFirefox, username, password});
		} else if(driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] {authenChrome, username, password});
		}
		
		driver.get("https://the-internet.herokuapp.com/basic_auth");
		sleepInSecond(5);
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());	

	}
	
	public String passUserAndPassToUrl(String url, String username, String password) {
		String[]arrayUrl = url.split("//");
		
		return arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];
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