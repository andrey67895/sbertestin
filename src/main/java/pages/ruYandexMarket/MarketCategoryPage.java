package pages.ruYandexMarket;

import helpers.AssertHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import webdriver.WebDriverActions;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.with;

public class MarketCategoryPage extends BasePage {
    @FindBy(xpath = "//h1")
    WebElement h1;

    public MarketCategoryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Выбрать раздел {text}")
    public MarketSubCategoryPage goToMarketSubCategoryPage(String text) {
        WebDriverActions.clickOnTheLink(driver, getByXpath("//a[. = '%s']", text));
        with().pollDelay(100, TimeUnit.MILLISECONDS).await().atMost
                (10, TimeUnit.SECONDS).untilAsserted(() ->
                AssertHelper.assertEquals(h1.getText().split(" ")[0], text));
        AssertHelper.assertEquals((h1).getText().split(" ")[0], text);
        return new MarketSubCategoryPage(driver);
    }
}
