package models;

import data.ProductType;

public class ProductTestData {

    private final ProductType productType;

    public ProductTestData(ProductType productType) {
        this.productType = productType;
    }

    public ProductType getProductType() {
        return productType;
    }
}