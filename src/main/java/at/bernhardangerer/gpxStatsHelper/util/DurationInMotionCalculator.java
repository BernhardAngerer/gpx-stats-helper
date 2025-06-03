package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;

import java.util.List;
import java.util.Objects;

import static at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil.calcDateTimeDifferenceInSeconds;
import static at.bernhardangerer.gpxStatsHelper.util.PropertyUtil.loadValue;

public final class DurationInMotionCalculator {

    private static final double MOTION_MIN_SPEED = Double.parseDouble(loadValue("motionMinSpeedThreshold"));

    private DurationInMotionCalculator() {
    }

    static Long fromWaypoints(final Waypoint fromWaypoint, final Waypoint toWaypoint) {
        if (fromWaypoint != null && fromWaypoint.getEle() != null && toWaypoint != null && toWaypoint.getEle() != null
                && fromWaypoint.getTime() != null && toWaypoint.getTime() != null) {
            final Double distance = DistanceUtil.calcDistance(fromWaypoint, toWaypoint);
            final long duration = calcDateTimeDifferenceInSeconds(fromWaypoint.getTime(), toWaypoint.getTime());
            if (distance != null) {
                final double speed = SpeedUtil.calculateSpeed(distance, duration);
                if (speed > MOTION_MIN_SPEED) {
                    return duration;
                }
            }
        }
        return null;
    }

    static Long fromWaypointList(final List<Waypoint> waypointList) {
        if (waypointList != null && waypointList.size() >= 2) {
            Long durationInSec = null;
            for (int count = 0; (count + 1) < waypointList.size(); count++) {
                final Long durInSec = fromWaypoints(waypointList.get(count), waypointList.get(count + 1));
                if (durInSec != null) {
                    if (durationInSec == null) {
                        durationInSec = 0L;
                    }
                    durationInSec += durInSec;
                }
            }
            return durationInSec;
        }
        return null;
    }

    static Long fromTrackSegment(final TrackSegment trackSegment) {
        if (trackSegment != null) {
            return fromWaypointList(trackSegment.getTrkpt());
        }
        return null;
    }

    static Long fromTrackSegmentList(final List<TrackSegment> trackSegmentList) {
        if (trackSegmentList != null && !trackSegmentList.isEmpty()) {
            return trackSegmentList.stream()
                    .map(DurationInMotionCalculator::fromTrackSegment)
                    .filter(Objects::nonNull)
                    .reduce(0L, Long::sum);
        }
        return null;
    }

    /**
     * Calculate the duration in motion in seconds.
     *
     * @param track
     * @return the duration in motion in seconds
     */
    public static Long fromTrack(final Track track) {
        if (track != null) {
            return fromTrackSegmentList(track.getTrkseg());
        }
        return null;
    }
}
