package tests;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchPage;
import utils.SeleniumUtils;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class SearchTests extends BaseTest{

    /**
     * This method will be executed before the test start.
     */

    @Test
    public void TC03_VerifySearchResultDisplayCorrectlyNumberOfGameItemsWithFilter() {
        reportUtils.extentTest = reportUtils.extentReports.createTest("TC-03","Verify search page display correctly number of game items");
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);

        reportUtils.extentTest.log(Status.INFO, "Check on checkbox 'Do not display for 1 days'");
        homePage.checkOnCheckbox();
        reportUtils.extentTest.log(Status.INFO, "Enter 'action' value into search textbox");
        homePage.searchGame("action");
        reportUtils.extentTest.log(Status.INFO, "Click on search button");
        homePage.clickSearchButton();
        reportUtils.extentTest.log(Status.INFO, "Wait for Search Result Label display");
        searchPage.waitForSearchFilterOptionDisplay("action",10);
        reportUtils.extentTest.log(Status.INFO, "Get current number of items");
        String currentBeforeFree = searchPage.getNumberOfResult();
        reportUtils.extentTest.log(Status.INFO, "Click on Free radio of Game Price section");
        searchPage.clickGamePrice("Free");
        reportUtils.extentTest.log(Status.INFO, "Wait for value change");
        searchPage.waitForDataSearchResultChange(currentBeforeFree);
        reportUtils.extentTest.log(Status.INFO, "Get current number of items");
        String currentBeforeDemo = searchPage.getNumberOfResult();
        reportUtils.extentTest.log(Status.INFO, "Click on DEMO radio of Game Type section");
        searchPage.clickGameType("DEMO");
        reportUtils.extentTest.log(Status.INFO, "Wait for value change");
        searchPage.waitForDataSearchResultChange(currentBeforeDemo);
        reportUtils.extentTest.log(Status.INFO, "Generate expected number of game items");
        String expectedNumOfGameItems = String.valueOf(searchPage.calculateTotalGameItems());
        reportUtils.extentTest.log(Status.INFO, "Get number of game items");
        String actualNumOfGameItems = searchPage.getNumberOfResult();
        reportUtils.extentTest.log(Status.INFO, "Verify number of game items display correctly");
        assertEquals(expectedNumOfGameItems,actualNumOfGameItems.trim());
    }

    @Test
    public void TC04_VerifyProductIsOpenedSuccessfullyViaSearchResult() {
        reportUtils.extentTest = reportUtils.extentReports.createTest("TC-03","Verify search page display correctly number of game items");
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);

        reportUtils.extentTest.log(Status.INFO, "Check on checkbox 'Do not display for 1 days'");
        homePage.checkOnCheckbox();
        reportUtils.extentTest.log(Status.INFO, "Enter 'action' value into search textbox");
        homePage.searchGame("Love of");
        reportUtils.extentTest.log(Status.INFO, "Press enter");
        homePage.enterSearch();
        reportUtils.extentTest.log(Status.INFO, "Wait for Search Result Label display");
        searchPage.waitForSearchFilterOptionDisplay("Love of",10);
        reportUtils.extentTest.log(Status.INFO, "Click on product Pulse of Love");
        searchPage.clickGameTitle("Pulse of Love");
//        reportUtils.extentTest.log(Status.INFO, "Get current number of items");
//        String currentBeforeFree = searchPage.getNumberOfResult();
//        reportUtils.extentTest.log(Status.INFO, "Click on Free radio of Game Price section");
//        searchPage.clickGamePrice("Free");
//        reportUtils.extentTest.log(Status.INFO, "Wait for value change");
//        searchPage.waitForDataSearchResultChange(currentBeforeFree);
//        reportUtils.extentTest.log(Status.INFO, "Get current number of items");
//        String currentBeforeDemo = searchPage.getNumberOfResult();
//        reportUtils.extentTest.log(Status.INFO, "Click on DEMO radio of Game Type section");
//        searchPage.clickGameType("DEMO");
//        reportUtils.extentTest.log(Status.INFO, "Wait for value change");
//        searchPage.waitForDataSearchResultChange(currentBeforeDemo);
//        reportUtils.extentTest.log(Status.INFO, "Generate expected number of game items");
//        String expectedNumOfGameItems = String.valueOf(searchPage.calculateTotalGameItems());
//        reportUtils.extentTest.log(Status.INFO, "Get number of game items");
//        String actualNumOfGameItems = searchPage.getNumberOfResult();
//        reportUtils.extentTest.log(Status.INFO, "Verify number of game items display correctly");
//        assertEquals(expectedNumOfGameItems,actualNumOfGameItems.trim());
    }

}
