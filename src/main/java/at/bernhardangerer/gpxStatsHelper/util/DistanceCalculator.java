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

    static AscentDescentPair fromTrackSegment(final TrackSegment trackSegment) {
        if (trackSegment != null) {
            return fromWaypointList(trackSegment.getTrkpt());
        }
        return null;
    }

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
     * Calculate the distance in meters.
     *
     * @param track
     * @return distance in meters
     */
    public static AscentDescentPair fromTrack(final Track track) {
        if (track != null) {
            return fromTrackSegmentList(track.getTrkseg());
        }
        return null;
    }

}
