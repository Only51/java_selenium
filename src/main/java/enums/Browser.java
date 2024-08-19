package enums;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import factory.BrowserProvider;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;

/**
 * This enum define how to initialize each browser driver.
 * 
 * @author Dat Pham
 *
 */
public enum Browser {
	FIREFOX {
		@Override
		public WebDriver initialize(DesiredCapabilities capabilities) {
			synchronized (BrowserProvider.class) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "/src/main/resources/webdriver/geckodriver");
				capabilities.setCapability("marionette", true);
				FirefoxOptions options = new FirefoxOptions();
				options.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox");
				options.merge(capabilities);
				return new FirefoxDriver(options);
			}
		}
	},

	CHROME {
		@Override
		public WebDriver initialize(DesiredCapabilities capabilities) {
			synchronized (BrowserProvider.class) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/src/main/resources/webdriver/chromedriver");
				ChromeOptions options = new ChromeOptions();
				options.merge(capabilities);
				return new ChromeDriver(options);
			}
		}
	},

	IE {
		@Override
		public WebDriver initialize(DesiredCapabilities capabilities) {
			synchronized (BrowserProvider.class) {
				InternetExplorerOptions options = new InternetExplorerOptions();
				options.merge(capabilities);
				return new InternetExplorerDriver(options);
			}
		}
	};

	/**
	 * Method to be implemented by each Browser Enum.
	 * 
	 * @param capabilities
	 * @return
	 */
	public abstract WebDriver initialize(DesiredCapabilities capabilities);

	@Override
	public String toString() {
		switch (this) {
		case FIREFOX:
			return "FIREFOX";
		case CHROME:
			return "CHROME";
		case IE:
			return "IE";
		default:
			throw new IllegalArgumentException();
		}
	}
}
