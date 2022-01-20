package apiTests.ipapi;

import apiTests.BaseApiTest;
import enums.Services;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class IpapiBaseTest extends BaseApiTest {
    protected final static String YOUR_IPV4 = "/{your_ipv4}";
    protected static String ACCESS_KEY;

    @BeforeAll
    @SneakyThrows
    public static void setBaseURI() {
        BaseApiTest.setBaseURI(Services.IPAPI);
        File file = new File("src/test/resources/test.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        ACCESS_KEY = properties.getProperty("access_key");
    }
}
