package commons;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    protected WebDriver driver;
    
    // Biến static lưu tập Cookie dùng chung cho mọi Test Class trong Test Suite
    public static Set<Cookie> savedCookies;
    
    // Lấy toàn bộ bộ Cookie hiện tại của phiên đăng nhập thành công và cất vào biến savedCookies
    public void saveCookie() {
    	savedCookies = driver.manage().getCookies();
    }
    
    /* Lấy bộ Cookie đã cất đó chèn ngược vào trình duyệt mới, 
    rồi refresh lại để trang web nhận diện trạng thái Đã Đăng Nhập mà không cần gõ lại Form
    */
    public void setCookieToSystem() {
    	if (savedCookies != null && !savedCookies.isEmpty()) {
            for (Cookie cookie : savedCookies) {
                driver.manage().addCookie(cookie);
            }
            driver.navigate().refresh();
        }
    }

    public WebDriver getBrowserDriver(String browserName, String appUrl) {
        if(browserName.equalsIgnoreCase("chrome")) {
        	WebDriverManager.chromedriver().setup();
        	driver = new ChromeDriver();
        } else if(browserName.equalsIgnoreCase("firefox")) {
        	WebDriverManager.firefoxdriver().setup();
        	driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Tên trình duyệt truyền vào không đúng!");
        }
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(appUrl);
        return driver;
    }
    
    
    // Hàm tạo email tự động(Dùng cho testcase Đăng ký)
    public String createEmailAuto() {
    	return System.currentTimeMillis() + "@gmail.com";
    }
    
    // Hàm chụp ảnh màn hình Base64 dán vào file Report
    public String getScreenshotBase64() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}