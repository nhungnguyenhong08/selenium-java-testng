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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Wait_Page_Ready {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		action = new Actions(driver);
		explicitWait = new WebDriverWait(driver, 30);
	}


	public void TC_01_Orange_HRM_API() {
		driver.get("https://api.orangehrm.com/");
		
		// Wait cho loading icon biến mất
		// Vì khi nó biến mất thì cái trang orange hrm sẽ load hết dữ liệu về thành công
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loader div.spinner")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(), "OrangeHRM REST API Documentation");
		}

	
	public void TC_02_Admin_NopCommerce() {
		driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2FAdmin");
		
		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		
		driver.findElement(By.cssSelector("input#Password")).clear();
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
		
		// Click chuyển trang từ Login vào Dashboard
		driver.findElement(By.cssSelector("button.login-button")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		// Click chuyển trang từ Dashboard về login (ấn logout)
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		Assert.assertEquals(driver.getTitle(),"Your store. Login");
		
		
	}

	@Test
	public void TC_03_Blog_Test_Project() {
		driver.get("https://blog.testproject.io/");
		
		// Hover chuột vào 1 element bất kì tại page này để cho page ready
		action.moveToElement(driver.findElement(By.cssSelector("h1.main-heading"))).perform();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		String keyword = "Selenium";
		
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys(keyword);
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		
		// Wait cho page heading visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='main-heading' and text()='Search Results']")));
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> postArticles = driver.findElements(By.cssSelector("h3.post-title>a"));
		
		for (WebElement article : postArticles) {
			Assert.assertTrue(article.getText().contains(keyword));
			
		}
	}
	public boolean waitForAjacBussyLoadingInvisible() {
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
	}
	public boolean isPageLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}