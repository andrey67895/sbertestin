package helpers.RestHelpers;

import enums.Services;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.java.Log;

import java.util.Map;

@Log
public class ApiHelper extends RequestHelper {
    public ApiHelper(Services services) {
        super(services);
    }

    private static void logResponse(Response response) {
        log.info("Response code: " + response.getStatusCode());
        log.info("Response message: " + response.asString());
    }

    @Step("Отправить Get запрос")
    public Response get(String path) {
        Response response = getRequest(path);
        logResponse(response);
        return response;
    }

    @Step("Отправить Get запрос")
    public Response get(String path, Map<String, ?> pathParams, Map<String, ?> queryParams) {
        Response response = getRequest(path, pathParams, queryParams);
        logResponse(response);
        return response;
    }

    @Step("Отправить Post запрос: {path}")
    public Response post(String path, Object jsonObject) {
        Response response = postRequest(path, jsonObject);
        logResponse(response);
        return response;
    }

    @Step("Отправить Put запрос: {path}")
    public Response put(String path, Object jsonObject, Map<String, ?> pathParams) {
        Response response = putRequest(path, jsonObject, pathParams);
        logResponse(response);
        return response;
    }
}
