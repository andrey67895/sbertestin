package entities.petstoreSwagger.json.user;

import helpers.RandomHelper;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {

    public static UserJson getUserJson() {
        String firstName = RandomHelper.getRandomFirstName();
        String lastName = RandomHelper.getRandomLastName();
        return UserJson.builder()
                .id(RandomHelper.randInt(1 , 100))
                .username(firstName + " " + lastName)
                .firstName(firstName)
                .lastName(lastName)
                .email(RandomHelper.nextEmail())
                .password(RandomHelper.nextString(10))
                .phone(RandomHelper.nextPhone())
                .userStatus(RandomHelper.randInt(1, 100))
                .build();
    }

    public static List<UserJson> getListUserJson(int maxUserCreate) {
        List<UserJson> createWithListJsons = new ArrayList<>();
        for (int i = 0; i < maxUserCreate; i++) {
            createWithListJsons.add(getUserJson());
        }
        return createWithListJsons;
    }
}
