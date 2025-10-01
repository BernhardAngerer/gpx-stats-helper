package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Gpx;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GpxReaderTest {

    @Test
    void fromStringSuccess() {
        final String gpxString =
                """
                        <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                        <gpx version="1.1" creator="me"
                        xsi:schemaLocation="http://www.topografix.com/GPX/1/1
                        http://www.topografix.com/GPX/1/1/gpx.xsd
                        http://www.garmin.com/xmlschemas/GpxExtensions/v3
                        http://www.garmin.com/xmlschemas/GpxExtensionsv3.xsd
                        http://www.garmin.com/xmlschemas/TrackPointExtension/v1
                        http://www.garmin.com/xmlschemas/TrackPointExtensionv1.xsd"
                        xmlns="http://www.topografix.com/GPX/1/1"
                        xmlns:gpxtpx="http://www.garmin.com/xmlschemas/TrackPointExtension/v1"
                        xmlns:gpxx="http://www.garmin.com/xmlschemas/GpxExtensions/v3"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                        <metadata>
                        <link>https://www.my-url.at</link></metadata>
                        <trk>
                        <trkseg>
                        <trkpt lat="47.80743" lon="12.378228"><ele>587</ele><time>2021-09-07T13:37:42Z</time> </trkpt>
                        <trkpt lat="47.807343" lon="12.378138"><ele>588</ele><time>2021-09-07T13:38:18Z</time> </trkpt>
                        <trkpt lat="47.807335" lon="12.378"><ele>588</ele><time>2021-09-07T13:38:25Z</time> </trkpt>
                        <trkpt lat="47.807377" lon="12.377702"><ele>588</ele><time>2021-09-07T13:38:31Z</time> </trkpt>
                        <trkpt lat="47.807346" lon="12.37751"><ele>588</ele><time>2021-09-07T13:38:34Z</time> </trkpt>
                        <trkpt lat="47.807312" lon="12.377382"><ele>588</ele><time>2021-09-07T13:38:36Z</time> </trkpt>
                        </trkseg>
                        </trk>
                        </gpx>""";
        final Gpx gpx = GpxReader.fromString(gpxString);
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

    @Test
    void fromStringShouldThrowOnNullInput() {
        assertThrows(IllegalArgumentException.class, () -> GpxReader.fromString(null));
    }

    @Test
    void fromStringShouldThrowOnEmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> GpxReader.fromString(""));
    }

    @Test
    void fromFileShouldThrowOnNullFile() {
        assertThrows(IllegalArgumentException.class, () -> GpxReader.fromFile((File) null));
    }

    @Test
    void fromClasspathShouldThrowOnInvalidPath() {
        assertThrows(IllegalArgumentException.class, () -> GpxReader.fromFile("nonexistent/file.gpx"));
    }

    @Test
    void fromClasspathShouldThrowOnNullPath() {
        assertThrows(IllegalArgumentException.class, () -> GpxReader.fromFile((String) null));
    }

    @Test
    void fromClasspathShouldThrowOnEmptyPath() {
        assertThrows(IllegalArgumentException.class, () -> GpxReader.fromFile(""));
    }
}
