package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.DistanceDuration;

public final class SpeedUtil {

    private static final int ONE_THOUSAND = 1000;
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
     * Calculate speed from DistanceDuration.
     *
     * @param distanceDuration
     * @return speed in kilometer per hour
     */
    public static double calculateSpeed(final DistanceDuration distanceDuration) {
        if (distanceDuration != null) {
            return calculateSpeed(distanceDuration.getDistance(), distanceDuration.getDuration());
        }
        return 0;
    }
}
