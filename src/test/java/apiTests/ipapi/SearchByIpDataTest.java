package apiTests.ipapi;

import entities.ipapi.json.IpapiResponseJson;
import enums.Country;
import helpers.AssertHelper;
import io.restassured.response.Response;
import lombok.extern.java.Log;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static java.net.HttpURLConnection.HTTP_OK;

@Log
@DisplayName("Проверка принадлежности IP-адресов стране USA")
public class SearchByIpDataTest extends IpapiBaseTest {

    @ParameterizedTest
    @CsvSource(value = {
            "198.77.76.0",
            "198.84.16.0",
            "198.80.51.255",
            "198.84.127.255"
    })

    @DisplayName("Проверка принадлежности IP-адресов стране USA")
    public void searchByIpData(String ipData) {
        Response response = apiHelper.get(YOUR_IPV4, getParams("your_ipv4", ipData), getParams("access_key", ACCESS_KEY));
        AssertHelper.checkResponseCodeAndSchema(response, HTTP_OK, IpapiResponseJson.class);
        IpapiResponseJson responseJson = response.as(IpapiResponseJson.class);
        responseJson.checkCountryCodeAndName(Country.US);
        log.info(responseJson.toString());
        System.out.println(responseJson);
    }
}
