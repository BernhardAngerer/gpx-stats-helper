package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.SpeedMetrics;

import static at.bernhardangerer.gpxStatsHelper.util.Constants.TWO_DIGIT_PADDING_FORMAT;

public final class PaceUtil {

    private static final double SIXTY = 60.0;
    private static final double ONE_THOUSAND = 1000.0;

    private PaceUtil() {
    }

    /**
     * Calculates the pace in minutes per kilometer from the given distance and duration.
     *
     * @param distance the distance in meters; must be greater than 0
     * @param duration the duration in seconds; must be greater than 0
     * @return the pace in minutes per kilometer (e.g., 5.5 = 5:30 min/km)
     * @throws IllegalArgumentException if distance or duration is negative
     */
    public static double calculatePace(final double distance, final long duration) {
        if (distance < 0 || duration < 0) {
            throw new IllegalArgumentException("Invalid distance or duration");
        }

        return (duration / SIXTY) / (distance / ONE_THOUSAND);
    }

    /**
     * Calculates pace from a SpeedMetrics object.
     *
     * @param speedMetrics the object containing distance (meters) and duration (seconds)
     * @return the pace in minutes per kilometer
     * @throws IllegalArgumentException if speedMetrics is null or contains invalid values
     *
     * @see #calculatePace(double, long)
     */
    public static double calculatePace(final SpeedMetrics speedMetrics) {
        if (speedMetrics == null || speedMetrics.getDistance() < 0 || speedMetrics.getDuration() < 0) {
            throw new IllegalArgumentException("Invalid speedMetrics, distance or duration");
        }

        return calculatePace(speedMetrics.getDistance(), speedMetrics.getDuration());
    }

    /**
     * Formats a decimal value representing minutes into a string in "mm:ss" format.
     * <p>
     * Example: {@code 4.5} → {@code "04:30"} (4 minutes and 30 seconds).
     *
     * @param minutes the time in minutes (e.g., 4.5 means 4 min 30 sec); must be >= 0
     * @return a formatted string in "mm:ss" format
     * @throws IllegalArgumentException if minutes is negative
     */
    public static String format(final double minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Invalid minutes");
        }

        final int totalSeconds = (int) Math.round(minutes * Constants.SIXTY);
        final int mm = totalSeconds / Constants.SIXTY;
        final int ss = totalSeconds % Constants.SIXTY;

        return TWO_DIGIT_PADDING_FORMAT.format(mm) + ":" + TWO_DIGIT_PADDING_FORMAT.format(ss);
    }
}
