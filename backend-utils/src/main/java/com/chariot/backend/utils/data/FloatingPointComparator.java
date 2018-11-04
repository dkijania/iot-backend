package com.chariot.backend.utils.data;

public class FloatingPointComparator {
    public static double PRECISION_LEVEL = 0.00000000000001;

    public boolean isWithinBounds(double value, double upperBound, double lowerBound) {
        return leftIsGreaterThanRight(upperBound, value) &&
                leftIsGreaterThanRight(value, lowerBound);
    }

    public boolean leftIsGreaterThanRight(double left, double right) {
        return Math.abs(left - right) > PRECISION_LEVEL;
    }

}
