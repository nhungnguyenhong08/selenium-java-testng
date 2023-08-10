package webdriver;

import java.io.IOException;
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

public class Topic_24_ExplicitWait {
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


	public void TC_01_Visible() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 3);
		
		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// Loading icon mất 5s mới biến mất => xuất hiện đoạn text
		
		// Wait cho text Hello world hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}

	public void TC_02_Invisible() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 5);

		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// Loading icon mất 5s mới biến mất => xuất hiện đoạn text
		
		// Wait cho loading icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}   

	public void TC_03_Ajax_Loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		explicitWait = new WebDriverWait(driver, 15);
		
		// Wait cho Date picker được hiển thị
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));
		
		
		// Verify cho selected dates là không có ngày nào được chọn
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		
		// Wait cho ngày 07 được phép click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='7']")));
		
		// Click vào ngày 07
		driver.findElement(By.xpath("//a[text()='7']")).click();
		
		// Wait cho Ajax icon loading biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1'] div.raDiv")));
		
		// Wait cho ngày 7 - vừa được click là được phép click trở lại
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected rcHover']//a[text()='7']")));

		// Verify cho Selected Date là "Monday, August 7, 2023"
		// cần find lại element vì sau khi click vào ngày 7/8/2023 -> page đã được refresh ngầm lại 
		// (dù span#ctl00_ContentPlaceholder1_Label1 giống hệt với trước khi click)
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Monday, August 7, 2023");

	}

	@Test
	public void TC_04_Upload_File() {
		
		driver.get("https://gofile.io/uploadFiles");
		
		explicitWait = new WebDriverWait(driver, 50);
		
		String beachFileName ="beach.jpg";
		String computerFileName ="computer.jpg";
		String mountainFileName ="mountain.jpg";
		
		String beachFilePath = projectPath + "\\Upload\\" + beachFileName;
		String computerFilePath = projectPath + "\\Upload\\" + computerFileName;
		String mountainFilePath = projectPath + "\\Upload\\" + mountainFileName;
		
		String autoITFirefoxMutipleTimePath = projectPath +  "\\autoIT\\firefoxUploadMultiple.exe";
		String autoITChromeMultipleTimePath = projectPath +  "\\autoIT\\chromeUploadMultiple.exe";
		
		// Wait cho Add files button được visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#filesUpload button.filesUploadButton")));
		
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(beachFilePath + "\n" + computerFilePath + "\n" + mountainFilePath);
		
		// Wait cho các loading icon của từng file biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.mainUploadFilesListDetails div.progress"))));
		
		// Wait cho Upload message thành công được visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-auto text-center']//div[text()='Your files have been successfully uploaded']")));
		
		// Verify message này displayed
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-auto text-center']//div[text()='Your files have been successfully uploaded']")).isDisplayed());
		
		// Wait +click download link khi hiển thị download link
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-6.text-center a.ajaxLink"))).click();
		

				
		// Wait + verify: cho filename với button download hiển thị
	
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + beachFileName + "']//parent::a//parent::div//following-sibling::div//span[text()='Download']//parent::button"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + computerFileName + "']//parent::a//parent::div//following-sibling::div//span[text()='Download']//parent::button"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + mountainFileName + "']//parent::a//parent::div//following-sibling::div//span[text()='Download']//parent::button"))).isDisplayed());

		// Wait + verify: cho filename với button download hiển thị
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + beachFileName + "']//parent::a//parent::div//following-sibling::div//button[contains(@class,'filesContentOptionPlay')]"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + computerFileName + "']//parent::a//parent::div//following-sibling::div//button[contains(@class,'filesContentOptionPlay')]"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + mountainFileName + "']//parent::a//parent::div//following-sibling::div//button[contains(@class,'filesContentOptionPlay')]"))).isDisplayed());
		
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}