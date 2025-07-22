package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.BoundingBox;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class GeographicExtentUtilTest {

    @Test
    void testFindBoundingReturnsCorrectBoundingBox() {
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setLat(BigDecimal.valueOf(47.80743));
        waypoint1.setLon(BigDecimal.valueOf(12.378228));
        waypoint1.setEle(BigDecimal.valueOf(587));

        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setLat(BigDecimal.valueOf(47.807343));
        waypoint2.setLon(BigDecimal.valueOf(12.378138));
        waypoint2.setEle(BigDecimal.valueOf(588));

        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setLat(BigDecimal.valueOf(47.807343));
        waypoint3.setLon(BigDecimal.valueOf(12.378000));
        waypoint3.setEle(BigDecimal.valueOf(589));

        final List<Waypoint> waypoints = Arrays.asList(waypoint1, waypoint2, waypoint3);

        final TrackSegment segment = new TrackSegment();
        segment.getTrkpt().addAll(waypoints);

        final Track track = new Track();
        track.getTrkseg().add(segment);

        final BoundingBox box = GeographicExtentUtil.findBounding(track);

        assertNotNull(box);
        assertEquals(new BigDecimal("47.807343"), box.getMinLat());
        assertEquals(new BigDecimal("12.378"), box.getMinLon());
        assertEquals(new BigDecimal("47.80743"), box.getMaxLat());
        assertEquals(new BigDecimal("12.378228"), box.getMaxLon());
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
