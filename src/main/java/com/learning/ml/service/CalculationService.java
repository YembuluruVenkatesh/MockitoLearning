package com.learning.ml.service;

import com.learning.ml.util.MathUtil;

public class CalculationService {

    public int calculate(int x, int y) {
        // Uses STATIC method
        return MathUtil.add(x, y);
    }
}
