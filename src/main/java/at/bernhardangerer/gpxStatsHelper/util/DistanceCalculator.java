package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.AscentDescentPair;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;

import java.math.BigDecimal;
import java.util.List;

import static at.bernhardangerer.gpxStatsHelper.util.DistanceUtil.calcDistance;
import static at.bernhardangerer.gpxStatsHelper.util.ElevationCalculator.fromWaypoints;

public final class DistanceCalculator {

    private DistanceCalculator() {
    }

    /**
     * Calculates distance traveled during ascending and descending elevation changes
     * between a list of waypoints.
     * <p>
     * Only segments with valid elevation values on both ends are considered.
     *
     * @param waypointList ordered list of {@link Waypoint} objects (must have ≥ 2 elements)
     * @return an {@link AscentDescentPair} where:
     *         - {@code ascent} holds distance traveled while climbing
     *         - {@code descent} holds distance while descending
     *         Returns {@code null} if input is invalid or elevation data is incomplete.
     */
    static AscentDescentPair fromWaypointList(final List<Waypoint> waypointList) {
        if (waypointList != null && waypointList.size() >= 2) {
            final AscentDescentPair distance = new AscentDescentPair();
            for (int count = 0; (count + 1) < waypointList.size(); count++) {
                final Double deltaDistance = calcDistance(waypointList.get(count), waypointList.get(count + 1));
                final BigDecimal elevationDelta = fromWaypoints(waypointList.get(count), waypointList.get(count + 1));
                if (elevationDelta != null) {
                    if (elevationDelta.doubleValue() >= 0) {
                        if (distance.getAscent() == null) {
                            distance.setAscent(BigDecimal.ZERO);
                        }
                        distance.setAscent(distance.getAscent().add(BigDecimal.valueOf(deltaDistance)));
                    } else if (elevationDelta.doubleValue() < 0) {
                        if (distance.getDescent() == null) {
                            distance.setDescent(BigDecimal.ZERO);
                        }
                        distance.setDescent(distance.getDescent().add(BigDecimal.valueOf(deltaDistance)));
                    }
                } else {
                    return null;
                }
            }
            return distance;
        }
        return null;
    }

    /**
     * Computes the ascent and descent distance from a single {@link TrackSegment}.
     *
     * @param trackSegment the track segment to analyze
     * @return an {@link AscentDescentPair} with distance in meters per elevation direction,
     *         or {@code null} if data is missing or invalid
     */
    static AscentDescentPair fromTrackSegment(final TrackSegment trackSegment) {
        if (trackSegment != null) {
            return fromWaypointList(trackSegment.getTrkpt());
        }
        return null;
    }

    /**
     * Aggregates ascent and descent distances over a list of {@link TrackSegment} entries.
     *
     * @param trackSegmentList the list of track segments to process
     * @return combined {@link AscentDescentPair} with total ascending and descending distances,
     *         or {@code null} if input is invalid or contains incomplete data
     */
    static AscentDescentPair fromTrackSegmentList(final List<TrackSegment> trackSegmentList) {
        if (trackSegmentList != null && !trackSegmentList.isEmpty()) {
            final AscentDescentPair result = new AscentDescentPair();
            for (final TrackSegment trackSegment : trackSegmentList) {
                final AscentDescentPair distance = fromTrackSegment(trackSegment);
                if (distance != null) {
                    if (distance.getAscent() != null) {
                        if (result.getAscent() == null) {
                            result.setAscent(BigDecimal.ZERO);
                        }
                        result.setAscent(result.getAscent().add(distance.getAscent()));
                    }
                    if (distance.getDescent() != null) {
                        if (result.getDescent() == null) {
                            result.setDescent(BigDecimal.ZERO);
                        }
                        result.setDescent(result.getDescent().add(distance.getDescent()));
                    }
                } else {
                    return null;
                }
            }
            return result;
        }
        return null;
    }

    /**
     * Calculates total ascending and descending distances for an entire GPX {@link Track}.
     * <p>
     * Only segments with valid elevation and coordinate data are included.
     *
     * @param track the track to analyze
     * @return {@link AscentDescentPair} of distances, or {@code null} if invalid
     */
    public static AscentDescentPair fromTrack(final Track track) {
        if (track != null) {
            return fromTrackSegmentList(track.getTrkseg());
        }
        return null;
    }

}
