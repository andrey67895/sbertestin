package uiTests.ruYandexMarket.page;

import helpers.AssertHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import webdriver.WebDriverActions;

public class MainPage extends BasePage {

    private static final String marketSelector = ".//a[@data-id='market']";
    private static final String title = "Яндекс";

    private MainPage(WebDriver driver) {
        super(driver);
    }

    public static MainPage getPage(WebDriver driver) {
        return new MainPage(driver);
    }

    @Step("Перейти в Яндекс Маркет.")
    public void clickOnTheLinkInTheMenu() {
        AssertHelper.assertEquals(driver.getTitle(), title);
        WebDriverActions.clickOnTheLink(driver, marketSelector);
    }
}
