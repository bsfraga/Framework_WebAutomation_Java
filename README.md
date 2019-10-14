# Framework Web - Java

## Usage
#### Package Core
This package contains the framework base structure.
You can select which browser you want to use to run tests by changing the value of BROWSER.
You can also change the browser behavior after finish a test by changing the value of CLOSE_BROWSER:
```java
public class ProjectProperties {

	/**
	 * Define if the browser must close after a test.
	 */
	public static boolean CLOSE_BROWSER = false;
	
	/**
	 * Define which browser should be used to run the tests.
	 */
	public static Browsers browser = Browsers.CHROME;

	/**
	 * Enumerate which browsers can run the tests. 
	 * The machine that contains the project must have the chosen one browser installed.
	 * @author BrunoFraga
	 */
	public enum Browsers{
		CHROME,
		FIREFOX,
		EDGE
	}
}
``` 

#### Package Features
This package contains the feature files from Cucumber BDDs.

#### Package Runner
This package contains the Cucumber configurations to run with jUnit.

#### Package Page Object
This package is where you'll create the page actions and the element mapping.
```java
public class ExamplePage extends BasePage{

    public void changeUrl(String url) {
		utils.goToUrl(url);
	}
	...

}
```

#### Package Steps
This package contains the steps from the current Scenario that will be test.
The class BindingSteps.java contains a few configurations related to the Cucumber steps, such as custom configurations for @Before and @After tags.

```java
public class ExampleSteps extends ExamplePage{

	@Given("I want to access Google homepage")
	public void mustChangeUrl(){
		changeUrl("http://www.google.com.br");
	}
	
	@Then("Check the tittle of the page")
	public void checkTheTittleOfThePage(){
		assertEquals("Google", getDriver().getTitle());
	}
    ...
}
```

#### Package Util
This package contains all the generics methods from the framework. Feel free to add more methods if you have to, just remember to add a JavaDoc Commentary explaining what the method executes by adding /** and pressing [Enter] before the method.
 
## Dependencies
This version is making use of:
- [Selenium 3.141](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)
- [jUnit 4.12](https://mvnrepository.com/artifact/junit/junit);
- [commons-io 2.6](https://mvnrepository.com/artifact/commons-io/commons-io);
- Cucumber ([core](https://mvnrepository.com/artifact/io.cucumber/cucumber-core) - [java](https://mvnrepository.com/artifact/io.cucumber/cucumber-java) - [junit](https://mvnrepository.com/artifact/io.cucumber/cucumber-junit) - [jvm-deps](https://mvnrepository.com/artifact/io.cucumber/cucumber-jvm-deps) - [reporting](https://mvnrepository.com/artifact/net.masterthought/cucumber-reporting));
- [Selenium Shutterbug 0.9.2](https://mvnrepository.com/artifact/com.assertthat/selenium-shutterbug);



Please make sure to update tests as appropriate.
