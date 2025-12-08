package com.learning.ml;

import com.learning.ml.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
//mvn -Dtest=MathAppAnnotationTest test
@ExtendWith(MockitoExtension.class)  // Enables @Mock + @InjectMocks
public class MathAppAnnotationTest {

    // ---------------------------------------------------------
    // Creates a MOCK of CalculatorService
    // ---------------------------------------------------------
    @Mock
    private CalculatorService service;

    // ---------------------------------------------------------
    // Creates MathApp and automatically injects the mock service
    // ---------------------------------------------------------
    @InjectMocks
    private MathApp app;

    // ---------------------------------------------------------
    // TEST 1 : Simple mocking using annotations
    // ---------------------------------------------------------
    @Test
    void testDoMultiply_UsingAnnotations() {

        // When multiply() is called → return 50
        when(service.multiply(5, 10)).thenReturn(50);

        // MathApp.doMultiply() uses the injected mock
        int result = app.doMultiply(5, 10);

        assertEquals(50, result);

        verify(service).multiply(5, 10);
    }

    // ---------------------------------------------------------
    // TEST 2 : Using matchers with @Mock
    // ---------------------------------------------------------
    @Test
    void testDoMultiply_WithMatchers() {

        // ANY int arguments → always return 100
        when(service.multiply(anyInt(), anyInt())).thenReturn(100);

        assertEquals(100, app.doMultiply(2, 3));
        assertEquals(100, app.doMultiply(10, 20));

        verify(service, times(2))
                .multiply(anyInt(), anyInt());
    }

    /*
     =========================================================
     FAILURE CASES (COMMENT & UNCOMMENT WHEN NEEDED)
     =========================================================

     // ❌ Failure: Should be 50 but stub returns 100
     // assertEquals(50, app.doMultiply(5, 10));

     // ❌ Wrong verification count (should be 2)
     // verify(service, times(1)).multiply(anyInt(), anyInt());

     // ❌ Wrong argument values
     // verify(service).multiply(100, 200);
     */
}
