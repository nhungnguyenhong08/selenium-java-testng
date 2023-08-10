package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Mix_Implicit_Explicit {
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
		driver.manage().window().maximize();
		
	}
	public void TC_01_Element_Found() {
		// Element có xuất hiện và không cần chờ hết timeout
		// Dù có set cả 2 loại Wait thì k ảnh hưởng
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		
		driver.get("https://www.facebook.com/");
		
	
		// Explicit
		System.out.println("Thời gian bắt đầu của explicit: " + getTimeStamp());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		System.out.println("Thời gian kết thúc của explicit: " + getTimeStamp());
		
		// Implicit
		System.out.println("Thời gian bắt đầu của implicit: " + getTimeStamp());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("Thời gian kết thúc của implicit: " + getTimeStamp());
	}
	
	public void TC_02_Element_Not_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
		
		// Implicit
				System.out.println("Thời gian bắt đầu của implicit: " + getTimeStamp());
				try {
					driver.findElement(By.cssSelector("input#selenium"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Thời gian kết thúc của implicit: " + getTimeStamp());
				}
	}

	public void TC_03_Element_Not_Found_Implicit_Explicit() {
			//3.1. Implicit = Explicit
			//3.2. Implicit < Explicit: Thời gian chạy testcase = thời gian timeout của explicitwait + chênh lệch 
			//     giữa thời gian bắt đầu apply implicit và explicit
			//3.3. Implicit > Explicit: Thời gian chạy testcase = thời gian timeout của implicitwait
		
			// Ưu tiên find element trước => implicit sẽ được áp dụng trước
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			explicitWait = new WebDriverWait(driver, 5);
			
			driver.get("https://www.facebook.com/");
			
					
			// Explicit
					System.out.println("Thời gian bắt đầu của explicit: " + getTimeStamp());
					try {
						explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Thời gian kết thúc của explicit - timeout exception " + getTimeStamp());
					}
					
		}  

	
	public void TC_04_Element_Not_Found_Explicit_By() {
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		
		// Explicit - By là tham số nhận vào của hàm explicit - visibilityOfElementLocated(By)
		// Tham số là By thì Implicit = 0
		// Thời gian chạy testcase = explicit
		System.out.println("Thời gian bắt đầu của explicit: " + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của explicit - timeout exception " + getTimeStamp());
		}
	}
	
	@Test
	public void TC_05_Element_Not_Found_Explicit_Element() {
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		
		System.out.println("Thời gian bắt đầu của explicit: " + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#selenium"))));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của explicit - timeout exception " + getTimeStamp());
		}
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

	// Show ra time-stamp tại thời điểm gọi hàm này
	// time-stamp = ngày-giờ-phút-giây
	public String getTimeStamp() {
		Date date = new Date();
		return date.toString();

	}
}							