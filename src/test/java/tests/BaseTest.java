package tests;

import com.aventstack.extentreports.Status;
import factory.BrowserProvider;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.Constant;
import utils.Report;

import java.io.IOException;

public class BaseTest {
    WebDriver driver;
    String screenShotPath = "";
    Report reportUtils = new Report();
    @BeforeMethod
    @Parameters("browser")
    public void init(@Optional String browser) {
        driver = BrowserProvider.createDriver(browser);
        driver.manage().window().maximize();
        driver.get(Constant.BASE_URL);
        reportUtils.startReporter(browser);
    }

    @AfterMethod
    public void getResult(ITestResult result) throws IOException {
        screenShotPath = reportUtils.capture(driver);
        if(result.getStatus() == ITestResult.FAILURE) {
            reportUtils.extentTest.log(Status.FAIL,result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            reportUtils.extentTest.log(Status.PASS, result.getTestName());
        }
        else {
            reportUtils.extentTest.log(Status.SKIP, result.getTestName());
        }
        reportUtils.extentTest.addScreenCaptureFromPath(screenShotPath);
    }
    @AfterTest
    public void tearDown() throws IOException {
        //to write or update test information to the reporter
        reportUtils.extentReports.flush();
        quitDriver();
    }

//    /**
//     * This method will be executed at the end of the test.
//     */
    public void quitDriver() throws IOException {
        driver.quit();
        driver = null;
    }

}
