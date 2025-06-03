package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;

import java.util.List;

import static at.bernhardangerer.gpxStatsHelper.util.DistanceUtil.calcDistance;

public final class DistanceTotalCalculator {

    private DistanceTotalCalculator() {
    }

    static Double fromWaypointList(final List<Waypoint> waypointList) {
        if (waypointList != null && waypointList.size() >= 2) {
            double distance = 0;
            for (int count = 0; (count + 1) < waypointList.size(); count++) {
                final Double dist = calcDistance(waypointList.get(count), waypointList.get(count + 1));
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

    /**
     * Calculate the distance in meters.
     *
     * @param track
     * @return distance in meters
     */
    public static Double fromTrack(final Track track) {
        if (track != null) {
            return fromTrackSegmentList(track.getTrkseg());
        }
        return null;
    }

}
