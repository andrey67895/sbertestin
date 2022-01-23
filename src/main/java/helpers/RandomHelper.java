package helpers;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class RandomHelper extends RandomUtils {
   private static final Faker faker = new Faker(new Locale("ru"));

   public static String nextPhone() {
        return faker.phoneNumber().phoneNumber();
    }

    public static int randInt(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String nextEmail() {
        return faker.internet().emailAddress(String.format("at%s", getTimeStemp()));
    }

    public static String nextString(int bound) {
        return RandomStringUtils.randomAlphabetic(bound);
    }

    private static String getTimeStemp() {
        return new SimpleDateFormat("yyyyMMddHHmmssSS")
                .format(new Timestamp(System.currentTimeMillis()));
    }
}
