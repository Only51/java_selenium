package tests;

import com.aventstack.extentreports.Status;
import org.testng.annotations.*;
import pages.HomePage;


import static org.testng.Assert.assertEquals;

public class HomeTests  extends BaseTest{

    /**
     * This method will be executed before the test start.
     */

    @Test
    public void verifyDialogCloseWhenCheckingOnCheckbox() {
        reportUtils.extentTest = reportUtils.extentReports.createTest("TC-01","Verify discount dialog will be close when check on checkbox 'Do not display for 1 days'");
        HomePage homePage = new HomePage(driver);
        reportUtils.extentTest.log(Status.INFO, "Check on checkbox 'Do not display for 1 days'");
        homePage.checkOnCheckbox();
        reportUtils.extentTest.log(Status.INFO, "Verify discount popup is closed");
        assertEquals(homePage.isPopupDisplay(),false);
        reportUtils.extentTest.log(Status.INFO, "Refresh home page");
        homePage.refreshPage();
        reportUtils.extentTest.log(Status.INFO, "Verify discount popup is closed");
        assertEquals(homePage.isPopupDisplay(),false);
    }
}
