package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * 
 * @author Bruno Fraga
 *
 */

public class DriverFactory {

	private static WebDriver driver;
	
	private DriverFactory() {}
	
	public static WebDriver getDriver() {

		if(driver == null) {
			switch (ProjectProperties.browser) {
			case CHROME:
				System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
				driver = new ChromeDriver();
				break;
			case FIREFOX:
				System.setProperty("webdriver.gecko.driver", "./src/main/resources/drivers/geckodriver.exe");
				driver = new FirefoxDriver();
				break;
			case EDGE:
				System.setProperty("webdriver.edge.driver", "./src/main/resources/drivers/msedgedriver.exe");
				driver = new EdgeDriver();
				break;
			}
		}
		return driver;
	}
	
	public static void killDriver() {
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
