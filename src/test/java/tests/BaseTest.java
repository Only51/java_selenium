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
    String browserName = "";

    @BeforeTest
    @Parameters("browser")
    public void init(@Optional("chrome") String browser) {
        this.browserName = browser;
        reportUtils.startReporter(browserName);
    }
    @BeforeMethod
    public void openWeb(){
        driver = BrowserProvider.createDriver(browserName);
        driver.manage().window().maximize();
        driver.get(Constant.BASE_URL);
    }
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        screenShotPath = Report.capture(driver);
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

        driver.quit();
        driver = null;
    }
    @AfterTest
    public void flushReport() throws IOException {
        //to write or update test information to the reporter
        reportUtils.extentReports.flush();
    }

}
