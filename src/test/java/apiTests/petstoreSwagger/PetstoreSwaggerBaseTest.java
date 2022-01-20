package apiTests.petstoreSwagger;

import apiTests.BaseApiTest;
import enums.Services;
import org.junit.jupiter.api.BeforeAll;

public class PetstoreSwaggerBaseTest extends BaseApiTest {
    protected final static String
    API_V2 = "/v2/user/",
    CREATE_WITH_LIST = API_V2 + "createWithList",
    USERNAME_PATH = API_V2 + "{username}";

    @BeforeAll
    public static void setBaseURI() {
        BaseApiTest.setBaseURI(Services.PETSTORE_SWAGGER);
    }
}
