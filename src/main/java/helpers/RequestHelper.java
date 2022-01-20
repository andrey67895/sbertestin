package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RequestHelper {
    private static final String APPLICATION_JSON = "application/json";

    @Step("Send post request")
    public static Response put(String path, Object jsonObject, Map<String, String> pathParams) {
        return getRequestSpecification().body(jsonObject).pathParams(pathParams).put(path);
    }

    @Step("Send post request")
    public static Response post(String path, Object jsonObject) {
        return getRequestSpecification().body(jsonObject).post(path);
    }

    @Step("Get request")
    public static Response get(String path) {
        return getRequestSpecification().get(path);
    }

    @Step("Get request")
    public static Response get(String path, Map<String, String> pathParams, Map<String, String> queryParams) {
        return getRequestSpecification().pathParams(pathParams)
                .queryParams(queryParams).get(path);
    }

    private static RequestSpecification getRequestSpecification() {
        return given().log().all(true)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON);
    }
}
