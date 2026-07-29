package models;

import constants.ErrorMessage;

public class CheckoutTestData {
    private final CheckoutInfo info;
    private final ErrorMessage errorMessage;

    public CheckoutTestData(CheckoutInfo info, ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
        this.info = info;
    }

    public CheckoutInfo getCheckoutInfo() {
        return info;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
