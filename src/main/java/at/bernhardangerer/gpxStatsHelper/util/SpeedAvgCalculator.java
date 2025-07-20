package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.SpeedMetrics;
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

    static SpeedMetrics fromWaypoints(final Waypoint fromWaypoint, final Waypoint toWaypoint) {
        if (fromWaypoint != null && fromWaypoint.getEle() != null && toWaypoint != null && toWaypoint.getEle() != null
                && fromWaypoint.getTime() != null && toWaypoint.getTime() != null) {
            final Double distance = DistanceUtil.calcDistance(fromWaypoint, toWaypoint);
            final long duration = calcDateTimeDifferenceInSeconds(fromWaypoint.getTime(), toWaypoint.getTime());
            if (distance != null) {
                final double speed = SpeedUtil.calculateSpeed(distance, duration);
                if (speed > MOTION_MIN_SPEED) {
                    return new SpeedMetrics(distance, duration);
                }
            }
        }
        return null;
    }

    static SpeedMetrics fromWaypointList(final List<Waypoint> waypointList) {
        if (waypointList != null && waypointList.size() >= 2) {
            final SpeedMetrics speedMetrics = new SpeedMetrics();
            for (int count = 0; (count + 1) < waypointList.size(); count++) {
                final SpeedMetrics tempSpeedMetrics = fromWaypoints(waypointList.get(count), waypointList.get(count + 1));
                if (tempSpeedMetrics != null) {
                    speedMetrics.setDistance(speedMetrics.getDistance() + tempSpeedMetrics.getDistance());
                    speedMetrics.setDuration(speedMetrics.getDuration() + tempSpeedMetrics.getDuration());
                }
            }
            return speedMetrics;
        }
        return null;
    }

    static SpeedMetrics fromTrackSegment(final TrackSegment trackSegment) {
        if (trackSegment != null) {
            return fromWaypointList(trackSegment.getTrkpt());
        }
        return null;
    }

    static SpeedMetrics fromTrackSegmentList(final List<TrackSegment> trackSegmentList) {
        if (trackSegmentList != null && !trackSegmentList.isEmpty()) {
            final SpeedMetrics speedMetrics = new SpeedMetrics();
            for (final TrackSegment trackSegment : trackSegmentList) {
                final SpeedMetrics tempSpeedMetrics = fromTrackSegment(trackSegment);
                if (tempSpeedMetrics != null) {
                    speedMetrics.setDistance(speedMetrics.getDistance() + tempSpeedMetrics.getDistance());
                    speedMetrics.setDuration(speedMetrics.getDuration() + tempSpeedMetrics.getDuration());
                }
            }
            return speedMetrics;
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
