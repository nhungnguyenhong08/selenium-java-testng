package webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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

public class Topic_16_Popup_Random {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress = "testdemo" + getRandomNumber() + "@gmail.com";

		
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
	
	// Yêu cầu:
	// Radom pop up nên nó có thể hiển thị 1 cách ngẫu nhiên hoặc k hiển thị
	// Nếu như nó hiển thị thì mình cần thao tác lên popup -> đóng popup để qua step tiếp theo
	// Khi mà đóng nó lại thì có thể refresh trang nó hiện lên lại/hoặc là không
	// Nếu như nó không hiển thị thì qua step tiếp theo luôn

	//@Test
	public void TC_01_Random_In_DOM_Java_Code_Geeks() {
		driver.get("https://www.javacodegeeks.com/");
		
		sleepInSecond(30);
		
		By lePopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
		
		// Do pop up luôn có trong DOM nên có thể dùng hàm isDisplayed() để kiểm tra được
		if (driver.findElement(lePopup).isDisplayed()) {
			// Nhập email vào
			driver.findElement(By.cssSelector("div.lepopup-input>input")).sendKeys(emailAddress);
			sleepInSecond(3);
			
			driver.findElement(By.cssSelector("a[data-label='OK'],[data-label='Get the Books']>span")).click();
			sleepInSecond(10);
			
			// Verify 
			Assert.assertEquals(driver.findElement(By.cssSelector("div.lepopup-element-html-content>h4")).getText(),"Thank you!");
			Assert.assertTrue(driver.findElement(By.cssSelector("div.lepopup-element-html-content>p")).getText().contains("Your sign-up request was successful."));

			// Đóng popup đi >> Qua step tiếp theo
			// Sau khoảng 5s popup sẽ tự động đóng
			sleepInSecond(15);
		}
		// Qua step tiếp theo
			String articleName= "Agile Testing Explained";
		
			driver.findElement(By.cssSelector("input#search-input")).sendKeys(articleName);
			driver.findElement(By.cssSelector("button#search-submit")).click();
			sleepInSecond(3);
			
			Assert.assertEquals(driver.findElement(By.cssSelector("ul#posts-container>li:first-child>div>h2>a")).getText(), articleName);
			
		}

	//@Test
	public void TC_02_Random_In_DOM_Vnk_Edu() {
    driver.get("https://vnk.edu.vn/");
    sleepInSecond(40);
    
    By popup = By.cssSelector("div#tve_editor");
    
    if (driver.findElement(popup).isDisplayed()) {
    	// Close popup này đi hoặc là click vào link xem lịch giảng dạy
		driver.findElement(By.cssSelector("div.thrv_icon")).click();
		sleepInSecond(3);
    }
	driver.findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
	sleepInSecond(5);
		
	Assert.assertEquals(driver.getTitle(),"Lịch khai giảng các khóa học tại VNK EDU | VNK EDU");
	
		
		} 

	@Test
	public void TC_03_Random_Not_In_DOM_De_Hieu() {
		 driver.get("https://dehieu.vn/");
		    sleepInSecond(5);
		    
		    By popup = By.cssSelector("div.popup-content");
		    
		    
		    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		    
		    // verify nếu popup hiển thị // isDisplayed()
		    if (driver.findElements(popup).size()>0 && driver.findElements(popup).get(0).isDisplayed()) {
				driver.findElement(By.id("popup-name")).sendKeys("Elon Musk");
				driver.findElement(By.id("popup-email")).sendKeys(emailAddress);
				driver.findElement(By.id("popup-phone")).sendKeys("0982541214");
				sleepInSecond(3);
				
				driver.findElement(By.cssSelector("button#close-popup")).click();
				sleepInSecond(3);
				
			}
		    
		   driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		   sleepInSecond(3);
		   
		   String courseName = "Khóa học Thiết kế và Thi công Hệ thống BMS";
		   driver.findElement(By.cssSelector("input#search-courses")).sendKeys(courseName);
		   driver.findElement(By.cssSelector("button#search-course-button")).click();
		   sleepInSecond(3);
		   
		   // Duy nhất 1 khóa học hiển thị
		   Assert.assertEquals(driver.findElements(By.cssSelector("div.course-content ")).size(),1);
		   Assert.assertEquals(driver.findElement(By.cssSelector("div.course-content h4")).getText(),courseName);
		
		} 
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000); // 1000 ms = 1s
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getRandomNumber() {
		return new Random().nextInt(99999);
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}