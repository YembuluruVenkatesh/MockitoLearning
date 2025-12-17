package com.learning.ml;

import com.learning.ml.service.PriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
// mvn -Dtest=SpyTest test
@ExtendWith(MockitoExtension.class)
public class SpyTest {

    @Spy
    PriceService priceService;

    @Test
    void test_partial_mocking() {

        // Override only ONE method
        doReturn(200).when(priceService).getPrice();

        // getDiscountedPrice() uses REAL logic
        int result = priceService.getDiscountedPrice();
        System.out.println("get price: "+priceService.getPrice());
        System.out.println("getDiscountedPrice: "+priceService.getDiscountedPrice());
        // 200 - 10 = 190
        assertEquals(190, result);

        // verify real method was called
        verify(priceService,times(3)).getPrice();
        //Verify minimum calls
        //verify(priceService, atLeastOnce()).getPrice();

        /*****
         *     doReturn(200).when(priceService).getPrice();
         *
         *     int discounted = priceService.getDiscountedPrice();
         *     int price = priceService.getPrice();
         *
         *     System.out.println("get price: " + price);
         *     System.out.println("getDiscountedPrice: " + discounted);
         *
         *     assertEquals(190, discounted);
         *
         *     verify(priceService, times(2)).getPrice();
         */
    }
}
