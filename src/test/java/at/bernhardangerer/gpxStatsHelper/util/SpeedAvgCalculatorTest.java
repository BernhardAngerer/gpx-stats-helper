package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.fixture.GpxFixture;
import at.bernhardangerer.gpxStatsHelper.model.SpeedMetrics;
import com.topografix.model.Gpx;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static at.bernhardangerer.gpxStatsHelper.util.SpeedUtil.calculateSpeed;
import static at.bernhardangerer.gpxStatsHelper.util.WaypointUtil.createWaypoint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpeedAvgCalculatorTest {

    private static final Gpx GPX_TYPE = GpxReader.fromString(GpxFixture.GPX);
    private static final Gpx GPX_TYPE_WITHOUT_TIME = GpxReader.fromString(GpxFixture.GPX_WITHOUT_TIME);

    @Test
    void fromWaypoints() {
        final Waypoint fromWaypoint = createWaypoint(47.80743, 12.378228, 587, LocalDateTime.of(2021, 9, 7, 16, 14, 16));
        final Waypoint toWaypoint = createWaypoint(47.807343, 12.378138, 588, LocalDateTime.of(2021, 9, 7, 16, 14, 20));

        SpeedMetrics speedMetrics = SpeedAvgCalculator.fromWaypoints(fromWaypoint, toWaypoint);
        assertNotNull(speedMetrics);
        assertTrue(speedMetrics.getDistance() > 0);
        assertTrue(speedMetrics.getDuration() > 0);
        assertEquals(10.63987214678773, calculateSpeed(speedMetrics));

        speedMetrics = SpeedAvgCalculator.fromWaypoints(null, null);
        assertNull(speedMetrics);

        speedMetrics = SpeedAvgCalculator.fromWaypoints(new Waypoint(), new Waypoint());
        assertNull(speedMetrics);
    }

    @Test
    void fromWaypointsWithoutTime() {
        final Waypoint fromWaypoint = createWaypoint(47.80743, 12.378228, 587, null);
        final Waypoint toWaypoint = createWaypoint(47.807343, 12.378138, 588, null);

        final SpeedMetrics speedMetrics = SpeedAvgCalculator.fromWaypoints(fromWaypoint, toWaypoint);
        assertNull(speedMetrics);
    }

    @Test
    void fromWaypointList() {
        final List<Waypoint> waypointList = GPX_TYPE.getTrk().get(0).getTrkseg().get(0).getTrkpt();

        SpeedMetrics speedMetrics = SpeedAvgCalculator.fromWaypointList(waypointList);
        assertNotNull(speedMetrics);
        assertTrue(speedMetrics.getDistance() > 0);
        assertTrue(speedMetrics.getDuration() > 0);
        assertEquals(4.675064466160021, calculateSpeed(speedMetrics));

        speedMetrics = SpeedAvgCalculator.fromWaypointList(null);
        assertNull(speedMetrics);

        speedMetrics = SpeedAvgCalculator.fromWaypointList(new ArrayList<>());
        assertNull(speedMetrics);
    }

    @Test
    void fromWaypointListWithoutTime() {
        final List<Waypoint> waypointList = GPX_TYPE_WITHOUT_TIME.getTrk().get(0).getTrkseg().get(0).getTrkpt();

        final SpeedMetrics speedMetrics = SpeedAvgCalculator.fromWaypointList(waypointList);
        assertNull(speedMetrics);
    }

    @Test
    void fromTrackSegment() {
        final TrackSegment trackSegment = GPX_TYPE.getTrk().get(0).getTrkseg().get(0);

        SpeedMetrics speedMetrics = SpeedAvgCalculator.fromTrackSegment(trackSegment);
        assertNotNull(speedMetrics);
        assertTrue(speedMetrics.getDistance() > 0);
        assertTrue(speedMetrics.getDuration() > 0);
        assertEquals(4.675064466160021, calculateSpeed(speedMetrics));

        speedMetrics = SpeedAvgCalculator.fromTrackSegment(null);
        assertNull(speedMetrics);

        speedMetrics = SpeedAvgCalculator.fromTrackSegment(new TrackSegment());
        assertNull(speedMetrics);
    }

    @Test
    void fromTrackSegmentWithoutTime() {
        final TrackSegment trackSegment = GPX_TYPE_WITHOUT_TIME.getTrk().get(0).getTrkseg().get(0);

        final SpeedMetrics speedMetrics = SpeedAvgCalculator.fromTrackSegment(trackSegment);
        assertNull(speedMetrics);
    }

    @Test
    void fromTrackSegmentList() {
        final List<TrackSegment> trackSegmentList = GPX_TYPE.getTrk().get(0).getTrkseg();

        SpeedMetrics speedMetrics = SpeedAvgCalculator.fromTrackSegmentList(trackSegmentList);
        assertNotNull(speedMetrics);
        assertTrue(speedMetrics.getDistance() > 0);
        assertTrue(speedMetrics.getDuration() > 0);
        assertEquals(7.444563520907939, calculateSpeed(speedMetrics));

        speedMetrics = SpeedAvgCalculator.fromTrackSegmentList(null);
        assertNull(speedMetrics);

        speedMetrics = SpeedAvgCalculator.fromTrackSegmentList(new ArrayList<>());
        assertNull(speedMetrics);
    }

    @Test
    void fromTrackSegmentListWithoutTime() {
        final List<TrackSegment> trackSegmentList = GPX_TYPE_WITHOUT_TIME.getTrk().get(0).getTrkseg();

        final SpeedMetrics speedMetrics = SpeedAvgCalculator.fromTrackSegmentList(trackSegmentList);
        assertNull(speedMetrics);
    }

    @Test
    void fromTrack() {
        final Track track = GPX_TYPE.getTrk().get(0);

        Double averageSpeed = SpeedAvgCalculator.fromTrack(track);
        assertNotNull(averageSpeed);
        assertEquals(7.444563520907939, averageSpeed);

        averageSpeed = SpeedAvgCalculator.fromTrack(null);
        assertNull(averageSpeed);

        averageSpeed = SpeedAvgCalculator.fromTrack(new Track());
        assertNull(averageSpeed);
    }

    @Test
    void fromTrackWithoutTime() {
        final Track track = GPX_TYPE_WITHOUT_TIME.getTrk().get(0);

        final Double averageSpeed = SpeedAvgCalculator.fromTrack(track);
        assertNull(averageSpeed);
    }
}
