package apiTests.ipapi;

import apiTests.BaseApiTest;
import enums.Services;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public abstract class IpapiBaseTest extends BaseApiTest {
    protected final static String YOUR_IPV4 = "/{your_ipv4}";
    protected static String ACCESS_KEY;

    /**
     * Инициируем базовый класс
     */
    public IpapiBaseTest() {
        super(Services.IPAPI);
    }

    /**
     * Параметры для запуска теста и получения переменных
     */
    @BeforeAll
    @SneakyThrows
    public static void setProperties() {
        File file = new File("src/test/resources/test.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        ACCESS_KEY = properties.getProperty("access_key");
    }
}
