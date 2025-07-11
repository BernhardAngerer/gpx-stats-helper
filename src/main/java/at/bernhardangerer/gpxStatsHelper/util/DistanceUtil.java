package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Waypoint;

public final class DistanceUtil {

    private static final int ONE_THOUSAND = 1000;

    private DistanceUtil() {
    }

    /**
     * Calculate distance between two waypoints.
     *
     * @param waypoint1 First waypoint
     * @param waypoint2 Second waypoint
     * @return Distance in Meters
     */
    public static double calcHaversineDistance(final Waypoint waypoint1, final Waypoint waypoint2) {
        return calcHaversineDistance(waypoint1.getLat().doubleValue(), waypoint2.getLat().doubleValue(),
                waypoint1.getLon().doubleValue(), waypoint2.getLon().doubleValue());
    }

    /**
     * Calculate distance between two points in latitude and longitude.
     *
     * @param lat1 Latitude of the first point (in decimal degrees)
     * @param lat2 Latitude of the second point (in decimal degrees)
     * @param lon1 Longitude of the first point (in decimal degrees)
     * @param lon2 Longitude of the second point (in decimal degrees)
     * @return Distance in Meters
     */
    public static double calcHaversineDistance(final double lat1, final double lat2, final double lon1, final double lon2) {
        final int radiusEarth = 6371; // Radius of the earth
        final double latDistance = Math.toRadians(lat2 - lat1);
        final double lonDistance = Math.toRadians(lon2 - lon1);
        final double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return radiusEarth * c * ONE_THOUSAND; // convert to meters
    }

    /**
     * Calculate distance between two waypoints
     * taking into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * @param waypoint1 First waypoint
     * @param waypoint2 Second waypoint
     * @return Distance in Meters
     */
    static Double calcDistance(final Waypoint waypoint1, final Waypoint waypoint2) {
        if (waypoint1 != null && waypoint1.getLat() != null && waypoint1.getLon() != null && waypoint1.getEle() != null
                && waypoint2 != null && waypoint2.getLat() != null && waypoint2.getLon() != null && waypoint2.getEle() != null) {
            return calcDistance(waypoint1.getLat().doubleValue(), waypoint2.getLat().doubleValue(), waypoint1.getLon().doubleValue(),
                    waypoint2.getLon().doubleValue(), waypoint1.getEle().doubleValue(), waypoint2.getEle().doubleValue());
        }

        return null;
    }

    /**
     * Calculate distance between two points in latitude and longitude
     * taking into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * @param lat1 Latitude of the first point (in decimal degrees)
     * @param lat2 Latitude of the second point (in decimal degrees)
     * @param lon1 Longitude of the first point (in decimal degrees)
     * @param lon2 Longitude of the second point (in decimal degrees)
     * @param ele1  Elevation of the first point (in meters)
     * @param ele2  Elevation of the second point (in meters)
     * @return Distance in Meters
     */
    static double calcDistance(final double lat1, final double lat2, final double lon1,
                               final double lon2, final double ele1, final double ele2) {
        double distanceMeters = calcHaversineDistance(lat1, lat2, lon1, lon2);
        final double deltaElevation = ele1 - ele2;
        distanceMeters = Math.pow(distanceMeters, 2) + Math.pow(deltaElevation, 2);

        return Math.sqrt(distanceMeters);
    }

}
