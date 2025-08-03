package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.fixture.GpxFixture;
import at.bernhardangerer.gpxStatsHelper.model.ElevationExtremes;
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

class ElevationExtremesCalculatorTest {

    private static final Gpx GPX_TYPE = GpxReader.fromString(GpxFixture.GPX);
    private static final Gpx GPX_TYPE_WITHOUT_ELEVATION = GpxReader.fromString(GpxFixture.GPX_WITHOUT_ELEVATION);

    @Test
    void fromWaypointList() {
        final List<Waypoint> waypointList = GPX_TYPE.getTrk().get(0).getTrkseg().get(0).getTrkpt();

        ElevationExtremes range = ElevationExtremesCalculator.fromWaypointList(waypointList);
        assertNotNull(range);
        assertEquals(BigDecimal.valueOf(588), range.getHighestPoint().getEle());
        assertEquals(BigDecimal.valueOf(586), range.getLowestPoint().getEle());

        range = ElevationExtremesCalculator.fromWaypointList(null);
        assertNull(range);

        range = ElevationExtremesCalculator.fromWaypointList(new ArrayList<>());
        assertNull(range);
    }

    @Test
    void fromWaypointListWithoutElevation() {
        final List<Waypoint> waypointList = GPX_TYPE_WITHOUT_ELEVATION.getTrk().get(0).getTrkseg().get(0).getTrkpt();

        final ElevationExtremes range = ElevationExtremesCalculator.fromWaypointList(waypointList);
        assertNull(range);
    }

    @Test
    void fromTrackSegment() {
        final TrackSegment trackSegment = GPX_TYPE.getTrk().get(0).getTrkseg().get(0);

        ElevationExtremes range = ElevationExtremesCalculator.fromTrackSegment(trackSegment);
        assertNotNull(range);
        assertEquals(BigDecimal.valueOf(588), range.getHighestPoint().getEle());
        assertEquals(BigDecimal.valueOf(586), range.getLowestPoint().getEle());

        range = ElevationExtremesCalculator.fromTrackSegment(null);
        assertNull(range);

        range = ElevationExtremesCalculator.fromTrackSegment(new TrackSegment());
        assertNull(range);
    }

    @Test
    void fromTrackSegmentWithoutElevation() {
        final TrackSegment trackSegment = GPX_TYPE_WITHOUT_ELEVATION.getTrk().get(0).getTrkseg().get(0);

        final ElevationExtremes range = ElevationExtremesCalculator.fromTrackSegment(trackSegment);
        assertNull(range);
    }

    @Test
    void fromTrackSegmentList() {
        final List<TrackSegment> trackSegmentList = GPX_TYPE.getTrk().get(0).getTrkseg();

        ElevationExtremes range = ElevationExtremesCalculator.fromTrackSegmentList(trackSegmentList);
        assertNotNull(range);
        assertEquals(BigDecimal.valueOf(598), range.getHighestPoint().getEle());
        assertEquals(BigDecimal.valueOf(586), range.getLowestPoint().getEle());

        range = ElevationExtremesCalculator.fromTrackSegmentList(null);
        assertNull(range);

        range = ElevationExtremesCalculator.fromTrackSegmentList(new ArrayList<>());
        assertNull(range);
    }

    @Test
    void fromTrackSegmentListWithoutElevation() {
        final List<TrackSegment> trackSegmentList = GPX_TYPE_WITHOUT_ELEVATION.getTrk().get(0).getTrkseg();

        final ElevationExtremes range = ElevationExtremesCalculator.fromTrackSegmentList(trackSegmentList);
        assertNull(range);
    }

    @Test
    void fromTrack() {
        final Track track = GPX_TYPE.getTrk().get(0);

        ElevationExtremes range = ElevationExtremesCalculator.fromTrack(track);
        assertNotNull(range);
        assertEquals(BigDecimal.valueOf(598), range.getHighestPoint().getEle());
        assertEquals(BigDecimal.valueOf(586), range.getLowestPoint().getEle());

        range = ElevationExtremesCalculator.fromTrack(null);
        assertNull(range);

        range = ElevationExtremesCalculator.fromTrack(new Track());
        assertNull(range);
    }

    @Test
    void fromTrackWithoutElevation() {
        final Track track = GPX_TYPE_WITHOUT_ELEVATION.getTrk().get(0);

        final ElevationExtremes range = ElevationExtremesCalculator.fromTrack(track);
        assertNull(range);
    }
}
