package com.learning.ml;

import com.learning.ml.service.CalculatorService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
// mvn -Dtest=MathAppMatchersTest test

public class MathAppMatchersTest {

    // ------------------------------------------------------------------------
    // TEST 1 : Using anyInt() matcher
    // ------------------------------------------------------------------------
    @Test
    void testWithAnyInt() {

        // Creating a mock object of CalculatorService
        CalculatorService service = mock(CalculatorService.class);

        // Stubbing: When multiply() is called with ANY two integers → return 100
        // This means the values 5,4 or 10,20 do not matter
        when(service.multiply(anyInt(), anyInt()))
                .thenReturn(100);

        // Inject mock into MathApp
        MathApp app = new MathApp(service);

        // Since ANY int values match → always returns 100
        assertEquals(100, app.doMultiply(5, 4));
        assertEquals(100, app.doMultiply(10, 20));
        assertEquals(100, app.doMultiply(-1, 99));

        // Verification: multiply() must be called exactly 3 times
        verify(service, times(3)).multiply(anyInt(), anyInt());

        // -------------------------------------------------------
        // FAILURE CASES (Uncomment to see the errors)
        // -------------------------------------------------------

        // ❌ FAIL: Expected 200 but stub ALWAYS returns 100
        // assertEquals(200, app.doMultiply(5, 4));

        // ❌ FAIL: multiply() was called 3 times, not 1
        // verify(service, times(1)).multiply(anyInt(), anyInt());

        // ❌ FAIL: exact value 5,4 is never matched because ANY matcher is used
        // verify(service).multiply(5, 4);
    }

    // ------------------------------------------------------------------------
    // TEST 2 : Using eq() matcher (exact matching)
    // ------------------------------------------------------------------------
    @Test
    void testWithEqMatcher() {

        // Mocking the CalculatorService
        CalculatorService service = mock(CalculatorService.class);

        /*
         * Stubbing rule:
         * - First argument MUST be exactly 5  (eq(5))
         * - Second argument can be ANY integer  (anyInt())
         * If this condition is matched → return 500
         */
        when(service.multiply(eq(5), anyInt()))
                .thenReturn(500);

        // Inject mock
        MathApp app = new MathApp(service);

        // These match because the first argument is exactly 5
        assertEquals(500, app.doMultiply(5, 10));
        assertEquals(500, app.doMultiply(5, 20));

        // This does NOT match because first argument is 6
        // So default int value 0 is returned
        assertEquals(0, app.doMultiply(6, 20));

        // multiply() called three times (2 match + 1 non-match)
        verify(service, times(3)).multiply(anyInt(), anyInt());

        // -------------------------------------------------------
        // FAILURE CASES (Uncomment to see the errors)
        // -------------------------------------------------------

        // ❌ FAIL: No match for (6,20) → expected 500 but got 0
        // assertEquals(500, app.doMultiply(6, 20));

        // ❌ FAIL: multiply() was called 3 times, not 2
        // verify(service, times(2)).multiply(anyInt(), anyInt());

        // ❌ FAIL: eq(5) matcher used but exact (5,10) verify is missing
        // verify(service).multiply(5, 10);
    }

    // ------------------------------------------------------------------------
    // TEST 3 : Using custom matchers with intThat()
    // ------------------------------------------------------------------------
    @Test
    void testWithCustomMatcher() {

        // Create mock
        CalculatorService service = mock(CalculatorService.class);

        /*
         * Custom Matcher:
         * intThat(predicate)
         * Predicate tested:
         * → allow only positive integers (i > 0)
         * If both parameters are positive → return 111
         */
        when(service.multiply(
                intThat(i -> i > 0),     // 1st number must be positive
                intThat(i -> i > 0)      // 2nd number must be positive
        )).thenReturn(111);

        MathApp app = new MathApp(service);

        // These satisfy the custom condition (both positive)
        assertEquals(111, app.doMultiply(5, 5));
        assertEquals(111, app.doMultiply(10, 3));

        // This fails the condition (–1 is not positive)
        // So default 0 is returned
        assertEquals(0, app.doMultiply(-1, 5));

        // multiply() should be called exactly 3 times
        verify(service, times(3)).multiply(anyInt(), anyInt());

        // -------------------------------------------------------
        // FAILURE CASES (Uncomment to see the errors)
        // -------------------------------------------------------

        // ❌ FAIL: -1 is NOT positive → expected 111 but returns 0
        // assertEquals(111, app.doMultiply(-1, 5));

        // ❌ FAIL: multiply() was called 3 times, not 1
        // verify(service, times(1)).multiply(anyInt(), anyInt());

        // ❌ FAIL: Does not match custom matcher so verify will fail
        // verify(service).multiply(intThat(i -> i > 0), intThat(i -> i > 0));
    }
}
