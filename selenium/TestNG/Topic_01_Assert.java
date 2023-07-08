package TestNG;

import org.testng.Assert;

public class Topic_01_Assert {

	public static void main(String[] args) {
		String fullName = "Automation Testing";
		
		
		// Mong đợi 1 điều kiện trả về đúng (true)
		Assert.assertTrue(fullName.contains("Automation"));

		
		// Mong đợi 1 điều kiện trả về sai(false)
		Assert.assertFalse(fullName.contains("Manual"));
		
		// Mong đợi 2 điều kiện bằng nhau Expected result = Actual result
		Assert.assertEquals(fullName, "Automation Testing");
	}

}
