package pages;

import org.openqa.selenium.WebDriver;
import utils.SeleniumUtils;

public class LoginPage extends BasePage {
    private WebDriver driver;

    private String txtUserName = "//input[@id='user-name']";
    private String txtPassword = "//input[@id='password']";
    private String btnLogin = "//input[@id='login-button']";
    private String lblErrorMsg = "//h3[@data-test='error']";

    /**
     * Constructor of the page. Initialize the Page Factory objects.
     *
     * @param driver
     */
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public HomePage login(String username, String pass) {
        SeleniumUtils.waitForElement(driver, txtUserName).sendKeys(username);
        SeleniumUtils.waitForElement(driver, txtPassword).sendKeys(pass);
        SeleniumUtils.waitForElementToBeClickable(driver, btnLogin).click();

        return new HomePage(driver);
    }

    public LoginPage loginInvalid(String username, String pass, Boolean isClick) {
        SeleniumUtils.waitForElement(driver, txtUserName).sendKeys(username);
        SeleniumUtils.waitForElement(driver, txtPassword).sendKeys(pass);
        if(isClick){
            SeleniumUtils.waitForElementToBeClickable(driver, btnLogin).click();
        }
        return this;
    }
    public Boolean verifyErrorMessage(String expectedMsg){
        String actualMsg = SeleniumUtils.waitForElement(driver, lblErrorMsg).getText();
        return actualMsg.trim().contains(expectedMsg);
    }
}
