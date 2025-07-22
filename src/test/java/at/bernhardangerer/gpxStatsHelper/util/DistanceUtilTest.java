package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import static at.bernhardangerer.gpxStatsHelper.util.WaypointUtil.createWaypoint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DistanceUtilTest {

    @Test
    void calcDistance() {
        final double distance = DistanceUtil.calcDistance(47.80743, 47.807343, 12.378228, 12.378138, 587, 588);
        assertTrue(distance > 0);
        assertEquals(11.822080163097478, distance);
    }

    @Test
    void calcDistanceFromWaypoints() {
        final Waypoint fromWaypoint = createWaypoint(47.80743, 12.378228, 587);
        final Waypoint toWaypoint = createWaypoint(47.807343, 12.378138, 588);

        Double distance = DistanceUtil.calcDistance(fromWaypoint, toWaypoint);
        assertNotNull(distance);
        assertTrue(distance > 0);
        assertEquals(11.822080163097478, distance);

        distance = DistanceUtil.calcDistance(null, null);
        assertNull(distance);
    }
}
