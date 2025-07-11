package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.DistanceDuration;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;

import java.util.List;

import static at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil.calcDateTimeDifferenceInSeconds;
import static at.bernhardangerer.gpxStatsHelper.util.PropertyUtil.loadValue;

public final class SpeedAvgCalculator {

    private static final double MOTION_MIN_SPEED = Double.parseDouble(loadValue("motionMinSpeedThreshold"));

    private SpeedAvgCalculator() {
    }

    static DistanceDuration fromWaypoints(final Waypoint fromWaypoint, final Waypoint toWaypoint) {
        if (fromWaypoint != null && fromWaypoint.getEle() != null && toWaypoint != null && toWaypoint.getEle() != null
                && fromWaypoint.getTime() != null && toWaypoint.getTime() != null) {
            final Double distance = DistanceUtil.calcDistance(fromWaypoint, toWaypoint);
            final long duration = calcDateTimeDifferenceInSeconds(fromWaypoint.getTime(), toWaypoint.getTime());
            if (distance != null) {
                final double speed = SpeedUtil.calculateSpeed(distance, duration);
                if (speed > MOTION_MIN_SPEED) {
                    return new DistanceDuration(distance, duration);
                }
            }
        }
        return null;
    }

    static DistanceDuration fromWaypointList(final List<Waypoint> waypointList) {
        if (waypointList != null && waypointList.size() >= 2) {
            final DistanceDuration distanceDuration = new DistanceDuration();
            for (int count = 0; (count + 1) < waypointList.size(); count++) {
                final DistanceDuration tempDistanceDuration = fromWaypoints(waypointList.get(count), waypointList.get(count + 1));
                if (tempDistanceDuration != null) {
                    distanceDuration.setDistance(distanceDuration.getDistance() + tempDistanceDuration.getDistance());
                    distanceDuration.setDuration(distanceDuration.getDuration() + tempDistanceDuration.getDuration());
                }
            }
            return distanceDuration;
        }
        return null;
    }

    static DistanceDuration fromTrackSegment(final TrackSegment trackSegment) {
        if (trackSegment != null) {
            return fromWaypointList(trackSegment.getTrkpt());
        }
        return null;
    }

    static DistanceDuration fromTrackSegmentList(final List<TrackSegment> trackSegmentList) {
        if (trackSegmentList != null && !trackSegmentList.isEmpty()) {
            final DistanceDuration distanceDuration = new DistanceDuration();
            for (final TrackSegment trackSegment : trackSegmentList) {
                final DistanceDuration tempDistanceDuration = fromTrackSegment(trackSegment);
                if (tempDistanceDuration != null) {
                    distanceDuration.setDistance(distanceDuration.getDistance() + tempDistanceDuration.getDistance());
                    distanceDuration.setDuration(distanceDuration.getDuration() + tempDistanceDuration.getDuration());
                }
            }
            return distanceDuration;
        }
        return null;
    }

    /**
     * Calculate the average speed in kilometer per hour.
     *
     * @param track
     * @return average speed in kilometer per hour
     */
    public static Double fromTrack(final Track track) {
        if (track != null) {
            return SpeedUtil.calculateSpeed(fromTrackSegmentList(track.getTrkseg()));
        }
        return null;
    }
}
