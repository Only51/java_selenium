package driver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.Constant;

import java.io.File;
import java.io.IOException;

public class DriverManagerFirefox implements IDriverManagerInterface{
    @Override
    public WebDriver createDriver(JsonNode capabilities) {
        String fileSeparator = File.separator;
        String driverPath = System.getProperty("user.dir") +Constant.PATH_DRIVER.replace("/",fileSeparator) + "geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", driverPath);
        WebDriver driver;
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        for (JsonNode arg : capabilities.get("firefox").get("args")) {
            firefoxOptions.addArguments(arg.asText());
        }
        driver = new FirefoxDriver(firefoxOptions);
        return driver;
    }
}
