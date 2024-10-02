package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Report {
    ExtentSparkReporter extentSparkReporter;
    public ExtentReports extentReports;
     public ExtentTest extentTest;
    public void startReporter(String browser)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        String reportName = "extentReport_" + browser + "_" + dtf.format(now);
        extentSparkReporter  = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/"+reportName+".html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);


        //configuration items to change the look and feel
        //add content, manage tests etc
        extentSparkReporter.config().setDocumentTitle(Constant.REPORT_TITLE);
        extentSparkReporter.config().setReportName("Test Report");
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

    public String captureBase64(WebDriver driver) throws IOException {
        String encodedBase64 = null;
        FileInputStream fileInputStream = null;
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File("src/test-output/screenshot" + System.currentTimeMillis()
                + ".png");
        String errflpath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);

        try {

            fileInputStream =new FileInputStream(Dest);
            byte[] bytes =new byte[(int)Dest.length()];
            fileInputStream.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return "data:image/png;base64,"+encodedBase64;


    }

    public static String capture(WebDriver driver) throws IOException {

        Screenshot s =new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        String imgPath = System.getProperty("user.dir") +"/src/test-output/screenshot" + System.currentTimeMillis()
                + ".png";
        ImageIO.write(s.getImage(),"PNG",new File(imgPath));
//        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        File Dest = new File("src/test-output/screenshot" + System.currentTimeMillis()
//                + ".png");
//        String errflpath = Dest.getAbsolutePath();
//        FileUtils.copyFile(scrFile, Dest);
        return imgPath;
    }
}
