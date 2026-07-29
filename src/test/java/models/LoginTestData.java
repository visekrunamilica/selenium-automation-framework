package models;

import constants.ErrorMessage;

public class LoginTestData {
    private final User user;
    private final ErrorMessage errorMessage;

    public LoginTestData(User user, ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
