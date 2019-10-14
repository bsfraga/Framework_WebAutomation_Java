package util;

import static core.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;;

/**
 * This class provides methods that interacts with web pages to executes tests.
 * 
 * @author Bruno Fraga
 *
 */
public class Utils {

	/**
	 * This method provides the action to navigate by URL.
	 * 
	 * @param url The URL that you want to navigate.
	 */
	public void goToUrl(String url) {
		try {
			getDriver().navigate().to(url);
		} catch (Exception e) {
			Assert.fail("It wasn't possible to go to the URL: " + url + "\n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * Send Keys (text) to an element by it's locator.
	 * 
	 * @param locator Locator from the element that you want to interact.
	 * @param text    Text that you want to send.
	 */
	public void sendKeys(By locator, String text) {
		try {
			getDriver().findElement(locator).clear();
			getDriver().findElement(locator).sendKeys(text);
		} catch (Exception e) {
			Assert.fail("It wasn't possible to send keys to element object: " + locator
					+ "\n-----==========-----\nMessage: " + e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method send keys (text only). If the element you want to interact is
	 * being displayed, enabled and the text you sent contains only characters from
	 * A to Z (lower or upper case). It uses Regex to validate the text that will be
	 * sent.
	 * 
	 * @param element The element that you want to interact with.
	 * @param text    The text you want to send.
	 */
	public void sendKeysOnlyText(WebElement element, String text) {
		try {
			boolean result = !Pattern.matches("^[a-zA-Z ]+$", element.getAttribute("value"));
			if (result && element.isDisplayed() && element.isEnabled()) {
				this.highlight(element, true);
				element.clear();
				element.sendKeys(text);
			}
		} catch (Exception e) {
			Assert.fail("It wasn't possible to clear and send keys to element object: \n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method send keys (text only). If the element you want to interact have a
	 * null or empty value [.getAttribute("value")], this will fail.
	 * 
	 * @param element The element that you want to interact with.
	 * @param text    The text you want to send.
	 */
	public void sendKeysInput(WebElement element, String text) {
		try {
			if (element.getAttribute("value") == null || element.getAttribute("value") == "") {
				this.highlight(element, true);
				element.sendKeys(text);
			}
		} catch (Exception e) {
			Assert.fail("It wasn't possible to clear and send keys to element object: \n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method returns the field value from a Locator [.getAttribute("value")].
	 * 
	 * @param locator Locator from the element that you want to interact.
	 * @return The value from a field Locator.
	 */
	public String getFieldValue(By locator) {
		return getDriver().findElement(locator).getAttribute("value");
	}

	/**
	 * This method executes a click on a radio button by it's locator.
	 * 
	 * @param locator Radio button locator.
	 */
	public void clickRadio(By locator) {
		try {
			getDriver().findElement(locator).click();
		} catch (Exception e) {
			Assert.fail("It wasn't possible to click on the Radio button: " + locator
					+ "\n-----==========-----\nMessage: " + e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method check if a specific radio button is marked.
	 * 
	 * @param locator Radio button locator.
	 * @return True if it's marked, False if it's not.
	 */
	public boolean isRadioMarked(By locator) {
		return getDriver().findElement(locator).isSelected();
	}

	/**
	 * This method executes a click on a check box by it's locator
	 * 
	 * @param locator Check Box locator.
	 */
	public void clickCheckBox(By locator) {
		try {
			getDriver().findElement(locator).click();
		} catch (Exception e) {
			Assert.fail("It wasn't possible to click on the CheckBox: " + locator + "\n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method check if a check box is marked.
	 * 
	 * @param locator Check box locator.
	 * @return True if it's marked, False if it's not.
	 */
	public boolean isCheckBoxMarked(By locator) {
		return getDriver().findElement(locator).isSelected();
	}

	/**
	 * This method selects a Combo by it's locator.
	 * 
	 * @param locator Combo locator.
	 * @param text    The text contained into the Combo.
	 */
	public void selectCombo(By locator, String text) {
		try {
			WebElement element = getDriver().findElement(locator);
			Select combo = new Select(element);
			combo.selectByVisibleText(text);
		} catch (Exception e) {
			Assert.fail("It wasn't possible to select the combo: " + locator + "\n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method deselect a Combo by it's locator.
	 * 
	 * @param locator Combo locator.
	 * @param text    The text contained into the Combo.
	 */
	public void deselectCombo(By locator, String text) {
		try {
			WebElement element = getDriver().findElement(locator);
			Select combo = new Select(element);
			combo.deselectByVisibleText(text);
		} catch (Exception e) {
			Assert.fail("It wasn't possible to deselect the combo: " + locator + "\n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method obtains the content from a Combo.
	 * 
	 * @param locator Combo locator.
	 * @return The text contained into the Combo.
	 */
	public String getComboValue(By locator) {
		try {
			WebElement element = getDriver().findElement(locator);
			Select combo = new Select(element);
			return combo.getFirstSelectedOption().getText();
		} catch (Exception e) {
			Assert.fail("It wasn't possible to get the combo value: " + locator + "\n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
		}
		return null;
	}

	/**
	 * This method get all the values contained into a Combo.
	 * 
	 * @param locator Combo locator.
	 * @return A list with all values inside the Combo.
	 */
	public List<String> getComboValues(By locator) {
		try {
			WebElement element = getDriver().findElement(locator);
			Select combo = new Select(element);
			List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
			List<String> values = new ArrayList<String>();
			for (WebElement option : allSelectedOptions) {
				values.add(option.getText());
			}
			return values;
		} catch (Exception e) {
			Assert.fail("It wasn't possible to get the combo values: " + locator + "\n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
		}
		return null;
	}

	/**
	 * This methos obtains the number of the elements inside a Combo.
	 * 
	 * @param locator Combo locator.
	 * @return The size (int) of the combo.
	 */
	public int getComboOptionsNumber(By locator) {
		List<WebElement> options = null;
		try {
			WebElement element = getDriver().findElement(locator);
			Select combo = new Select(element);
			options = combo.getOptions();
		} catch (Exception e) {
			Assert.fail("It wasn't possible to click on the CheckBox: " + locator + "\n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
		}
		return options.size();
	}

	/**
	 * This method verifies if  the Combo contains an option with a specific text.
	 * 
	 * @param locator Combo locator.
	 * @param text    Text that you want to check if is contained into the combo.
	 * @return
	 */
	public boolean verifyComboOption(By locator, String text) {
		try {
			WebElement element = getDriver().findElement(locator);
			Select combo = new Select(element);
			List<WebElement> options = combo.getOptions();
			for (WebElement option : options) {
				if (option.getText().equals(text)) {
					return true;
				}
			}
		} catch (Exception e) {
			Assert.fail("It wasn't possible to verify the combo option: " + locator
					+ "\n-----==========-----\nMessage: " + e.getMessage() + "\n-----==========-----");
		}
		return false;
	}

	/**
	 * This method executes the action of a click on a button.
	 * 
	 * @param locator Button locator.
	 */
	public void clickButton(By locator) {
		try {
			getDriver().findElement(locator).click();
		} catch (Exception e) {
			Assert.fail("It wasn't possible to click the button: " + locator + "\n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method obtains the text inside an element.
	 * 
	 * @param locator Element locator.
	 * @return Text contained into the element.
	 */
	public String getText(By locator) {
		try {
			return getDriver().findElement(locator).getText();
		} catch (Exception e) {
			Assert.fail("It wasn't possible to get the text from: " + locator + "\n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
		}
		return null;
	}

	/**
	 * This method obtains the text inside an AlertBox.
	 * 
	 * @return The text contained into the AlertBox.
	 */
	public String getTextFromAlert() {
		try {
			Alert alert = getDriver().switchTo().alert();
			return alert.getText();
		} catch (Exception e) {
			Assert.fail("It wasn't possible to click the button: " + "\n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
			return null;
		}
	}

	/**
	 * This method get the text from a AlertBox and then click on the Accept/Ok
	 * button.
	 * 
	 * @return The text contained into the AlertBox.
	 */
	public String getTextFromAlertAndAccept() {
		try {
			Alert alert = getDriver().switchTo().alert();
			String value = alert.getText();
			alert.accept();
			return value;
		} catch (Exception e) {
			Assert.fail("It wasn't possible to get the text from the Alert box and click Accept "
					+ "\n-----==========-----\nMessage: " + e.getMessage() + "\n-----==========-----");
		}
		return null;

	}

	/**
	 * This method obtains the text inside a AlertBox and then click on Deny/Cancel
	 * button.
	 * 
	 * @return The text contained into the AlertBox.
	 */
	public String getTextFromAlertAndDeny() {
		try {
			Alert alert = getDriver().switchTo().alert();
			String value = alert.getText();
			alert.dismiss();
			return value;
		} catch (Exception e) {
			Assert.fail("It wasn't possible to get the text from the Alert box and click Deny: "
					+ "\n-----==========-----\nMessage: " + e.getMessage() + "\n-----==========-----");
		}
		return null;

	}

	/**
	 * This method send keys (text) to an AlertBox.
	 * 
	 * @param text Text that will be sent to the AlertBox.
	 */
	public void sendKeysOnAlert(String text) {
		try {
			Alert alert = getDriver().switchTo().alert();
			alert.sendKeys(text);
			alert.accept();
		} catch (Exception e) {
			Assert.fail("It wasn't possible to send keys on Alert box: " + "\n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method changes the driver focus to a Frame View.
	 * 
	 * @param frame_id Frame id.
	 */
	public void getIntoFrame(String frame_id) {
		try {
			getDriver().switchTo().frame(frame_id);
		} catch (Exception e) {
			Assert.fail("It wasn't possible to get into a the frame: " + frame_id + "\n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method changes driver focus to out of the Frame View.
	 */
	public void getOutFrame() {
		try {
			getDriver().switchTo().defaultContent();
		} catch (Exception e) {
			Assert.fail("It wasn't possible to out of the frame:" + "\n-----==========-----\nMessage: " + e.getMessage()
					+ "\n-----==========-----");
		}
	}

	/**
	 * This method change the driver focus to a specific window.
	 * 
	 * @param window_id The if of the window.
	 */
	public void switchWindow(String window_id) {
		try {
			getDriver().switchTo().window(window_id);
		} catch (Exception e) {
			Assert.fail("It wasn't possible to switch to window: " + window_id + "\n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method execute a JavaScript command.
	 * 
	 * @param command The JavaScript command.
	 * @param args    The arguments that the command will recieve.
	 * @return
	 */
	public Object executeJS(String command, Object... args) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			return executor.executeScript(command, args);
		} catch (Exception e) {
			Assert.fail("It wasn't possible to execute the JavaScript command: " + "\n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
		}
		return null;
	}

	/**
	 * This method scroll the page to a specific locator.
	 * 
	 * @param locator Element locator.
	 */
	public void scrollToElement(By locator) {
		try {
			WebElement element = getDriver().findElement(locator);

			if (element != null) {
				JavascriptExecutor executor = (JavascriptExecutor) getDriver();
				executor.executeScript("arguments[0].scrollIntoView(true)", element);
			}
		} catch (Exception e) {
			Assert.fail("It wasn't possible to scroll to the element: " + locator + "\n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
		}

	}

	/**
	 * This method scroll the page to a specific web element.
	 * 
	 * @param element WebElement.
	 */
	public void scrollToElement(WebElement element) {
		try {
			if (element != null) {
				JavascriptExecutor executor = (JavascriptExecutor) getDriver();
				executor.executeScript("arguments[0].scrollIntoView(true)", element);
			}
		} catch (Exception e) {
			Assert.fail("It wasn't possible to scroll to the element locator element: "
					+ "\n-----==========-----\nMessage: " + e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method highlights an element.
	 * 
	 * @param locator Element locator.
	 * @param arg     (boolean) If True highlight with green color, if False
	 *                highlight with red color.
	 */
	public void highlight(By locator, boolean arg) {
		try {
			WebElement element = getDriver().findElement(locator);
			String color = arg ? "outline: 4px solid #00FF00;" : "outline: 4px solid #ff0000;";
			this.scrollToElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].setAttribute('style, arguments[1]');", element, color);
		} catch (Exception e) {
			Assert.fail("It wasn't possible to highlight the element: " + locator + "\n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method highlight an element.
	 * 
	 * @param element WebElement.
	 * @param arg     (boolean) If True highlight with green color, if False
	 *                highlight with red color.
	 */
	public void highlight(WebElement element, boolean arg) {
		try {
			String color = arg ? "outline: 4px solid #00FF00;" : "outline: 4px solid #ff0000;";
			this.scrollToElement(element);
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, color);
		} catch (Exception e) {
			Assert.fail("It wasn't possible to highlight the element locator element: "
					+ "\n-----==========-----\nMessage: " + e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method focus on an element using JavaScript command.
	 * 
	 * @param locator Element locator.
	 */
	public void focusJS(By locator) {
		try {
			WebElement element = getDriver().findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", element);
		} catch (Exception e) {
			Assert.fail("It wasn't possible to focus on the element: " + locator + "\n-----==========-----\nMessage: "
					+ e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method execute a click using javaScript command.
	 * 
	 * @param locator Element locator.
	 */
	public void clickJS(By locator) {
		try {
			waitElementIsVisible(locator, 10);
			WebElement element = getDriver().findElement(locator);
			this.highlight(element, true);
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			Assert.fail("It wasn't possible to click using JavaScript on the element: " + locator
					+ "\n-----==========-----\nMessage: " + e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method executes a double click using JavaScript command.
	 * 
	 * @param locator Element locator.
	 */
	public void doubleClickJS(By locator) {
		try {
			waitElementIsVisible(locator, 10);
			WebElement element = getDriver().findElement(locator);
			this.highlight(element, true);
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].doubleclick();", element);
		} catch (Exception e) {
			Assert.fail("It wasn't possible to double click using JavaScript on the element: " + locator
					+ "\n-----==========-----\nMessage: " + e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method executes a click on a list.
	 * 
	 * @param locator Element locator.
	 * @param text    Text contained into the element on the list.
	 */
	public void clickOnListJS(By locator, String text) {
		try {
			List<WebElement> elements = getDriver().findElements(locator);
			for (WebElement webElement : elements) {
				if (webElement.getText().contains(text)) {
					JavascriptExecutor executor = (JavascriptExecutor) getDriver();
					executor.executeScript("arguments[0].click();", webElement);
					break;
				}
			}
		} catch (Exception e) {
			Assert.fail("It wasn't possible to click on the list of elements using JavaScript " + locator
					+ "\n-----==========-----\nMessage: " + e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * Send keys (text) to a specific element using JavaScript command.
	 * 
	 * @param locator Element locator.
	 * @param text    The text that will be sent.
	 */
	public void sendKeysJS(By locator, String text) {
		try {
			waitElementIsVisible(locator, 10);
			WebElement element = getDriver().findElement(locator);
			this.highlight(element, true);
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].value = '" + text + "';", element);
		} catch (Exception e) {
			Assert.fail("It wasn't possible to send keys on the element using JavaScript " + locator
					+ "\n-----==========-----\nMessage: " + e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method obtains the value of an element.
	 * 
	 * @param locator Element locator.
	 * @return The value of an element.
	 */
	public String getElementValue(By locator) {
		return getDriver().findElement(locator).getAttribute("value");
	}

	/**
	 * This method makes the driver sleep to wait an specific element to be visible.
	 * 
	 * @param locator Element locator.
	 * @param timeout Time (long). Recommended: 30.
	 * @return True if the element is visible, False if it's not.
	 * @throws InterruptedException
	 */
	public boolean waitElementIsVisible(By locator, int timeout) throws InterruptedException {
		int count = 0;
		do {
			try {
				WebElement element = getDriver().findElement(locator);
				if (element.isDisplayed()) {
					return true;
				}
			} catch (Exception e) {
			}
			Thread.sleep(250);
			count++;
		} while (count < timeout * 4);
		return false;
	}

	/**
	 * This method makes the driver sleep to wait a specific element to be visible.
	 * 
	 * @param locator Element locator.
	 * @param timeout Time (long). Recommended: 30.
	 * @return True if the element is NOT visible. False if it's.
	 * @throws InterruptedException
	 */
	public boolean waitElementIsNotVisible(By locator, int timeout) throws InterruptedException {
		int count = 0;
		do {
			try {
				WebElement element = getDriver().findElement(locator);
				if (!element.isDisplayed()) {
					return true;
				}
			} catch (Exception e) {
			}
			Thread.sleep(250);
			count++;
		} while (count < timeout * 4);
		return false;
	}

	/**
	 * This method makes the driver wait until a specific element is located.
	 * 
	 * @param locator Element locator.
	 * @param timeout Time (long). Recommended: 30.
	 */
	public void waitWebDriverElementExists(By locator, long timeout) {
		WebDriverWait driverWait = new WebDriverWait(getDriver(), timeout);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * This method makes the driver wait until all elements from an element be
	 * loaded.
	 * 
	 * @param locator
	 * @param timeout Time (long). Recommended: 30.
	 */
	public void waitWebDriverElementListPresent(By locator, long timeout) {
		WebDriverWait driverWait = new WebDriverWait(getDriver(), timeout);
		driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	/**
	 * This method makes the driver sleep to wait a specific element to be enabled.
	 * 
	 * @param locator Element locator.
	 * @param timeout Time (long). Recommended: 30.
	 * @return True if the element is Enable, False if it's not.
	 * @throws InterruptedException
	 */
	public boolean waitElementIsEnable(By locator, long timeout) throws InterruptedException {
		int count = 0;
		do {
			try {
				WebElement element = getDriver().findElement(locator);
				if (element.isDisplayed() && element.isEnabled()) {
					return true;
				}
			} catch (Exception e) {
			}
			Thread.sleep(250);
			count++;
		} while (count < timeout * 4);
		return false;
	}

	/**
	 * This method verifies if the element contains a specific text.
	 * 
	 * @param locator Element locator.
	 * @param text    Text that will be verified.
	 * @return True if the element contains the text, False if it's not.
	 */
	public boolean elementContainsText(By locator, String text) {
		try {
			WebElement element = getDriver().findElement(locator);
			this.highlight(element, true);
			return element.getText().contains(text);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * This methods verifies if the attribute of an element contains a specific text
	 * by it's locator.
	 * 
	 * @param locator Element locator.
	 * @param att     Attribute name.
	 * @param text    Text to be verified if it's contained into the attribute.
	 * @return True if the text it's contained, False if it's not.
	 */
	public boolean attributeContaisText(By locator, String att, String text) {
		try {
			WebElement element = getDriver().findElement(locator);
			this.highlight(element, true);
			return element.getAttribute(att).contains(text);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * This method obtains the value of an attribute by it's locator.
	 * 
	 * @param locator Element locator.
	 * @param att     Attribute to obtain it's value.
	 * @return The attribute value.
	 */
	public String getAttribute(By locator, String att) {

		try {
			WebElement element = getDriver().findElement(locator);
			this.highlight(element, true);
			return element.getAttribute(att);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * This method checks if an element is disabled by it's locator.
	 * 
	 * @param locator Element locator.
	 * @return True if the element is enabled, False if it's not.
	 */
	public boolean isEnabled(By locator) {
		try {
			WebElement element = getDriver().findElement(locator);
			this.highlight(element, true);
			return element.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This method check if a element is disabled by it's locator.
	 * 
	 * @param locator Element locator.
	 * @return True if the element isn't enabled, False if it's.
	 */
	public boolean isDisabled(By locator) {
		try {
			WebElement element = getDriver().findElement(locator);
			this.highlight(element, true);
			return !element.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This method checks if an element is being displayed by it's locator.
	 * 
	 * @param locator Element locator.
	 * @return True if the element is being displayed, False if it's not.
	 */
	public boolean isDisplayed(By locator) {
		try {
			WebElement element = getDriver().findElement(locator);
			this.highlight(element, true);
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This method checks if an element is not being displayed by it's locator.
	 * 
	 * @param locator Element locator.
	 * @return True if the element is not being displayed, false if it is.
	 */
	public boolean isNotDisplayed(By locator) {
		try {
			WebElement element = getDriver().findElement(locator);
			this.highlight(element, true);
			return !element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This method executes a refresh on the actual page.
	 */
	public void refreshPage() {
		try {
			getDriver().navigate().refresh();
		} catch (Exception e) {
		}
	}

	/**
	 * This method selects a specific element from a drop down list by it's index.
	 * 
	 * @param locator Element locator.
	 * @param index   Index of the element from the drop down list.
	 */
	public void setSelect(By locator, int index) {
		try {
			WebElement element = getDriver().findElement(locator);
			Select selectElement = new Select(getDriver().findElement(locator));
			if (element.isDisplayed() && element.isEnabled()) {
				this.highlight(element, true);
				selectElement.selectByIndex(index);
			}
		} catch (Exception e) {
			Assert.fail("It wasn't possible to select element locator index" + locator
					+ "\n-----==========-----\nMessage: " + e.getMessage() + "\n-----==========-----");
		}
	}

	/**
	 * This method selects a specific element from a drop down list by it's text.
	 * 
	 * @param locator Element locator.
	 * @param text    Text of the element from the drop down list.
	 */
	public void setSelect(By locator, String text) {
		try {
			WebElement element = getDriver().findElement(locator);
			Select selectElement = new Select(getDriver().findElement(locator));
			if (element.isDisplayed() && element.isEnabled()) {
				this.highlight(element, true);
				selectElement.selectByValue(text);
			}
		} catch (Exception e) {
			Assert.fail("It wasn't possible to select element locator index" + locator
					+ "\n-----==========-----\nMessage: " + e.getMessage() + "\n-----==========-----");
		}
	}

}
