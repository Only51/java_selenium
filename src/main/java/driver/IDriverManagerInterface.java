package driver;

import com.fasterxml.jackson.databind.JsonNode;
import org.openqa.selenium.WebDriver;
public interface IDriverManagerInterface {

    WebDriver createDriver(JsonNode capabilities);
}
