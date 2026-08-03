package data;

import constants.ErrorMessage;
import models.CheckoutInfo;
import models.CheckoutTestData;
import org.testng.annotations.DataProvider;

public class CheckoutDataProvider {
    @DataProvider(name = "invalidCheckoutData")
    public Object[][] invalidCheckoutData() {
        return new Object[][]{
                {
                        new CheckoutTestData(new CheckoutInfo(
                                "", "Peric", "11000"
                        ), ErrorMessage.FIRST_NAME_REQUIRED)
                },
                {
                        new CheckoutTestData(new CheckoutInfo(
                                "Pera", "", "11000"
                        ), ErrorMessage.LAST_NAME_REQUIRED)
                },
                {
                        new CheckoutTestData(new CheckoutInfo(
                                "Pera", "Peric", ""
                        ), ErrorMessage.POSTAL_CODE_REQUIRED)
                }

        };
    }
}
