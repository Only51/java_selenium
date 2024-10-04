package utils;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Utility class containing many static methods to help testing.
 * 
 * @author Dat Pham
 *
 */
public class SeleniumUtils {
	private static final int DEFAULT_WAIT_TIME = 10;
	
	/**
	 * Try to find an element in the page by xpath. If the element is not found,
	 * null is returned.
	 * 
	 * @param driver
	 * @param xpath
	 * @return
	 */
	public static WebElement findElement(WebDriver driver, String xpath) {
		try {
			return driver.findElement(By.xpath(xpath));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Try to find elements in the page by xpath. If the elements are not found,
	 * null is returned.
	 * 
	 * @param driver
	 * @param xpath
	 * @return
	 */
	public static List<WebElement> findElements(WebDriver driver, String xpath) {
		try {
			return driver.findElements(By.xpath(xpath));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Wait for an element to be visible on the screen, given a maximum time to wait
	 * 
	 * @param driver
	 * @param xpath
	 * @param timeOutInSeconds
	 * @return
	 */
	public static WebElement waitForElementToBeVisible(WebDriver driver, String xpath, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Wait for an element to be visible on the screen, using the DEFAULT_WAIT_TIME
	 * 
	 * @param driver
	 * @param xpath
	 * @return
	 */
	public static WebElement waitForElementToBeVisible(WebDriver driver, String xpath) {
		return waitForElementToBeVisible(driver, xpath, DEFAULT_WAIT_TIME);
	}
	
	/**
	 * Wait for an element to be present in the DOM, given a maximum time to wait
	 * 
	 * @param driver
	 * @param xpath
	 * @param timeOutInSeconds
	 * @return
	 */
	public static WebElement waitForElement(WebDriver driver, String xpath, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		try {
			return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Wait for an element to be present in the DOM, using the DEFAULT_WAIT_TIME
	 * 
	 * @param driver
	 * @param xpath
	 * @return
	 */
	public static WebElement waitForElement(WebDriver driver, String xpath) {
		return waitForElement(driver, xpath, DEFAULT_WAIT_TIME);
	}
	
	/**
	 * Wait for at least one element to be present in the DOM, given a maximum time to wait
	 * 
	 * @param driver
	 * @param xpath
	 * @param timeOutInSeconds
	 * @return
	 */
	public static List<WebElement> waitForElements(WebDriver driver, String xpath, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		try {
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Wait for at least one element to be present in the DOM, using the DEFAULT_WAIT_TIME
	 * 
	 * @param driver
	 * @param xpath
	 * @return
	 */
	public static List<WebElement> waitForElements(WebDriver driver, String xpath) {
		return waitForElements(driver, xpath, DEFAULT_WAIT_TIME);
	}
	
	/**
	 * Wait for an element to be clickable, given a maximum time to wait
	 * 
	 * @param driver
	 * @param xpath
	 * @param timeOutInSeconds
	 * @return
	 */
	public static WebElement waitForElementToBeClickable(WebDriver driver, String xpath, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		try {
			return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Wait for an element to be clickable, using the DEFAULT_WAIT_TIME
	 * 
	 * @param driver
	 * @param xpath
	 * @return
	 */
	public static WebElement waitForElementToBeClickable(WebDriver driver, String xpath) {
		return waitForElementToBeClickable(driver, xpath, DEFAULT_WAIT_TIME);
	}
	
	/**
	 * Wait for a certain condition to occur for up to DEFAULT_WAIT_TIME
	 * 
	 * Usage: waitUntil(driver, ExpectedConditions.elementToBeClickable(By.xpath("/input[@id='q']")))
	 * 
	 * @param condition
	 * @return
	 */
	public <T> T waitUntil(WebDriver driver, Function<? super WebDriver, T> condition) {
		return waitUntil(driver, condition, DEFAULT_WAIT_TIME);
	}
	
	/**
	 * Wait for a certain condition to occur for up to a given time in seconds
	 * 
	 * @param condition
	 * @param timeOutInSeconds
	 * @return
	 */
	public <T> T waitUntil(WebDriver driver, Function<? super WebDriver, T> condition, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		return wait.until(condition);
	}

	/**
	 * Wait for an element to be clickable, given a maximum time to wait
	 *
	 * @param driver
	 * @param element
	 * @param timeOutInSeconds
	 * @return
	 */
	public static Boolean waitForElementDisappear(WebDriver driver, String element, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		try {
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element)));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Convert WebElement type to By type
	 *
	 * @param we
	 * @return
	 */
	public static By toByVal(WebElement we) {
		// By format = "[foundFrom] -> locator: term"
		// see RemoteWebElement toString() implementation
		String[] data = we.toString().split(" -> ")[1].replace("]", "").split(": ");
		String locator = data[0];
		String term = data[1];

		switch (locator) {
			case "xpath":
				return By.xpath(term);
			case "css selector":
				return By.cssSelector(term);
			case "id":
				return By.id(term);
			case "tag name":
				return By.tagName(term);
			case "name":
				return By.name(term);
			case "link text":
				return By.linkText(term);
			case "class name":
				return By.className(term);
		}
		return (By) we;
	}

	/**
	 * Wait for an element to be clickable, given a maximum time to wait
	 *
	 * @param driver
	 * @param element
	 * @param expectedValue
	 * @param timeOutInSeconds
	 * @return
	 */
	public static void waitForValue(WebDriver driver, String element, String expectedValue, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		try {
			wait.until((ExpectedCondition<Boolean>) d -> {
                String currentValue = Objects.requireNonNull(findElement(driver, element)).getText();
                return currentValue.equals(expectedValue); // Check if the value has changed
            });

			// At this point, the value has changed; you can perform further actions
			System.out.println("The value has changed to: " + Objects.requireNonNull(findElement(driver, element)).getText());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Wait for an element to be clickable, given a maximum time to wait
	 *
	 * @param driver
	 * @param element
	 * @param initialValue
	 * @param timeOutInSeconds
	 * @return
	 */
	public static void waitForValueChange(WebDriver driver, String element, String initialValue, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		try {
			wait.until((ExpectedCondition<Boolean>) d -> {
				String currentValue = Objects.requireNonNull(findElement(driver, element)).getText();
				return !currentValue.equals(initialValue); // Check if the value has changed
			});

			// At this point, the value has changed; you can perform further actions
			System.out.println("The value has changed to: " + Objects.requireNonNull(findElement(driver, element)).getText());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
