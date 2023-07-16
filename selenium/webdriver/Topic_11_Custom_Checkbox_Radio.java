package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Checkbox_Radio {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		
		// Luôn khởi tại sau biến driver này
		jsExecutor = (JavascriptExecutor)driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(3);
		
		/* CASE 1 */
		// Thẻ input bị che nên không thao tác được (k click được)
		// Thẻ input lại dùng để verify được -> vì hàm isSelected() nó chỉ work với thẻ input
		
		// Thao tác chọn
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div//input")).click();
		
		// Verify đã chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div//input")).isSelected());
		}

	//@Test
	public void TC_02_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(3);
		
		/* CASE 2 */
		// Thẻ thẻ khác với input để click (span/div/lable/...)-> Đang hiển thị (ở trình duyệt chrome: khi hover chuột vào nó ra hình răng cưa và màu tím)
		// Thẻ input lại dùng để verify được -> vì hàm isSelected() nó chỉ work với thẻ input
		
		// Thao tác chọn
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).click();
		
		// Verify đã chọn thành công // Thẻ span/div/lable/...luôn trả về là False, vì hàm isSelected() nó chỉ work với thẻ input
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).isSelected());	
		
	}

	//@Test
	public void TC_03_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(3);
		
		/* CASE 3 */
		// Thẻ thẻ khác với input để click (span/div/lable/...)-> Đang hiển thị (ở trình duyệt chrome: khi hover chuột vào nó ra hình răng cưa và màu tím)
		// Thẻ input lại dùng để verify được -> vì hàm isSelected() nó chỉ work với thẻ input
		
		// Thao tác chọn
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).click();
		
		// Verify đã chọn thành công // Dùng thẻ input để verify vì hàm isSelected() nó chỉ work với thẻ input
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div//input")).isSelected());	
		
		// Ở trường hơpj basic/demo thì được
		// Nếu apply vào 1 framework/ dự án thực tế thì k nên 
		// do 1 element phải define tới nhiều locator (dễ bị hiểu nhầm/ mất time để maintain/ k tập trung)
	}   

	//@Test
	public void TC_04_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(3);
		
		/* CASE 4 */
		// Thẻ input bị ẩn nhưng vẫn dùng để click
		// Hàm click() của WebElement nó sẽ không thao tác vào element bị ẩn được (The element must be visible and it must have a height and width greater then 0.)
		// Nên sẽ dùng 1 hàm click() của Javascript để click (click vào element bị ẩn được)
		// Selenium nó cung cấp 1 thư viện để có thể nhúng các đoạn code JS vào kịch bản test được -> JavascriptExecutor
		
		// Thẻ input lại dùng để verify được -> vì hàm isSelected() nó chỉ work với thẻ input
		
		// Selenium nó cung cấp 1 thư viện để có thể nhúng các đoạn code JS vào kịch bản test được -> JavascriptExecutor
		
		By radioButton = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div//input");
		
		// Thao tác chọn
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(radioButton));
		sleepInSecond(3);
		
		// Verify đã chọn thành công // Dùng thẻ input để verify vì hàm isSelected() nó chỉ work với thẻ input
		Assert.assertTrue(driver.findElement(radioButton).isSelected());	
		
		// Ở trường hơpj basic/demo thì được
		// Nếu apply vào 1 framework/ dự án thực tế thì k nên 
		// do 1 element phải define tới nhiều locator (dễ bị hiểu nhầm/ mất time để maintain/ k tập trung)
	}   

	@Test
	public void TC_05_Google_Form(){
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(3);
		
		By radioButton = By.cssSelector("div[aria-label='Hà Nội']");
		By checkbox = By.cssSelector("div[aria-label='Quảng Ngãi']");
		
		// Thao tác chọn
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(radioButton));
		sleepInSecond(3);
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(checkbox));
		sleepInSecond(3);
		
		// Verify đã chọn thành công // Dùng thẻ input để verify vì hàm isSelected() nó chỉ work với thẻ input
		
		// Cách 1
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Hà Nội'][aria-checked = 'true']")).isDisplayed());	
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Quảng Ngãi'][aria-checked = 'true']")).isDisplayed());	

		// Cách 2
		Assert.assertEquals(driver.findElement(radioButton).getAttribute("aria-checked"),"true");
		Assert.assertEquals(driver.findElement(checkbox).getAttribute("aria-checked"),"true");

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