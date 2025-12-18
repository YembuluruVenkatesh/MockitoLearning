package com.learning.ml;

import com.learning.ml.external.PaymentClient;
import com.learning.ml.service.OrderServiceMockConstruction;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
// mvn -Dtest=ConstructorMockTest test
public class ConstructorMockTest {

    @Test
    void should_mock_object_created_with_new() {

        // Intercept ALL new PaymentClient() calls
        try (MockedConstruction<PaymentClient> mocked =
                     mockConstruction(PaymentClient.class,
                             (mock, context) -> {

                                 // Stub method
                                 when(mock.pay(100)).thenReturn("MOCK PAYMENT");
                             })) {

            OrderServiceMockConstruction service = new OrderServiceMockConstruction();

            String result = service.placeOrder(100);

            assertEquals("MOCK PAYMENT", result);

            // Verify constructor was called exactly once
            assertEquals(1, mocked.constructed().size());

            // Verify method call
            verify(mocked.constructed().get(0)).pay(100);
        }
    }
}
