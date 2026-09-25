package drivers;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class BasesClassDriver {

    MyChromeDriver myChromeDriver;
    public WebDriver driver;
    Properties properties;
    String environment;


    public BasesClassDriver(){
        properties = new Properties();
        environment = System.getProperty("environment", "qa");
        try {
            FileInputStream file = new FileInputStream(new File("config.properties"));
            properties.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        String envOverride = System.getenv(key.equals("baseUrl") ? "BASE_URL" : key);
        if (envOverride != null && !envOverride.isBlank()) {
            return envOverride;
        }
        return properties.getProperty(environment + "." + key);
    }

    public void setup()
    {
        if(driver==null)
        {
            String baseUrl = getProperty("baseUrl");
            String browser = System.getProperty("browser", "chrome");
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = MyChromeDriver.chromeHisBrowserWeb().onTheUrl(baseUrl);
                    break;
//                case "firefox":
//                    driver = MyFirefoxDriver.firefoxHisBrowserWeb().onTheUrl(baseUrl);
//                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }
    }

    public void openBaseURL()
    {
        String baseUrl = getProperty("baseUrl");
        driver = myChromeDriver.onTheUrl(baseUrl);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void tearDown()
    {
        driver.quit();
    }
}
