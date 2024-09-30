package tests;

import com.aventstack.extentreports.Status;
import org.testng.annotations.*;
import pages.HomePage;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class HomeTests  extends BaseTest{

    /**
     * This method will be executed before the test start.
     */

    @Test
    public void TC01_VerifyDialogCloseWhenCheckingOnCheckbox() {
        reportUtils.extentTest = reportUtils.extentReports.createTest("TC-01","Verify discount dialog will be close when check on checkbox 'Do not display for 1 days'");
        HomePage homePage = new HomePage(driver);
        reportUtils.extentTest.log(Status.INFO, "Check on checkbox 'Do not display for 1 days'");
        homePage.checkOnCheckbox();
        reportUtils.extentTest.log(Status.INFO, "Verify discount popup is closed");
        assertFalse(homePage.isPopupDisplay());
        reportUtils.extentTest.log(Status.INFO, "Refresh home page");
        homePage.refreshPage();
        reportUtils.extentTest.log(Status.INFO, "Verify discount popup is closed");
        assertFalse(homePage.isPopupDisplay());
    }

    @Test
    public void TC02_VerifyLordNineGameDisplayInListDropDownSearch() {
        reportUtils.extentTest = reportUtils.extentReports.createTest("TC-02","Verify 'Lord Nine' game display in dropdown search when user input lord into search textbox");
        HomePage homePage = new HomePage(driver);
        reportUtils.extentTest.log(Status.INFO, "Check on checkbox 'Do not display for 1 days'");
        homePage.checkOnCheckbox();
        reportUtils.extentTest.log(Status.INFO, "Enter 'lord' value into search textbox");
        homePage.searchGame("lord");
        reportUtils.extentTest.log(Status.INFO, "Verify 'Lord Nine' game display in dropdown search");
        assertFalse(homePage.isLordNineDisplay());
    }
}
