package pages.ruYandexMarket;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.ArrayList;
import java.util.List;

import static helpers.AssertHelper.assertEquals;
import static helpers.AssertHelper.assertTrue;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.with;
import static webdriver.WebDriverActions.*;

public class MarketSubCategoryPage extends BasePage {
    private static final String costTitle = "Цена, ₽";
    private static final String producerTitle = "Производитель";

    @FindBy(xpath = ".//input[@id='glpricefrom']")
    WebElement inputFromCost;

    @FindBy(xpath = ".//input[@id='glpriceto']")
    WebElement inputToCost;

    @FindBy(xpath = "//h1")
    WebElement h1;

    @FindBy(xpath = "//button[. = 'Найти']")
    WebElement searchButton;

    @FindBy(xpath = "//input[@id='header-search']")
    WebElement searchBar;

    @FindBy(xpath = "//h3[@data-zone-name='title']/a")
    List<WebElement> snippetName;

    public MarketSubCategoryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Задать параметр поиска от {fromCost} рублей. до {toCost} рублей.")
    public void sendTheCostOfTheProduct(String fromCost, String toCost) {
        assertEquals(findAndGetText(driver, getByXpath("//legend[. = '%s']", costTitle)), costTitle);
        findAndSendText(inputFromCost, fromCost);
        findAndSendText(inputToCost, toCost);
    }

    @Step("Выбрать производителя {producer}.")
    public void chooseAProducer(String producer) {
        assertEquals(findAndGetText(driver, getByXpath("//legend[. = '%s']", producerTitle)), producerTitle);
        waitVisibleElement(driver, getByXpath(".//span[. = '%s']", producer));
        clickOnTheLink(driver, getByXpath(".//span[. = '%s']", producer));
    }

    @Step("Ожидаем активации всех фильтров")
    public void waitingActivationFilters(String producer) {
        with().pollDelay(1, SECONDS).await().atMost(15, SECONDS).untilAsserted(() -> assertEquals(h1.getText().split(" ")[1], producer));
    }

    @Step("Проверить, что товаров на странице 12.")
    public List<String> checkSizeOfProduct() {
        List<String> webElementTitleList = new ArrayList<>();
        String cookieNamed = "yandexmarket";
        int valueCatalogLimit = 12;
        deleteAndSetCookie(driver, cookieNamed, String.valueOf(valueCatalogLimit));
        refresh(driver);
        snippetName.forEach(element -> webElementTitleList.add(element.getAttribute("title")));
        assertTrue(String.format("Элементов на странице %s", valueCatalogLimit), webElementTitleList.size() == valueCatalogLimit);
        return webElementTitleList;
    }

    @Step("Запомнить первый элемент в списке.")
    public String getFirstName(List<String> webElementTitleList) {
        return webElementTitleList.get(0);
    }

    @Step("В поисковую строку ввести запомненное значение.")
    public MarketSearchPage searchProduct(String nameProduct) {
        findAndSendText(searchBar, nameProduct);
        clickOnTheLink(searchButton);
        return new MarketSearchPage(driver);
    }
}
