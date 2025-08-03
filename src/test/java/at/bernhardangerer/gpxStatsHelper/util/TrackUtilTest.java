package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.fixture.GpxFixture;
import com.topografix.model.Gpx;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.util.List;

import static at.bernhardangerer.gpxStatsHelper.util.WaypointUtil.createWaypoint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrackUtilTest {

    private static final Gpx GPX_TYPE = GpxReader.fromString(GpxFixture.GPX);
    private static final Gpx GPX_TYPE_WITHOUT_ELEVATION = GpxReader.fromString(GpxFixture.GPX_WITHOUT_ELEVATION);
    private static final Gpx GPX_TYPE_WITHOUT_TIME = GpxReader.fromString(GpxFixture.GPX_WITHOUT_TIME);

    @Test
    void testCountWaypointsReturnsCorrectCount() {
        final Waypoint waypoint1 = createWaypoint(47.80743, 12.378228, 587);
        final Waypoint waypoint2 = createWaypoint(47.807343, 12.378138, 588);
        final Waypoint waypoint3 = createWaypoint(47.807343, 12.378000, 589);

        final TrackSegment seg1 = new TrackSegment();
        seg1.getTrkpt().addAll(List.of(waypoint1, waypoint2));

        final TrackSegment seg2 = new TrackSegment();
        seg2.getTrkpt().add(waypoint3);

        final Track track = new Track();
        track.getTrkseg().addAll(List.of(seg1, seg2));

        final long count = TrackUtil.countWaypoints(track);

        assertEquals(3, count);
    }

    @Test
    void testCountWaypointsNullTrackReturnsZero() {
        assertEquals(0, TrackUtil.countWaypoints(null));
    }

    @Test
    void testCountWaypointsEmptyTrackReturnsZero() {
        final Track track = new Track();
        track.getTrkseg().addAll(List.of());
        assertEquals(0, TrackUtil.countWaypoints(track));
    }

    @Test
    void allWaypointsWithTime() {
        assertTrue(TrackUtil.allWaypointsWithTime(GPX_TYPE.getTrk().get(0)));
        assertTrue(TrackUtil.allWaypointsWithTime(GPX_TYPE_WITHOUT_ELEVATION.getTrk().get(0)));
        assertFalse(TrackUtil.allWaypointsWithTime(GPX_TYPE_WITHOUT_TIME.getTrk().get(0)));
    }

    @Test
    void allWaypointsWithElevation() {
        assertTrue(TrackUtil.allWaypointsWithElevation(GPX_TYPE.getTrk().get(0)));
        assertFalse(TrackUtil.allWaypointsWithElevation(GPX_TYPE_WITHOUT_ELEVATION.getTrk().get(0)));
        assertTrue(TrackUtil.allWaypointsWithElevation(GPX_TYPE_WITHOUT_TIME.getTrk().get(0)));
    }
}
