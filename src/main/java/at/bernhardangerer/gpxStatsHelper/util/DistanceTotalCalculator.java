package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;

import java.util.List;

public final class DistanceTotalCalculator {

    private DistanceTotalCalculator() {
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * @param lat1 Start point Lat
     * @param lat2 End point Lat
     * @param lon1 Start point Lon
     * @param lon2 End point Lon
     * @param el1 Start point elevation
     * @param el2 End point elevation
     * @return Distance in Meters
     */
    static double calcDistance(final double lat1, final double lat2, final double lon1,
                               final double lon2, final double el1, final double el2) {
        final int radiusEarth = 6371; // Radius of the earth
        final double latDistance = Math.toRadians(lat2 - lat1);
        final double lonDistance = Math.toRadians(lon2 - lon1);
        final double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = radiusEarth * c * 1000; // convert to meters
        final double height = el1 - el2;
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        return Math.sqrt(distance);
    }

    static Double fromTrackpoints(final Waypoint fromWaypoint, final Waypoint toWaypoint) {
        if (fromWaypoint != null && fromWaypoint.getLat() != null && fromWaypoint.getLon() != null && fromWaypoint.getEle() != null
            && toWaypoint != null && toWaypoint.getLat() != null && toWaypoint.getLon() != null && toWaypoint.getEle() != null) {
            return calcDistance(fromWaypoint.getLat().doubleValue(), toWaypoint.getLat().doubleValue(),
                fromWaypoint.getLon().doubleValue(), toWaypoint.getLon().doubleValue(),
                fromWaypoint.getEle().doubleValue(), toWaypoint.getEle().doubleValue());
        }
        return null;
    }

    static Double fromWaypointList(final List<Waypoint> waypointList) {
        if (waypointList != null && waypointList.size() >= 2) {
            double distance = 0;
            for (int count = 0; (count + 1) < waypointList.size(); count++) {
                final Double dist = fromTrackpoints(waypointList.get(count), waypointList.get(count + 1));
                if (dist != null) {
                    distance = distance + dist;
                } else {
                    return null;
                }
            }
            return distance;
        }
        return null;
    }

    static Double fromTrackSegment(final TrackSegment trackSegment) {
        if (trackSegment != null) {
            return fromWaypointList(trackSegment.getTrkpt());
        }
        return null;
    }

    static Double fromTrackSegmentList(final List<TrackSegment> trackSegmentList) {
        if (trackSegmentList != null && !trackSegmentList.isEmpty()) {
            double distance = 0;
            for (final TrackSegment trackSegment : trackSegmentList) {
                final Double dist = fromTrackSegment(trackSegment);
                if (dist != null) {
                    distance = distance + dist;
                } else {
                    return null;
                }
            }
            return distance;
        }
        return null;
    }

    public static Double fromTrack(final Track track) {
        if (track != null) {
            return fromTrackSegmentList(track.getTrkseg());
        }
        return null;
    }
}
