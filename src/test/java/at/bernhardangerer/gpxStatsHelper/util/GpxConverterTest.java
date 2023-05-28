package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Gpx;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GpxConverterTest {

    @Test
    void convertGpx() {
        final String gpxString =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<gpx version=\"1.1\" creator=\"me\"\n" +
                "xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1\n" +
                "http://www.topografix.com/GPX/1/1/gpx.xsd\n" +
                "http://www.garmin.com/xmlschemas/GpxExtensions/v3\n" +
                "http://www.garmin.com/xmlschemas/GpxExtensionsv3.xsd\n" +
                "http://www.garmin.com/xmlschemas/TrackPointExtension/v1\n" +
                "http://www.garmin.com/xmlschemas/TrackPointExtensionv1.xsd\"\n" +
                "xmlns=\"http://www.topografix.com/GPX/1/1\"\n" +
                "xmlns:gpxtpx=\"http://www.garmin.com/xmlschemas/TrackPointExtension/v1\"\n" +
                "xmlns:gpxx=\"http://www.garmin.com/xmlschemas/GpxExtensions/v3\"\n" +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "<metadata>\n" +
                "<link>https://www.my-url.at</link></metadata>\n" +
                "<trk>\n" +
                "<trkseg>\n" +
                "<trkpt lat=\"47.80743\" lon=\"12.378228\"><ele>587</ele><time>2021-09-07T13:37:42Z</time> </trkpt>\n" +
                "<trkpt lat=\"47.807343\" lon=\"12.378138\"><ele>588</ele><time>2021-09-07T13:38:18Z</time> </trkpt>\n" +
                "<trkpt lat=\"47.807335\" lon=\"12.378\"><ele>588</ele><time>2021-09-07T13:38:25Z</time> </trkpt>\n" +
                "<trkpt lat=\"47.807377\" lon=\"12.377702\"><ele>588</ele><time>2021-09-07T13:38:31Z</time> </trkpt>\n" +
                "<trkpt lat=\"47.807346\" lon=\"12.37751\"><ele>588</ele><time>2021-09-07T13:38:34Z</time> </trkpt>\n" +
                "<trkpt lat=\"47.807312\" lon=\"12.377382\"><ele>588</ele><time>2021-09-07T13:38:36Z</time> </trkpt>\n" +
                "</trkseg>\n" +
                "</trk>\n" +
                "</gpx>";
        final Gpx gpx = GpxConverter.convertGpxFromString(gpxString);
        assertNotNull(gpx);

        final List<Track> trackList = gpx.getTrk();
        assertNotNull(trackList);
        assertFalse(trackList.isEmpty());
        assertEquals(1, trackList.size());

        final List<TrackSegment> trackSegmentList = trackList.get(0).getTrkseg();
        assertNotNull(trackSegmentList);
        assertFalse(trackSegmentList.isEmpty());
        assertEquals(1, trackSegmentList.size());

        final List<Waypoint> waypointList = trackSegmentList.get(0).getTrkpt();
        assertNotNull(waypointList);
        assertFalse(waypointList.isEmpty());
        assertEquals(6, waypointList.size());

        final Waypoint waypoint = waypointList.get(0);
        assertNotNull(waypoint);
        assertEquals(BigDecimal.valueOf(47.80743), waypoint.getLat());
        assertEquals(BigDecimal.valueOf(12.378228), waypoint.getLon());
        assertEquals(BigDecimal.valueOf(587), waypoint.getEle());
    }
}
