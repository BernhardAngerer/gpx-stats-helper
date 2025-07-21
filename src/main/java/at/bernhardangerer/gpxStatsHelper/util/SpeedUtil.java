package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.SpeedMetrics;

import static at.bernhardangerer.gpxStatsHelper.util.Constants.ONE_THOUSAND;

public final class SpeedUtil {

    private static final double THREE_THOUSAND_SIX_HUNDRED = 3600.0;

    private SpeedUtil() {
    }

    /**
     * Calculate speed from distance and duration.
     *
     * @param distance in meter
     * @param duration in seconds
     * @return speed in kilometer per hour
     */
    public static double calculateSpeed(final double distance, final long duration) {
        if (distance > 0 && duration > 0) {
            return (distance / ONE_THOUSAND) / (duration / THREE_THOUSAND_SIX_HUNDRED);
        }
        return 0;
    }

    /**
     * Calculate speed from SpeedMetrics.
     *
     * @param speedMetrics
     * @return speed in kilometer per hour
     */
    public static double calculateSpeed(final SpeedMetrics speedMetrics) {
        if (speedMetrics != null) {
            return calculateSpeed(speedMetrics.getDistance(), speedMetrics.getDuration());
        }
        return 0;
    }
}
