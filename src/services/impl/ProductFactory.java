package services.impl;

import models.Product;

public class ProductFactory {
    public static Product getProduct(String s) throws Exception {
        return switch (s) {
            case "coke" -> Product.COKE;
            case "pepsi" -> Product.PEPSI;
            case "soda" -> Product.SODA;
            default -> throw new Exception("invalid product type: " + s);
        };
    }
}