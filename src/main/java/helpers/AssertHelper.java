package helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.victools.jsonschema.generator.*;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;

@Log
public class AssertHelper {
    @Step("Проверка ожидаемого и фактического значения")
    public static void assertEquals(Object expected, Object actual) {
        log.info(String.format("expected: %s and actual: %s", expected.toString(), actual.toString()));
        Assertions.assertEquals(expected, actual);
    }

    @Step("{msg}")
    public static void assertTrue(String msg, boolean condition) {
        log.info(msg);
        Assertions.assertTrue(condition);
    }

    @Step("Проверка кода ответа")
    public static void checkResponse(Response response, int expCode) {
        log.info("Проверка статуса ответа");
        response.then().assertThat().statusCode(expCode);
    }

    @Step("Проверка ответа на соответствие JSON схемы")
    public static void checkSchema(Response response, Class<?> clazz) {
        log.info("Проверка JSON схемы ответа");
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(getSchema(clazz)));
    }

    private static String getSchema(Class<?> clazz) {
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2019_09, OptionPreset.PLAIN_JSON);
        SchemaGeneratorConfig config = configBuilder.build();
        SchemaGenerator generator = new SchemaGenerator(config);
        JsonNode jsonSchema = generator.generateSchema(clazz);
        return jsonSchema.toString();
    }
}
