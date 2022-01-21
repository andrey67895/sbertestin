package pages.ruYandexMarket;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import static helpers.AssertHelper.assertEquals;
import static webdriver.WebDriverActions.*;

public class MarketPage extends BasePage {
    private static final String title = "Яндекс.Маркет — покупки с быстрой доставкой";

    @FindBy(xpath = ".//button[@id='catalogPopupButton']")
    WebElement catalogPopupButton;

    @FindBy(xpath = "//h1")
    WebElement h1;

    public MarketPage(WebDriver driver) {
        super(driver);
    }

    @Step("Кликнуть на меню 'Категория'")
    public void clickOnTheLinkInTheMenu() {
        switchToWindowTab(driver, 1);
        assertEquals(driver.getTitle(), title);
        catalogPopupButton.click();
    }

    @Step("Выбрать раздел {text}")
    public MarketCategoryPage goToMarketCategoryPage(String text) {
        clickOnTheLink(driver, getByXpath("//span[. = '%s']", text));
        waitVisibleElement(h1);
        assertEquals(h1.getText(), text);
        return new MarketCategoryPage(driver);
    }
}
