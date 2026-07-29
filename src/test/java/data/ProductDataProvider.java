package data;

import models.ProductTestData;
import org.testng.annotations.DataProvider;

public class ProductDataProvider {

    @DataProvider(name = "products")
    public Object[][] products() {

        return new Object[][]{
                {
                        new ProductTestData(ProductType.BACKPACK)
                },
                {
                        new ProductTestData(ProductType.BIKE_LIGHT)
                },
                {
                        new ProductTestData(ProductType.BOLT_TSHIRT)
                }
        };
    }
}
