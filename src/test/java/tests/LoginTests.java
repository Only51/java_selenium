package tests;

import com.aventstack.extentreports.Status;
import enums.Browser;
import factory.BrowserProvider;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import utils.Constant;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTests  extends BaseTest{

    /**
     * This method will be executed before the test start.
     */
    @BeforeMethod
    public void init() {
        driver = BrowserProvider.createDriver(Browser.FIREFOX);
        driver.manage().window().maximize();
        driver.get(Constant.BASE_URL);
    }

    /**
     * This method perform a Google search test. We can have multiple @Test methods inside this class.
     */
    @Test(priority = 1)
    public void loginTest() {

        extentTest = extentReports.createTest("TC-01","Verify user can login with valid username and password");
        LoginPage loginPage = new LoginPage(driver);
        extentTest.log(Status.INFO, "Input username and password");
        HomePage homePage = loginPage.login(Constant.Username,Constant.Password);

        extentTest.log(Status.INFO, "Verify home page displayed after login successfully");
        assertEquals(homePage.getTitle(),"Swag Labs");
    }

    @Test(priority = 2)
    public void loginTestInvalid() {

        extentTest = extentReports.createTest("TC-02","Verify user can’t login with invalid username and password");
        LoginPage loginPage = new LoginPage(driver);
        extentTest.log(Status.INFO, "Input username and password");
        loginPage.loginInvalid(Constant.Username,"invalid",true);

        extentTest.log(Status.INFO, "Verify error message display with invalid username/password: Epic sadface: Username and password do not match any user in this service");
        Boolean isValid = loginPage.verifyErrorMessage("Epic sadface: Username and password do not match any user in this service");
        assertTrue(isValid);
    }

    @Test(priority = 3)
    public void loginLockedAccount() {

        extentTest = extentReports.createTest("TC-03","Verify user can’t login with locked account");
        LoginPage loginPage = new LoginPage(driver);
        extentTest.log(Status.INFO, "Input username and password");
        loginPage.loginInvalid(Constant.LockedUsername,Constant.Password,true);

        extentTest.log(Status.INFO, "Verify error message display : Epic sadface: Sorry, this user has been locked out.\n");
        Boolean isValid = loginPage.verifyErrorMessage("Epic sadface: Sorry, this user has been locked out.");
        assertTrue(isValid);
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
