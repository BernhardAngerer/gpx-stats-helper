package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.enumeration.StepRoundingMode;

public final class RoundingUtil {

    private static final String INVALID_STEP_ROUNDING_MODE = "StepRoundingMode must not be null";
    private static final String UNSUPPORTED_STEP_ROUNDING_MODE = "Unsupported StepRoundingMode";

    private RoundingUtil() {
    }

    public static int roundUpToStep(final int input, final int step) {
        return ((input + step - 1) / step) * step;
    }

    public static int roundUpToStep(final double input, final int step) {
        return (int) Math.ceil(input / step) * step;
    }

    public static int roundDownToStep(final int input, final int step) {
        return (input / step) * step;
    }

    public static int roundDownToStep(final double input, final int step) {
        return (int) Math.floor(input / step) * step;
    }

    public static int roundToNearestStep(final int input, final int step) {
        return Math.round((float) input / step) * step;
    }

    public static int roundToNearestStep(final double input, final int step) {
        return (int) Math.round(input / step) * step;
    }

    public static int roundToStep(final int input, final int step, final StepRoundingMode stepRoundingMode) {
        if (stepRoundingMode == null) {
            throw new IllegalArgumentException(INVALID_STEP_ROUNDING_MODE);
        }

        switch (stepRoundingMode) {
            case UP: return roundUpToStep(input, step);
            case DOWN: return roundDownToStep(input, step);
            case NEAREST: return roundToNearestStep(input, step);
            default: throw new IllegalArgumentException(UNSUPPORTED_STEP_ROUNDING_MODE);
        }
    }

    public static int roundToStep(final double input, final int step, final StepRoundingMode stepRoundingMode) {
        if (stepRoundingMode == null) {
            throw new IllegalArgumentException(INVALID_STEP_ROUNDING_MODE);
        }

        switch (stepRoundingMode) {
            case UP: return roundUpToStep(input, step);
            case DOWN: return roundDownToStep(input, step);
            case NEAREST: return roundToNearestStep(input, step);
            default: throw new IllegalArgumentException(UNSUPPORTED_STEP_ROUNDING_MODE);
        }
    }
}
