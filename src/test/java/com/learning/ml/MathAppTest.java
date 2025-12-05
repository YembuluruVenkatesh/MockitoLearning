package com.learning.ml;

import com.learning.ml.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MathAppTest {

    @Test
    void testDoMultiply() {
       /* // 1. Create mock object
        CalculatorService service = mock(CalculatorService.class);

        // 2. Define behavior for mock
        when(service.multiply(5, 4)).thenReturn(20);

        // 3. Inject mock into MathApp
        MathApp app = new MathApp(service);

        // 4. Call real method
        int result = app.doMultiply(5, 4);

        // 5. Assertions
        assertEquals(20, result);

        // 6. Verify method call
        verify(service).multiply(5, 4);*/
    }
}
