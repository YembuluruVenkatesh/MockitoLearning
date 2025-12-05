package com.learning.ml;

import org.junit.jupiter.api.Test;

import com.learning.ml.service.CalculatorService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
// mvn -Dtest=ReturnBasedOnInputValues test
public class ReturnBasedOnInputValues {

    @Test
    void ReturnBasedOnInputValues(){
        CalculatorService serv = mock(CalculatorService.class);

        //ReturnBasedOnInputValues
        when(serv.multiply(5,5)).thenReturn(25);
        when(serv.multiply(5,6)).thenReturn(30);

        MathApp app = new MathApp(serv);

        assertEquals(25,app.doMultiply(5,5));
        assertEquals(30,app.doMultiply(5,6));
    }
}
