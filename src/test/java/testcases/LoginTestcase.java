package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalDataReader;
import dataObjects.UserDataLogin;
import dataObjects.UserIncorrectDataLogin;
import pageObjects.LoginPageObject;

public class LoginTestcase extends BaseTest {
    private LoginPageObject loginPage;

    @BeforeMethod
    public void beforeMethod() {
        getBrowserDriver("chrome", "https://box8.vn/account/login");
        loginPage = new LoginPageObject(driver);
    }
    
    @DataProvider(name = "getUserLogin")
    public UserDataLogin[] getUserLogin() {
    	return GlobalDataReader.getJsonDataArray("CorrectLoginData.json", UserDataLogin[].class);
    }
   
    @DataProvider(name = "getUserIncorrectLogin")
    public UserIncorrectDataLogin[] getUserIncorrectLogin() {
    	return GlobalDataReader.getJsonDataArray("IncorrectLoginData.json", UserIncorrectDataLogin[].class);
    }
    
    // Testcase đăng nhập thành công
    @Test(dataProvider = "getUserLogin")
    public void TC_01_Login_Completed(UserDataLogin user) {
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        
        loginPage.clickBtnLogin();
        loginPage.waitForUrlContains(driver, "https://box8.vn/");
    }
    
    // Testcase bỏ trống email, password
    @Test
    public void TC_02_Login_Null_Data() {
    	loginPage.enterEmail("");
    	loginPage.enterPassword("");
    	
    	loginPage.clickBtnLogin();
    	
    	// Assert: Xác nhận form bị chặn, URL không đổi sang trang chủ
        Assert.assertEquals(driver.getCurrentUrl(), "https://box8.vn/account/login");
    }
    
    // Testcase bỏ trống email
    @Test
    public void TC_03_Login_Null_Email() {
    	loginPage.enterEmail("");
    	loginPage.enterPassword("12345678");
    	
    	loginPage.clickBtnLogin();
    	
    	// Assert: Xác nhận form bị chặn, URL không đổi sang trang chủ
        Assert.assertEquals(driver.getCurrentUrl(), "https://box8.vn/account/login");
    }
    
    // Testcase bỏ trống password
    @Test
    public void TC_04_Login_Null_Password() {
    	loginPage.enterEmail("solomon.dev@gmail.com");
    	loginPage.enterPassword("");
    	
    	loginPage.clickBtnLogin();
    	
    	// Assert: Xác nhận form bị chặn, URL không đổi sang trang chủ
        Assert.assertEquals(driver.getCurrentUrl(), "https://box8.vn/account/login");
    }
    
    // Test login các trường hợp không hợp lệ: Email không tồn tại, Sai mật khẩu
    @Test(dataProvider = "getUserIncorrectLogin")
    public void TC_05_Incorrect_Login_Data(UserIncorrectDataLogin user) {
    	loginPage.enterEmail(user.getEmail());
    	loginPage.enterPassword(user.getPassword());
    	
    	loginPage.clickBtnLogin();
    	// Assert: Xác nhận form bị chặn, URL không đổi sang trang chủ
        Assert.assertEquals(driver.getCurrentUrl(), "https://box8.vn/account/login", user.getExpectedErrorMessage());
    }
    
    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
		if (driver != null) {
	        driver.quit(); // Đóng tất cả tab và giải phóng session
	    }
    }
    
}