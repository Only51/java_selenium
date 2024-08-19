package tests;

import com.aventstack.extentreports.Status;
import enums.Browser;
import factory.BrowserProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import utils.Constant;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class ProductDetailsTest extends BaseTest{
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
    public void verifyProductDetails() {

        extentTest = extentReports.createTest("TC-08","Verify user can view product details when clicking on item’s name");
        LoginPage loginPage = new LoginPage(driver);
        extentTest.log(Status.INFO, "Login successfully with valid user/pass");
        HomePage homePage = loginPage.login(Constant.Username,Constant.Password);

        extentTest.log(Status.INFO, "Verify home page displayed after login successfully");
        assertEquals(homePage.getTitle(),"Swag Labs");

        extentTest.log(Status.INFO, "Click on product name of product 'Sauce Labs Backpack'");
        ProductDetailsPage productDetails = homePage.clickProductName("Sauce Labs Backpack");

        extentTest.log(Status.INFO, "Verify product name on product details page");
        assertEquals(productDetails.getProductName(),"Sauce Labs Backpack");

        extentTest.log(Status.INFO, "Verify product description on product details page");
        assertEquals(productDetails.getProductDescription(),"carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");

        extentTest.log(Status.INFO, "Verify product price on product details page");
        assertEquals(productDetails.getProductPrice(),"$29.99");
    }

    @Test
    public void verifyUserCanAddProductOnProductDetailsPage() {

        extentTest = extentReports.createTest("TC-09","Verify user can add product to cart in product details page");
        LoginPage loginPage = new LoginPage(driver);
        extentTest.log(Status.INFO, "Login successfully with valid user/pass");
        HomePage homePage = loginPage.login(Constant.Username,Constant.Password);

        extentTest.log(Status.INFO, "Verify home page displayed after login successfully");
        assertEquals(homePage.getTitle(),"Swag Labs");

        extentTest.log(Status.INFO, "Click on product name of product 'Sauce Labs Backpack'");
        ProductDetailsPage productDetails = homePage.clickProductName("Sauce Labs Backpack");

        extentTest.log(Status.INFO, "Click on button 'Add to Cart'");
        productDetails.clickAddToCart();

        extentTest.log(Status.INFO, "Verify number of product in shopping cart icon is 1");
        String strNumberOfProduct = productDetails.getNumberOfProductInShoppingCart();
        assertEquals(strNumberOfProduct,"1");
    }

    @Test
    public void verifyUserCanRemoveProductOnProductDetailsPage() {

        extentTest = extentReports.createTest("TC-10","Verify user can add product to cart in product details page");
        LoginPage loginPage = new LoginPage(driver);
        extentTest.log(Status.INFO, "Login successfully with valid user/pass");
        HomePage homePage = loginPage.login(Constant.Username,Constant.Password);

        extentTest.log(Status.INFO, "Verify home page displayed after login successfully");
        assertEquals(homePage.getTitle(),"Swag Labs");

        extentTest.log(Status.INFO, "Click on product name of product 'Sauce Labs Backpack'");
        ProductDetailsPage productDetails = homePage.clickProductName("Sauce Labs Backpack");

        extentTest.log(Status.INFO, "Click on button 'Add to Cart'");
        productDetails.clickAddToCart();

        extentTest.log(Status.INFO, "Verify number of product in shopping cart icon is 1");
        String strNumberOfProduct = productDetails.getNumberOfProductInShoppingCart();
        assertEquals(strNumberOfProduct,"1");

        extentTest.log(Status.INFO, "Click on button 'Remove'");
        productDetails.clickRemoveProduct();

        extentTest.log(Status.INFO, "Verify number of product in shopping cart icon is 1");
        String strNumberOfProductRemoved = productDetails.getNumberOfProductInShoppingCart();
        assertEquals(strNumberOfProductRemoved,"There is no item shopping cart");
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
