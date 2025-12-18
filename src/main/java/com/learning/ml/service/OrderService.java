package com.learning.ml.service;

public class OrderService {

    private final OrderValidator validator;
    private final OrderRepository repo;
    private final NotificationService notifier;

    // Constructor injection (Mockito uses this)
    public OrderService(OrderValidator validator,
                        OrderRepository repo,
                        NotificationService notifier) {
        this.validator = validator;
        this.repo = repo;
        this.notifier = notifier;
    }

    public void placeOrder() {

        // STEP 1: Validate order first
        validator.validate();

        // STEP 2: Save order only if validation passes
        repo.save();

        // STEP 3: Notify user after saving
        notifier.notifyUser();
    }
}
