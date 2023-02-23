package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {
    private static final Duration IMPLICIT_WAIT_TIME_IN_SECONDS = Duration.ofSeconds(20);
    private static final Duration IMPLICIT_PAGE_LOAD_TIMEOUT = Duration.ofSeconds(20);

    public Driver() {
    }

    private static WebDriver driver;

    public static WebDriver getDriverReference() {
        return driver;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (Config.getProperty("browser")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
            }
        }
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME_IN_SECONDS);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(IMPLICIT_PAGE_LOAD_TIMEOUT);
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
