package pages.ruYandexMarket;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import static helpers.AssertHelper.assertEquals;
import static webdriver.WebDriverActions.clickOnTheLink;

public class MainPage extends BasePage {

    private static final String title = "Яндекс";
    @FindBy(xpath = ".//a[@data-id='market']")
    WebElement marketSelector;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Перейти в Яндекс Маркет.")
    public MarketPage goToMarketPage() {
        assertEquals(driver.getTitle(), title);
        clickOnTheLink(marketSelector);
        return new MarketPage(driver);
    }
}
