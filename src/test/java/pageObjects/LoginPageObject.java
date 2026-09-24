package pageObjects;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUI.LoginUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;
	
	public LoginPageObject(WebDriver driverInput) {
		driver = driverInput;
	}
	
	public void enterEmail(String email) {
		sendKeyToElement(driver, LoginUI.EMAIL_INPUT, email);
	}
	
	public void enterPassword(String password) {
		sendKeyToElement(driver, LoginUI.PASSWORD_INPUT, password);
	}
	
	public HomePageObject clickBtnLogin() {
		clickToElement(driver, LoginUI.BUTTON_LOGIN);
		waitForUrlContains(driver, "https://box8.vn/account");
		return new HomePageObject(driver);
	}
}
