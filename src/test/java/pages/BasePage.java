package pages;

import org.openqa.selenium.WebDriver;

/**
 * Class to be extended by all Page Objects Model (POM) classes.
 * 
 * Contains common methods to be used by every page.
 * 
 * @author Dat Pham
 *
 */
public class BasePage {
	public WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Get the page title
	 * 
	 * @return
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	public void refreshPage(){
		driver.navigate().refresh();
	}
}
