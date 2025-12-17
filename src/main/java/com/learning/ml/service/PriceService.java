package com.learning.ml.service;

public class PriceService {

    public int getPrice() {
        return 100;
    }

    public int getDiscountedPrice() {
        // Real logic
        return getPrice() - 10;
    }
}
