package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.RegisterUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;
	
	public RegisterPageObject(WebDriver driverInput) {
		driver = driverInput;
	}
	
	public void enterLastName(String lastName) {
		sendKeyToElement(driver, RegisterUI.LASTNAME_INPUT, lastName);
	}
	
	public void enterFirstName(String firstName) {
		sendKeyToElement(driver, RegisterUI.FIRST_NAME, firstName);
	}
	
	public void enterEmail(String email) {
		sendKeyToElement(driver, RegisterUI.EMAIL_INPUT, email);
	}
	
	public void enterPassword(String password) {
		sendKeyToElement(driver, RegisterUI.PASSWORD_INPUT, password);
	}
	
	public HomePageObject clickBtnRegister() {
		clickToElement(driver, RegisterUI.BUTTON_REGISTER);
		waitForUrlContains(driver, "https://box8.vn/");
		return new HomePageObject(driver);
	}
}
