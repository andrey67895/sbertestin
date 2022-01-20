package ru.yandex.market.page;

import helpers.AssertHelpers;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import webdriver.WebDriverActions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static io.qameta.allure.Allure.step;
import static org.awaitility.Awaitility.with;

public class MarketPage extends BasePage {
    private static final String catalogPopupButton = ".//button[@id='catalogPopupButton']";
    private static final String inputFromCost = ".//input[@id='glpricefrom']";
    private static final String inputToCost = ".//input[@id='glpriceto']";
    private static final String title = "Яндекс.Маркет — покупки с быстрой доставкой";
    private static final String costTitle = "Цена, ₽";
    private static final String producerTitle = "Производитель";
    private static final String snippetName = "//h3[@data-zone-name='title']/a";
    private static final String searchBar = "//input[@id='header-search']";
    private static final String searchButton = "//button[. = 'Найти']";
    private static final String h1 = "//h1";

    private MarketPage(WebDriver driver) {
        super(driver);
    }

    public static MarketPage getPage(WebDriver driver) {
        return new MarketPage(driver);
    }

    @Step("Кликнуть на меню 'Категория'")
    public void clickOnTheLinkInTheMenu() {
        WebDriverActions.switchToWindowTab(driver, 1);
        AssertHelpers.assertEquals(driver.getTitle(), title);
        WebDriverActions.clickOnTheLink(driver, catalogPopupButton);
    }

    @Step("Выбрать раздел {text}")
    public void choosingAProductCategory(String text) {

        WebDriverActions.clickOnTheLink(driver, String.format("//span[. = '%s']", text));
        By by = By.xpath(h1);
        WebDriverActions.waitVisibleElement(driver, by);
        AssertHelpers.assertEquals(driver.findElement(by).getText(), text);
    }

    @Step("Выбрать раздел {text}")
    public void choosingAProductSubcategory(String text) {
        WebDriverActions.clickOnTheLink(driver, String.format("//a[. = '%s']", text));
        with().pollDelay(100, TimeUnit.MILLISECONDS).await().atMost
                (10, TimeUnit.SECONDS).untilAsserted(() ->
                AssertHelpers.assertEquals(driver.findElement(By.xpath(h1)).getText().split(" ")[0], text));
        AssertHelpers.assertEquals(driver.findElement(By.xpath(h1)).getText().split(" ")[0], text);
    }

    @Step("Задать параметр поиска от {fromCost} рублей. до {toCost} рублей.")
    public void sendTheCostOfTheProduct(String fromCost, String toCost) {
        AssertHelpers.assertEquals(driver.findElement(By.xpath(String.format("//legend[. = '%s']", costTitle))).getText(), costTitle);
        if (fromCost != null && toCost != null) {
            WebDriverActions.findAndSendText(driver, inputFromCost, fromCost);
            WebDriverActions.findAndSendText(driver, inputToCost, toCost);
        } else if (fromCost == null) {
            WebDriverActions.findAndSendText(driver, inputToCost, toCost);
        } else {
            WebDriverActions.findAndSendText(driver, inputFromCost, fromCost);
        }
    }

    @Step("Выбрать производителя {producer}.")
    public void chooseAProducer(String producer) {
        AssertHelpers.assertEquals(driver.findElement(By.xpath(String.format("//legend[. = '%s']", producerTitle))).getText(), producerTitle);
        WebDriverActions.waitVisibleElement(driver, By.xpath(String.format(".//span[. = '%s']", producer)));
        WebDriverActions.clickOnTheLink(driver, String.format(".//span[. = '%s']", producer));
    }

    @Step("Ожидаем активации всех фильтров")
    public void waitingActivationFilters(String producer) {
        with().pollDelay(100, TimeUnit.MILLISECONDS).await().atMost
                (10, TimeUnit.SECONDS).untilAsserted(() ->
                AssertHelpers.assertEquals(driver.findElement(By.xpath(h1)).getText().split(" ")[1], producer));

    }

    public String checkSizeOfProductAndGetFirstName() {
        List<String> webElementTitleList = new ArrayList<>();
        step("Проверить, что элементов на странице 12.", () -> {
            String cookieNamed = "yandexmarket";
            int valueCatalogLimit = 12;
            WebDriverActions.deleteAndSetCookie(driver, cookieNamed, String.valueOf(valueCatalogLimit));
            WebDriverActions.refresh(driver);
            driver.findElements(By.xpath(snippetName))
                    .forEach(element -> webElementTitleList.add(element.getAttribute("title")));
            AssertHelpers.assertTrue(String.format("Элементов на странице %s", valueCatalogLimit), webElementTitleList.size() == valueCatalogLimit);
        });
        AtomicReference<String> firstElement = new AtomicReference<>();
        step(" Запомнить первый элемент в списке.", () ->
                firstElement.set(webElementTitleList.get(0)));
        return firstElement.get();
    }

    @Step("В поисковую строку ввести запомненное значение.")
    public void searchProduct(String nameProduct) {
        WebDriverActions.findAndSendText(driver, searchBar, nameProduct);
        WebDriverActions.clickOnTheLink(driver, searchButton);
    }

    @Step("Найти и проверить, что наименование товара соответствует запомненному значению.")
    public void checkFirstProduct(String nameProduct) {
        with().pollDelay(100, TimeUnit.MILLISECONDS).await().atMost
                (10, TimeUnit.SECONDS).untilAsserted(() ->
                AssertHelpers.assertEquals(driver.findElement(By.xpath(snippetName))
                        .getAttribute("title"), nameProduct));
    }
}
