package data;

import config.TestDataReader;
import models.User;

public class UserFactory {

    public static User getStandardUser() {
        return new User(TestDataReader.get("standard.username"),
                TestDataReader.get("standard.password"));
    }

    public static User getLockedUser() {
        return new User(TestDataReader.get("locked.username"),
                TestDataReader.get("locked.password"));
    }

    public static User getUserWithWrongPassword() {
        return new User(TestDataReader.get("standard.username"),
                TestDataReader.get("wrong_password"));
    }

    public static User getUserWithMissingUsername() {
        return new User("",
                TestDataReader.get("standard.password"));
    }
}
