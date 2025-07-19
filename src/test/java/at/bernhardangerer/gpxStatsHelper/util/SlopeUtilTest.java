package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Waypoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SlopeUtilTest {

    private List<Waypoint> waypoints;

    @BeforeEach
    void init() {
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
