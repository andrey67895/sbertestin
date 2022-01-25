package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import webdriver.WebDriverActions;

public abstract class BasePage {
    protected WebDriver driver;

    /**
     * Инициируем driver и используем PageFactory для работы со странцией
     */
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Открываем страницу сервиса в WebDriver
     */
    public void open(String url) {
        WebDriverActions.open(driver, url);
    }

    /**
     * Метод получения селекотора
     */
    protected By getByXpath(String selectors, String text) {
        return By.xpath(String.format(selectors, text));
    }
}
