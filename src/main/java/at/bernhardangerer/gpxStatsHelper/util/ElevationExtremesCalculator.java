package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.ElevationExtremes;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public final class ElevationExtremesCalculator {

    private ElevationExtremesCalculator() {
    }

    static ElevationExtremes fromWaypointList(final List<Waypoint> waypointList) {
        if (waypointList != null && !waypointList.isEmpty()) {
            final Waypoint highest = waypointList.stream()
                    .filter(waypoint -> waypoint.getEle() != null)
                    .max(Comparator.comparing(Waypoint::getEle))
                    .orElse(null);
            final Waypoint lowest = waypointList.stream()
                    .filter(waypoint -> waypoint.getEle() != null)
                    .min(Comparator.comparing(Waypoint::getEle))
                    .orElse(null);
            if (highest != null || lowest != null) {
                return new ElevationExtremes(highest, lowest);
            }
        }
        return null;
    }

    static ElevationExtremes fromTrackSegment(final TrackSegment trackSegment) {
        if (trackSegment != null) {
            return fromWaypointList(trackSegment.getTrkpt());
        }
        return null;
    }

    static ElevationExtremes fromTrackSegmentList(final List<TrackSegment> trackSegmentList) {
        if (trackSegmentList != null && !trackSegmentList.isEmpty()) {
            final Waypoint highest = trackSegmentList.stream()
                    .map(ElevationExtremesCalculator::fromTrackSegment)
                    .filter(Objects::nonNull)
                    .map(ElevationExtremes::getHighestPoint)
                    .max(Comparator.comparing(Waypoint::getEle))
                    .orElse(null);
            final Waypoint lowest = trackSegmentList.stream()
                    .map(ElevationExtremesCalculator::fromTrackSegment)
                    .filter(Objects::nonNull)
                    .map(ElevationExtremes::getLowestPoint)
                    .min(Comparator.comparing(Waypoint::getEle))
                    .orElse(null);
            if (highest != null || lowest != null) {
                return new ElevationExtremes(highest, lowest);
            }
        }
        return null;
    }

    /**
     * Calculate the lowest and highest elevation waypoints.
     *
     * @param track
     * @return ElevationRange as Waypoints
     */
    public static ElevationExtremes fromTrack(final Track track) {
        if (track != null) {
            return fromTrackSegmentList(track.getTrkseg());
        }
        return null;
    }
}
