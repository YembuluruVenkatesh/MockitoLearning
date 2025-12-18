package com.learning.ml.service;

import com.learning.ml.external.PaymentClient;

public class OrderServiceMockConstruction {
    public String placeOrder(int amount) {
        PaymentClient client = new PaymentClient(); // ❌ hard dependency
        return client.pay(amount);
    }
}
