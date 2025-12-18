package com.learning.ml;

import com.learning.ml.service.CalculationService;
import com.learning.ml.util.MathUtil;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
// mvn -Dtest=StaticMockTest test
public class StaticMockTest {

    @Test
    void test_static_method_mocking() {

        // try-with-resources is MANDATORY
        try (MockedStatic<MathUtil> mockedStatic = mockStatic(MathUtil.class)) {

            // Stub static method
            mockedStatic.when(() -> MathUtil.add(5, 10))
                    .thenReturn(100);

            CalculationService service = new CalculationService();

            int result = service.calculate(5, 10);

            assertEquals(100, result);

            // Verify static method call
            mockedStatic.verify(() -> MathUtil.add(5, 10));
        }
    }
}
