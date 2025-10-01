package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.BoundingBox;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static at.bernhardangerer.gpxStatsHelper.util.WaypointUtil.createWaypoint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class GeographicExtentUtilTest {

    @Test
    void testFindBoundingReturnsCorrectBoundingBox() {
        final Waypoint waypoint1 = createWaypoint(47.80743, 12.378228, 587);
        final Waypoint waypoint2 = createWaypoint(47.807343, 12.378138, 588);
        final Waypoint waypoint3 = createWaypoint(47.807343, 12.378000, 589);

        final List<Waypoint> waypoints = Arrays.asList(waypoint1, waypoint2, waypoint3);

        final TrackSegment segment = new TrackSegment();
        segment.getTrkpt().addAll(waypoints);

        final Track track = new Track();
        track.getTrkseg().add(segment);

        final BoundingBox box = GeographicExtentUtil.findBounding(track);

        assertNotNull(box);
        assertEquals(new BigDecimal("47.807343"), box.minLat());
        assertEquals(new BigDecimal("12.378"), box.minLon());
        assertEquals(new BigDecimal("47.80743"), box.maxLat());
        assertEquals(new BigDecimal("12.378228"), box.maxLon());
    }

    @Test
    void testFindBoundingNullTrackReturnsNull() {
        assertNull(GeographicExtentUtil.findBounding(null));
    }

    @Test
    void testFindBoundingEmptySegmentsReturnsNull() {
        final Track track = new Track();
        track.getTrkseg().addAll(List.of());
        assertNull(GeographicExtentUtil.findBounding(track));
    }
}
