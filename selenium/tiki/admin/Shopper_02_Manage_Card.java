package tiki.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Shopper_02_Manage_Card {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeTest (alwaysRun = true)
	public void initBrowser() {
		System.out.println("--------------- Open browser and driver ---------------");
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
			
		
		driver = new ChromeDriver();
		
	}
	@Test(groups ="admin")
	  public void Card_01_Create_Visa() {
	  }
	  
	  @Test(groups ="admin")
	  public void Card_02_View_Visa() {
	  }

	  @Test(groups ="admin")
	  public void Card_03_Update_Visa() {
	  }
	  
	  @Test(groups ="admin")
	  public void Card_04_Delete_Visa() {
	  }
	  
	@AfterTest (alwaysRun = true)
		public void clearBrowser() {
		System.out.println("--------------- Open browser and driver ---------------");
			driver.quit();
		}
}
