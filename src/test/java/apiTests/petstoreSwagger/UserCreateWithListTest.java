package apiTests.petstoreSwagger;

import entities.petstoreSwagger.json.ResponsePetstoreSwaggerJson;
import entities.petstoreSwagger.json.user.UserFactory;
import entities.petstoreSwagger.json.user.UserJson;
import helpers.AssertHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static java.net.HttpURLConnection.HTTP_OK;

@DisplayName("Тест на создания списка пользователей с заданным входным массивом")
public class UserCreateWithListTest extends PetstoreSwaggerBaseTest {
    @ParameterizedTest
    @CsvSource({"0", "1", "5", "100"})
    @DisplayName("Регистрация списка юзеров")
    public void userCreateWithList(int maxUserCreate) {
        List<UserJson> createWithListJsons = UserFactory.getListUserJson(maxUserCreate);
        Response response = apiHelper.post(CREATE_WITH_LIST, createWithListJsons);
        step("Проверка ответа", () -> {
            AssertHelper.checkResponse(response, HTTP_OK);
            AssertHelper.checkSchema(response, ResponsePetstoreSwaggerJson.class);
            ResponsePetstoreSwaggerJson responseJson = response.as(ResponsePetstoreSwaggerJson.class);
            AssertHelper.assertEquals(responseJson.getCode(), HTTP_OK);
            AssertHelper.assertEquals(responseJson.getMessage(), "ok");
        });
    }
}
