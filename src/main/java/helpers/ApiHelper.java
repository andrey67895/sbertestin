package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.java.Log;

import java.util.Map;

@Log
public class ApiHelper {
    @Step("Отправить Get запрос")
    public static Response get(String path) {
        Response response = RequestHelper.get(path);
        logResponse(response);
        return response;
    }

    @Step("Отправить Get запрос")
    public static Response get(String path, Map<String, String> pathParams, Map<String, String> queryParams) {
        Response response = RequestHelper.get(path, pathParams, queryParams);
        logResponse(response);
        return response;
    }

    @Step("Отправить Post запрос: {path}")
    public static Response post(String path, Object jsonObject) {
        Response response = RequestHelper.post(path, jsonObject);
        logResponse(response);
        return response;
    }

    @Step("Отправить Put запрос: {path}")
    public static Response put(String path, Object jsonObject, Map<String, String> pathParams) {
        Response response = RequestHelper.put(path, jsonObject, pathParams);
        logResponse(response);
        return response;
    }

    private static void logResponse(Response response) {
        log.info("Response code: " + response.getStatusCode());
        log.info("Response message: " + response.asString());
    }
}
