package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.GpxType;
import com.topografix.model.TrkType;
import com.topografix.model.TrksegType;
import com.topografix.model.WptType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DistanceTotalCalculatorTest {

    private static final String GPX = """
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
            <trkseg>
            <trkpt lat="47.806938" lon="12.378183"><ele>598</ele><time>2021-09-07T16:10:27Z</time> </trkpt>
            <trkpt lat="47.806793" lon="12.378217"><ele>598</ele><time>2021-09-07T16:11:20Z</time> </trkpt>
            <trkpt lat="47.806873" lon="12.378148"><ele>598</ele><time>2021-09-07T16:13:51Z</time> </trkpt>
            <trkpt lat="47.806969" lon="12.37816"><ele>598</ele><time>2021-09-07T16:13:55Z</time> </trkpt>
            <trkpt lat="47.80706" lon="12.378138"><ele>598</ele><time>2021-09-07T16:14:00Z</time> </trkpt>
            <trkpt lat="47.807156" lon="12.378108"><ele>598</ele><time>2021-09-07T16:14:06Z</time> </trkpt>
            <trkpt lat="47.807251" lon="12.378079"><ele>598</ele><time>2021-09-07T16:14:11Z</time> </trkpt>
            <trkpt lat="47.807346" lon="12.378055"><ele>598</ele><time>2021-09-07T16:14:16Z</time> </trkpt>
            </trkseg>
            </trk>
            </gpx>
            """;
    private static final GpxType GPX_TYPE = GpxConverter.convertGpxFromString(GPX);

    @Test
    void calcDistance() {
        final double distance = DistanceTotalCalculator.calcDistance(47.80743, 47.807343, 12.378228, 12.378138, 587, 588);
        assertTrue(distance > 0);
        assertEquals(11.822080163097478, distance);
    }

    @Test
    void fromTrkpts() {
        final WptType fromTrkpt = new WptType();
        fromTrkpt.setLat(BigDecimal.valueOf(47.80743));
        fromTrkpt.setLon(BigDecimal.valueOf(12.378228));
        fromTrkpt.setEle(BigDecimal.valueOf(587));
        final WptType toTrkpt = new WptType();
        toTrkpt.setLat(BigDecimal.valueOf(47.807343));
        toTrkpt.setLon(BigDecimal.valueOf(12.378138));
        toTrkpt.setEle(BigDecimal.valueOf(588));

        Double distance = DistanceTotalCalculator.fromTrkpts(fromTrkpt, toTrkpt);
        assertNotNull(distance);
        assertTrue(distance > 0);
        assertEquals(11.822080163097478, distance);

        distance = DistanceTotalCalculator.fromTrkpts(null, null);
        assertNull(distance);
    }

    @Test
    void fromTrkptList() {
        final List<WptType> trkptList = GPX_TYPE.getTrk().get(0).getTrkseg().get(0).getTrkpt();

        Double distance = DistanceTotalCalculator.fromTrkptList(trkptList);
        assertNotNull(distance);
        assertTrue(distance > 0);
        assertEquals(69.93321548302377, distance);

        distance = DistanceTotalCalculator.fromTrkptList(null);
        assertNull(distance);

        distance = DistanceTotalCalculator.fromTrkptList(new ArrayList<>());
        assertNull(distance);
    }

    @Test
    void fromTrkseg() {
        final TrksegType trkseg = GPX_TYPE.getTrk().get(0).getTrkseg().get(0);

        Double distance = DistanceTotalCalculator.fromTrkseg(trkseg);
        assertNotNull(distance);
        assertTrue(distance > 0);
        assertEquals(69.93321548302377, distance);

        distance = DistanceTotalCalculator.fromTrkseg(null);
        assertNull(distance);

        distance = DistanceTotalCalculator.fromTrkseg(new TrksegType());
        assertNull(distance);
    }

    @Test
    void fromTrksegList() {
        final List<TrksegType> trksegList = GPX_TYPE.getTrk().get(0).getTrkseg();

        Double distance = DistanceTotalCalculator.fromTrksegList(trksegList);
        assertNotNull(distance);
        assertTrue(distance > 0);
        assertEquals(149.9041198759723, distance);

        distance = DistanceTotalCalculator.fromTrksegList(null);
        assertNull(distance);

        distance = DistanceTotalCalculator.fromTrksegList(new ArrayList<>());
        assertNull(distance);
    }

    @Test
    void fromTrk() {
        final TrkType track = GPX_TYPE.getTrk().get(0);

        Double distance = DistanceTotalCalculator.fromTrk(track);
        assertNotNull(distance);
        assertTrue(distance > 0);
        assertEquals(149.9041198759723, distance);

        distance = DistanceTotalCalculator.fromTrk(null);
        assertNull(distance);

        distance = DistanceTotalCalculator.fromTrk(new TrkType());
        assertNull(distance);
    }
}
