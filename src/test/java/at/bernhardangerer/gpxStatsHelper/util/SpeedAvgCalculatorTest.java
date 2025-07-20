package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.SpeedMetrics;
import com.topografix.model.Gpx;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static at.bernhardangerer.gpxStatsHelper.util.SpeedUtil.calculateSpeed;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpeedAvgCalculatorTest {

    private static final String GPX =
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
            + "<gpx version=\"1.1\" creator=\"me\"\n"
            + "xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1\n"
            + "http://www.topografix.com/GPX/1/1/gpx.xsd\n"
            + "http://www.garmin.com/xmlschemas/GpxExtensions/v3\n"
            + "http://www.garmin.com/xmlschemas/GpxExtensionsv3.xsd\n"
            + "http://www.garmin.com/xmlschemas/TrackPointExtension/v1\n"
            + "http://www.garmin.com/xmlschemas/TrackPointExtensionv1.xsd\"\n"
            + "xmlns=\"http://www.topografix.com/GPX/1/1\"\n"
            + "xmlns:gpxtpx=\"http://www.garmin.com/xmlschemas/TrackPointExtension/v1\"\n"
            + "xmlns:gpxx=\"http://www.garmin.com/xmlschemas/GpxExtensions/v3\"\n"
            + "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
            + "<metadata>\n"
            + "<link>https://www.my-url.at</link></metadata>\n"
            + "<trk>\n"
            + "<trkseg>\n"
            + "<trkpt lat=\"47.80743\" lon=\"12.378228\"><ele>587</ele><time>2021-09-07T13:37:42Z</time> </trkpt>\n"
            + "<trkpt lat=\"47.807343\" lon=\"12.378138\"><ele>588</ele><time>2021-09-07T13:38:18Z</time> </trkpt>\n"
            + "<trkpt lat=\"47.807335\" lon=\"12.378\"><ele>588</ele><time>2021-09-07T13:38:25Z</time> </trkpt>\n"
            + "<trkpt lat=\"47.807377\" lon=\"12.377702\"><ele>588</ele><time>2021-09-07T13:38:31Z</time> </trkpt>\n"
            + "<trkpt lat=\"47.807346\" lon=\"12.37751\"><ele>588</ele><time>2021-09-07T13:38:34Z</time> </trkpt>\n"
            + "<trkpt lat=\"47.807312\" lon=\"12.377382\"><ele>586</ele><time>2021-09-07T13:38:36Z</time> </trkpt>\n"
            + "</trkseg>\n"
            + "<trkseg>\n"
            + "<trkpt lat=\"47.806938\" lon=\"12.378183\"><ele>598</ele><time>2021-09-07T16:10:27Z</time> </trkpt>\n"
            + "<trkpt lat=\"47.806793\" lon=\"12.378217\"><ele>598</ele><time>2021-09-07T16:11:20Z</time> </trkpt>\n"
            + "<trkpt lat=\"47.806873\" lon=\"12.378148\"><ele>598</ele><time>2021-09-07T16:13:51Z</time> </trkpt>\n"
            + "<trkpt lat=\"47.806969\" lon=\"12.37816\"><ele>598</ele><time>2021-09-07T16:13:55Z</time> </trkpt>\n"
            + "<trkpt lat=\"47.80706\" lon=\"12.378138\"><ele>598</ele><time>2021-09-07T16:14:00Z</time> </trkpt>\n"
            + "<trkpt lat=\"47.807156\" lon=\"12.378108\"><ele>598</ele><time>2021-09-07T16:14:06Z</time> </trkpt>\n"
            + "<trkpt lat=\"47.807251\" lon=\"12.378079\"><ele>598</ele><time>2021-09-07T16:14:11Z</time> </trkpt>\n"
            + "<trkpt lat=\"47.807346\" lon=\"12.38\"><ele>596</ele><time>2021-09-07T16:14:16Z</time> </trkpt>\n"
            + "</trkseg>\n"
            + "</trk>\n"
            + "</gpx>";
    private static final Gpx GPX_TYPE = GpxConverter.convertGpxFromString(GPX);

    @Test
    void fromWaypoints() {
        final Waypoint fromWaypoint = new Waypoint();
        fromWaypoint.setLat(BigDecimal.valueOf(47.80743));
        fromWaypoint.setLon(BigDecimal.valueOf(12.378228));
        fromWaypoint.setEle(BigDecimal.valueOf(587));
        fromWaypoint.setTime(LocalDateTime.of(2021, 9, 7, 16, 14, 16));
        final Waypoint toWaypoint = new Waypoint();
        toWaypoint.setLat(BigDecimal.valueOf(47.807343));
        toWaypoint.setLon(BigDecimal.valueOf(12.378138));
        toWaypoint.setEle(BigDecimal.valueOf(588));
        toWaypoint.setTime(LocalDateTime.of(2021, 9, 7, 16, 14, 20));

        SpeedMetrics speedMetrics = SpeedAvgCalculator.fromWaypoints(fromWaypoint, toWaypoint);
        assertNotNull(speedMetrics);
        assertTrue(speedMetrics.getDistance() > 0);
        assertTrue(speedMetrics.getDuration() > 0);
        assertEquals(10.63987214678773, calculateSpeed(speedMetrics));

        speedMetrics = SpeedAvgCalculator.fromWaypoints(null, null);
        assertNull(speedMetrics);

        speedMetrics = SpeedAvgCalculator.fromWaypoints(new Waypoint(), new Waypoint());
        assertNull(speedMetrics);
    }

    @Test
    void fromWaypointList() {
        final List<Waypoint> waypointList = GPX_TYPE.getTrk().get(0).getTrkseg().get(0).getTrkpt();

        SpeedMetrics speedMetrics = SpeedAvgCalculator.fromWaypointList(waypointList);
        assertNotNull(speedMetrics);
        assertTrue(speedMetrics.getDistance() > 0);
        assertTrue(speedMetrics.getDuration() > 0);
        assertEquals(4.675064466160021, calculateSpeed(speedMetrics));

        speedMetrics = SpeedAvgCalculator.fromWaypointList(null);
        assertNull(speedMetrics);

        speedMetrics = SpeedAvgCalculator.fromWaypointList(new ArrayList<>());
        assertNull(speedMetrics);
    }

    @Test
    void fromTrackSegment() {
        final TrackSegment trackSegment = GPX_TYPE.getTrk().get(0).getTrkseg().get(0);

        SpeedMetrics speedMetrics = SpeedAvgCalculator.fromTrackSegment(trackSegment);
        assertNotNull(speedMetrics);
        assertTrue(speedMetrics.getDistance() > 0);
        assertTrue(speedMetrics.getDuration() > 0);
        assertEquals(4.675064466160021, calculateSpeed(speedMetrics));

        speedMetrics = SpeedAvgCalculator.fromTrackSegment(null);
        assertNull(speedMetrics);

        speedMetrics = SpeedAvgCalculator.fromTrackSegment(new TrackSegment());
        assertNull(speedMetrics);
    }

    @Test
    void fromTrackSegmentList() {
        final List<TrackSegment> trackSegmentList = GPX_TYPE.getTrk().get(0).getTrkseg();

        SpeedMetrics speedMetrics = SpeedAvgCalculator.fromTrackSegmentList(trackSegmentList);
        assertNotNull(speedMetrics);
        assertTrue(speedMetrics.getDistance() > 0);
        assertTrue(speedMetrics.getDuration() > 0);
        assertEquals(7.444563520907939, calculateSpeed(speedMetrics));

        speedMetrics = SpeedAvgCalculator.fromTrackSegmentList(null);
        assertNull(speedMetrics);

        speedMetrics = SpeedAvgCalculator.fromTrackSegmentList(new ArrayList<>());
        assertNull(speedMetrics);
    }

    @Test
    void fromTrack() {
        final Track track = GPX_TYPE.getTrk().get(0);

        Double averageSpeed = SpeedAvgCalculator.fromTrack(track);
        assertNotNull(averageSpeed);
        assertEquals(7.444563520907939, averageSpeed);

        averageSpeed = SpeedAvgCalculator.fromTrack(null);
        assertNull(averageSpeed);

        averageSpeed = SpeedAvgCalculator.fromTrack(new Track());
        assertNotNull(averageSpeed);
        assertEquals(0.0, averageSpeed);
    }
}
