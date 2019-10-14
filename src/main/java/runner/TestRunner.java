package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/java/features",
					glue = {"steps"},
					plugin = {"pretty", "junit:target/cucumber-results.xml"},
					monochrome = true,
					snippets = SnippetType.CAMELCASE)
public class TestRunner {

	
}