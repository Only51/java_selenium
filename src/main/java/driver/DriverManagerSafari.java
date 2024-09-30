package driver;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class DriverManagerSafari implements IDriverManagerInterface{
    @Override
    public WebDriver createDriver(JsonNode capabilities) {
        WebDriverManager.safaridriver().setup();
        WebDriver driver;
        SafariOptions safariOptions = new SafariOptions();
//        for (JsonNode arg : capabilities.get("safari").get("args")) {
//            safariOptions.addArguments(arg.asText());
//        }
        driver = new SafariDriver(safariOptions);
        return driver;
    }
}
