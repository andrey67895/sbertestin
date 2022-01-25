package helpers.RestHelpers;

import enums.Services;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * APPLICATION_JSON - указываем тип данных
 * BASE_URL - домен, который мы хотим использовать,
 * не через RestAssured.baseURI потому что в этом случае у нас глобальная переменная и она пробрасывается для всего проекта,
 * а мы хотим пользоваться обращениями к различным доменам, можно переопределять, но это потоконебезопасно.
 *
 * pathParams и queryParams: так указывать правильно согласно спецификации OpenAPI Specification
 *
 */

public abstract class RequestHelper {
    private static final String APPLICATION_JSON = "application/json";
    private final String BASE_URL;
    protected RequestHelper(Services services) {
        this.BASE_URL = services.getServices();
    }

    @Step("Send post request")
    protected Response putRequest(String path, Object jsonObject, Map<String, ?> pathParams) {
        return getRequestSpecification().body(jsonObject).pathParams(pathParams).put(path);
    }

    @Step("Send post request")
    protected Response postRequest(String path, Object jsonObject) {
        return getRequestSpecification().body(jsonObject).post(path);
    }

    @Step("Get request")
    protected Response getRequest(String path) {
        return getRequestSpecification().get(path);
    }

    @Step("Get request")
    protected Response getRequest(String path, Map<String, ?> pathParams, Map<String, ?> queryParams) {
        return getRequestSpecification().pathParams(pathParams)
                .queryParams(queryParams).get(path);
    }

    private RequestSpecification getRequestSpecification() {
        return given().log().all(true).baseUri(BASE_URL)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON);
    }
}
