package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.HomeUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;
	
	public HomePageObject(WebDriver driverInput) {
		driver = driverInput;
	}
	
	public void clickLogoWrapper() {
		clickToElement(driver, HomeUI.LOGO_WRAPPER);
	}
	
	public void searchProduct(String nameProduct) {
		sendKeyToElement(driver, HomeUI.INPUT_SEARCH, nameProduct);
	}
	
	public void clickButtonSearch() {
		clickToElement(driver, HomeUI.BUTTON_SEARCH);
	}
	
	public void clickButtonCart() {
		clickToElement(driver, HomeUI.BUTTON_CART);
	}
	
	public void hoverAndClickButtonCategory() {
		hoverToElement(driver, HomeUI.BUTTON_CATEGORY);
		clickToElement(driver, HomeUI.BUTTON_CATEGORY);
	}
	
	public void hoverMenuMega() {
		hoverToElement(driver, HomeUI.BUTTON_MENU_MEGA);
		clickToElement(driver, HomeUI.BUTTON_MENU_MEGA);
	}

	public String displayResultSearchProduct() {
		return getElementText(driver, HomeUI.NOTIFICATION_SEARCH_PRODUCT);
	}
}
