package com.learning.ml;

import com.learning.ml.service.CalculatorService;

public class MathApp {

    private final CalculatorService calculatorService;

    public MathApp(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public int doMultiply(int x, int y) {
        return calculatorService.multiply(x, y);
    }
}
