package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	// By chưa đi tìm element ngay nên có thể khai báo trước khi mở page đi tìm element
	By emailTextboxBy = By.id("Email");
	By passwordTextboxBy = By.id("Pass");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.Chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.Chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver(); 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_00_Register() {
		// Mở trang web 
		driver.get("https://www.nopcommerce.com/vi/login?returnUrl=%2Fvi%2Fdemo");
		
		driver.findElement(emailTextboxBy).sendKeys("");
		driver.findElement(passwordTextboxBy).sendKeys("");
	}
	
	@Test
	public void TC_01_WebElement() {
		// Tương tác được với Element thì cần phải tìm dược element đó
		// Thông qua các locator của nó
		
		// By: id/ class/ name/ xpath/ css/ tagname/ linktext/ partialLinktext
	
		driver.get("https://www.nopcommerce.com/vi/login?returnUrl=%2Fvi%2Fdemo");
		
		// Khi mà element này dùng lại nhiều lần -> Khai báo biến
		WebElement emailTextbox = driver.findElement(By.id("Email"));
		emailTextbox.isDisplayed();
		emailTextbox.clear();
		emailTextbox.sendKeys("");
		
		// Khi dùng 1 lần -> không cần khai báo biến
		driver.findElement(By.id("Email")).sendKeys("");
		////////////
		
		WebElement element = driver.findElement(By.className(""));
		
		// Dùng cho các textbox/ textarea/ dropdown (editable)
		element.clear(); // xóa dữ liệu đi trước khi nhập text //*
		
		element.sendKeys(""); // nhập dữ liệu //**
		
		element.click(); //click vào các button/ link/ radio/ image/ check box/... //**
		
		String searchAttribute = element.getAttribute("placeholder"); // lấy ra chữ Search store //**
		
		// GUI: Font/ size/ color/ Location/ Position/.. // Ít sử dụng do thời gian dành cho test FUI được ưu tiên trước
		element.getCssValue("background-color"); // ví dụ: Biết button có màu là gì // lấy ra bất kỳ attribute nào của css //*
		
		element.getLocation(); // Vị trí của element so với Web (bên ngoài)
		
		element.getSize();// Kích thước của element (bên trong)
		
		element.getRect(); // Location + size
		
		element.getScreenshotAs(OutputType.FILE); // chụp hình khi testcase fail //*
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.BASE64);
		
		element.getTagName(); // Cần lấy ra tên thẻ HTML của element đó -> truyền vào cho 1 locator khác
		
		driver.findElement(By.id("Email")).getTagName();
		driver.findElement(By.name("Email")).getTagName();
		
		String emailTextboxTagname = driver.findElement(By.cssSelector("#Email")).getTagName(); // thẻ imput
		driver.findElement(By.xpath("//" + emailTextboxTagname+ "[@id='email']"));
		
		
		element.getText(); // Lấy text từ Error message/sucess massage/ lable/ header/... //**
		
		// Khi nào dùng getText - getAttribute:
		// getText: Khi value mình cần lấy nằm bên ngoài thẻ
		// getAttribute: Khi value mình cần lấy nằm bên trong attribute
		
		element.isDisplayed(); // Dùng để verify 1 element hiển thị hay không. Phạm vi: Tất cả các element //**
		Assert.assertTrue(element.isDisplayed()); 
		Assert.assertFalse(element.isDisplayed());
		
		element.isEnabled(); // Dùng để verify xem 1 element có thao tác được hay không. Phạm vi: Tất cả các element
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		
		element.isSelected(); // Dùng để verify xem 1 element được chọn hay chưa. Phạm vi: checkbox/ Radio button //*
		Assert.assertTrue(element.isSelected()); 
		Assert.assertFalse(element.isSelected());
		
		element.submit(); // Element trong 1 form (thẻ form). Tương ứng với hành vi của end user khi ấn Enter
}
	
  

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}