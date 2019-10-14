package steps;

import static core.DriverFactory.getDriver;

import java.io.IOException;

import core.DriverFactory;
import core.ProjectProperties;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import util.ScreenshotManager;

/**
 * This class is where you can define the behavior of each scenario. 
 * What is going to happend before and/or after each scenario run.
 * @author Bruno Fraga
 *
 */

public class BindingSteps {
	
	private Scenario scenario;
	
	@Before
	public void init(Scenario scenario) {
		this.scenario = scenario;
	}
	
	@Before("@First")
	public void start() {
		getDriver();
	}
	
	@After(order = 1)
	public void afterScenario() throws IOException{
		new ScreenshotManager();
		ScreenshotManager.captureFullPageScreenshotNamedByScenario(getDriver(), scenario);
		System.out.println(scenario.toString());
	}
	
	@After(value = "@Last", order = 0)
	public void finish() {
		if(ProjectProperties.CLOSE_BROWSER) {
			DriverFactory.killDriver();
		}
	}
}
