package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Waypoint;

public final class SlopeUtil {

    private static final int ONE_HUNDRED = 100;

    private SlopeUtil() {
    }

    /**
     * Calculates the slope (gradient) between two waypoints in percent.
     *
     * <p>The slope is computed using the elevation difference and the horizontal distance
     * (calculated via the Haversine formula) between the two given points.</p>
     *
     * @param waypoint1 First waypoint
     * @param waypoint2 Second waypoint
     * @return Slope in Percent
     */
    public static Double calcSlopePercent(final Waypoint waypoint1, final Waypoint waypoint2) {
        if (waypoint1 != null && waypoint1.getLat() != null && waypoint1.getLon() != null && waypoint1.getEle() != null
                && waypoint2 != null && waypoint2.getLat() != null && waypoint2.getLon() != null && waypoint2.getEle() != null) {
            return calcSlopePercent(waypoint1.getLat().doubleValue(), waypoint2.getLat().doubleValue(), waypoint1.getLon().doubleValue(),
                    waypoint2.getLon().doubleValue(), waypoint1.getEle().doubleValue(), waypoint2.getEle().doubleValue());
        }

        return null;
    }

    /**
     * Calculates the slope (gradient) between two geographic points in percent.
     *
     * <p>The slope is computed using the elevation difference and the horizontal distance
     * (calculated via the Haversine formula) between the two given points.</p>
     *
     * @param lat1 Latitude of the first point (in decimal degrees)
     * @param lat2 Latitude of the second point (in decimal degrees)
     * @param lon1 Longitude of the first point (in decimal degrees)
     * @param lon2 Longitude of the second point (in decimal degrees)
     * @param ele1 Elevation of the first point (in meters)
     * @param ele2 Elevation of the second point (in meters)
     * @return Slope in Percent
     */
    public static double calcSlopePercent(final double lat1, final double lat2, final double lon1,
                                          final double lon2, final double ele1, final double ele2) {
        final double distanceMeters = DistanceUtil.calcHaversineDistance(lat1, lat2, lon1, lon2);
        final double deltaElevation = ele2 - ele1;

        return (deltaElevation / distanceMeters) * ONE_HUNDRED;
    }
}
