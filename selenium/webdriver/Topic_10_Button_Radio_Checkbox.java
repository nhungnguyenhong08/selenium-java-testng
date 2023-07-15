package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_Radio_Checkbox {
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
			
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		By loginButton = By.cssSelector("button.fhs-btn-login");
		
		// Verify login button is disable
		
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		String loginButtonBackground = driver.findElement(loginButton).getCssValue("background-image");
		System.out.println(loginButtonBackground);
		
		Assert.assertTrue(loginButtonBackground.contains("rgb(224, 224, 224)"));
		
		// Nhập dữ liệu hợp lệ
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0969214569");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");
		sleepInSecond(2);
		
		// Verify login button is disable
		
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
				
		loginButtonBackground = driver.findElement(loginButton).getCssValue("background-color");
		System.out.println(loginButtonBackground);
		
		Color loginButtonBackgroundColour = Color.fromString(loginButtonBackground);
		// Chuyển qua HEXA để verify (từ rgb/rgba qua hexa thay vì verify bằng rgb/rgba do
		// có thể ở trình duyệt khác khi run testcase sẽ dùng bảng màu rgba)
		Assert.assertEquals(loginButtonBackgroundColour.asHex().toUpperCase(), "#C92127");		
		
		}

	//@Test
	public void TC_02_Default_Checkbox_Radio_Single() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		// Click chọn 1 checkbox
		driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).click();
		
		// Click chọn 1 radio
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).click();
		
		// Verify các checkbox/radio đã chọn
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).isSelected());

		// Checkbox có thể tự bỏ chọn được
		driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).click();
		// Verify checkbox đã được bỏ chọn rồi
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).isSelected());

		
		// Radio không thể bỏ chọn được
		// Verify radio vẫn được chọn rồi
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).isSelected());

	}

	//@Test
	public void TC_03_Default_Checkbox_Radio_Multiple() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> allCheckBoxes= driver.findElements(By.cssSelector("input.form-checkbox"));
		
		// Dùng vòng lặp để duyệt qua và click vào tất cả các checkbox này
		
		for (WebElement checkbox : allCheckBoxes) {
			checkbox.click();
			sleepInSecond(1);	
		}
		// Verify tất cả các checkbox được chọn thành công
		for (WebElement checkbox : allCheckBoxes) {
			Assert.assertTrue(checkbox.isSelected());
		}	
		
		// Nếu gặp 1 checkbox có tên là X thì mới click
		for (WebElement checkbox : allCheckBoxes) {
			if (checkbox.getAttribute("value").equals("Diabetes")){
				checkbox.click();
			}
		}
	}
	//@Test
	public void TC_04_Default_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		// Click chọn 1 checkbox
		checktoCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
					
		// Verify các checkbox đã được chọn
			Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());

		// Bỏ chọn Checkbox 
		unchecktoCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		
		// Verify checkbox đã được bỏ chọn rồi
			Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());

	} 
	@Test
	public void TC_05_Default_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		// Click chọn 1 radio button
		if (!driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input")).click();
		}
		driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input")).click();
					
		// Verify radio button đã được chọn
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input")).isSelected());
	
		// Radio không thể bỏ chọn được
		// Verify radio vẫn được chọn rồi
		//driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input")).click();
		if (driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input")).click();
		}
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input")).isSelected());		
	}
		

	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000); // 1000 ms = 1s
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void checktoCheckbox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void unchecktoCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}