package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalDataReader;
import dataObjects.UserDataRegister;
import dataObjects.UserIncorrectEmailRegister;
import dataObjects.UserIncorrectPasswordRegister;
import pageObjects.RegisterPageObject;

public class RegisterTestcase extends BaseTest {
	private RegisterPageObject registerPage; 
	
	@BeforeMethod
	public void beforeMethod() {
        getBrowserDriver("chrome", "https://box8.vn/account/register");
        registerPage = new RegisterPageObject(driver);
    }
	
	@DataProvider(name = "getUserRegister")
	public UserDataRegister[] getUserRegister() {
		return GlobalDataReader.getJsonDataArray("CorrectRegisterData.json", UserDataRegister[].class);
	}
	
	@DataProvider(name = "getUserIncorrectEmailRegister")
	public UserIncorrectEmailRegister[] getUserIncorrectEmailRegister() {
		return GlobalDataReader.getJsonDataArray("IncorrectEmailRegister.json", UserIncorrectEmailRegister[].class);
	}
	
	@DataProvider(name = "getUserIncorrectPasswordRegister")
	public UserIncorrectPasswordRegister[] getUserIncorrectPasswordRegister() {
		return GlobalDataReader.getJsonDataArray("IncorrectPasswordRegister.json", UserIncorrectPasswordRegister[].class);
	}

	// Hàm đăng ký thành công
	@Test(dataProvider = "getUserRegister")
	public void TC_01_Register_Completed(UserDataRegister user) {
		registerPage.enterLastName(user.getLastName());
		registerPage.enterFirstName(user.getFirstName());
		
		String fullEmailString = user.getEmail() + createEmailAuto(); // Biến ghép chuỗi email ngẫu nhiên
		registerPage.enterEmail(fullEmailString);
		registerPage.enterPassword(user.getPassword());
		
		registerPage.clickBtnRegister();
		registerPage.waitForUrlContains(driver, "https://box8.vn/");
	}
	
	// Hàm đăng ký khi bỏ trống form
	@Test
	public void TC_02_Register_Null_Data() {
		registerPage.enterLastName("");
		registerPage.enterFirstName("");
		registerPage.enterEmail("");
		registerPage.enterPassword("");
		
		registerPage.clickBtnRegister();
	}
	
	// Hàm đăng ký bỏ trống Họ, Tên
	@Test
	public void TC_03_Register_Null_Lastname() {
		registerPage.enterLastName("");
		registerPage.enterFirstName("Phương Liên");
		registerPage.enterEmail("lia.be@gmail.com");
		registerPage.enterPassword("12345678Lia");
		
		registerPage.clickBtnRegister();
		
		// Assert: Xác nhận form bị chặn, URL không đổi sang trang chủ
        Assert.assertEquals(driver.getCurrentUrl(), "https://box8.vn/account/register");
	}
	
	@Test
	public void TC_04_Register_Null_Firstname() {
		registerPage.enterLastName("Nguyễn Thị");
		registerPage.enterFirstName("");
		registerPage.enterEmail("lia.be@gmail.com");
		registerPage.enterPassword("12345678Lia");
		
		registerPage.clickBtnRegister();
		
		// Assert: Xác nhận form bị chặn, URL không đổi sang trang chủ
        Assert.assertEquals(driver.getCurrentUrl(), "https://box8.vn/account/register");
	}
	
	// Hàm đăng ký bỏ trống Email, Password
	@Test
	public void TC_05_Register_Null_Email() {
		registerPage.enterLastName("Nguyễn Thị");
		registerPage.enterFirstName("Phương Liên");
		registerPage.enterEmail("");
		registerPage.enterPassword("12345678Lia");
		
		registerPage.clickBtnRegister();
		
		// Assert: Xác nhận form bị chặn, URL không đổi sang trang chủ
        Assert.assertEquals(driver.getCurrentUrl(), "https://box8.vn/account/register");
	}
	
	@Test
	public void TC_06_Register_Null_Password() {
		registerPage.enterLastName("Nguyễn Thị");
		registerPage.enterFirstName("Phương Liên");
		registerPage.enterEmail("lia.be@gmail.com");
		registerPage.enterPassword("");
		
		registerPage.clickBtnRegister();
		
		// Assert: Xác nhận form bị chặn, URL không đổi sang trang chủ
        Assert.assertEquals(driver.getCurrentUrl(), "https://box8.vn/account/register");
	}
	
	// Hàm đăng ký email sai định dạng
	@Test(dataProvider = "getUserIncorrectEmailRegister")
	public void TC_07_Register_Incorrect_Email(UserIncorrectEmailRegister user) {
		registerPage.enterLastName(user.getLastname());
		registerPage.enterFirstName(user.getFirstname());
		registerPage.enterEmail(user.getEmail());
		registerPage.enterPassword(user.getPassword());
		
		registerPage.clickBtnRegister();
		
		// Assert: Xác nhận form bị chặn, URL không đổi sang trang chủ
        Assert.assertEquals(driver.getCurrentUrl(), "https://box8.vn/account/register");
	}
	
	// Hàm kiểm tra password đăng ký
	@Test(dataProvider = "getUserIncorrectPasswordRegister")
	public void TC_08_Register_Incorrect_Password(UserIncorrectPasswordRegister user) {
		registerPage.enterLastName(user.getLastname());
		registerPage.enterFirstName(user.getFirstname());
		registerPage.enterEmail(user.getEmail());
		registerPage.enterPassword(user.getPassword());
		
		registerPage.clickBtnRegister();
		
		// Assert: Xác nhận form bị chặn, URL không đổi sang trang chủ
        Assert.assertEquals(driver.getCurrentUrl(), "https://box8.vn/account/register");
	}
	
	
	@AfterMethod(alwaysRun = true)
    public void afterMethod() {
		if (driver != null) {
	        driver.quit(); // Đóng tất cả tab và giải phóng session
	    }
    }
}
