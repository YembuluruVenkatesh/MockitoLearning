package com.learning.ml;

import com.learning.ml.service.CalculatorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
// mvn -Dtest=MathAppTestInt test
public class MathAppTestInt {

    @Test
    void testDoMultiplyWithAnyInt() {
        // Create mock
        CalculatorService serv = mock(CalculatorService.class);
        System.out.println("in testDoMultiplyWithAnyInt");
        // Use argument matcher
        when(serv.multiply(anyInt(), anyInt())).thenReturn(100);
        //when(service.multiply(eq(5), anyInt())).thenReturn(20);

        // Inject into MathApp
        MathApp app = new MathApp(serv);
        // Any values
        int res = app.doMultiply(99, 20);
        // Returns whatever we defined
        assertEquals(100, res);
        //Verifying with matchers
        verify(serv).multiply(anyInt(), anyInt());
    }
}
