package webdriver;

import helpers.AssertHelper;
import io.qameta.allure.Step;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.with;

@Log
public class WebDriverActions {

    @Step("Переход на страницу {subUrl}")
    public static void open(WebDriver driver, String subUrl) {
        log.info(String.format("Переход на страницу %s", subUrl));
        driver.get(subUrl);
        AssertHelper.assertEquals(driver.getCurrentUrl(), subUrl);
    }

    @Step("Найти и кликнуть на селектор {selector}")
    public static void clickOnTheLink(WebDriver driver, String selector) {
        log.info(String.format("Найти элемент на странице и кликнуть на него %s", selector));
        driver.findElement(By.xpath(selector)).click();
    }

    @Step("Найти селектор {selector} и написать текст {text} ")
    public static void findAndSendText(WebDriver driver, String selector, String text) {
        log.info(String.format("Найти элемент на странице и вписать в него текст %s, %s", selector, text));
        driver.findElement(By.xpath(selector)).sendKeys(text);
    }

    @Step("Ждем элемент {by} на странице")
    public static void waitVisibleElement(WebDriver driver, By by) {
        log.info(String.format("Ждем элемент {%s} на странице", by));
        with().pollDelay(100, TimeUnit.MILLISECONDS).await().atMost
                (10, TimeUnit.SECONDS).until(driver.findElement(by)::isDisplayed);
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
