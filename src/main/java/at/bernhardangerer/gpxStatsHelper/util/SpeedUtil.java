package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.DistanceDuration;

public final class SpeedUtil {

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
            return (distance / 1000) / (duration / 3600.0);
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
