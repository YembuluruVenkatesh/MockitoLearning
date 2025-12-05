package com.learning.ml;

import com.learning.ml.service.CalculatorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
// mvn -Dtest=MockingMethodsWithMultipleBehaviors test
public class MockingMethodsWithMultipleBehaviors {

    @Test
    void MethodsWithMultipleBehaviors(){
        // Create mock
        CalculatorService serv = mock(CalculatorService.class);
        // Use argument matcher
        when(serv.multiply(2,3))
                .thenReturn(6)
                .thenReturn(12)
                .thenReturn(18);
        // Inject into MathApp
        MathApp app = new MathApp(serv);
        // Returns whatever we defined
        assertEquals(6,app.doMultiply(2,3));
        assertEquals(12,app.doMultiply(2,3));
        assertEquals(18,app.doMultiply(2,3));


    }
}
