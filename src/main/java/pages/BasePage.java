package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import webdriver.WebDriverActions;

public abstract class BasePage {
    protected WebDriver driver;

    /**
     * @param driver Инициируем driver и используем PageFactory для работы со странцией
     */
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * @param url Открываем страницу сервиса в WebDriver
     */
    public void open(String url) {
        WebDriverActions.open(driver, url);
    }

    /**
     * Метод получения селекотора
     * @param selectors Метод получения селекотора
     * @param text с указанным текстом
     * @return возвращаем готовый селект
     */
    protected By getByXpath(String selectors, String text) {
        return By.xpath(String.format(selectors, text));
    }
}
