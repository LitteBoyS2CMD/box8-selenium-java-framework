package testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalDataReader;
import dataObjects.UserDataRegister;
import pageObjects.RegisterPageObject;

public class RegisterTestcase extends BaseTest {
	private RegisterPageObject registerPage; 
	
	@BeforeMethod
	public void beforeMethod() {
        getBrowserDriver("chrome", "https://box8.vn/account/register");
        registerPage = new RegisterPageObject(driver);
    }
	
	@DataProvider(name = "getUserRegisterData")
	public UserDataRegister[] getUserRegisterData() {
		return GlobalDataReader.getJsonDataArray("RegisterData.json", UserDataRegister[].class);
	}
	
	@Test(dataProvider = "getUserRegisterData")
	public void TC_01_Register_Completed(UserDataRegister user) {
		registerPage.enterLastName(user.getLastName());
		registerPage.enterFirstName(user.getFirstName());
		
		String fullEmailString = user.getEmail() + createEmailAuto(); // Biến ghép chuỗi email ngẫu nhiên
		registerPage.enterEmail(fullEmailString);
		registerPage.enterPassword(user.getPassword());
		
		registerPage.clickBtnRegister();
		registerPage.waitForUrlContains(driver, "https://box8.vn/");
	}
	
	@Test
	public void TC_02_Register_Null_Data() {
		registerPage.enterLastName("");
		registerPage.enterFirstName("");
		registerPage.enterEmail("");
		registerPage.enterPassword("");
		
		registerPage.clickBtnRegister();
	}
	
	@AfterMethod(alwaysRun = true)
    public void afterMethod() {
		if (driver != null) {
	        driver.quit(); // Đóng tất cả tab và giải phóng session
	    }
    }
}
