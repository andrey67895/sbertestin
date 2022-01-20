package ru.yandex.market.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Проверка каталога Я.Маркет")
public class MarketCatalogTest extends BaseTest {

    @BeforeEach
    protected void initPage() {
        pageFactory();
    }

    @ParameterizedTest
    @CsvSource({
            "Компьютеры, Ноутбуки, null, 30000, Lenovo",
            "Компьютеры, Планшеты, 20000, 95000, Xiaomi"
    })
    @DisplayName("Проверка каталога и поиска товаров")
    public void chromeSession(String category, String subcategory, String costFrom, String costTo, String producer) {
        mainPages.open(domain);
        mainPages.clickOnTheLinkInTheMenu();
        marketPage.clickOnTheLinkInTheMenu();
        marketPage.choosingAProductCategory(category);
        marketPage.choosingAProductSubcategory(subcategory);
        marketPage.sendTheCostOfTheProduct(costFrom, costTo);
        marketPage.chooseAProducer(producer);
        marketPage.waitingActivationFilters(producer);
        String productName = marketPage.checkSizeOfProductAndGetFirstName();
        marketPage.searchProduct(productName);
        marketPage.checkFirstProduct(productName);
    }

    @AfterEach
    public void sessionDown() {
        mainPages.quit();
    }
}
