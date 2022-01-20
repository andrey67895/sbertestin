package uiTests.ruYandexMarket.page;

import org.openqa.selenium.WebDriver;
import webdriver.WebDriverActions;

public class BasePage {
    protected WebDriver driver;

    protected BasePage(WebDriver webDriver) {
        driver = webDriver;
    }

    public void open(String url) {
        WebDriverActions.open(driver, url);
    }

    public void quit() {
        driver.quit();
    }
}
