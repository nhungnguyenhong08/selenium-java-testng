package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		// 1. Click vào 1 thẻ bất kì để làm sao cho nó xổ ra hết các item của dropdown
		driver.findElement(By.cssSelector("span#speed-button")).click();
		
		// 2. Chờ tất cả các item load ra thành công => k nên dùng sleep cứng
		// Locator phải lất để đại diện cho TẤT CẢ các item
		// Lấy đến thẻ chứa text
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));
		
		// Khai báo hết các item trong drop vào 1 List
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));
		
		// 3. Tìm item xem đúng cái đang cần hay không (dùng vòng lặp for duyệt qua từng item)
		for (WebElement tempItem: speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);
			
		// 4. Kiểm tra cái text của item đúng với cái mình mong muốn
			if(itemText.equals("Fast")) {
		
		// 5. Click vào item đó
				System.out.println("Click vào item");
				tempItem.click();
				
		// Thoát ra khỏi vòng lặp k xét cho các case còn lại nữa (vì Fast nằm ở vị trí thứ 4 trước faster nên khi tìm element thỏa mãn điều kiện thì click chọn 
				//-> k hiển thị danh sách các item trong dropdown nữa)
				break;
		// không cần else (nhưng do đang học nên thêm else để in ra cho dễ hiểu)		
			}else {
				System.out.println("Không click vào item");
			}
		}
		
		// 3.1. Nếu nó nằm trong 1 khoảng nhìn thấy của User không cần phải scroll xuống
		// 3.2. Nếu nó không nằm trong khoảng nhìn thấy của User thì cần scroll xuống đến item đó
		
	}

	@Test
	public void TC_02_() {
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
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}