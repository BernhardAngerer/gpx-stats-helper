package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.fixture.GpxFixture;
import com.topografix.model.Gpx;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static at.bernhardangerer.gpxStatsHelper.util.Constants.FORMATTER_WITHOUT_MILLIS;
import static at.bernhardangerer.gpxStatsHelper.util.WaypointUtil.createWaypoint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class WaypointUtilTest {

    private static final Gpx GPX_TYPE = GpxReader.fromString(GpxFixture.GPX);
    private static final Track TRACK = GPX_TYPE.getTrk().get(0);

    @Test
    void findFirstWaypoint() {
        Waypoint first = WaypointUtil.findFirstWaypoint(TRACK);
        assertNotNull(first);
        assertEquals(LocalDateTime.parse("2021-09-07T13:37:42Z", FORMATTER_WITHOUT_MILLIS), first.getTime());

        first = WaypointUtil.findFirstWaypoint(null);
        assertNull(first);

        first = WaypointUtil.findFirstWaypoint(new Track());
        assertNull(first);
    }

    @Test
    void findLastWaypoint() {
        Waypoint last = WaypointUtil.findLastWaypoint(TRACK);
        assertNotNull(last);
        assertEquals(LocalDateTime.parse("2021-09-07T16:14:16Z", FORMATTER_WITHOUT_MILLIS), last.getTime());

        last = WaypointUtil.findLastWaypoint(null);
        assertNull(last);

        last = WaypointUtil.findLastWaypoint(new Track());
        assertNull(last);
    }

    @Test
    void testFindFarthestWaypointReturnsCorrectWaypoint() {
        final Waypoint reference = createWaypoint(47.80743, 12.378228, 587);
        final Waypoint near = createWaypoint(47.807343, 12.378138, 588);
        final Waypoint far = createWaypoint(47.807343, 12.378000, 589);

        final TrackSegment segment = new TrackSegment();
        segment.getTrkpt().addAll(List.of(near, far));

        final Track track = new Track();
        track.getTrkseg().add(segment);

        final Waypoint result = WaypointUtil.findFarthestWaypoint(reference, track);

        assertNotNull(result);
        assertEquals(far.getLat(), result.getLat());
        assertEquals(far.getLon(), result.getLon());
    }

    @Test
    void testFindFarthestWaypointNullReferenceReturnsNull() {
        final Track track = new Track();
        track.getTrkseg().add(new TrackSegment());
        assertNull(WaypointUtil.findFarthestWaypoint(null, track));
    }

    @Test
    void testFindFarthestWaypointNullTrackReturnsNull() {
        final Waypoint reference = createWaypoint(47.80743, 12.378228, 587);

        assertNull(WaypointUtil.findFarthestWaypoint(reference, null));
    }

    @Test
    void formatWaypointWithElevation() {
        final Waypoint waypoint = createWaypoint(47.123456, 12.654321, 512);

        final String result = WaypointUtil.formatWaypoint(waypoint, true);

        assertEquals("Lat 47.123456, Lon 12.654321, Ele 512", result);
    }

    @Test
    void formatWaypointWithElevationFlagButNoValue() {
        final Waypoint waypoint = new Waypoint();
        waypoint.setLat(BigDecimal.valueOf(47.123456));
        waypoint.setLon(BigDecimal.valueOf(12.654321));

        final String result = WaypointUtil.formatWaypoint(waypoint, true);

        assertEquals("Lat 47.123456, Lon 12.654321", result);
    }

    @Test
    void formatWaypointWithoutElevation() {
        final Waypoint waypoint = createWaypoint(47.123456, 12.654321, 512);

        final String result = WaypointUtil.formatWaypoint(waypoint, false);

        assertEquals("Lat 47.123456, Lon 12.654321", result);
    }

    @Test
    void formatWaypointNullInput() {
        assertNull(WaypointUtil.formatWaypoint(null, true));
        assertNull(WaypointUtil.formatWaypoint(null, false));
    }
}
