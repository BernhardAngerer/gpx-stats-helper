package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;

import java.util.List;

import static at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil.calcDateTimeDifferenceInSeconds;

public final class SpeedMaxCalculator {

    private SpeedMaxCalculator() {
    }

    static double fromWaypoints(final Waypoint fromWaypoint, final Waypoint toWaypoint) {
        if (fromWaypoint != null && fromWaypoint.getEle() != null && toWaypoint != null && toWaypoint.getEle() != null
                && fromWaypoint.getTime() != null && toWaypoint.getTime() != null) {
            final Double distance = DistanceTotalCalculator.fromTrackpoints(fromWaypoint, toWaypoint);
            final long duration = calcDateTimeDifferenceInSeconds(fromWaypoint.getTime(), toWaypoint.getTime());
            if (distance != null) {
                return SpeedUtil.calculateSpeed(distance, duration);
            }
        }
        return 0;
    }

    static double fromWaypointList(final List<Waypoint> waypointList) {
        if (waypointList != null && waypointList.size() >= 2) {
            double speedMax = 0;
            for (int count = 0; (count + 1) < waypointList.size(); count++) {
                final double speed = fromWaypoints(waypointList.get(count), waypointList.get(count + 1));
                speedMax = Math.max(speed, speedMax);
            }
            return speedMax;
        }
        return 0;
    }

    static double fromTrackSegment(final TrackSegment trackSegment) {
        if (trackSegment != null) {
            return fromWaypointList(trackSegment.getTrkpt());
        }
        return 0;
    }

    static double fromTrackSegmentList(final List<TrackSegment> trackSegmentList) {
        if (trackSegmentList != null && !trackSegmentList.isEmpty()) {
            double speedMax = 0;
            for (final TrackSegment trackSegment : trackSegmentList) {
                final double speed = fromTrackSegment(trackSegment);
                speedMax = Math.max(speed, speedMax);
            }
            return speedMax;
        }
        return 0;
    }

    public static double fromTrack(final Track track) {
        if (track != null) {
            return fromTrackSegmentList(track.getTrkseg());
        }
        return 0;
    }
}
