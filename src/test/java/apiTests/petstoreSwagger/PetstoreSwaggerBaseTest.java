package apiTests.petstoreSwagger;

import apiTests.BaseApiTest;
import enums.Services;

public abstract class PetstoreSwaggerBaseTest extends BaseApiTest {
    protected final static String
            API_V2 = "/v2/user/",
            CREATE_WITH_LIST = API_V2 + "createWithList",
            USERNAME_PATH = API_V2 + "{username}";
    /**
     * Инициируем базовый класс
     */
    public PetstoreSwaggerBaseTest() {
        super(Services.PETSTORE_SWAGGER);
    }
}
