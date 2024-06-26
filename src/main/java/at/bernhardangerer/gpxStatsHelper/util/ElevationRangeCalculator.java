package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.ElevationRange;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;

import java.util.Comparator;
import java.util.List;

public final class ElevationRangeCalculator {

    private ElevationRangeCalculator() {
    }

    static ElevationRange fromWaypointList(final List<Waypoint> waypointList) {
        if (waypointList != null && !waypointList.isEmpty()) {
            final ElevationRange range = new ElevationRange();
            range.setHighest(waypointList.stream()
                    .max(Comparator.comparing(Waypoint::getEle))
                    .get());
            range.setLowest(waypointList.stream()
                    .min(Comparator.comparing(Waypoint::getEle))
                    .get());
            return range;
        }
        return null;
    }

    static ElevationRange fromTrackSegment(final TrackSegment trackSegment) {
        if (trackSegment != null) {
            return fromWaypointList(trackSegment.getTrkpt());
        }
        return null;
    }

    static ElevationRange fromTrackSegmentList(final List<TrackSegment> trackSegmentList) {
        if (trackSegmentList != null && !trackSegmentList.isEmpty()) {
            final ElevationRange range = new ElevationRange();
            range.setHighest(trackSegmentList.stream()
                    .map(trackSegment -> fromTrackSegment(trackSegment).getHighest())
                    .max(Comparator.comparing(Waypoint::getEle)).get());
            range.setLowest(trackSegmentList.stream()
                    .map(trackSegment -> fromTrackSegment(trackSegment).getLowest())
                    .min(Comparator.comparing(Waypoint::getEle)).get());
            return range;
        }
        return null;
    }

    /**
     * Calculate the lowest and highest elevation waypoints.
     *
     * @param track
     * @return ElevationRange as Waypoints
     */
    public static ElevationRange fromTrack(final Track track) {
        if (track != null) {
            return fromTrackSegmentList(track.getTrkseg());
        }
        return null;
    }
}
