package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GeoCoordinateConverterTest {

    @Test
    public void testWaypointToDmsValidWaypoint() {
        final Waypoint waypoint = new Waypoint();
        waypoint.setLat(BigDecimal.valueOf(16.46643889));
        waypoint.setLon(BigDecimal.valueOf(-73.83330667));

        final String expected = "16°27'59.180\"N, 73°49'59.904\"W";
        final String actual = GeoCoordinateConverter.waypointToDms(waypoint);

        assertEquals(expected, actual);
    }

    @Test
    public void testWaypointToDmsNullWaypointThrowsException() {
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            GeoCoordinateConverter.waypointToDms(null);
        });
        assertEquals("Waypoint, Lat or Lon must not be null!", thrown.getMessage());
    }

    @Test
    public void testWaypointToDmsNullLatitudeThrowsException() {
        final Waypoint waypoint = new Waypoint();
        waypoint.setLat(null);
        waypoint.setLon(BigDecimal.valueOf(12.345));

        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            GeoCoordinateConverter.waypointToDms(waypoint);
        });
        assertEquals("Waypoint, Lat or Lon must not be null!", thrown.getMessage());
    }

    @Test
    public void testWaypointToDmsNullLongitudeThrowsException() {
        final Waypoint waypoint = new Waypoint();
        waypoint.setLat(BigDecimal.valueOf(12.345));
        waypoint.setLon(null);

        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            GeoCoordinateConverter.waypointToDms(waypoint);
        });
        assertEquals("Waypoint, Lat or Lon must not be null!", thrown.getMessage());
    }

    @Test
    public void testLatitudeDecimalToDmsNorth() {
        final double input = 16.46643889;
        final String expected = "16°27'59.180\"N";
        final String actual = GeoCoordinateConverter.latitudeDecimalToDms(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testLatitudeDecimalToDmsSouth() {
        final double input = -23.55052;
        final String expected = "23°33'01.872\"S";
        final String actual = GeoCoordinateConverter.latitudeDecimalToDms(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testLongitudeDecimalToDmsEast() {
        final double input = 77.5946;
        final String expected = "77°35'40.560\"E";
        final String actual = GeoCoordinateConverter.longitudeDecimalToDms(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testLongitudeDecimalToDmsWest() {
        final double input = -73.833089;
        final String expected = "73°49'59.120\"W";
        final String actual = GeoCoordinateConverter.longitudeDecimalToDms(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testLatitudeDecimalToDmsZero() {
        final double input = 0.0;
        final String expected = "0°0'00.000\"N";
        final String actual = GeoCoordinateConverter.latitudeDecimalToDms(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testLongitudeDecimalToDmsZero() {
        final double input = 0.0;
        final String expected = "0°0'00.000\"E";
        final String actual = GeoCoordinateConverter.longitudeDecimalToDms(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testDecimalToDmsGeneralLatitude() {
        final double input = 45.0;
        final String expected = "45°0'00.000\"N";
        final String actual = GeoCoordinateConverter.decimalToDms(input, true);
        assertEquals(expected, actual);
    }

    @Test
    public void testDecimalToDmsGeneralLongitude() {
        final double input = -120.5041666667;
        final String expected = "120°30'15.000\"W";
        final String actual = GeoCoordinateConverter.decimalToDms(input, false);
        assertEquals(expected, actual);
    }
}
