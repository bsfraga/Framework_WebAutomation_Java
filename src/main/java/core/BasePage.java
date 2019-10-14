package core;

import util.Utils;

/**
 * Generic behavior that will be inherited among all Pages.
 * 
 * @author Bruno Fraga
 *
 */
public class BasePage {

	protected Utils utils;
	
	public BasePage() {
		utils = new Utils();
	}
	
}
