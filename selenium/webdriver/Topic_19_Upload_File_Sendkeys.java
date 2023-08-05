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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Upload_File_Sendkeys {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;
	JavascriptExecutor jsExecutor;

	String beachFileName ="beach.jpg";
	String computerFileName ="computer.jpg";
	String mountainFileName ="mountain.jpg";
	String tabletFileName ="tablet.jpg";
	
	String beachFilePath = projectPath + "\\Upload\\" + beachFileName;
	String computerFilePath = projectPath + "\\Upload\\" + computerFileName;
	String mountainFilePath = projectPath + "\\Upload\\" + mountainFileName;

	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
			
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
		
	}

	//@Test
	public void TC_01_One_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		// Load file lên
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(beachFilePath);
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(computerFilePath);
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(mountainFilePath);
		sleepInSecond(2);
		
		// Verify file được load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + beachFileName + "' and @class='name']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + computerFileName + "' and @class='name']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + mountainFileName + "' and @class='name']")).isDisplayed());

		// Click upload
		List<WebElement> buttonUpload =	driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : buttonUpload) {
			button.click();
			sleepInSecond(2);	
		}
	
		// Verify upload thành công (link)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + beachFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + computerFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + mountainFileName + "']")).isDisplayed());

		// Verify upload thành công (image)
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + beachFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + computerFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + mountainFileName + "')]"));
	
	}

	@Test
	public void TC_02_Multiple_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		// Load file lên
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(beachFilePath + "\n" + computerFilePath + "\n" + mountainFilePath);
		sleepInSecond(2);
		
		
		// Verify file được load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + beachFileName + "' and @class='name']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + computerFileName + "' and @class='name']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + mountainFileName + "' and @class='name']")).isDisplayed());

		// Click upload
		List<WebElement> buttonUpload =	driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : buttonUpload) {
			button.click();
			sleepInSecond(5);	
		}
	
		// Verify upload thành công (link)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + beachFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + computerFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + mountainFileName + "']")).isDisplayed());

		// Verify upload thành công (image)
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + beachFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + computerFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + mountainFileName + "')]"));
	
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
	
	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}