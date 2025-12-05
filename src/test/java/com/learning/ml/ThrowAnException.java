package com.learning.ml;

import com.learning.ml.service.CalculatorService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
// mvn -Dtest=ThrowAnException test

public class ThrowAnException {

    @Test
    void thrownExceptionEx(){

        CalculatorService serv = mock(CalculatorService.class);

        when(serv.multiply(0,5)).thenThrow(new IllegalArgumentException("Zero not allowed"));

        System.out.println("Zero not allowed Exception");
        MathApp app = new MathApp(serv);

        assertThrows(IllegalArgumentException.class,() ->{
            app.doMultiply(0,5);
                }
        );
    }
}
