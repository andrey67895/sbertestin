package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;

@Log
public class AssertHelper {

    @Step("Проверка ожидаемого и фактического значения")
    public static void assertEquals(Object expected, Object actual) {
        log.info(String.format("expected: %s and actual: %s", expected.toString(), actual.toString()));
        Assertions.assertEquals(expected, actual);
    }

    @Step("{msg}")
    public static void assertTrue(String msg, boolean condition) {
        log.info(msg);
        Assertions.assertTrue(condition);
    }

    @Step("Проверка кода ответа")
    public static void checkResponse(Response response, int expCode) {
        log.info("Проверка статуса ответа");
        response.then().assertThat().statusCode(expCode);
    }
}
