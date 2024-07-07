package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.ElevationRange;
import com.topografix.model.Gpx;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ElevationRangeCalculatorTest {

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

    private static final Gpx GPX_TYPE = GpxConverter.convertGpxFromString(GPX);

    @Test
    void fromWaypointList() {
        final List<Waypoint> waypointList = GPX_TYPE.getTrk().get(0).getTrkseg().get(0).getTrkpt();

        ElevationRange range = ElevationRangeCalculator.fromWaypointList(waypointList);
        assertNotNull(range);
        assertEquals(BigDecimal.valueOf(588), range.getHighest().getEle());
        assertEquals(BigDecimal.valueOf(586), range.getLowest().getEle());

        range = ElevationRangeCalculator.fromWaypointList(null);
        assertNull(range);

        range = ElevationRangeCalculator.fromWaypointList(new ArrayList<>());
        assertNull(range);
    }

    @Test
    void fromTrackSegment() {
        final TrackSegment trackSegment = GPX_TYPE.getTrk().get(0).getTrkseg().get(0);

        ElevationRange range = ElevationRangeCalculator.fromTrackSegment(trackSegment);
        assertNotNull(range);
        assertEquals(BigDecimal.valueOf(588), range.getHighest().getEle());
        assertEquals(BigDecimal.valueOf(586), range.getLowest().getEle());

        range = ElevationRangeCalculator.fromTrackSegment(null);
        assertNull(range);

        range = ElevationRangeCalculator.fromTrackSegment(new TrackSegment());
        assertNull(range);
    }

    @Test
    void fromTrackSegmentList() {
        final List<TrackSegment> trackSegmentList = GPX_TYPE.getTrk().get(0).getTrkseg();

        ElevationRange range = ElevationRangeCalculator.fromTrackSegmentList(trackSegmentList);
        assertNotNull(range);
        assertEquals(BigDecimal.valueOf(598), range.getHighest().getEle());
        assertEquals(BigDecimal.valueOf(586), range.getLowest().getEle());

        range = ElevationRangeCalculator.fromTrackSegmentList(null);
        assertNull(range);

        range = ElevationRangeCalculator.fromTrackSegmentList(new ArrayList<>());
        assertNull(range);
    }

    @Test
    void fromTrack() {
        final Track track = GPX_TYPE.getTrk().get(0);

        ElevationRange range = ElevationRangeCalculator.fromTrack(track);
        assertNotNull(range);
        assertEquals(BigDecimal.valueOf(598), range.getHighest().getEle());
        assertEquals(BigDecimal.valueOf(586), range.getLowest().getEle());

        range = ElevationRangeCalculator.fromTrack(null);
        assertNull(range);

        range = ElevationRangeCalculator.fromTrack(new Track());
        assertNull(range);
    }
}
