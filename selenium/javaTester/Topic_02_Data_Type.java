package javaTester;

import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_02_Data_Type {

	public static void main(String[] args) {
		// I - Kiểu dữ liệu nguyên thủy (Primitive)
		
		// Số nguyên: byte short int long (không có phần thập phân)
		// Kích thức/ độ rộng để lưu trữ dữ liệu từ nhỏ đến lớn
		byte bNumber = 127;
		
		short sNumber = 3200;
		
		int iNumber = 499233299;
		
		long lNumber = 83294;
		
		// Số thực: float double (có phần thập phân)
		float studentPoint = 9.5f;
		
		double employeeSalary = 35.6d;

		
		// Logic: boolean
		boolean status = true; // Nam
		status = false; // Nữ
		
		
		// Kí tự: char
		char a = 'A';
		
		
		// II - Kiểu dữ liệu tham chiếu (Reference)
		// Class
		FirefoxDriver driver = new FirefoxDriver();
		
		
		// Interface
		webElement firstNameTextbox;
		
		// String
		String firstName = "Automation Testing";
		
		// Object
		Object people;
		
		// Array
		String [] studentName = {"Nguyễn Văn An", "Lê Văn Hùng", "Nguyễn Thị Loan"};
		
		// Collection: List/ Set/ Queue
		List<WebElement> checkboxes = driver.findElements(By.cssSelector(""));
		
		//Map
		Map<String, Interger> student = new HashMap<String, Interger>();
		
	}

}
