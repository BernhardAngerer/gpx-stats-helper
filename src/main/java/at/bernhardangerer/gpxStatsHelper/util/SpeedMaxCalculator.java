package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;

import java.util.List;

import static at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil.calcDateTimeDifferenceInSeconds;

public final class SpeedMaxCalculator {

    private SpeedMaxCalculator() {
    }

    static Double fromWaypoints(final Waypoint fromWaypoint, final Waypoint toWaypoint) {
        if (fromWaypoint != null && fromWaypoint.getEle() != null && toWaypoint != null && toWaypoint.getEle() != null
                && fromWaypoint.getTime() != null && toWaypoint.getTime() != null) {
            final Double distance = DistanceUtil.calcDistance(fromWaypoint, toWaypoint);
            final long duration = calcDateTimeDifferenceInSeconds(fromWaypoint.getTime(), toWaypoint.getTime());
            if (distance != null) {
                return SpeedUtil.calculateSpeed(distance, duration);
            }
        }
        return null;
    }

    static Double fromWaypointList(final List<Waypoint> waypointList) {
        if (waypointList != null && waypointList.size() >= 2) {
            Double speedMax = null;
            for (int count = 0; (count + 1) < waypointList.size(); count++) {
                final Double speed = fromWaypoints(waypointList.get(count), waypointList.get(count + 1));
                if (speed != null) {
                    if (speedMax == null) {
                        speedMax = 0d;
                    }
                    speedMax = Math.max(speed, speedMax);
                }
            }
            return speedMax;
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
            Double speedMax = null;
            for (final TrackSegment trackSegment : trackSegmentList) {
                final Double speed = fromTrackSegment(trackSegment);
                if (speed != null) {
                    if (speedMax == null) {
                        speedMax = 0d;
                    }
                    speedMax = Math.max(speed, speedMax);
                }
            }
            return speedMax;
        }
        return null;
    }

    /**
     * Calculate the maximum speed in kilometer per hour.
     *
     * @param track
     * @return maximum speed in kilometer per hour
     */
    public static Double fromTrack(final Track track) {
        if (track != null) {
            return fromTrackSegmentList(track.getTrkseg());
        }
        return null;
    }
}
