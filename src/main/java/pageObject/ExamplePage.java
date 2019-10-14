package pageObject;

import core.BasePage;

/**
 * This class is an example of pageObject package usage. All pageObjects classes
 * must extends BasePage.
 * 
 * @author Bruno Fraga
 *
 */
public class ExamplePage extends BasePage {

	public void changeUrl(String url) {
		utils.goToUrl(url);
	}
}
