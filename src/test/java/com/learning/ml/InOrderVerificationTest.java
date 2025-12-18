package com.learning.ml;

import com.learning.ml.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
// mvn -Dtest=InOrderVerificationTest test
@ExtendWith(MockitoExtension.class)
public class InOrderVerificationTest {

    // Mock dependencies (fake implementations)
    @Mock
    OrderValidator validator;

    @Mock
    OrderRepository repo;

    @Mock
    NotificationService notifier;

    // Inject mocks into OrderService
    @InjectMocks
    OrderService service;

    @Test
    void test_method_call_order() {

        // ============================
        // ACT (execute business logic)
        // ============================
        service.placeOrder();

        // ============================
        // VERIFY ORDER OF CALLS
        // ============================

        /*
         * InOrder object remembers the sequence of method calls
         * across multiple mocks.
         */
        InOrder inOrder = inOrder(validator, repo, notifier);

        /*
         * Step-by-step verification:
         * Mockito will FAIL if order is different
         */

        // 1️⃣ validate() must be FIRST
        inOrder.verify(validator).validate();

        // 2️⃣ save() must be SECOND
        //inOrder.verify(repo).save();

        // 3️⃣ notifyUser() must be LAST
        inOrder.verify(notifier).notifyUser();

        /*
         * This ensures:
         * - No extra method calls happened
         * - No method was called after notifyUser()
         */
        inOrder.verifyNoMoreInteractions();
    }
}
