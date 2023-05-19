package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.DistanceDuration;
import com.topografix.model.GpxType;
import com.topografix.model.TrkType;
import com.topografix.model.TrksegType;
import com.topografix.model.WptType;
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
            <trkpt lat="47.807312" lon="12.377382"><ele>586</ele><time>2021-09-07T13:38:36Z</time> </trkpt>
            </trkseg>
            <trkseg>
            <trkpt lat="47.806938" lon="12.378183"><ele>598</ele><time>2021-09-07T16:10:27Z</time> </trkpt>
            <trkpt lat="47.806793" lon="12.378217"><ele>598</ele><time>2021-09-07T16:11:20Z</time> </trkpt>
            <trkpt lat="47.806873" lon="12.378148"><ele>598</ele><time>2021-09-07T16:13:51Z</time> </trkpt>
            <trkpt lat="47.806969" lon="12.37816"><ele>598</ele><time>2021-09-07T16:13:55Z</time> </trkpt>
            <trkpt lat="47.80706" lon="12.378138"><ele>598</ele><time>2021-09-07T16:14:00Z</time> </trkpt>
            <trkpt lat="47.807156" lon="12.378108"><ele>598</ele><time>2021-09-07T16:14:06Z</time> </trkpt>
            <trkpt lat="47.807251" lon="12.378079"><ele>598</ele><time>2021-09-07T16:14:11Z</time> </trkpt>
            <trkpt lat="47.807346" lon="12.38"><ele>596</ele><time>2021-09-07T16:14:16Z</time> </trkpt>
            </trkseg>
            </trk>
            </gpx>
            """;
    private static final GpxType GPX_TYPE = GpxConverter.convertGpxFromString(GPX);

    @Test
    void fromTrkpts() {
        final WptType fromTrkpt = new WptType();
        fromTrkpt.setLat(BigDecimal.valueOf(47.80743));
        fromTrkpt.setLon(BigDecimal.valueOf(12.378228));
        fromTrkpt.setEle(BigDecimal.valueOf(587));
        fromTrkpt.setTime(LocalDateTime.of(2021, 9, 7, 16, 14, 16));
        final WptType toTrkpt = new WptType();
        toTrkpt.setLat(BigDecimal.valueOf(47.807343));
        toTrkpt.setLon(BigDecimal.valueOf(12.378138));
        toTrkpt.setEle(BigDecimal.valueOf(588));
        toTrkpt.setTime(LocalDateTime.of(2021, 9, 7, 16, 14, 20));

        DistanceDuration distanceDuration = SpeedAvgCalculator.fromTrkpts(fromTrkpt, toTrkpt);
        assertNotNull(distanceDuration);
        assertTrue(distanceDuration.getDistance() > 0);
        assertTrue(distanceDuration.getDuration() > 0);
        assertEquals(10.63987214678773, calculateSpeed(distanceDuration));

        distanceDuration = SpeedAvgCalculator.fromTrkpts(null, null);
        assertNull(distanceDuration);

        distanceDuration = SpeedAvgCalculator.fromTrkpts(new WptType(), new WptType());
        assertNull(distanceDuration);
    }

    @Test
    void fromTrkptList() {
        final List<WptType> trkptList = GPX_TYPE.getTrk().get(0).getTrkseg().get(0).getTrkpt();

        DistanceDuration distanceDuration = SpeedAvgCalculator.fromTrkptList(trkptList);
        assertNotNull(distanceDuration);
        assertTrue(distanceDuration.getDistance() > 0);
        assertTrue(distanceDuration.getDuration() > 0);
        assertEquals(4.675064466160021, calculateSpeed(distanceDuration));

        distanceDuration = SpeedAvgCalculator.fromTrkptList(null);
        assertNull(distanceDuration);

        distanceDuration = SpeedAvgCalculator.fromTrkptList(new ArrayList<>());
        assertNull(distanceDuration);
    }

    @Test
    void fromTrkseg() {
        final TrksegType trkseg = GPX_TYPE.getTrk().get(0).getTrkseg().get(0);

        DistanceDuration distanceDuration = SpeedAvgCalculator.fromTrkseg(trkseg);
        assertNotNull(distanceDuration);
        assertTrue(distanceDuration.getDistance() > 0);
        assertTrue(distanceDuration.getDuration() > 0);
        assertEquals(4.675064466160021, calculateSpeed(distanceDuration));

        distanceDuration = SpeedAvgCalculator.fromTrkseg(null);
        assertNull(distanceDuration);

        distanceDuration = SpeedAvgCalculator.fromTrkseg(new TrksegType());
        assertNull(distanceDuration);
    }

    @Test
    void fromTrksegList() {
        final List<TrksegType> trksegList = GPX_TYPE.getTrk().get(0).getTrkseg();

        DistanceDuration distanceDuration = SpeedAvgCalculator.fromTrksegList(trksegList);
        assertNotNull(distanceDuration);
        assertTrue(distanceDuration.getDistance() > 0);
        assertTrue(distanceDuration.getDuration() > 0);
        assertEquals(7.444563520907939, calculateSpeed(distanceDuration));

        distanceDuration = SpeedAvgCalculator.fromTrksegList(null);
        assertNull(distanceDuration);

        distanceDuration = SpeedAvgCalculator.fromTrksegList(new ArrayList<>());
        assertNull(distanceDuration);
    }

    @Test
    void fromTrk() {
        final TrkType track = GPX_TYPE.getTrk().get(0);

        DistanceDuration distanceDuration = SpeedAvgCalculator.fromTrk(track);
        assertNotNull(distanceDuration);
        assertTrue(distanceDuration.getDistance() > 0);
        assertTrue(distanceDuration.getDuration() > 0);
        assertEquals(7.444563520907939, calculateSpeed(distanceDuration));

        distanceDuration = SpeedAvgCalculator.fromTrk(null);
        assertNull(distanceDuration);

        distanceDuration = SpeedAvgCalculator.fromTrk(new TrkType());
        assertNull(distanceDuration);
    }
}
