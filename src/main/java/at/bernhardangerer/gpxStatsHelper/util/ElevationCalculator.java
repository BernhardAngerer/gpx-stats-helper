package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.AscentDescentPair;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;

import java.math.BigDecimal;
import java.util.List;

public final class ElevationCalculator {

    private ElevationCalculator() {
    }

    /**
     * Computes the elevation difference between two waypoints.
     * Returns {@code null} if either waypoint is {@code null} or has no elevation data.
     *
     * @param fromWaypoint the starting waypoint
     * @param toWaypoint   the ending waypoint
     * @return elevation delta as {@link BigDecimal} in meters,
     *         or {@code null} if elevation data is missing
     */
    static BigDecimal fromWaypoints(final Waypoint fromWaypoint, final Waypoint toWaypoint) {
        if (fromWaypoint != null && fromWaypoint.getEle() != null && toWaypoint != null && toWaypoint.getEle() != null) {
            return toWaypoint.getEle().subtract(fromWaypoint.getEle());
        }
        return null;
    }

    /**
     * Calculates total ascent and descent from a list of waypoints.
     * The elevation is computed as the sum of all positive and negative elevation deltas
     * between consecutive waypoints.
     *
     * @param waypointList list of GPX waypoints (must have at least 2)
     * @return an {@link AscentDescentPair} with ascent and descent in meters,
     *         or {@code null} if input is invalid or contains incomplete elevation data
     */
    static AscentDescentPair fromWaypointList(final List<Waypoint> waypointList) {
        if (waypointList != null && waypointList.size() >= 2) {
            final AscentDescentPair elevation = new AscentDescentPair();
            for (int count = 0; (count + 1) < waypointList.size(); count++) {
                final BigDecimal elevationDelta = fromWaypoints(waypointList.get(count), waypointList.get(count + 1));
                if (elevationDelta != null) {
                    if (elevationDelta.doubleValue() > 0) {
                        if (elevation.getAscent() == null) {
                            elevation.setAscent(BigDecimal.ZERO);
                        }
                        elevation.setAscent(elevation.getAscent().add(elevationDelta));
                    } else if (elevationDelta.doubleValue() < 0) {
                        if (elevation.getDescent() == null) {
                            elevation.setDescent(BigDecimal.ZERO);
                        }
                        elevation.setDescent(elevation.getDescent().add(elevationDelta.abs()));
                    }
                } else {
                    return null;
                }
            }
            return elevation;
        }
        return null;
    }

    /**
     * Calculates ascent and descent from a single {@link TrackSegment}.
     *
     * @param trackSegment the track segment containing waypoints
     * @return an {@link AscentDescentPair} with ascent and descent in meters,
     *         or {@code null} if input is invalid or incomplete
     */
    static AscentDescentPair fromTrackSegment(final TrackSegment trackSegment) {
        if (trackSegment != null) {
            return fromWaypointList(trackSegment.getTrkpt());
        }
        return null;
    }

    /**
     * Aggregates ascent and descent across a list of track segments.
     *
     * @param trackSegmentList list of {@link TrackSegment}
     * @return combined ascent and descent, or {@code null} if input is invalid
     */
    static AscentDescentPair fromTrackSegmentList(final List<TrackSegment> trackSegmentList) {
        if (trackSegmentList != null && !trackSegmentList.isEmpty()) {
            final AscentDescentPair delta = new AscentDescentPair();
            for (final TrackSegment trackSegment : trackSegmentList) {
                final AscentDescentPair elevation = fromTrackSegment(trackSegment);
                if (elevation != null) {
                    if (elevation.getAscent() != null) {
                        if (delta.getAscent() == null) {
                            delta.setAscent(BigDecimal.ZERO);
                        }
                        delta.setAscent(delta.getAscent().add(elevation.getAscent()));
                    }
                    if (elevation.getDescent() != null) {
                        if (delta.getDescent() == null) {
                            delta.setDescent(BigDecimal.ZERO);
                        }
                        delta.setDescent(delta.getDescent().add(elevation.getDescent()));
                    }
                } else {
                    return null;
                }
            }
            return delta;
        }
        return null;
    }

    /**
     * Computes the total ascent and descent of an entire {@link Track}.
     *
     * @param track the GPX track containing one or more segments
     * @return elevation statistics, or {@code null} if track is {@code null} or invalid
     */
    public static AscentDescentPair fromTrack(final Track track) {
        if (track != null) {
            return fromTrackSegmentList(track.getTrkseg());
        }
        return null;
    }
}
