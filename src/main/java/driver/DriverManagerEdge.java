package driver;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.Constant;

import java.io.File;

public class DriverManagerEdge implements IDriverManagerInterface{
    @Override
    public WebDriver createDriver(JsonNode capabilities) {
        WebDriverManager.edgedriver().setup();
        WebDriver driver;
        EdgeOptions edgeOptions = new EdgeOptions();
        for (JsonNode arg : capabilities.get("firefox").get("args")) {
            edgeOptions.setCapability(arg.asText(),arg.asText());
        }
        driver = new EdgeDriver(edgeOptions);
        return driver;
    }
}
