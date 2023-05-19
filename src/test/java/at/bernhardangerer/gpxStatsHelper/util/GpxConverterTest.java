package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.GpxType;
import com.topografix.model.TrkType;
import com.topografix.model.TrksegType;
import com.topografix.model.WptType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GpxConverterTest {

    @Test
    void convertGpx() {
        final String gpx = """
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
            </gpx>
            """;
        final GpxType gpxType = GpxConverter.convertGpxFromString(gpx);
        assertNotNull(gpxType);
        final List<TrkType> trkList = gpxType.getTrk();
        assertNotNull(trkList);
        assertFalse(trkList.isEmpty());
        assertEquals(1, trkList.size());
        final List<TrksegType> trksegList = trkList.get(0).getTrkseg();
        assertNotNull(trksegList);
        assertFalse(trksegList.isEmpty());
        assertEquals(1, trksegList.size());
        final List<WptType> trkptList = trksegList.get(0).getTrkpt();
        assertNotNull(trkptList);
        assertFalse(trkptList.isEmpty());
        assertEquals(6, trkptList.size());
        final WptType trkpt = trkptList.get(0);
        assertNotNull(trkpt);
        assertEquals(BigDecimal.valueOf(47.80743), trkpt.getLat());
        assertEquals(BigDecimal.valueOf(12.378228), trkpt.getLon());
        assertEquals(BigDecimal.valueOf(587), trkpt.getEle());
    }
}
