package steps;

import static core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObject.ExamplePage;

/**
 * This class is an example of steps package usage.
 * 
 * @author Bruno Fraga
 *
 */
public class ExampleSteps extends ExamplePage{


	@Given("que acesso a homepage do google")
	public void queAcessoAHomepageDoGoogle() {
		changeUrl("https://www.google.com.br");
		
	}

	@Given("valido se a pagina acessada e a homepage do google")
	public void validoSeAPaginaAcessadaEAHomepageDoGoogle() {
		assertEquals("Google", getDriver().getTitle());
	}

	@Given("que acesso a pagina do youtube")
	public void queAcessoAPaginaDoYoutube() {
		changeUrl("https://www.youtube.com/");
	}

	@Then("valido se a pagina acessa e a pagina do youtube")
	public void validoSeAPaginaAcessaAPaginaDoYoutube() {
		assertEquals("YouTube", getDriver().getTitle());
	}


}
