package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.Elevation;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;

import java.math.BigDecimal;
import java.util.List;

public final class ElevationCalculator {

    private ElevationCalculator() {
    }

    static BigDecimal fromWaypoints(final Waypoint fromWaypoint, final Waypoint toWaypoint) {
        if (fromWaypoint != null && fromWaypoint.getEle() != null && toWaypoint != null && toWaypoint.getEle() != null) {
            return toWaypoint.getEle().subtract(fromWaypoint.getEle());
        }
        return null;
    }

    static Elevation fromWaypointList(final List<Waypoint> waypointList) {
        if (waypointList != null && waypointList.size() >= 2) {
            final Elevation elevation = new Elevation();
            for (int count = 0; (count + 1) < waypointList.size(); count++) {
                final BigDecimal elevationDelta = fromWaypoints(waypointList.get(count), waypointList.get(count + 1));
                if (elevationDelta != null) {
                    if (elevationDelta.doubleValue() >= 0) {
                        if (elevation.getAscent() == null) {
                            elevation.setAscent(BigDecimal.ZERO);
                        }
                        elevation.setAscent(elevation.getAscent().add(elevationDelta));
                    } else if (elevationDelta.doubleValue() < 0) {
                        if (elevation.getDescent() == null) {
                            elevation.setDescent(BigDecimal.ZERO);
                        }
                        elevation.setDescent(elevation.getDescent().add(elevationDelta.abs()));
                    }
                } else {
                    return null;
                }
            }
            return elevation;
        }
        return null;
    }

    static Elevation fromTrackSegment(final TrackSegment trackSegment) {
        if (trackSegment != null) {
            return fromWaypointList(trackSegment.getTrkpt());
        }
        return null;
    }

    static Elevation fromTrackSegmentList(final List<TrackSegment> trackSegmentList) {
        if (trackSegmentList != null && !trackSegmentList.isEmpty()) {
            final Elevation delta = new Elevation();
            for (final TrackSegment trackSegment : trackSegmentList) {
                final Elevation elevation = fromTrackSegment(trackSegment);
                if (elevation != null) {
                    if (elevation.getAscent() != null) {
                        if (delta.getAscent() == null) {
                            delta.setAscent(BigDecimal.ZERO);
                        }
                        delta.setAscent(delta.getAscent().add(elevation.getAscent()));
                    }
                    if (elevation.getDescent() != null) {
                        if (delta.getDescent() == null) {
                            delta.setDescent(BigDecimal.ZERO);
                        }
                        delta.setDescent(delta.getDescent().add(elevation.getDescent()));
                    }
                } else {
                    return null;
                }
            }
            return delta;
        }
        return null;
    }

    /**
     * Calculate the ascent and descent elevation.
     *
     * @param track
     * @return ElevationDelta in meters
     */
    public static Elevation fromTrack(final Track track) {
        if (track != null) {
            return fromTrackSegmentList(track.getTrkseg());
        }
        return null;
    }
}
