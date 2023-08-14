package TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_01_Annotation {
  @Test()
  public void TC_01() {
	  System.out.println("Test case 01");
  }
  
  @Test()
  public void TC_02() {
	  System.out.println("Test case 02");

  }
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Before Method");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("After Method");

  }

 
  @BeforeClass
  public void beforeClass() {
	  System.out.println("Before class");

  }

  @AfterClass
  public void afterClass() {
	  System.out.println("After class");

  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("Before test");

  }

  @AfterTest
  public void afterTest() {
	  System.out.println("After test");

  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("Before suite");

  }
  

  @AfterSuite
  public void afterSuite() {
	  System.out.println("After suite");

  }

}
