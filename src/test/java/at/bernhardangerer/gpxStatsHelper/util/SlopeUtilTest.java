package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SlopeUtilTest {

    @Test
    void calcSlopePercent() {
        final double slopePercent = SlopeUtil.calcSlopePercent(47.80743, 47.807343, 12.378228, 12.378138, 587, 588);
        assertTrue(slopePercent > 0);
        assertEquals(8.48917297494072, slopePercent);
    }

    @Test
    void calcSlopePercentFromWaypoints() {
        final Waypoint fromWaypoint = new Waypoint();
        fromWaypoint.setLat(BigDecimal.valueOf(47.80743));
        fromWaypoint.setLon(BigDecimal.valueOf(12.378228));
        fromWaypoint.setEle(BigDecimal.valueOf(587));

        final Waypoint toWaypoint = new Waypoint();
        toWaypoint.setLat(BigDecimal.valueOf(47.807343));
        toWaypoint.setLon(BigDecimal.valueOf(12.378138));
        toWaypoint.setEle(BigDecimal.valueOf(588));

        Double slopePercent = SlopeUtil.calcSlopePercent(fromWaypoint, toWaypoint);
        assertNotNull(slopePercent);
        assertTrue(slopePercent > 0);
        assertEquals(8.48917297494072, slopePercent);

        slopePercent = SlopeUtil.calcSlopePercent(null, null);
        assertNull(slopePercent);
    }
}
