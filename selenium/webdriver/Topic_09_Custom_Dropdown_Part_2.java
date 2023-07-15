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

public class Topic_09_Custom_Dropdown_Part_2 {
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

	//@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		// * SỬ DỤNG HÀM * //
		// Muốn chọn item cho Speed dropdown
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slower");
		sleepInSecond(3);
		
		// Dựa vào HTML của dropdown để verify
		// Sau khi chọn xong thì cái text item sẽ nằm ở những thẻ nào -> Dùng thẻ đó để verify
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Slower");
		
		 
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Fast");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Fast");

		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slow");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Slow");

		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Faster");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Faster");


		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Medium");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Medium");

	}

	//@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		// * SỬ DỤNG HÀM * //
		// Muốn chọn item cho Speed dropdown
		selectItemInDropdown("i.dropdown.icon", "span.text", "Justen Kitsune");
		sleepInSecond(3);
	
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Justen Kitsune");

		selectItemInDropdown("i.dropdown.icon", "span.text", "Matt");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Matt");

		selectItemInDropdown("i.dropdown.icon", "span.text", "Jenny Hess");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Jenny Hess");

	}

	//@Test
	public void TC_03_VueJS() {
		
			driver.get("https://mikerodham.github.io/vue-dropdowns/");
			
			// * SỬ DỤNG HÀM * //
			// Muốn chọn item cho Speed dropdown
			selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
			sleepInSecond(5);
			Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Second Option");
			
			selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
			sleepInSecond(5);
			Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"First Option");

			selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
			sleepInSecond(5);
			Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Third Option");

	}   
	
	@Test
	public void TC_04_Editable() {
		
			driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
			
			// * SỬ DỤNG HÀM * //
			// Muốn chọn item cho Speed dropdown
			selectItemInDropdown("input.search", "span.text", "Angola");
			sleepInSecond(5);
			Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(),"Angola");
			
			selectItemInDropdown("input.search", "span.text", "Bahamas");
			sleepInSecond(5);
			Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(),"Bahamas");

			selectItemInDropdown("input.search", "span.text", "Australia");
			sleepInSecond(5);
			Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(),"Australia");

	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
		
	// Tránh lặp lại code nhiều lần thì chỉ cần gọi hàm ra để dùng
		// Đi kèm với tham số
		// Nếu truyền chứng 1 giá trị vào trong hàm = vô nghĩa => Mình nên define để dùng đi dùng lại được nhiều lần
	
	// Hàm chọn 
	public void selectItemInDropdown(String parentCSS, String allItems, String expectedTextItem ) {
			// 1. Click vào 1 thẻ bất kì để làm sao cho nó xổ ra hết các item của dropdown
			driver.findElement(By.cssSelector(parentCSS)).click();
			sleepInSecond(2);
			
			// 2. Chờ tất cả các item load ra thành công => k nên dùng sleep cứng
			// Locator phải lất để đại diện cho TẤT CẢ các item
			// Lấy đến thẻ chứa text
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItems)));
			
			// Khai báo hết các item trong drop vào 1 List
			List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItems));
			
			// 3. Tìm item xem đúng cái đang cần hay không (dùng vòng lặp for duyệt qua từng item)
			for (WebElement tempItem: speedDropdownItems) {
				String itemText = tempItem.getText();
				System.out.println(itemText);
				
			// 4. Kiểm tra cái text của item đúng với cái mình mong muốn
				if(itemText.trim().equals(expectedTextItem)) {
					sleepInSecond(2);
			
			// 5. Click vào item đó
					System.out.println("Click vào item");
					tempItem.click();
					
			// Thoát ra khỏi vòng lặp k xét cho các case còn lại nữa (vì Fast nằm ở vị trí thứ 4 trước faster nên khi tìm element thỏa mãn điều kiện thì click chọn 
					//-> k hiển thị danh sách các item trong dropdown nữa)
					break;
			}
		}
	}


	// Hàm chọn và nhập
	public void enterAndSelectItemInDropdown(String textboxCss, String allItems, String expectedTextItem ) {
		// 1. Nhập expected text item vào -> xổ ra tất cả các item matching
		driver.findElement(By.cssSelector(textboxCss)).clear();
		driver.findElement(By.cssSelector(textboxCss)).sendKeys(expectedTextItem);
		sleepInSecond(2);
		
		// 2. Chờ tất cả các item load ra thành công => k nên dùng sleep cứng
		// Locator phải lất để đại diện cho TẤT CẢ các item
		// Lấy đến thẻ chứa text
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItems)));
		
		// Khai báo hết các item trong drop vào 1 List
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItems));
		
		// 3. Tìm item xem đúng cái đang cần hay không (dùng vòng lặp for duyệt qua từng item)
		for (WebElement tempItem: speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);
			
		// 4. Kiểm tra cái text của item đúng với cái mình mong muốn
			if(itemText.trim().equals(expectedTextItem)) {
		
		// 5. Click vào item đó
				System.out.println("Click vào item");
				tempItem.click();
				
		// Thoát ra khỏi vòng lặp k xét cho các case còn lại nữa (vì Fast nằm ở vị trí thứ 4 trước faster nên khi tìm element thỏa mãn điều kiện thì click chọn 
				//-> k hiển thị danh sách các item trong dropdown nữa)
				break;
		}
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
}
