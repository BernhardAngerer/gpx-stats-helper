package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;

import static at.bernhardangerer.gpxStatsHelper.util.DistanceUtil.calcDistance;

public final class WaypointUtil {

    private WaypointUtil() {
    }

    /**
     * Returns the first {@link Waypoint} in the provided {@link Track}.
     * <p>
     * Looks into the first {@link TrackSegment} and returns its first {@link Waypoint}, if available.
     *
     * @param track the GPX {@link Track} to examine
     * @return the first {@link Waypoint}, or {@code null} if none found
     */
    public static Waypoint findFirstWaypoint(final Track track) {
        if (track != null && track.getTrkseg() != null && !track.getTrkseg().isEmpty()
                && track.getTrkseg().get(0) != null && track.getTrkseg().get(0).getTrkpt() != null
                && !track.getTrkseg().get(0).getTrkpt().isEmpty()) {
            return track.getTrkseg()
                .get(0)
                .getTrkpt()
                .get(0);
        }
        return null;
    }

    /**
     * Returns the last {@link Waypoint} in the provided {@link Track}.
     * <p>
     * Looks into the last {@link TrackSegment} and returns its last {@link Waypoint}, if available.
     *
     * @param track the GPX {@link Track} to examine
     * @return the last {@link Waypoint}, or {@code null} if none found
     */
    public static Waypoint findLastWaypoint(final Track track) {
        if (track != null && track.getTrkseg() != null && !track.getTrkseg().isEmpty()
                && track.getTrkseg().get(track.getTrkseg().size() - 1) != null
                && track.getTrkseg().get(track.getTrkseg().size() - 1).getTrkpt() != null
                && !track.getTrkseg().get(track.getTrkseg().size() - 1).getTrkpt().isEmpty()) {
            return track.getTrkseg()
                    .get(track.getTrkseg().size() - 1)
                    .getTrkpt()
                    .get(track.getTrkseg().get(track.getTrkseg().size() - 1).getTrkpt().size() - 1);
        }
        return null;
    }

    /**
     * Finds the {@link Waypoint} in the given {@link Track} that is farthest from the specified reference position.
     *
     * @param referencePosition the waypoint to measure distance from
     * @param track             the {@link Track} containing waypoints
     * @return the {@link Waypoint} with the maximum distance from the reference, or {@code null} if not found
     */
    public static Waypoint findFarthestWaypoint(final Waypoint referencePosition, final Track track) {
        if (referencePosition != null && track != null) {
            return track.getTrkseg().stream()
                    .flatMap(trackSegment -> trackSegment.getTrkpt().stream())
                    .max(Comparator.comparingDouble(waypoint -> calcDistance(referencePosition, waypoint)))
                    .orElse(null);
        }
        return null;
    }

    /**
     * Formats the given {@link Waypoint} into a readable coordinate string.
     * <p>
     * Includes elevation if available.
     *
     * @param waypoint the {@link Waypoint} to format
     * @return string formatted as "Lat xx.xxxx, Lon yy.yyyy, Ele zz.z" or {@code null} if waypoint is null
     */
    public static String formatWaypoint(final Waypoint waypoint) {
        return formatWaypoint(waypoint, true);
    }

    /**
     * Formats the given {@link Waypoint} into a readable coordinate string.
     *
     * @param waypoint      the {@link Waypoint} to format
     * @param withElevation whether to include elevation in the output
     * @return string formatted as "Lat xx.xxxx, Lon yy.yyyy[, Ele zz.z]" or {@code null} if waypoint is null
     */
    public static String formatWaypoint(final Waypoint waypoint, final boolean withElevation) {
        if (waypoint != null) {
            String coordinates = "Lat " + waypoint.getLat() + ", Lon " + waypoint.getLon();
            if (withElevation && waypoint.getEle() != null) {
                coordinates += ", Ele " + waypoint.getEle();
            }

            return coordinates;
        }
        return null;
    }

    /**
     * Creates a {@link Waypoint} with the specified latitude, longitude and elevation.
     *
     * @param lat  the latitude in decimal degrees
     * @param lon  the longitude in decimal degrees
     * @param ele  the elevation in meters
     * @return a {@code Waypoint} initialized with the given values
     */
    public static Waypoint createWaypoint(double lat, double lon, int ele) {
        return createWaypoint(lat, lon, ele, null);
    }

    /**
     * Creates a {@link Waypoint} with the specified latitude, longitude, elevation, and timestamp.
     *
     * @param lat  the latitude in decimal degrees
     * @param lon  the longitude in decimal degrees
     * @param ele  the elevation in meters
     * @param time the timestamp of the waypoint
     * @return a {@code Waypoint} initialized with the given values
     */
    public static Waypoint createWaypoint(double lat, double lon, int ele, LocalDateTime time) {
        final Waypoint wp = new Waypoint();
        wp.setLat(BigDecimal.valueOf(lat));
        wp.setLon(BigDecimal.valueOf(lon));
        wp.setEle(BigDecimal.valueOf(ele));
        wp.setTime(time);
        return wp;
    }

    /**
     * Creates a {@link Waypoint} with the specified latitude, longitude and elevation.
     *
     * @param lat  the latitude in decimal degrees
     * @param lon  the longitude in decimal degrees
     * @param ele  the elevation in meters
     * @return a {@code Waypoint} initialized with the given values
     */
    public static Waypoint createWaypoint(double lat, double lon, double ele) {
        return createWaypoint(lat, lon, ele, null);
    }

    /**
     * Creates a {@link Waypoint} with the specified latitude, longitude, elevation, and timestamp.
     *
     * @param lat  the latitude in decimal degrees
     * @param lon  the longitude in decimal degrees
     * @param ele  the elevation in meters
     * @param time the timestamp of the waypoint
     * @return a {@code Waypoint} initialized with the given values
     */
    public static Waypoint createWaypoint(double lat, double lon, double ele, LocalDateTime time) {
        final Waypoint wp = new Waypoint();
        wp.setLat(BigDecimal.valueOf(lat));
        wp.setLon(BigDecimal.valueOf(lon));
        wp.setEle(BigDecimal.valueOf(ele));
        wp.setTime(time);
        return wp;
    }

}
