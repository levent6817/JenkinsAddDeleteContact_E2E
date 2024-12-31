package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {
    // Singleton driver class

    private static WebDriver driver;

    private Driver() {} // Prevents instantiation of this class with the new keyword.

    public static WebDriver getDriver() {

        if (driver == null) { // If the driver has not been created before

            String browser = ConfigReader.getProperty("browser");

            switch (browser) {

                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "chrome-headless":
                    driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
                    break;
                default:
                    driver = new ChromeDriver();

            }

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;
    }

    public static void closeDriver() {
        if (driver != null) { // If the driver has not been closed before

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            driver.quit();
            driver = null; // Set the closed driver to null to allow it to be called again with the getDriver method
        }
    }
}