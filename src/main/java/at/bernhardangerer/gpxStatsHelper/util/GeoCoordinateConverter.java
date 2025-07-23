package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Waypoint;

import java.util.Locale;

public final class GeoCoordinateConverter {

    private GeoCoordinateConverter() {
    }

    /**
     * Converts the latitude and longitude of a given {@code Waypoint} into
     * a human-readable Degrees, Minutes, Seconds (DMS) string format.
     *
     * Example: `"16°27'59.180\"N, 73°49'59.904\"W"`
     *
     * @param waypoint the Waypoint object containing latitude and longitude values; must not be null
     * @return a String representing the latitude and longitude in DMS format separated by a comma
     * @throws IllegalArgumentException if {@code waypoint} is null or if its latitude or longitude is null
     */
    public static String waypointToDms(final Waypoint waypoint) {
        if (waypoint == null || waypoint.getLat() == null || waypoint.getLon() == null) {
            throw new IllegalArgumentException("Waypoint, Lat or Lon must not be null!");
        }
        return latitudeDecimalToDms(waypoint.getLat().doubleValue()) + ", " + longitudeDecimalToDms(waypoint.getLon().doubleValue());
    }

    /**
     * Converts a latitude from decimal degrees to degrees-minutes-seconds (DMS) format.
     *
     * @param latitude The latitude in decimal degrees (positive for North, negative for South).
     * @return The formatted latitude string in DMS format (e.g., 16°27'59.180"N).
     */
    public static String latitudeDecimalToDms(final double latitude) {
        return decimalToDms(latitude, true);
    }

    /**
     * Converts a longitude from decimal degrees to degrees-minutes-seconds (DMS) format.
     *
     * @param longitude The longitude in decimal degrees (positive for East, negative for West).
     * @return The formatted longitude string in DMS format (e.g., 73°49'59.123"W).
     */
    public static String longitudeDecimalToDms(final double longitude) {
        return decimalToDms(longitude, false);
    }

    /**
     * Converts a geographic coordinate (latitude or longitude) from decimal degrees
     * to degrees-minutes-seconds (DMS) format with hemisphere indicator.
     *
     * @param decimalDegrees The coordinate in decimal degrees. Positive for North/East, negative for South/West.
     * @param isLatitude     True if the coordinate is latitude, false if longitude.
     * @return The formatted coordinate string in DMS format (e.g., 16°27'59.180"N).
     */
    static String decimalToDms(final double decimalDegrees, final boolean isLatitude) {
        final String direction;
        if (isLatitude) {
            direction = decimalDegrees >= 0 ? "N" : "S";
        } else {
            direction = decimalDegrees >= 0 ? "E" : "W";
        }

        final double absDecimalDegrees = Math.abs(decimalDegrees);
        final int degrees = (int) absDecimalDegrees;
        final double minutesDecimal = (absDecimalDegrees - degrees) * 60;
        final int minutes = (int) minutesDecimal;
        final double seconds = (minutesDecimal - minutes) * 60;

        return String.format(Locale.US, "%d°%d'%06.3f\"%s", degrees, minutes, seconds, direction);
    }

}
