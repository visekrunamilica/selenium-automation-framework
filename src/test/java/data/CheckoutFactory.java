package data;

import config.CheckoutDataReader;
import models.CheckoutInfo;

public class CheckoutFactory {
    public static CheckoutInfo getValidCheckoutInfo() {
        return new CheckoutInfo(CheckoutDataReader.get("standard.firstname"),
                CheckoutDataReader.get("standard.lastname"),
                CheckoutDataReader.get("standard.postalcode"));
    }
}
