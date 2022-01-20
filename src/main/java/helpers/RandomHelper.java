package helpers;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomHelper extends RandomUtils {
    private static final List<String> lastNames = Arrays.asList("Косоруков", "Эскин", "Немцов", "Крыжов", "Кондаков", "Мячин", "Бессонов", "Щедров", "Буслаев", "Ярошенко", "Кутузов", "Хлопонин", "Паринов", "Кабалкин", "Гуськов", "Алябьев", "Москвин", "Филиппов", "Карпюк", "Болокан", "Кардапольцев", "Ягутян", "Есаулов", "Мятлев", "Кандаков", "Шевцов", "Каде", "Веселков", "Воронов", "Кубланов", "Старицкий", "Митрушичев", "Барышев", "Цехановецкий", "Явленский", "Кутепов", "Манторов", "Прибылов", "Алленов", "Саянов", "Лобачёв", "Хуртин", "Топорков", "Храмов", "Кручинин", "Комолов", "Богданов", "Болтунов", "Норин", "Тимирязев", "Водопьянов", "Степанишин", "Изотов", "Шмаков", "Сомов", "Федоров", "Добролюбов", "Мандрыко", "Люба", "Шатохин", "Ящук", "Квартиров", "Мадулин", "Лясин", "Саитов", "Поздов", "Хабенский", "Ян", "Иканов", "Ярополов", "Титов", "Розанов", "Ясенев", "Ямпольский", "Ешевский", "Мощенский", "Круминьш", "Ожогин", "Митрохов", "Прохоров", "Бальсунов", "Яимов", "Перфильев", "Цирюльников", "Опринчук", "Кадцын", "Мусорин", "Ревягин", "Шелагин", "Печкин", "Владимиров", "Никишов", "Шкуратов", "Богачёв", "Голодяев", "Саянкин", "Петрунин", "Нужнов", "Ягренев");
    private static final List<String> firstNames = Arrays.asList("Михей", "Артур", "Прокл", "Федот", "Мефодий", "Эрнест", "Онуфрий", "Изяслав", "Роман", "Измаил", "Клавдий", "Георгий", "Богдан", "Константин", "Дмитрий", "Владилен", "Матвей", "Феофан", "Михаил", "Григорий", "Борислав", "Порфирий", "Артём", "Адам", "Андрон", "Виталий", "Марк", "Агап", "Макар", "Демьян", "Ростислав", "Евстигней", "Потап", "Евгений", "Ярослав", "Артем", "Никанор", "Мартын", "Тимур", "Чеслав", "Иннокентий", "Данила", "Лев", "Борис", "Станислав", "Аскольд", "Анатолий", "Назар", "Серафим", "Феликс", "Капитон", "Александр", "Викентий", "Фока", "Поликарп", "Пимен", "Вацлав", "Игнатий", "Иосиф", "Федор", "Петр", "Егор", "Денис", "Якуб", "Осип", "Прокофий", "Трофим", "Зиновий", "Фома", "Ефрем", "Кир", "Никон", "Варфоломей", "Лукьян", "Адриан", "Авдей", "Мирон", "Эдуард", "Елизар");

    public static String nextPhone() {
        return "+7999" + nextLong(1000000, 2999999);
    }

    public static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static String getRandomLastName() {
        return lastNames.get(randInt(0, lastNames.size() - 1));
    }

    public static String getRandomFirstName() {
        return firstNames.get(randInt(0, firstNames.size() - 1));
    }

    public static String nextEmail() {
        return String.format("at%s@sbertestin.ru", getTimeStemp());
    }

    public static String nextString(int bound) {
        return RandomStringUtils.randomAlphabetic(bound);
    }

    public static String getTimeStemp() {
        return new SimpleDateFormat("yyyyMMddHHmmssSS")
                .format(new Timestamp(System.currentTimeMillis()));
    }
}
