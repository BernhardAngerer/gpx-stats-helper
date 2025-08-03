package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.SpeedMetrics;

import static at.bernhardangerer.gpxStatsHelper.util.Constants.ONE_THOUSAND;

public final class SpeedUtil {

    private static final double THREE_THOUSAND_SIX_HUNDRED = 3600.0;

    private SpeedUtil() {
    }

    /**
     * Calculates the average speed from a given distance and duration.
     *
     * @param distance the distance covered in meters
     * @param duration the time taken in seconds
     * @return the speed in kilometers per hour (km/h); returns {@code 0} if either input is non-positive
     */
    public static double calculateSpeed(final double distance, final long duration) {
        if (distance > 0 && duration > 0) {
            return (distance / ONE_THOUSAND) / (duration / THREE_THOUSAND_SIX_HUNDRED);
        }
        return 0;
    }

    /**
     * Calculates the average speed based on a {@link SpeedMetrics} object,
     * which encapsulates both distance and duration.
     *
     * @param speedMetrics the {@code SpeedMetrics} instance containing distance (in meters) and duration (in seconds)
     * @return the speed in kilometers per hour (km/h); returns {@code null} if {@code speedMetrics} is {@code null}
     */
    public static Double calculateSpeed(final SpeedMetrics speedMetrics) {
        if (speedMetrics != null) {
            return calculateSpeed(speedMetrics.getDistance(), speedMetrics.getDuration());
        }
        return null;
    }
}
