package pages.ruYandexMarket;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import static helpers.AssertHelper.assertEquals;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.with;

public class MarketSearchPage extends BasePage {
    @FindBy(xpath = "//h3[@data-zone-name='title']/a")
    WebElement snippetName;

    public MarketSearchPage(WebDriver driver) {
        super(driver);
    }

    @Step("Найти и проверить, что наименование товара соответствует запомненному значению.")
    public void checkFirstProduct(String nameProduct) {
        with().pollDelay(100, MILLISECONDS).await().atMost
                (10, SECONDS).untilAsserted(() ->
                assertEquals(snippetName.getAttribute("title"), nameProduct));
    }
}
