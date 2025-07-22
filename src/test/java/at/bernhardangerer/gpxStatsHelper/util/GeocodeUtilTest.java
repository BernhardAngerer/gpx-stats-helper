package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GeocodeUtilTest {

    @Test
    void testCreateOpenStreetMapUrlValidWaypointReturnsExpectedUrl() {
        final Waypoint waypoint = new Waypoint();
        waypoint.setLat(BigDecimal.valueOf(47.80743));
        waypoint.setLon(BigDecimal.valueOf(12.378228));
        waypoint.setEle(BigDecimal.valueOf(587));

        final String url = GeocodeUtil.createOpenStreetMapUrl(waypoint);

        assertNotNull(url);
        assertTrue(url.contains("lat=47.80743"));
        assertTrue(url.contains("lon=12.378228"));
        assertTrue(url.contains("zoom=18"));
        assertTrue(url.contains("layer=poi"));
        assertTrue(url.startsWith("https://nominatim.openstreetmap.org/ui/reverse.html"));
    }

    @Test
    void testCreateOpenStreetMapUrlNullWaypointThrowsException() {
        final IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                GeocodeUtil.createOpenStreetMapUrl(null));
        assertEquals("Waypoint must not be null!", ex.getMessage());
    }
}
