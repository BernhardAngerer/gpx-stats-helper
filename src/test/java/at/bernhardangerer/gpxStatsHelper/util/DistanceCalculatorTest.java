package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.fixture.GpxFixture;
import at.bernhardangerer.gpxStatsHelper.model.ElevationProfile;
import com.topografix.model.Gpx;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class DistanceCalculatorTest {

    private static final Gpx GPX_TYPE = GpxReader.fromString(GpxFixture.GPX);
    private static final Gpx GPX_TYPE_WITHOUT_ELEVATION = GpxReader.fromString(GpxFixture.GPX_WITHOUT_ELEVATION);

    @Test
    void fromWaypointList() {
        final List<Waypoint> waypointList = GPX_TYPE.getTrk().get(0).getTrkseg().get(0).getTrkpt();

        ElevationProfile distance = DistanceCalculator.fromWaypointList(waypointList);
        assertNotNull(distance);
        assertEquals(11.822080163097478, distance.getAscent().doubleValue());
        assertEquals(47.831457437114850, distance.getFlat().doubleValue());
        assertEquals(10.472429392187978, distance.getDescent().doubleValue());
        assertNull(distance.getUnknown());

        distance = DistanceCalculator.fromWaypointList(null);
        assertNull(distance);

        distance = DistanceCalculator.fromWaypointList(new ArrayList<>());
        assertNull(distance);
    }

    @Test
    void fromWaypointListWithoutElevation() {
        final List<Waypoint> waypointList = GPX_TYPE_WITHOUT_ELEVATION.getTrk().get(0).getTrkseg().get(0).getTrkpt();

        final ElevationProfile distance = DistanceCalculator.fromWaypointList(waypointList);
        assertNotNull(distance);
        assertNull(distance.getAscent());
        assertNull(distance.getFlat());
        assertNull(distance.getDescent());
        assertEquals(69.89084581648362, distance.getUnknown().doubleValue());
    }

    @Test
    void fromTrackSegment() {
        final TrackSegment trackSegment = GPX_TYPE.getTrk().get(0).getTrkseg().get(0);

        ElevationProfile distance = DistanceCalculator.fromTrackSegment(trackSegment);
        assertNotNull(distance);
        assertEquals(11.822080163097478, distance.getAscent().doubleValue());
        assertEquals(47.831457437114850, distance.getFlat().doubleValue());
        assertEquals(10.472429392187978, distance.getDescent().doubleValue());
        assertNull(distance.getUnknown());

        distance = DistanceCalculator.fromTrackSegment(null);
        assertNull(distance);

        distance = DistanceCalculator.fromTrackSegment(new TrackSegment());
        assertNull(distance);
    }

    @Test
    void fromTrackSegmentWithoutElevation() {
        final TrackSegment trackSegment = GPX_TYPE_WITHOUT_ELEVATION.getTrk().get(0).getTrkseg().get(0);

        final ElevationProfile distance = DistanceCalculator.fromTrackSegment(trackSegment);
        assertNotNull(distance);
        assertNull(distance.getAscent());
        assertNull(distance.getFlat());
        assertNull(distance.getDescent());
        assertEquals(69.89084581648362, distance.getUnknown().doubleValue());
    }

    @Test
    void fromTrackSegmentList() {
        final List<TrackSegment> trackSegmentList = GPX_TYPE.getTrk().get(0).getTrkseg();

        ElevationProfile distance = DistanceCalculator.fromTrackSegmentList(trackSegmentList);
        assertNotNull(distance);
        assertEquals(11.822080163097478, distance.getAscent().doubleValue());
        assertEquals(117.0878647797619, distance.getFlat().doubleValue());
        assertEquals(154.33773779672697, distance.getDescent().doubleValue());
        assertNull(distance.getUnknown());

        distance = DistanceCalculator.fromTrackSegmentList(null);
        assertNull(distance);

        distance = DistanceCalculator.fromTrackSegmentList(new ArrayList<>());
        assertNull(distance);
    }

    @Test
    void fromTrackSegmentListWithoutElevation() {
        final List<TrackSegment> trackSegmentList = GPX_TYPE_WITHOUT_ELEVATION.getTrk().get(0).getTrkseg();

        final ElevationProfile distance = DistanceCalculator.fromTrackSegmentList(trackSegmentList);
        assertNotNull(distance);
        assertNull(distance.getAscent());
        assertNull(distance.getFlat());
        assertNull(distance.getDescent());
        assertEquals(282.998658999786672, distance.getUnknown().doubleValue());
    }

    @Test
    void fromTrack() {
        final Track track = GPX_TYPE.getTrk().get(0);

        ElevationProfile distance = DistanceCalculator.fromTrack(track);
        assertNotNull(distance);
        assertEquals(11.822080163097478, distance.getAscent().doubleValue());
        assertEquals(117.0878647797619, distance.getFlat().doubleValue());
        assertEquals(154.33773779672697, distance.getDescent().doubleValue());
        assertNull(distance.getUnknown());

        distance = DistanceCalculator.fromTrack(null);
        assertNull(distance);

        distance = DistanceCalculator.fromTrack(new Track());
        assertNull(distance);
    }

    @Test
    void fromTrackWithoutElevation() {
        final Track track = GPX_TYPE_WITHOUT_ELEVATION.getTrk().get(0);

        final ElevationProfile distance = DistanceCalculator.fromTrack(track);
        assertNotNull(distance);
        assertNull(distance.getAscent());
        assertNull(distance.getFlat());
        assertNull(distance.getDescent());
        assertEquals(282.998658999786672, distance.getUnknown().doubleValue());
    }
}
