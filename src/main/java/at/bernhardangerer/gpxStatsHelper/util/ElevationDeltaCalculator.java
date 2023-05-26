package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.ElevationDelta;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;

import java.math.BigDecimal;
import java.util.List;

public final class ElevationDeltaCalculator {

    private ElevationDeltaCalculator() {
    }

    static BigDecimal fromWaypoints(final Waypoint fromWaypoint, final Waypoint toWaypoint) {
        if (fromWaypoint != null && fromWaypoint.getEle() != null && toWaypoint != null && toWaypoint.getEle() != null) {
            return toWaypoint.getEle().subtract(fromWaypoint.getEle());
        }
        return null;
    }

    static ElevationDelta fromWaypointList(final List<Waypoint> waypointList) {
        if (waypointList != null && waypointList.size() >= 2) {
            final ElevationDelta delta = new ElevationDelta();
            for (int count = 0; (count + 1) < waypointList.size(); count++) {
                final BigDecimal elevationDelta = fromWaypoints(waypointList.get(count), waypointList.get(count + 1));
                if (elevationDelta != null) {
                    if (elevationDelta.doubleValue() > 0) {
                        if (delta.getAscent() == null) {
                            delta.setAscent(BigDecimal.ZERO);
                        }
                        delta.setAscent(delta.getAscent().add(elevationDelta));
                    } else if (elevationDelta.doubleValue() < 0) {
                        if (delta.getDescent() == null) {
                            delta.setDescent(BigDecimal.ZERO);
                        }
                        delta.setDescent(delta.getDescent().add(elevationDelta.abs()));
                    }
                } else {
                    return null;
                }
            }
            return delta;
        }
        return null;
    }

    static ElevationDelta fromTrackSegment(final TrackSegment trackSegment) {
        if (trackSegment != null) {
            return fromWaypointList(trackSegment.getTrkpt());
        }
        return null;
    }

    static ElevationDelta fromTrackSegmentList(final List<TrackSegment> trackSegmentList) {
        if (trackSegmentList != null && !trackSegmentList.isEmpty()) {
            final ElevationDelta delta = new ElevationDelta();
            for (final TrackSegment trackSegment : trackSegmentList) {
                final ElevationDelta elevationDelta = fromTrackSegment(trackSegment);
                if (elevationDelta != null) {
                    if (elevationDelta.getAscent() != null) {
                        if (delta.getAscent() == null) {
                            delta.setAscent(BigDecimal.ZERO);
                        }
                        delta.setAscent(delta.getAscent().add(elevationDelta.getAscent()));
                    }
                    if (elevationDelta.getDescent() != null) {
                        if (delta.getDescent() == null) {
                            delta.setDescent(BigDecimal.ZERO);
                        }
                        delta.setDescent(delta.getDescent().add(elevationDelta.getDescent()));
                    }
                } else {
                    return null;
                }
            }
            return delta;
        }
        return null;
    }

    public static ElevationDelta fromTrack(final Track track) {
        if (track != null) {
            return fromTrackSegmentList(track.getTrkseg());
        }
        return null;
    }
}
