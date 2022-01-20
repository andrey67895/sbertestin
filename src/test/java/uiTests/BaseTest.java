package uiTests;

import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import uiTests.ruYandexMarket.page.MainPage;
import uiTests.ruYandexMarket.page.MarketPage;
import webdriver.WebDriverUI;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Log
public abstract class BaseTest extends WebDriverUI {
    public static MainPage mainPages;
    public static MarketPage marketPage;
    private static final Map<String, String> optionDriver = new HashMap<>();
    protected static String domain;


    public static void pageFactory() {
        WebDriver driver = getSessionWindowsMaximize(optionDriver);
        mainPages = MainPage.getPage(driver);
        marketPage = MarketPage.getPage(driver);
    }

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
}
