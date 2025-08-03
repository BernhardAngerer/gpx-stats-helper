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

class DurationInMotionCalculatorTest {

    private static final Gpx GPX_TYPE = GpxReader.fromString(GpxFixture.GPX);
    private static final Gpx GPX_TYPE_WITHOUT_TIME = GpxReader.fromString(GpxFixture.GPX_WITHOUT_TIME);

    @Test
    void fromWaypoints() {
        final Waypoint fromWaypoint = createWaypoint(47.80743, 12.378228, 587, LocalDateTime.of(2021, 9, 7, 16, 14, 16));
        final Waypoint toWaypoint = createWaypoint(47.807343, 12.378138, 588, LocalDateTime.of(2021, 9, 7, 16, 14, 20));

        Long durationInSec = DurationInMotionCalculator.fromWaypoints(fromWaypoint, toWaypoint);
        assertNotNull(durationInSec);
        assertTrue(durationInSec > 0);
        assertEquals(4, durationInSec);

        durationInSec = DurationInMotionCalculator.fromWaypoints(null, null);
        assertNull(durationInSec);

        durationInSec = DurationInMotionCalculator.fromWaypoints(new Waypoint(), new Waypoint());
        assertNull(durationInSec);
    }

    @Test
    void fromWaypointsWithoutTime() {
        final Waypoint fromWaypoint = createWaypoint(47.80743, 12.378228, 587, null);
        final Waypoint toWaypoint = createWaypoint(47.807343, 12.378138, 588, null);

        final Long durationInSec = DurationInMotionCalculator.fromWaypoints(fromWaypoint, toWaypoint);
        assertNull(durationInSec);
    }

    @Test
    void fromWaypointList() {
        final List<Waypoint> waypointList = GPX_TYPE.getTrk().get(0).getTrkseg().get(0).getTrkpt();

        Long durationInSec = DurationInMotionCalculator.fromWaypointList(waypointList);
        assertNotNull(durationInSec);
        assertTrue(durationInSec > 0);
        assertEquals(54, durationInSec);

        durationInSec = DurationInMotionCalculator.fromWaypointList(null);
        assertNull(durationInSec);

        durationInSec = DurationInMotionCalculator.fromWaypointList(new ArrayList<>());
        assertNull(durationInSec);
    }

    @Test
    void fromWaypointListWithoutTime() {
        final List<Waypoint> waypointList = GPX_TYPE_WITHOUT_TIME.getTrk().get(0).getTrkseg().get(0).getTrkpt();

        final Long durationInSec = DurationInMotionCalculator.fromWaypointList(waypointList);
        assertNull(durationInSec);
    }

    @Test
    void fromTrackSegment() {
        final TrackSegment trackSegment = GPX_TYPE.getTrk().get(0).getTrkseg().get(0);

        Long durationInSec = DurationInMotionCalculator.fromTrackSegment(trackSegment);
        assertNotNull(durationInSec);
        assertTrue(durationInSec > 0);
        assertEquals(54, durationInSec);

        durationInSec = DurationInMotionCalculator.fromTrackSegment(null);
        assertNull(durationInSec);

        durationInSec = DurationInMotionCalculator.fromTrackSegment(new TrackSegment());
        assertNull(durationInSec);
    }

    @Test
    void fromTrackSegmentWithoutTime() {
        final TrackSegment trackSegment = GPX_TYPE_WITHOUT_TIME.getTrk().get(0).getTrkseg().get(0);

        final Long durationInSec = DurationInMotionCalculator.fromTrackSegment(trackSegment);
        assertNull(durationInSec);
    }

    @Test
    void fromTrackSegmentList() {
        final List<TrackSegment> trackSegmentList = GPX_TYPE.getTrk().get(0).getTrkseg();

        Long durationInSec = DurationInMotionCalculator.fromTrackSegmentList(trackSegmentList);
        assertNotNull(durationInSec);
        assertTrue(durationInSec > 0);
        assertEquals(132, durationInSec);

        durationInSec = DurationInMotionCalculator.fromTrackSegmentList(null);
        assertNull(durationInSec);

        durationInSec = DurationInMotionCalculator.fromTrackSegmentList(new ArrayList<>());
        assertNull(durationInSec);
    }

    @Test
    void fromTrackSegmentListWithoutTime() {
        final List<TrackSegment> trackSegmentList = GPX_TYPE_WITHOUT_TIME.getTrk().get(0).getTrkseg();

        final Long durationInSec = DurationInMotionCalculator.fromTrackSegmentList(trackSegmentList);
        assertNull(durationInSec);
    }

    @Test
    void fromTrack() {
        final Track track = GPX_TYPE.getTrk().get(0);

        Long durationInSec = DurationInMotionCalculator.fromTrack(track);
        assertNotNull(durationInSec);
        assertTrue(durationInSec > 0);
        assertEquals(132, durationInSec);

        durationInSec = DurationInMotionCalculator.fromTrack(null);
        assertNull(durationInSec);

        durationInSec = DurationInMotionCalculator.fromTrack(new Track());
        assertNull(durationInSec);
    }

    @Test
    void fromTrackWithoutTime() {
        final Track track = GPX_TYPE_WITHOUT_TIME.getTrk().get(0);

        final Long durationInSec = DurationInMotionCalculator.fromTrack(track);
        assertNull(durationInSec);
    }
}
