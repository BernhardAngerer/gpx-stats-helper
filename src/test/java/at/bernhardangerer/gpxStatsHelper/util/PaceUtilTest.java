package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.SpeedMetrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaceUtilTest {

    @Test
    void calculatePaceWithValidDistanceAndDurationReturnsPaceInMinutesPerKm() {
        final double distance = 1000.0; // 1km
        final long duration = 300; // 5 minutes

        final double pace = PaceUtil.calculatePace(distance, duration);

        assertEquals(5.0, pace, 0.001);
    }

    @Test
    void calculatePaceWithPartialKilometerCalculatesCorrectPace() {
        final double distance = 500.0; // 0.5km
        final long duration = 150; // 2.5 minutes

        final double pace = PaceUtil.calculatePace(distance, duration);

        assertEquals(5.0, pace, 0.001);
    }

    @Test
    void calculatePaceWithNegativeDistanceThrowsIllegalArgumentException() {
        final double distance = -100.0;
        final long duration = 300;

        final IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PaceUtil.calculatePace(distance, duration)
        );
        assertEquals("Invalid distance or duration", exception.getMessage());
    }

    @Test
    void calculatePaceWithNegativeDurationThrowsIllegalArgumentException() {
        final double distance = 1000.0;
        final long duration = -300;

        final IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PaceUtil.calculatePace(distance, duration)
        );
        assertEquals("Invalid distance or duration", exception.getMessage());
    }

    @Test
    void calculatePaceWithZeroDistanceReturnsInfinity() {
        final double distance = 0.0;
        final long duration = 300;

        final double pace = PaceUtil.calculatePace(distance, duration);

        assertEquals(Double.POSITIVE_INFINITY, pace);
    }

    @Test
    void calculatePaceWithZeroDurationReturnsZero() {
        final double distance = 1000.0;
        final long duration = 0;

        final double pace = PaceUtil.calculatePace(distance, duration);

        assertEquals(0.0, pace, 0.001);
    }

    @Test
    void calculatePaceWithSpeedMetricsReturnsPaceInMinutesPerKm() {
        final SpeedMetrics speedMetrics = new SpeedMetrics(2000.0, 600); // 2km in 10 minutes

        final double pace = PaceUtil.calculatePace(speedMetrics);

        assertEquals(5.0, pace, 0.001);
    }

    @Test
    void calculatePaceWithNullSpeedMetricsThrowsIllegalArgumentException() {
        final SpeedMetrics speedMetrics = null;

        final IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PaceUtil.calculatePace(speedMetrics)
        );
        assertEquals("Invalid speedMetrics, distance or duration", exception.getMessage());
    }

    @Test
    void calculatePaceWithSpeedMetricsNegativeDistanceThrowsIllegalArgumentException() {
        final SpeedMetrics speedMetrics = new SpeedMetrics(-1000.0, 300);

        final IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PaceUtil.calculatePace(speedMetrics)
        );
        assertEquals("Invalid speedMetrics, distance or duration", exception.getMessage());
    }

    @Test
    void calculatePaceWithSpeedMetricsNegativeDurationThrowsIllegalArgumentException() {
        final SpeedMetrics speedMetrics = new SpeedMetrics(1000.0, -300);

        final IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PaceUtil.calculatePace(speedMetrics)
        );
        assertEquals("Invalid speedMetrics, distance or duration", exception.getMessage());
    }

    @Test
    void formatWithWholeMinutesReturnsFormattedString() {
        final double minutes = 5.0;

        final String formatted = PaceUtil.format(minutes);

        assertEquals("05:00", formatted);
    }

    @Test
    void formatWithMinutesAndSecondsReturnsFormattedString() {
        final double minutes = 4.5; // 4 minutes 30 seconds

        final String formatted = PaceUtil.format(minutes);

        assertEquals("04:30", formatted);
    }

    @Test
    void formatWithDecimalSecondsRoundsCorrectly() {
        final double minutes = 5.75; // 5 minutes 45 seconds

        final String formatted = PaceUtil.format(minutes);

        assertEquals("05:45", formatted);
    }

    @Test
    void formatWithLessThanOneMinuteReturnsFormattedString() {
        final double minutes = 0.5; // 30 seconds

        final String formatted = PaceUtil.format(minutes);

        assertEquals("00:30", formatted);
    }

    @Test
    void formatWithZeroMinutesReturnsZeroFormat() {
        final double minutes = 0.0;

        final String formatted = PaceUtil.format(minutes);

        assertEquals("00:00", formatted);
    }

    @Test
    void formatWithNegativeMinutesThrowsIllegalArgumentException() {
        final double minutes = -1.0;

        final IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PaceUtil.format(minutes)
        );
        assertEquals("Invalid minutes", exception.getMessage());
    }

    @Test
    void formatWithLargeValueHandlesCorrectly() {
        final double minutes = 65.25; // 1 hour 5 minutes 15 seconds

        final String formatted = PaceUtil.format(minutes);

        assertEquals("65:15", formatted);
    }

    @Test
    void formatWithRoundingCaseRoundsToNearestSecond() {
        final double minutes = 4.51; // 4 minutes + 30.6 seconds, should round to 4:31

        final String formatted = PaceUtil.format(minutes);

        assertEquals("04:31", formatted);
    }
}
