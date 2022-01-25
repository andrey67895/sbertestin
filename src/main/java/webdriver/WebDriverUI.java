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

    /**
     * @param optionDriver Создаем WebDriver и прописываем параметры запуска
     * @return возвращаем созданный WebDriver
     */
    public static WebDriver getSession(Map<String, String> optionDriver) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        if (Boolean.parseBoolean(optionDriver.get("headless"))) {
            String userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.50 Safari/537.36";
            chromeOptions.setHeadless(true);
            chromeOptions.addArguments("user-agent=" + userAgent);
        }

        if (Boolean.parseBoolean(optionDriver.get("log"))) {
            chromeOptions.setLogLevel(ChromeDriverLogLevel.ALL);
        }
        return new ChromeDriver(chromeOptions);
    }

    /**
     * Разворачиваем браузер на весь экран
     * @param optionDriver - передаем параметры запуска
     * @return возвращаем созданный WebDriver
     */
    @Step("Открыть браузер Chrome и развернуть на весь экран.")
    public static WebDriver getSessionWindowsMaximize(Map<String, String> optionDriver) {
        WebDriver webDriver = getSession(optionDriver);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        webDriver.manage().window().maximize();
        return webDriver;
    }
}
