package core;

public class ProjectProperties {

	/**
	 * Define if the browser must close after a test.
	 */
	public static boolean CLOSE_BROWSER = true;

	/**
	 * Define which browser should be used to run the tests.
	 */
	public static Browsers browser = Browsers.FIREFOX;

	/**
	 * Enumerate which browsers can run the tests. The machine that contains the
	 * project must have the chosen one browser installed.
	 * 
	 * @author Bruno Fraga
	 *
	 */
	public enum Browsers {
		CHROME, FIREFOX, EDGE
	}
}
