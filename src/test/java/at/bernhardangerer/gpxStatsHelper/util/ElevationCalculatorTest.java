package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.fixture.GpxFixture;
import at.bernhardangerer.gpxStatsHelper.model.AscentDescentPair;
import com.topografix.model.Gpx;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ElevationCalculatorTest {

    private static final Gpx GPX_TYPE = GpxReader.fromString(GpxFixture.GPX);
    private static final Gpx GPX_TYPE_WITHOUT_ELEVATION = GpxReader.fromString(GpxFixture.GPX_WITHOUT_ELEVATION);

    @Test
    void fromWaypoints() {
        final Waypoint fromWaypoint = new Waypoint();
        fromWaypoint.setEle(BigDecimal.valueOf(587));
        final Waypoint toWaypoint = new Waypoint();
        toWaypoint.setEle(BigDecimal.valueOf(590));

        BigDecimal elevationDelta = ElevationCalculator.fromWaypoints(fromWaypoint, toWaypoint);
        assertNotNull(elevationDelta);
        assertEquals(BigDecimal.valueOf(3), elevationDelta);

        elevationDelta = ElevationCalculator.fromWaypoints(null, null);
        assertNull(elevationDelta);
    }

    @Test
    void fromWaypointsWithoutElevation() {
        final Waypoint fromWaypoint = new Waypoint();
        final Waypoint toWaypoint = new Waypoint();

        final BigDecimal elevationDelta = ElevationCalculator.fromWaypoints(fromWaypoint, toWaypoint);
        assertNull(elevationDelta);
    }

    @Test
    void fromWaypointList() {
        final List<Waypoint> waypointList = GPX_TYPE.getTrk().get(0).getTrkseg().get(0).getTrkpt();

        AscentDescentPair delta = ElevationCalculator.fromWaypointList(waypointList);
        assertNotNull(delta);
        assertEquals(BigDecimal.ONE, delta.getAscent());
        assertEquals(BigDecimal.valueOf(2), delta.getDescent());

        delta = ElevationCalculator.fromWaypointList(null);
        assertNull(delta);

        delta = ElevationCalculator.fromWaypointList(new ArrayList<>());
        assertNull(delta);
    }

    @Test
    void fromWaypointListWithoutElevation() {
        final List<Waypoint> waypointList = GPX_TYPE_WITHOUT_ELEVATION.getTrk().get(0).getTrkseg().get(0).getTrkpt();

        final AscentDescentPair delta = ElevationCalculator.fromWaypointList(waypointList);
        assertNull(delta);
    }

    @Test
    void fromTrackSegment() {
        final TrackSegment trackSegment = GPX_TYPE.getTrk().get(0).getTrkseg().get(0);

        AscentDescentPair delta = ElevationCalculator.fromTrackSegment(trackSegment);
        assertNotNull(delta);
        assertEquals(BigDecimal.ONE, delta.getAscent());
        assertEquals(BigDecimal.valueOf(2), delta.getDescent());

        delta = ElevationCalculator.fromTrackSegment(null);
        assertNull(delta);

        delta = ElevationCalculator.fromTrackSegment(new TrackSegment());
        assertNull(delta);
    }

    @Test
    void fromTrackSegmentWithoutElevation() {
        final TrackSegment trackSegment = GPX_TYPE_WITHOUT_ELEVATION.getTrk().get(0).getTrkseg().get(0);

        final AscentDescentPair delta = ElevationCalculator.fromTrackSegment(trackSegment);
        assertNull(delta);
    }

    @Test
    void fromTrackSegmentList() {
        final List<TrackSegment> trackSegmentList = GPX_TYPE.getTrk().get(0).getTrkseg();

        AscentDescentPair delta = ElevationCalculator.fromTrackSegmentList(trackSegmentList);
        assertNotNull(delta);
        assertEquals(BigDecimal.ONE, delta.getAscent());
        assertEquals(BigDecimal.valueOf(4), delta.getDescent());

        delta = ElevationCalculator.fromTrackSegmentList(null);
        assertNull(delta);

        delta = ElevationCalculator.fromTrackSegmentList(new ArrayList<>());
        assertNull(delta);
    }

    @Test
    void fromTrackSegmentListWithoutElevation() {
        final List<TrackSegment> trackSegmentList = GPX_TYPE_WITHOUT_ELEVATION.getTrk().get(0).getTrkseg();

        final AscentDescentPair delta = ElevationCalculator.fromTrackSegmentList(trackSegmentList);
        assertNull(delta);
    }

    @Test
    void fromTrack() {
        final Track track = GPX_TYPE.getTrk().get(0);

        AscentDescentPair delta = ElevationCalculator.fromTrack(track);
        assertNotNull(delta);
        assertEquals(BigDecimal.ONE, delta.getAscent());
        assertEquals(BigDecimal.valueOf(4), delta.getDescent());

        delta = ElevationCalculator.fromTrack(null);
        assertNull(delta);

        delta = ElevationCalculator.fromTrack(new Track());
        assertNull(delta);
    }

    @Test
    void fromTrackWithoutElevation() {
        final Track track = GPX_TYPE_WITHOUT_ELEVATION.getTrk().get(0);

        final AscentDescentPair delta = ElevationCalculator.fromTrack(track);
        assertNull(delta);
    }
}
