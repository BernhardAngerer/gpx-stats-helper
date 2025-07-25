package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.enumeration.StepRoundingMode;

public final class RoundingUtil {

    private static final String INVALID_STEP_ROUNDING_MODE = "StepRoundingMode must not be null";
    private static final String UNSUPPORTED_STEP_ROUNDING_MODE = "Unsupported StepRoundingMode";

    private RoundingUtil() {
    }

    /**
     * Rounds an integer up to the nearest multiple of the given step.
     *
     * @param input the input value
     * @param step  the step size to round to
     * @return the smallest multiple of step that is ≥ input
     */
    public static int roundUpToStep(final int input, final int step) {
        return ((input + step - 1) / step) * step;
    }

    /**
     * Rounds a double up to the nearest multiple of the given step.
     *
     * @param input the input value
     * @param step  the step size to round to
     * @return the smallest multiple of step that is ≥ input
     */
    public static int roundUpToStep(final double input, final int step) {
        return (int) Math.ceil(input / step) * step;
    }

    /**
     * Rounds an integer down to the nearest multiple of the given step.
     *
     * @param input the input value
     * @param step  the step size to round to
     * @return the largest multiple of step that is ≤ input
     */
    public static int roundDownToStep(final int input, final int step) {
        return (input / step) * step;
    }

    /**
     * Rounds a double down to the nearest multiple of the given step.
     *
     * @param input the input value
     * @param step  the step size to round to
     * @return the largest multiple of step that is ≤ input
     */
    public static int roundDownToStep(final double input, final int step) {
        return (int) Math.floor(input / step) * step;
    }

    /**
     * Rounds an integer to the nearest multiple of the given step.
     * Rounds up if the remainder is ≥ step / 2.
     *
     * @param input the input value
     * @param step  the step size to round to
     * @return the multiple of step nearest to input
     */
    public static int roundToNearestStep(final int input, final int step) {
        return Math.round((float) input / step) * step;
    }

    /**
     * Rounds a double to the nearest multiple of the given step.
     * Rounds up if the remainder is ≥ step / 2.
     *
     * @param input the input value
     * @param step  the step size to round to
     * @return the multiple of step nearest to input
     */
    public static int roundToNearestStep(final double input, final int step) {
        return (int) Math.round(input / step) * step;
    }

    /**
     * Rounds an integer using the provided {@link StepRoundingMode}.
     *
     * @param input             the input value
     * @param step              the rounding step
     * @param stepRoundingMode rounding direction: UP, DOWN, or NEAREST
     * @return the rounded result
     * @throws IllegalArgumentException if stepRoundingMode is null or unsupported
     */
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

    /**
     * Rounds a double using the provided {@link StepRoundingMode}.
     *
     * @param input             the input value
     * @param step              the rounding step
     * @param stepRoundingMode rounding direction: UP, DOWN, or NEAREST
     * @return the rounded result
     * @throws IllegalArgumentException if stepRoundingMode is null or unsupported
     */
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
