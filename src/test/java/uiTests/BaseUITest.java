package uiTests;

import enums.Services;
import lombok.extern.java.Log;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Log
@Timeout(60)
public abstract class BaseUITest{
    protected WebDriver driver;
    protected static final Map<String, String> optionDriver = new HashMap<>();
    protected String domain;


    /**
     * Базовый класс UI тестов.
     * Логируем сервис который запускаем. Прописываем домен.
     * @param services запускаем сервис WebDriver UI
     */
    protected BaseUITest(Services services) {
        log.info(String.format("domain: %s", services.getServices()));
        this.domain = services.getServices();
    }

    /**
     * Перед прогоном всех автотестов логируем и добавляем необходимые параметры для WebDriver
     */
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

    /**
    После каждого теста завершаем работу WebDriver
     */
    @AfterEach
    public void sessionDown() {
        driver.quit();
    }
}
