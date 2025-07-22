package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrackUtilTest {

    @Test
    void testCountWaypointsReturnsCorrectCount() {
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
}
