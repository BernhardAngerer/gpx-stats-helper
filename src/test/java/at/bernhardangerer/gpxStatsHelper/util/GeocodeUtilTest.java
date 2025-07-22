package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import static at.bernhardangerer.gpxStatsHelper.util.WaypointUtil.createWaypoint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GeocodeUtilTest {

    @Test
    void testCreateOpenStreetMapUrlValidWaypointReturnsExpectedUrl() {
        final Waypoint waypoint = createWaypoint(47.80743, 12.378228, 587);

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
