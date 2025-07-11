package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
        final Waypoint fromWaypoint = new Waypoint();
        fromWaypoint.setLat(BigDecimal.valueOf(47.80743));
        fromWaypoint.setLon(BigDecimal.valueOf(12.378228));
        fromWaypoint.setEle(BigDecimal.valueOf(587));

        final Waypoint toWaypoint = new Waypoint();
        toWaypoint.setLat(BigDecimal.valueOf(47.807343));
        toWaypoint.setLon(BigDecimal.valueOf(12.378138));
        toWaypoint.setEle(BigDecimal.valueOf(588));

        Double distance = DistanceUtil.calcDistance(fromWaypoint, toWaypoint);
        assertNotNull(distance);
        assertTrue(distance > 0);
        assertEquals(11.822080163097478, distance);

        distance = DistanceUtil.calcDistance(null, null);
        assertNull(distance);
    }
}
