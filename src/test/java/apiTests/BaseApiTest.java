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

    protected BaseApiTest(Services services) {
        this.apiHelper = new ApiHelper(services);
        validateServiceIsUp();
    }

    @Step("Validate service is UP")
    private void validateServiceIsUp() {
        Response response = apiHelper.get("/");
        AssertHelper.checkResponse(response, HTTP_OK);
    }

    protected Map<String, String> getParams(String key, String value) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put(key, value);
        return mapParams;
    }
}
