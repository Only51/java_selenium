package driver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.Constant;

import java.io.File;
import java.io.IOException;

public class DriverManagerChrome implements IDriverManagerInterface{
    @Override
    public WebDriver createDriver(JsonNode capabilities) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver;
        ChromeOptions chromeOptions = new ChromeOptions();
//        for (JsonNode arg : capabilities.get("chrome").get("args")) {
//            chromeOptions.addArguments(arg.asText());
//        }
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        return driver;
    }
}
