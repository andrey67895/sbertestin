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
        AssertHelper.checkResponse(response, HTTP_OK);
        IpapiResponseJson responseJson = response.as(IpapiResponseJson.class);
        AssertHelper.assertEquals(responseJson.getCountryCode(), Country.US);
        AssertHelper.assertEquals(responseJson.getCountryName(), Country.US.getCountry());
        log.info(String.format("This IP belongs to %s, %s",
                responseJson.getCountryName(),
                responseJson.getRegionName()));
        System.out.printf("This IP belongs to %s, %s",
                responseJson.getCountryName(),
                responseJson.getRegionName()
        );
    }
}
