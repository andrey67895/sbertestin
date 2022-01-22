package uiTests.ruYandexMarket.tests;

import enums.Services;
import org.junit.jupiter.api.BeforeEach;
import pages.ruYandexMarket.MainPage;
import uiTests.BaseUITest;
import webdriver.WebDriverUI;

public class YandexMarketBaseTest extends BaseUITest {
    protected MainPage mainPages;

    public YandexMarketBaseTest() {
        super(Services.YANDEX);
    }

    @BeforeEach
    protected void initPage() {
        driver = WebDriverUI.getSessionWindowsMaximize(optionDriver);
        mainPages = new MainPage(driver);
    }
}
