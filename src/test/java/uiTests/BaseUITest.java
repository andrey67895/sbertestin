package uiTests;

import lombok.extern.java.Log;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.WebDriver;
import pages.ruYandexMarket.MainPage;
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
    protected MainPage mainPages;
    protected WebDriver driver;
    protected static final Map<String, String> optionDriver = new HashMap<>();
    protected static String domain;

    @BeforeAll
    public static void setProperties() {
        File file = new File("src/test/resources/test.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
            domain = properties.getProperty("domain");
            optionDriver.put("domain", domain);
            log.info(String.format("domain: %s", domain));
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

    @BeforeEach
    protected void initPage() {
        driver = getSessionWindowsMaximize(optionDriver);
        mainPages = new MainPage(driver);
    }

    @AfterEach
    public void sessionDown() {
        driver.quit();
    }
}
