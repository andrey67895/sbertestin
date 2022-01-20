package helpers;

import io.qameta.allure.Step;
import org.junit.Assert;

public class AssertHelpers {

    @Step("Assert equals")
    public static void assertEquals(Object expected, Object actual) {
        Assert.assertEquals(String.format(" %s, is not equal to %s", expected.toString(), actual.toString()), expected, actual);
    }

    @Step("{msg}")
    public static void assertTrue(String msg, boolean condition) {
        Assert.assertTrue(msg, condition);
    }
}
