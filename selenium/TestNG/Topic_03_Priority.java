package TestNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_03_Priority {
  @Test(description = "JIRA-0897 - Creat a new employee and verify the employee created sucessfully")
  public void EndUser_01_Register_New_Employee() {
	  Assert.assertTrue(false);
  }
  
  @Test(enabled = false)
  public void EndUser_02_View_Employee() {
  }

  @Test
  public void EndUser_03_Edit_Employee() {
  }
  
  @Test
  public void EndUser_04_Move_Employee() {
  }
}
