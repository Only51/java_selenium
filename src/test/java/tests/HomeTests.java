package tests;

import com.aventstack.extentreports.Status;
import enums.Browser;
import factory.BrowserProvider;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import utils.Constant;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomeTests  extends BaseTest{

    /**
     * This method will be executed before the test start.
     */
    @BeforeMethod
    public void init() {
        driver = BrowserProvider.createDriver(Browser.CHROME);
        driver.manage().window().maximize();
        driver.get(Constant.BASE_URL);
    }

    @Test
    public void verifyProductsDisplay() {

        List<String> arrProducts = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt","Sauce Labs Fleece Jacket","Sauce Labs Onesie","Test.allTheThings() T-Shirt (Red)");
        extentTest = extentReports.createTest("TC-04","Verify all product will be displayed when user login successfully");
        LoginPage loginPage = new LoginPage(driver);
        extentTest.log(Status.INFO, "Login successfully with valid user/pass");
        HomePage homePage = loginPage.login(Constant.Username,Constant.Password);

        extentTest.log(Status.INFO, "Verify home page displayed after login successfully");
        assertEquals(homePage.getTitle(),"Swag Labs");

        extentTest.log(Status.INFO, "Verify all products will be displayed when user login successfully");
        assertTrue(homePage.verifyProductsDisplay(arrProducts));
    }

    @Test
    public void verifyUserCanAddItemOnProductPage() {

        extentTest = extentReports.createTest("TC-05","Verify user can add an item to cart");
        LoginPage loginPage = new LoginPage(driver);
        extentTest.log(Status.INFO, "Login successfully with valid user/pass");
        HomePage homePage = loginPage.login(Constant.Username,Constant.Password);

        extentTest.log(Status.INFO, "Verify home page displayed after login successfully");
        assertEquals(homePage.getTitle(),"Swag Labs");

        extentTest.log(Status.INFO, "Click on button 'Add to Cart' of product 'Sauce Labs Bolt T-Shirt'");
        homePage.clickAddToCart("Sauce Labs Bolt T-Shirt");

        extentTest.log(Status.INFO, "Verify number of product in shopping cart icon is 1");
        String strNumberOfProduct = homePage.getNumberOfProductInShoppingCart();
        assertEquals(strNumberOfProduct,"1");

    }

    @Test
    public void verifyUserCanAddMultipleItemOnProductPage() {

        extentTest = extentReports.createTest("TC-06","Verify user can add an item to cart");
        LoginPage loginPage = new LoginPage(driver);
        extentTest.log(Status.INFO, "Login successfully with valid user/pass");
        HomePage homePage = loginPage.login(Constant.Username,Constant.Password);

        extentTest.log(Status.INFO, "Verify home page displayed after login successfully");
        assertEquals(homePage.getTitle(),"Swag Labs");

        extentTest.log(Status.INFO, "Click on button 'Add to Cart' of product 'Sauce Labs Bolt T-Shirt'");
        homePage.clickAddToCart("Sauce Labs Backpack");

        extentTest.log(Status.INFO, "Click on button 'Add to Cart' of product 'Sauce Labs Bolt T-Shirt'");
        homePage.clickAddToCart("Sauce Labs Bike Light");

        extentTest.log(Status.INFO, "Click on button 'Add to Cart' of product 'Sauce Labs Bolt T-Shirt'");
        homePage.clickAddToCart("Sauce Labs Bolt T-Shirt");

        extentTest.log(Status.INFO, "Verify number of product in shopping cart icon is 1");
        String strNumberOfProduct = homePage.getNumberOfProductInShoppingCart();
        assertEquals(strNumberOfProduct,"3");

    }

    @Test
    public void verifyUserCanRemoveItemOnProductPage() {

        extentTest = extentReports.createTest("TC-07","Verify user can add an item to cart");
        LoginPage loginPage = new LoginPage(driver);
        extentTest.log(Status.INFO, "Login successfully with valid user/pass");
        HomePage homePage = loginPage.login(Constant.Username,Constant.Password);

        extentTest.log(Status.INFO, "Verify home page displayed after login successfully");
        assertEquals(homePage.getTitle(),"Swag Labs");

        extentTest.log(Status.INFO, "Click on button 'Add to Cart' of product 'Sauce Labs Bolt T-Shirt'");
        homePage.clickAddToCart("Sauce Labs Backpack");

        extentTest.log(Status.INFO, "Click on button 'Add to Cart' of product 'Sauce Labs Bolt T-Shirt'");
        homePage.clickAddToCart("Sauce Labs Bike Light");

        extentTest.log(Status.INFO, "Click on button 'Add to Cart' of product 'Sauce Labs Bolt T-Shirt'");
        homePage.clickAddToCart("Sauce Labs Bolt T-Shirt");

        extentTest.log(Status.INFO, "Verify number of product in shopping cart icon is 3");
        String strNumberOfProduct = homePage.getNumberOfProductInShoppingCart();
        assertEquals(strNumberOfProduct,"3");

        extentTest.log(Status.INFO, "Click on button 'Remove' of product 'Sauce Labs Bolt T-Shirt'");
        homePage.clickRemoveProduct("Sauce Labs Bolt T-Shirt");

        extentTest.log(Status.INFO, "Verify number of product in shopping cart icon is 3");
        String strNumberOfProductAfterRemove = homePage.getNumberOfProductInShoppingCart();
        assertEquals(strNumberOfProductAfterRemove,"2");
    }

    /**
     * This method will be executed at the end of the test.
     */
    @AfterMethod
    public void quitDriver() throws IOException {
        screenShotPath = capture(driver);
        driver.quit();
        driver = null;
    }
}
