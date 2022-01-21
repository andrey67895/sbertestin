package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.java.Log;
import org.junit.Assert;

@Log
public class AssertHelper {


    @Step("Проверка ожидаемого и фактического значения")
    public static void assertEquals(Object expected, Object actual) {
        Assert.assertEquals(String.format(" %s, is not equal to %s", expected.toString(), actual.toString()), expected, actual);
    }

    @Step("{msg}")
    public static void assertTrue(String msg, boolean condition) {
        Assert.assertTrue(msg, condition);
    }

    @Step("Проверка кода ответа")
    public static void checkResponse(Response response, int expCode) {
        log.info("Проверка статуса ответа");
        response.then().assertThat().statusCode(expCode);
    }
}
