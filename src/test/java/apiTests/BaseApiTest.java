package apiTests;

import enums.Services;
import helpers.AssertHelper;
import helpers.RestHelpers.ApiHelper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.Timeout;

import java.util.HashMap;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

@Timeout(60)
public abstract class BaseApiTest {
    protected ApiHelper apiHelper;

    /**
     * Базовый класс API тестов.
     * Создаем экземпляр АпиХелпера для работы с нужным сервисом.
     * Запускаем првоерку, что сервис работает
     * @param services Запускаем сервис
     */
    protected BaseApiTest(Services services) {
        this.apiHelper = new ApiHelper(services);
        validateServiceIsUp();
    }


    /**
     * Проверка, что сервис работает
     */
    @Step("Validate service is UP")
    private void validateServiceIsUp() {
        Response response = apiHelper.get("/");
        AssertHelper.checkResponseCode(response, HTTP_OK);
    }

    /**
     * Метод для генерации Map чтобы прописывать параметры пути
     * или запроса через соответсвующий метод RestAssured согласно спецификации.
     * @param key - Название параметра согласно спецификации
     * @param value - Передаем параметр, который мы хотим видеть в URL
     * @return возвращаем Карту параметров
     */
    protected Map<String, String> getParams(String key, String value) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put(key, value);
        return mapParams;
    }
}
