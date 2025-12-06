package com.learning.ml;

import com.learning.ml.service.CalculatorService;
import com.learning.ml.service.CalculatorServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MathAppSpyMockTest {

    // =========================================================
    // TEST 1: Using MOCK (Fake object, no real method executed)
    // =========================================================
    @Test
    void testWithMock() {

        // Create MOCK object (no real logic)
        CalculatorService mockService = mock(CalculatorService.class);

        // Stub the multiply() method
        when(mockService.multiply(5, 4)).thenReturn(20);

        MathApp app = new MathApp(mockService);

        // Works because we defined behavior
        assertEquals(20, app.doMultiply(5, 4));

        // Default case → no rule → returns 0
        assertEquals(0, app.doMultiply(10, 10));

        verify(mockService, times(2)).multiply(anyInt(), anyInt());
    }

    // =========================================================
    // TEST 2: Using SPY (Real object with selective stubbing)
    // =========================================================
    @Test
    void testWithSpy() {

        // REAL service object
        CalculatorService realService = new CalculatorServiceImpl();

        // Create SPY → wraps real object
        CalculatorService spyService = spy(realService);

        MathApp app = new MathApp(spyService);

        // Real method will run → multiply = 5 * 4 = 20
        assertEquals(20, app.doMultiply(5, 4));

        // PARTIAL MOCK: Override one method
        when(spyService.multiply(10, 10)).thenReturn(999);

        // Real method will NOT run → returns mocked value
        assertEquals(999, app.doMultiply(10, 10));

        verify(spyService, times(2)).multiply(anyInt(), anyInt());
    }
}
