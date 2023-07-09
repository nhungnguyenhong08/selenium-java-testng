package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Part1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Tương tác với Browser thì sẽ thông qua biến WebDriver (Có thể đặt tên biến là Driver)
		
		// Tương tác với Element thì sẽ thông qua biến WebElement (Có thể đặt tên biến là Element)
	}

	@Test
	public void TC_01_() {
		// Java document (cách sử dụng hàm này như thế nào)
		
		// Khi có >= 2 cửa sổ:  Nó sẽ đóng tab/window mà nó đang đứng
		// Khi có 1 cửa sổ: Nó đóng browser (giống quit)
		driver.close(); //* (có thể dùng)
		
		
		// K quan tâm có bao nhiêu tab/windown -> Đóng browser
		driver.quit(); //** (hay dùng)
		
		// - Có thể lưu nó vào 1 biến để sử dụng cho các step sau -> dùng lại nhiều lần
		
		// Tìm 1 element
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']")); //**
		 
		 emailTextbox.clear();
		 emailTextbox.sendKeys("");
		 
		 // - Có thể sử dụng luôn (không cần tạo biến) - khi k sử dụng lại ở các step sau đó
		 driver.findElement(By.xpath("//button[@id='login']")).click();
		 driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		 
		 // Tìm nhiều element
		 List<WebElement> checkboxes = driver.findElements(By.xpath("")); //*
		 
		 // Mở ra 1 url nào đó
		 driver.get("https://www.facebook.com/"); //**
		 
		 
		 // Click vào link: Tiếng Việt
		 
		 // Trả về Url của page hiện tại
		 
		 // - Có thể lưu nó vào 1 biến để sử dụng cho các step sau -> dùng lại nhiều lần
		 String vietnamPageUrl = driver.getCurrentUrl();
		 
		 Assert.assertEquals(vietnamPageUrl, "https://vi-vn.facebook.com/");
		 
		 // - Có thể sử dụng luôn (không cần tạo biến)
		 Assert.assertEquals(driver.getCurrentUrl(), "https://vi-vn.facebook.com/");
		
		 
		 // Trả về Source Code HTML của page hiện tại
		 // Verify tương đối
		 driver.getPageSource();
		 
		 Assert.assertTrue(driver.getPageSource().contains("Đăng nhập gần đây"));
		 Assert.assertTrue(driver.getPageSource().contains("Đăng nhập gần"));
	
		 // Trả về title của page hiện tại
		 Assert.assertEquals(driver.getTitle(),"Facebook - Đăng nhập hoặc đăng ký");
		 
		 // lấy tra được ID của windown/ Tab mà driver đang đứng (active)
		 String loginWindowID = driver.getWindowHandle(); //*
		 
		 // Lấy ra ID của tất cả các windown/ Tab
		 Set<String> allIDs = driver.getWindowHandles(); //*
		 
		 // Cookies/ Cache
		 Options opt = driver.manage(); //*
		 
		 // Login thành công -> lưu lại
		 opt.getCookies();
		 
		 // Testcase khác -> Set cookie vào lại -> k cần login nữa
		 
		 Timeouts time = opt.timeouts();
		 
		 // Khoảng thờigian chờ element xuất hiện
		 time.implicitlyWait(5, TimeUnit.SECONDS); // 5s = 5000 ms = 5000000 micro sencond //**
		 time.implicitlyWait(5000, TimeUnit.MILLISECONDS); 
		 time.implicitlyWait(5000000, TimeUnit.MICROSECONDS); 
		 
		 // Khoảng thời gian chờ page load xong trong vòng x giây 
		 time.pageLoadTimeout(5, TimeUnit.SECONDS);// x = 5 giây
		 
		 // Khoảng thời gian chờ script được thực thi xong trong vòng x giây
		 time.setScriptTimeout(5, TimeUnit.SECONDS);
		 
	
		 Window win = opt.window();
		 win.fullscreen();
		 win.maximize(); //**
		 
		 // Test FUI: functional
		 // Test GUI: Font/Size/Color/position/location/....
		 win.getPosition();
		 win.getSize();
		 
		Navigation nav = driver.navigate();
		
		nav.back();
		nav.forward();
		nav.refresh();
		
		nav.to("https://www.facebook.com/");// "to" sẽ support cho history tốt hơn "get" cho (back/forward/refresh)
		
		driver.get("https://www.facebook.com/");// cách dùng thì dùng get nhiều hơn
		
		TargetLocator tar = driver.switchTo();
		
		// Bài WebDriver API - Alert
		tar.alert(); //*
		
		
		// Bài WebDriver API - Frame/Iframe
		tar.frame(""); //*
		
		
		// Bài WebDriver API - Windown/Tabs
		tar.window(""); //*
	}


	@Test
	public void TC_02_() {
	}

	@Test
	public void TC_03_() {
	}   

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}