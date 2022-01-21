package webdriver;

import helpers.AssertHelper;
import io.qameta.allure.Step;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.with;

@Log
public abstract class WebDriverActions {

    @Step("Переход на страницу {subUrl}")
    public static void open(WebDriver driver, String subUrl) {
        log.info(String.format("Переход на страницу %s", subUrl));
        driver.get(subUrl);
        AssertHelper.assertEquals(driver.getCurrentUrl(), subUrl);
    }

    @Step("Найти и кликнуть на селектор {element}")
    public static void clickOnTheLink(WebElement element) {
        log.info(String.format("Найти элемент на странице и кликнуть на него %s", element));
        element.click();
    }

    @Step("Найти и кликнуть на селектор {by}")
    public static void clickOnTheLink(WebDriver driver, By by) {
        log.info(String.format("Найти элемент на странице и кликнуть на него %s", by));
        driver.findElement(by).click();
    }


    @Step("Найти селектор {element} и написать текст {text} ")
    public static void findAndSendText(WebElement element, String text) {
        log.info(String.format("Найти элемент на странице и вписать в него текст %s, %s", element, text));
        element.sendKeys(text);
    }

    @Step("Найти селектор {by} и получить текст")
    public static String findAndGetText(WebDriver driver, By by) {
        log.info(String.format("Найти элемент на странице и вписать в него текст %s", by));
        return driver.findElement(by).getText();
    }

    @Step("Ждем элемент {element} на странице")
    public static void waitVisibleElement(WebElement element) {
        log.info(String.format("Ждем элемент {%s} на странице", element));
        with().pollDelay(100, TimeUnit.MILLISECONDS).await().atMost(10, TimeUnit.SECONDS).until(element::isDisplayed);
    }

    @Step("Ждем элемент {by} на странице")
    public static void waitVisibleElement(WebDriver driver, By by) {
        log.info(String.format("Ждем элемент {%s} на странице", by));
        with().pollDelay(100, TimeUnit.MILLISECONDS).await().atMost(10, TimeUnit.SECONDS).until(driver.findElement(by)::isDisplayed);
    }

    @Step("Удалить и добавить Cookie:{cookieNamed} c Value:{value}")
    public static void deleteAndSetCookie(WebDriver driver, String cookieNamed, String value) {
        log.info(String.format("Удалить  Cookie c Named: %s", cookieNamed));
        driver.manage().deleteCookieNamed(cookieNamed);
        log.info(String.format("Добавить Cookie c Named: %s и Value: %s", cookieNamed, value));
        driver.manage().addCookie(new Cookie(cookieNamed, value));
    }

    @Step("Обновить текущую страницу")
    public static void refresh(WebDriver driver) {
        log.info(String.format("Обновновлена текущая страницы: %s", driver.getCurrentUrl()));
        driver.navigate().refresh();
    }

    @Step("Переключиться на другую вкладку")
    public static void switchToWindowTab(WebDriver driver, int indexTab) {
        List<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        log.info(String.format("Переключиться на вкладку: %s", (indexTab + 1)));
        driver.switchTo().window(tabs2.get(indexTab));
    }
}
