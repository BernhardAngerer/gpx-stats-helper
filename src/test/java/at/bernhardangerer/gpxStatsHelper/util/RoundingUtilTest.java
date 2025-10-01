package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.enumeration.StepRoundingMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoundingUtilTest {

    @Test
    public void testRoundIntToStepNearest() {
        assertEquals(6, RoundingUtil.roundToStep(7, 3, StepRoundingMode.NEAREST));
        assertEquals(8, RoundingUtil.roundToStep(7, 2, StepRoundingMode.NEAREST));
        assertEquals(12, RoundingUtil.roundToStep(11, 4, StepRoundingMode.NEAREST));
    }

    @Test
    public void testRoundIntToStepDown() {
        assertEquals(6, RoundingUtil.roundToStep(7, 3, StepRoundingMode.DOWN));
        assertEquals(6, RoundingUtil.roundToStep(7, 2, StepRoundingMode.DOWN));
        assertEquals(8, RoundingUtil.roundToStep(11, 4, StepRoundingMode.DOWN));
    }

    @Test
    public void testRoundIntToStepUp() {
        assertEquals(9, RoundingUtil.roundToStep(7, 3, StepRoundingMode.UP));
        assertEquals(8, RoundingUtil.roundToStep(7, 2, StepRoundingMode.UP));
        assertEquals(12, RoundingUtil.roundToStep(11, 4, StepRoundingMode.UP));
    }

    @Test
    public void testIntInvalidRoundingModeThrows() {
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> RoundingUtil.roundToStep(10, 2, null));
        assertEquals("StepRoundingMode must not be null", exception.getMessage());
    }

    @Test
    public void testRoundIntToNearestStep() {
        assertEquals(6, RoundingUtil.roundToNearestStep(7, 3));
        assertEquals(8, RoundingUtil.roundToNearestStep(7, 2));
        assertEquals(12, RoundingUtil.roundToNearestStep(11, 4));
        assertEquals(10, RoundingUtil.roundToNearestStep(10, 5));
        assertEquals(0, RoundingUtil.roundToNearestStep(1, 10));
    }

    @Test
    public void testRoundIntDownToStep() {
        assertEquals(6, RoundingUtil.roundDownToStep(7, 3));
        assertEquals(6, RoundingUtil.roundDownToStep(7, 2));
        assertEquals(8, RoundingUtil.roundDownToStep(11, 4));
        assertEquals(10, RoundingUtil.roundDownToStep(14, 5));
        assertEquals(0, RoundingUtil.roundDownToStep(4, 10));
    }

    @Test
    public void testRoundIntUpToStep() {
        assertEquals(9, RoundingUtil.roundUpToStep(7, 3));
        assertEquals(8, RoundingUtil.roundUpToStep(7, 2));
        assertEquals(12, RoundingUtil.roundUpToStep(11, 4));
        assertEquals(15, RoundingUtil.roundUpToStep(14, 5));
        assertEquals(10, RoundingUtil.roundUpToStep(1, 10));
    }

    @Test
    public void testRoundDoubleToStepNearest() {
        assertEquals(6, RoundingUtil.roundToStep(7.2, 3, StepRoundingMode.NEAREST));
        assertEquals(9, RoundingUtil.roundToStep(7.6, 3, StepRoundingMode.NEAREST));
        assertEquals(10, RoundingUtil.roundToStep(10.0, 5, StepRoundingMode.NEAREST));
    }

    @Test
    public void testRoundDoubleToStepDown() {
        assertEquals(6, RoundingUtil.roundToStep(7.9, 3, StepRoundingMode.DOWN));
        assertEquals(6, RoundingUtil.roundToStep(7.2, 3, StepRoundingMode.DOWN));
        assertEquals(5, RoundingUtil.roundToStep(9.9, 5, StepRoundingMode.DOWN));
    }

    @Test
    public void testRoundDoubleToStepUp() {
        assertEquals(9, RoundingUtil.roundToStep(7.1, 3, StepRoundingMode.UP));
        assertEquals(9, RoundingUtil.roundToStep(7.9, 3, StepRoundingMode.UP));
        assertEquals(10, RoundingUtil.roundToStep(9.1, 5, StepRoundingMode.UP));
    }

    @Test
    public void testRoundDoubleToStepWithExactMultiple() {
        assertEquals(9, RoundingUtil.roundToStep(9.0, 3, StepRoundingMode.UP));
        assertEquals(9, RoundingUtil.roundToStep(9.0, 3, StepRoundingMode.DOWN));
        assertEquals(9, RoundingUtil.roundToStep(9.0, 3, StepRoundingMode.NEAREST));
    }

    @Test
    public void testRoundDoubleToStepNullModeThrows() {
        final Exception exception = assertThrows(IllegalArgumentException.class, () ->
                RoundingUtil.roundToStep(7.0, 2, null)
        );
        assertEquals("StepRoundingMode must not be null", exception.getMessage());
    }

    @Test
    public void testNegativeDoubleValues() {
        assertEquals(-6, RoundingUtil.roundToStep(-7.2, 3, StepRoundingMode.NEAREST));
        assertEquals(-9, RoundingUtil.roundToStep(-7.2, 3, StepRoundingMode.DOWN));
        assertEquals(-6, RoundingUtil.roundToStep(-7.2, 3, StepRoundingMode.UP));
    }
}
