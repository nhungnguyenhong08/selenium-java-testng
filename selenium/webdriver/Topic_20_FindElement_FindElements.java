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

public class Topic_20_FindElement_FindElements {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;
	JavascriptExecutor jsExecutor;

		
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
			
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		// Đang apply 15s cho việc find element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Find_Element() {
		// 1 - Tìm thấy duy nhất 1 element/ node
		// Tìm thấy vào thao tác trực tiếp lên node đó
		// Vì nó tìm thấy nên k cần phải chờ hết timeout 15s
		driver.findElement(By.cssSelector("input#email"));
		
		// 2 - Tìm thấy nhiều hơn 1 element/ node
		// Nó sẽ thao tác với node đầu tiên
		// Không quan tâm các node còn lại
		// Trong case bắt sai locator sai thì nó sẽ tìm sai
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("automationfc@gmail.com");
		
		// 3 - Không tìm thấy element/node nào
		// Có cơ chế tìm lại = 0.5s sẽ tìm lại 1 lần
		// Nếu trong thời gian đang tìm lại mà thấy element thỏa mãn đk - pas
		// Nếu hết thời gian timeout (15s) mà vẫn k tìm thấy element thì
		// + Đánh fail testcase + không chạy step tiếp theo
		// + Throw ra 1 ngoại lệ: NoSuchElementException
		driver.findElement(By.cssSelector("input#check"));

		}

	@Test
	public void TC_02_Find_Elements() {
		
		// 1 - Tìm thấy duy nhất 1 element/ node
		// Tìm thấy vào thao tác trực tiếp lên node đó
		// Vì nó tìm thấy nên k cần phải chờ hết timeout 15s
		List<WebElement> elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("List element number =" + elements.size());
		
		// 2 - Tìm thấy nhiều hơn 1 element/ node
		// Nó sẽ thao tác với node đầu tiên
		// Không quan tâm các node còn lại
		// Trong case bắt sai locator sai thì nó sẽ tìm sai
		elements = driver.findElements(By.cssSelector("input"));	
		System.out.println("List element number =" + elements.size());

		// 3 - Không tìm thấy element/node nào
		// Có cơ chế tìm lại = 0.5s sẽ tìm lại 1 lần
		// Nếu trong thời gian đang tìm lại mà thấy element thỏa mãn đk - pas
		// Nếu hết thời gian timeout (15s) mà vẫn k tìm thấy element thì
		// + Đánh fail testcase + không chạy step tiếp theo
		// + Throw ra 1 ngoại lệ: NoSuchElementException
		elements = driver.findElements(By.cssSelector("input[type='check']"));	
		System.out.println("List element number =" + elements.size());

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