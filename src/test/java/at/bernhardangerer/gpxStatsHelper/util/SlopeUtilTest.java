package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Waypoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static at.bernhardangerer.gpxStatsHelper.util.WaypointUtil.createWaypoint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SlopeUtilTest {

    private List<Waypoint> waypoints;

    @BeforeEach
    void init() {
        final Waypoint waypoint1 = createWaypoint(47.80743, 12.378228, 587);
        final Waypoint waypoint2 = createWaypoint(47.807343, 12.378138, 588);
        final Waypoint waypoint3 = createWaypoint(47.807343, 12.378000, 589);

        waypoints = Arrays.asList(waypoint1, waypoint2, waypoint3);
    }

    @Test
    void calcSlopePercentPositive() {
        final Waypoint waypoint1 = waypoints.get(0);
        final Waypoint waypoint2 = waypoints.get(1);

        final double slopePercent = SlopeUtil.calcSlopePercent(
                waypoint1.getLat().doubleValue(), waypoint2.getLat().doubleValue(),
                waypoint1.getLon().doubleValue(), waypoint2.getLon().doubleValue(),
                waypoint1.getEle().doubleValue(), waypoint2.getEle().doubleValue());
        assertTrue(slopePercent > 0);
        assertEquals(8.48917297494072, slopePercent);
    }

    @Test
    void calcSlopePercentNegative() {
        final Waypoint waypoint1 = waypoints.get(1);
        final Waypoint waypoint2 = waypoints.get(0);

        final double slopePercent = SlopeUtil.calcSlopePercent(
                waypoint1.getLat().doubleValue(), waypoint2.getLat().doubleValue(),
                waypoint1.getLon().doubleValue(), waypoint2.getLon().doubleValue(),
                waypoint1.getEle().doubleValue(), waypoint2.getEle().doubleValue());
        assertTrue(slopePercent < 0);
        assertEquals(-8.48917297494072, slopePercent);
    }

    @Test
    void calcSlopePercentFromWaypoints() {
        Double slopePercent = SlopeUtil.calcSlopePercent(waypoints.get(0), waypoints.get(1));
        assertNotNull(slopePercent);
        assertTrue(slopePercent > 0);
        assertEquals(8.48917297494072, slopePercent);

        slopePercent = SlopeUtil.calcSlopePercent(null, null);
        assertNull(slopePercent);
    }
}
