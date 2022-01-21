package uiTests;

import enums.Services;
import lombok.extern.java.Log;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.WebDriver;
import webdriver.WebDriverUI;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Log
@Timeout(60)
public abstract class BaseUITest extends WebDriverUI {
    protected WebDriver driver;
    protected static final Map<String, String> optionDriver = new HashMap<>();
    protected String domain;

    protected BaseUITest(Services services) {
        log.info(String.format("domain: %s", services.getServices()));
        this.domain = services.getServices();
    }

    @BeforeAll
    public static void setProperties() {
        File file = new File("src/test/resources/test.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
            String headless = properties.getProperty("headless");
            optionDriver.put("headless", headless);
            log.info(String.format("headless: %s", headless));
            String logOption = properties.getProperty("log");
            optionDriver.put("log", logOption);
            log.info(String.format("log: %s", logOption));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void sessionDown() {
        driver.quit();
    }
}
