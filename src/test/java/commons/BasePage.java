package commons;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    
    private long longTimeout = 10; // Thời gian chờ tối đa (giây)

    // 1. Hàm tạo By XPath gọn gàng
    public By getByXpath(String xpathLocator) {
        return By.xpath(xpathLocator);
    }

    // 2. Chờ Element hiển thị (Visible)
    public void waitForElementVisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
    }

    // 3. Chờ Element sẵn sàng Click (Clickable)
    public void waitForElementClickable(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
    }

    // 4. Hàm Click đã nâng cấp (Chờ clickable rồi mới click)
    public void clickToElement(WebDriver driver, String xpathLocator) {
        waitForElementClickable(driver, xpathLocator);
        driver.findElement(getByXpath(xpathLocator)).click();
    }

    // 5. Hàm Nhập liệu đã nâng cấp (Chờ visible -> clear -> sendKeys)
    public void sendKeyToElement(WebDriver driver, String xpathLocator, String value) {
        clearValueInElement(driver, xpathLocator);
        driver.findElement(getByXpath(xpathLocator)).sendKeys(value);
    }
    
    // 6. Hàm Lấy Text đã nâng cấp
    public String getElementText(WebDriver driver, String xpathLocator) {
        waitForElementVisible(driver, xpathLocator);
        return driver.findElement(getByXpath(xpathLocator)).getText();
    }

    // 7. Kiểm tra Element có hiển thị hay không (trả về true/false để Assert)
    public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
        waitForElementVisible(driver, xpathLocator); // Thêm dòng này để chờ element xuất hiện
        return driver.findElement(getByXpath(xpathLocator)).isDisplayed();
    }
    
    // 8. Hàm xóa dữ liệu trong ô nhập liệu
    public void clearValueInElement(WebDriver driver, String xpathLocator) {
    	waitForElementVisible(driver, xpathLocator);
        WebElement element = driver.findElement(getByXpath(xpathLocator));
        
        // Tô đen tất cả chữ cũ và nhấn Delete
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }
    
    // 9. Hàm chờ chuyển trang khi login/register thành công
    public void waitForUrlContains(WebDriver driver, String expectedUrl) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.urlContains(expectedUrl));
    }
}