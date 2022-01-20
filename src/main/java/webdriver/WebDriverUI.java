package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Map;

public abstract class WebDriverUI extends WebDriverActions {
    public static WebDriver getSession(Map<String, String> optionDriver) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(Boolean.parseBoolean(optionDriver.get("headless")));
        if (Boolean.parseBoolean(optionDriver.get("log"))) {
            chromeOptions.setLogLevel(ChromeDriverLogLevel.ALL);
        }
        return new ChromeDriver(chromeOptions);
    }

    @Step("Открыть браузер Chrome и развернуть на весь экран.")
    public static WebDriver getSessionWindowsMaximize(Map<String, String> optionDriver) {
        WebDriver webDriver = getSession(optionDriver);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        webDriver.manage().window().maximize();
        return webDriver;
    }
}
