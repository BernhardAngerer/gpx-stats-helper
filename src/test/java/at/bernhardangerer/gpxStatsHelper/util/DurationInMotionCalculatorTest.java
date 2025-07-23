package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Gpx;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static at.bernhardangerer.gpxStatsHelper.util.WaypointUtil.createWaypoint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DurationInMotionCalculatorTest {

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
    private static final Gpx GPX_TYPE = GpxReader.fromString(GPX);

    @Test
    void fromWaypoints() {
        final Waypoint fromWaypoint = createWaypoint(47.80743, 12.378228, 587, LocalDateTime.of(2021, 9, 7, 16, 14, 16));
        final Waypoint toWaypoint = createWaypoint(47.807343, 12.378138, 588, LocalDateTime.of(2021, 9, 7, 16, 14, 20));

        Long durationInSec = DurationInMotionCalculator.fromWaypoints(fromWaypoint, toWaypoint);
        assertNotNull(durationInSec);
        assertTrue(durationInSec > 0);
        assertEquals(4, durationInSec);

        durationInSec = DurationInMotionCalculator.fromWaypoints(null, null);
        assertNull(durationInSec);

        durationInSec = DurationInMotionCalculator.fromWaypoints(new Waypoint(), new Waypoint());
        assertNull(durationInSec);
    }

    @Test
    void fromWaypointList() {
        final List<Waypoint> waypointList = GPX_TYPE.getTrk().get(0).getTrkseg().get(0).getTrkpt();

        Long durationInSec = DurationInMotionCalculator.fromWaypointList(waypointList);
        assertNotNull(durationInSec);
        assertTrue(durationInSec > 0);
        assertEquals(54, durationInSec);

        durationInSec = DurationInMotionCalculator.fromWaypointList(null);
        assertNull(durationInSec);

        durationInSec = DurationInMotionCalculator.fromWaypointList(new ArrayList<>());
        assertNull(durationInSec);
    }

    @Test
    void fromTrackSegment() {
        final TrackSegment trackSegment = GPX_TYPE.getTrk().get(0).getTrkseg().get(0);

        Long durationInSec = DurationInMotionCalculator.fromTrackSegment(trackSegment);
        assertNotNull(durationInSec);
        assertTrue(durationInSec > 0);
        assertEquals(54, durationInSec);

        durationInSec = DurationInMotionCalculator.fromTrackSegment(null);
        assertNull(durationInSec);

        durationInSec = DurationInMotionCalculator.fromTrackSegment(new TrackSegment());
        assertNull(durationInSec);
    }

    @Test
    void fromTrackSegmentList() {
        final List<TrackSegment> trackSegmentList = GPX_TYPE.getTrk().get(0).getTrkseg();

        Long durationInSec = DurationInMotionCalculator.fromTrackSegmentList(trackSegmentList);
        assertNotNull(durationInSec);
        assertTrue(durationInSec > 0);
        assertEquals(132, durationInSec);

        durationInSec = DurationInMotionCalculator.fromTrackSegmentList(null);
        assertNull(durationInSec);

        durationInSec = DurationInMotionCalculator.fromTrackSegmentList(new ArrayList<>());
        assertNull(durationInSec);
    }

    @Test
    void fromTrack() {
        final Track track = GPX_TYPE.getTrk().get(0);

        Long durationInSec = DurationInMotionCalculator.fromTrack(track);
        assertNotNull(durationInSec);
        assertTrue(durationInSec > 0);
        assertEquals(132, durationInSec);

        durationInSec = DurationInMotionCalculator.fromTrack(null);
        assertNull(durationInSec);

        durationInSec = DurationInMotionCalculator.fromTrack(new Track());
        assertNull(durationInSec);
    }
}
