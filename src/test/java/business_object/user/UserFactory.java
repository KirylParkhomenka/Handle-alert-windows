package business_object.user;

import static business_object.user.UserCredentials.SENDER_LOGIN;
import static business_object.user.UserCredentials.SENDER_PASSWORD;

public class UserFactory {

    public static User createDefaultUser() {
        User user = new User();
        user.setLogin(SENDER_LOGIN);
        user.setPassword(SENDER_PASSWORD);
        return user;
    }
}