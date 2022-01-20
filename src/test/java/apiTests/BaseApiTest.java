package apiTests;

import enums.Services;
import helpers.ApiHelper;
import helpers.AssertHelper;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

public class BaseApiTest {
    public static void setBaseURI(Services services) {
        RestAssured.baseURI = getServiceApi(services);
        validateServiceIsUp();
    }

    @Step("Validate service is UP")
    public static void validateServiceIsUp() {
        Response response = ApiHelper.get("/");
        AssertHelper.checkResponse(response, HTTP_OK);
    }

    protected Map<String, String> getParams(String key, String value) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put(key, value);
        return mapParams;
    }

    protected static String getServiceApi(Services services) {
        return services.getServices();
    }
}
