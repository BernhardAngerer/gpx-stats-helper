package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Track;
import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static at.bernhardangerer.gpxStatsHelper.util.LocalDateTimeAdapter.DATE_TIME_FORMATTER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class WaypointUtilTest {

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
            + "<trkpt lat=\"47.807346\" lon=\"12.378055\"><ele>596</ele><time>2021-09-07T16:14:16Z</time> </trkpt>\n"
            + "</trkseg>\n"
            + "</trk>\n"
            + "</gpx>";
    private static final Track TRACK = GpxConverter.convertGpxFromString(GPX).getTrk().get(0);

    @Test
    void findFirstWaypoint() {
        Waypoint first = WaypointUtil.findFirstWaypoint(TRACK);
        assertNotNull(first);
        assertEquals(LocalDateTime.parse("2021-09-07T13:37:42Z", DATE_TIME_FORMATTER), first.getTime());

        first = WaypointUtil.findFirstWaypoint(null);
        assertNull(first);

        first = WaypointUtil.findFirstWaypoint(new Track());
        assertNull(first);
    }

    @Test
    void findLastWaypoint() {
        Waypoint last = WaypointUtil.findLastWaypoint(TRACK);
        assertNotNull(last);
        assertEquals(LocalDateTime.parse("2021-09-07T16:14:16Z", DATE_TIME_FORMATTER), last.getTime());

        last = WaypointUtil.findLastWaypoint(null);
        assertNull(last);

        last = WaypointUtil.findLastWaypoint(new Track());
        assertNull(last);
    }
}
