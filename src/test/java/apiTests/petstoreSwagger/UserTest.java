package apiTests.petstoreSwagger;

import entities.petstoreSwagger.json.ResponsePetstoreSwaggerJson;
import entities.petstoreSwagger.json.user.UserFactory;
import entities.petstoreSwagger.json.user.UserJson;
import helpers.AssertHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static java.net.HttpURLConnection.HTTP_OK;

@DisplayName("Сьют для методов User")
public class UserTest extends PetstoreSwaggerBaseTest {
    @Test
    @DisplayName("Тест на обновление данных юзера")
    public void updateUser() {
        UserJson userJson = UserFactory.getUserJson();
        Response response = apiHelper.put(USERNAME_PATH, userJson, getParams("username", userJson.getUsername()));
        step("Проверка ответа", () -> {
            AssertHelper.checkResponse(response, HTTP_OK);
            AssertHelper.checkSchema(response, ResponsePetstoreSwaggerJson.class);
            ResponsePetstoreSwaggerJson responseJson = response.as(ResponsePetstoreSwaggerJson.class);
            AssertHelper.assertEquals(responseJson.getCode(), HTTP_OK);
            AssertHelper.assertEquals(responseJson.getMessage(), String.valueOf(userJson.getId()));
        });
    }
}
