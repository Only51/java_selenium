package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.SeleniumUtils;
import java.util.List;

public class HomePage extends BasePage {
    private String chkDoNotDisplayFor1Days = "//label[text()='Do not display for 1 days']";
//    private String chkDoNotDisplayFor1Days = "//input[@type='checkbox' and @id='common-popup-checkbox-skip-0']";
    private String dlgDiscount = "//div[ @id='common-popup-wrapper']";
    /**
     * Constructor of the page. Initialize the Page Factory objects.
     *
     * @param driver
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }


    public void checkOnCheckbox(){
        SeleniumUtils.waitForElementToBeClickable(driver,chkDoNotDisplayFor1Days).click();
    }

    public boolean isPopupDisplay(){
        boolean isDisplay = false;
        WebElement dlg = SeleniumUtils.findElement(driver,dlgDiscount);
        if(dlg != null){
            isDisplay = true;
        }
        return isDisplay;
    }

}
