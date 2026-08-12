package data;

import constants.ErrorMessage;
import models.LoginTestData;
import org.testng.annotations.DataProvider;

public class LoginDataProvider {
    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {
                        new LoginTestData(
                                UserFactory.getUserWithWrongPassword(),
                                ErrorMessage.WRONG_CREDENTIALS
                        )
                },
                {
                        new LoginTestData(
                                UserFactory.getLockedUser(),
                                ErrorMessage.LOCKED_USER
                        )
                },
                {
                        new LoginTestData(
                                UserFactory.getUserWithMissingUsername(),
                                ErrorMessage.NO_USERNAME
                        )
                }
        };
    }
}
