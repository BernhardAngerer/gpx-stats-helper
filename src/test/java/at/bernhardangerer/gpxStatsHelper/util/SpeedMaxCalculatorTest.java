package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.fixture.GpxFixture;
import com.topografix.model.Gpx;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static at.bernhardangerer.gpxStatsHelper.util.WaypointUtil.createWaypoint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpeedMaxCalculatorTest {

    private static final Gpx GPX_TYPE = GpxReader.fromString(GpxFixture.GPX);
    private static final Gpx GPX_TYPE_WITHOUT_TIME = GpxReader.fromString(GpxFixture.GPX_WITHOUT_TIME);

    @Test
    void fromWaypoints() {
        final Waypoint fromWaypoint = createWaypoint(47.80743, 12.378228, 587, LocalDateTime.of(2021, 9, 7, 16, 14, 16));
        final Waypoint toWaypoint = createWaypoint(47.807343, 12.378138, 588, LocalDateTime.of(2021, 9, 7, 16, 14, 20));

        Double speedMax = SpeedMaxCalculator.fromWaypoints(fromWaypoint, toWaypoint);
        assertTrue(speedMax > 0);
        assertEquals(10.63987214678773, speedMax);

        speedMax = SpeedMaxCalculator.fromWaypoints(null, null);
        assertNull(speedMax);

        speedMax = SpeedMaxCalculator.fromWaypoints(new Waypoint(), new Waypoint());
        assertNull(speedMax);
    }

    @Test
    void fromWaypointsWithoutTime() {
        final Waypoint fromWaypoint = createWaypoint(47.80743, 12.378228, 587, null);
        final Waypoint toWaypoint = createWaypoint(47.807343, 12.378138, 588, null);

        final Double speedMax = SpeedMaxCalculator.fromWaypoints(fromWaypoint, toWaypoint);
        assertNull(speedMax);
    }

    @Test
    void fromWaypointList() {
        final List<Waypoint> waypointList = GPX_TYPE.getTrk().get(0).getTrkseg().get(0).getTrkpt();

        Double speedMax = SpeedMaxCalculator.fromWaypointList(waypointList);
        assertTrue(speedMax > 0);
        assertEquals(18.85037290593836, speedMax);

        speedMax = SpeedMaxCalculator.fromWaypointList(null);
        assertNull(speedMax);

        speedMax = SpeedMaxCalculator.fromWaypointList(new ArrayList<>());
        assertNull(speedMax);
    }

    @Test
    void fromWaypointListWithoutTime() {
        final List<Waypoint> waypointList = GPX_TYPE_WITHOUT_TIME.getTrk().get(0).getTrkseg().get(0).getTrkpt();

        final Double speedMax = SpeedMaxCalculator.fromWaypointList(waypointList);
        assertNull(speedMax);
    }

    @Test
    void fromTrackSegment() {
        final TrackSegment trackSegment = GPX_TYPE.getTrk().get(0).getTrkseg().get(0);

        Double speedMax = SpeedMaxCalculator.fromTrackSegment(trackSegment);
        assertTrue(speedMax > 0);
        assertEquals(18.85037290593836, speedMax);

        speedMax = SpeedMaxCalculator.fromTrackSegment(null);
        assertNull(speedMax);

        speedMax = SpeedMaxCalculator.fromTrackSegment(new TrackSegment());
        assertNull(speedMax);
    }

    @Test
    void fromTrackSegmentWithoutTime() {
        final TrackSegment trackSegment = GPX_TYPE_WITHOUT_TIME.getTrk().get(0).getTrkseg().get(0);

        final Double speedMax = SpeedMaxCalculator.fromTrackSegment(trackSegment);
        assertNull(speedMax);
    }

    @Test
    void fromTrackSegmentList() {
        final List<TrackSegment> trackSegmentList = GPX_TYPE.getTrk().get(0).getTrkseg();

        Double speedMax = SpeedMaxCalculator.fromTrackSegmentList(trackSegmentList);
        assertTrue(speedMax > 0);
        assertEquals(103.58302205126809, speedMax);

        speedMax = SpeedMaxCalculator.fromTrackSegmentList(null);
        assertNull(speedMax);

        speedMax = SpeedMaxCalculator.fromTrackSegmentList(new ArrayList<>());
        assertNull(speedMax);
    }

    @Test
    void fromTrackSegmentListWithoutTime() {
        final List<TrackSegment> trackSegmentList = GPX_TYPE_WITHOUT_TIME.getTrk().get(0).getTrkseg();

        final Double speedMax = SpeedMaxCalculator.fromTrackSegmentList(trackSegmentList);
        assertNull(speedMax);
    }

    @Test
    void fromTrack() {
        final Track track = GPX_TYPE.getTrk().get(0);

        Double speedMax = SpeedMaxCalculator.fromTrack(track);
        assertNotNull(speedMax);
        assertEquals(103.58302205126809, speedMax);

        speedMax = SpeedMaxCalculator.fromTrack(null);
        assertNull(speedMax);

        speedMax = SpeedMaxCalculator.fromTrack(new Track());
        assertNull(speedMax);
    }

    @Test
    void fromTrackWithoutTime() {
        final Track track = GPX_TYPE_WITHOUT_TIME.getTrk().get(0);

        final Double speedMax = SpeedMaxCalculator.fromTrack(track);
        assertNull(speedMax);
    }
}
