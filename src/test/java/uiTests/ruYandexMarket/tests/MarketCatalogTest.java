package uiTests.ruYandexMarket.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.ruYandexMarket.MarketPage;
import pages.ruYandexMarket.MarketSubCategoryPage;
import uiTests.BaseUITest;

@DisplayName("Проверка каталога Я.Маркет")
public class MarketCatalogTest extends BaseUITest {

    @ParameterizedTest
    @CsvSource({"Компьютеры, Ноутбуки, null, 30000, Lenovo", "Компьютеры, Планшеты, 20000, 95000, Xiaomi"})
    @DisplayName("Проверка каталога и поиска товаров")
    public void marketCatalogTest(String category, String subcategory, String costFrom, String costTo, String producer) {
        mainPages.open(domain);
        MarketPage marketPage = mainPages.goToMarketPage();
        marketPage.clickOnTheLinkInTheMenu();
        MarketSubCategoryPage subCategoryPage = marketPage.goToMarketCategoryPage(category).goToMarketSubCategoryPage(subcategory);
        subCategoryPage.sendTheCostOfTheProduct(costFrom, costTo);
        subCategoryPage.chooseAProducer(producer);
        subCategoryPage.waitingActivationFilters(producer);
        String productName = subCategoryPage.getFirstName(subCategoryPage.checkSizeOfProduct());
        subCategoryPage.searchProduct(productName).checkFirstProduct(productName);
    }
}
