package factory;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import driver.DriverManagerChrome;
import driver.DriverManagerEdge;
import driver.DriverManagerFirefox;
import org.openqa.selenium.WebDriver;

import utils.Constant;

/**
 * Class responsible to handle the WebDrivers 
 * 
 * @author Dat Pham
 *
 */
public class BrowserProvider {
    private static JsonNode loadCapabilities() {
        String fileSeparator = File.separator;
        String configFilePath = System.getProperty("user.dir") + Constant.PATH_CONFIG.replace("/",fileSeparator);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(new File(configFilePath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load capabilities from capabilities.json", e);
        }
    }
    public static WebDriver createDriver(String driverType) {
        JsonNode capabilities = loadCapabilities();
        if(driverType == null){
            driverType = "chrome";
        }
        switch (driverType.toLowerCase()) {

            case "chrome" : {
                return new DriverManagerChrome().createDriver(capabilities);
            }
            case "firefox" : {
                return new DriverManagerFirefox().createDriver(capabilities);
            }
            case "edge" : {
                return new DriverManagerEdge().createDriver(capabilities);
            }
            default : throw new IllegalArgumentException("Invalid Driver: " + driverType);
        }
    }
}
