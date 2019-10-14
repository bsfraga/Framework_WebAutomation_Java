package util;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import io.cucumber.core.api.Scenario;

/**
 * This class provides methods to capture screenshots.
 * @author Bruno Fraga
 *
 */
public class ScreenshotManager {

	private static String path = "screenshot/entireScreen";

	@Rule
	public static TestName testName;

	/**
	 * This method capture a screenshot from the current test. The file name is the same as the method who calls this.
	 * @param driver Current instance of the driver. I.e: getDriver() or DriverFactory.getDriver().
	 * @throws IOException
	 */
	public static void captureScreenshot(WebDriver driver) throws IOException {
		new TestName();
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyDirectory(source, new File("screenshot/" + testName.getMethodName() + ".png"));
		} catch (Exception e) {
			fail("Failed to take screenshot.\nMessage: " + e.getMessage());
		}
	}

	/**
	 * This method capture a screenshot from the current test. The file name is named by the current scenario.
	 * @param driver Current driver instance. I.e: getDriver() or DriverFactory.getDriver().
	 */
	public static void captureFullPageScreenshotNamedByScenario(WebDriver driver, Scenario scenario) {
		String screenshot_name = scenario.getName().toUpperCase().replace(" ", "_");
		String status = scenario.isFailed() ? "FALHOU" : "PASSOU";
		try {
			Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE, true).withName(screenshot_name + "_" + status)
					.save(path);
		} catch (Exception e) {
			fail("Failed to take fullpage screenshot.\nMessage: " + e.getMessage());
		}
	}

	/**
	 * This method capture a screenshot from the current test. When called, the file name must be declared.
	 * @param driver Current driver instance. I.e. getDriver() or DriverFactory.getDriver().
	 * @param filename The name of the file.
	 */
	public static void captureFullPageScreenshot(WebDriver driver, String filename) {
		try {
			Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE, true).withName(filename).save(path);
		} catch (Exception e) {
			fail("Failed to take fullpage screenshot.\nMessage: " + e.getMessage());
		}
	}

}
