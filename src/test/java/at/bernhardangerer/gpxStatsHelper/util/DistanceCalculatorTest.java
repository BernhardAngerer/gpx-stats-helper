package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.enumeration.SlopeSensitivity;
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
        final double expectedTotalDistance = 70.12596699240031;

        ElevationProfile distance = DistanceCalculator.fromWaypointList(waypointList, SlopeSensitivity.HIGHEST);
        assertNotNull(distance);
        assertEquals(11.822080163097478, distance.getAscent().doubleValue());
        assertEquals(47.831457437114850, distance.getFlat().doubleValue());
        assertEquals(10.472429392187978, distance.getDescent().doubleValue());
        assertNull(distance.getUnknown());
        assertEquals(expectedTotalDistance, distance.sum().doubleValue());

        distance = DistanceCalculator.fromWaypointList(waypointList, SlopeSensitivity.HIGH);
        assertNotNull(distance);
        assertEquals(11.822080163097478, distance.getAscent().doubleValue());
        assertEquals(33.0841264721799, distance.getFlat().doubleValue());
        assertEquals(25.219760357122922, distance.getDescent().doubleValue());
        assertNull(distance.getUnknown());
        assertEquals(expectedTotalDistance, distance.sum().doubleValue());

        distance = DistanceCalculator.fromWaypointList(waypointList, SlopeSensitivity.MEDIUM);
        assertNotNull(distance);
        assertEquals(22.166428881868878, distance.getAscent().doubleValue());
        assertEquals(0.0, distance.getFlat().doubleValue());
        assertEquals(47.959538110531426, distance.getDescent().doubleValue());
        assertNull(distance.getUnknown());
        assertEquals(expectedTotalDistance, distance.sum().doubleValue());

        distance = DistanceCalculator.fromWaypointList(waypointList, SlopeSensitivity.LOW);
        assertNotNull(distance);
        assertEquals(0.0, distance.getAscent().doubleValue());
        assertEquals(0.0, distance.getFlat().doubleValue());
        assertEquals(70.12596699240031, distance.getDescent().doubleValue());
        assertNull(distance.getUnknown());
        assertEquals(expectedTotalDistance, distance.sum().doubleValue());

        distance = DistanceCalculator.fromWaypointList(null, SlopeSensitivity.HIGHEST);
        assertNull(distance);

        distance = DistanceCalculator.fromWaypointList(new ArrayList<>(), SlopeSensitivity.HIGHEST);
        assertNull(distance);
    }

    @Test
    void fromWaypointListWithoutElevation() {
        final List<Waypoint> waypointList = GPX_TYPE_WITHOUT_ELEVATION.getTrk().get(0).getTrkseg().get(0).getTrkpt();

        final ElevationProfile distance = DistanceCalculator.fromWaypointList(waypointList, SlopeSensitivity.HIGHEST);
        assertNotNull(distance);
        assertNull(distance.getAscent());
        assertNull(distance.getFlat());
        assertNull(distance.getDescent());
        assertEquals(69.89084581648362, distance.getUnknown().doubleValue());
    }

    @Test
    void fromTrackSegment() {
        final TrackSegment trackSegment = GPX_TYPE.getTrk().get(0).getTrkseg().get(0);

        ElevationProfile distance = DistanceCalculator.fromTrackSegment(trackSegment, SlopeSensitivity.HIGHEST);
        assertNotNull(distance);
        assertEquals(11.822080163097478, distance.getAscent().doubleValue());
        assertEquals(47.831457437114850, distance.getFlat().doubleValue());
        assertEquals(10.472429392187978, distance.getDescent().doubleValue());
        assertNull(distance.getUnknown());

        distance = DistanceCalculator.fromTrackSegment(null, SlopeSensitivity.HIGHEST);
        assertNull(distance);

        distance = DistanceCalculator.fromTrackSegment(new TrackSegment(), SlopeSensitivity.HIGHEST);
        assertNull(distance);
    }

    @Test
    void fromTrackSegmentWithoutElevation() {
        final TrackSegment trackSegment = GPX_TYPE_WITHOUT_ELEVATION.getTrk().get(0).getTrkseg().get(0);

        final ElevationProfile distance = DistanceCalculator.fromTrackSegment(trackSegment, SlopeSensitivity.HIGHEST);
        assertNotNull(distance);
        assertNull(distance.getAscent());
        assertNull(distance.getFlat());
        assertNull(distance.getDescent());
        assertEquals(69.89084581648362, distance.getUnknown().doubleValue());
    }

    @Test
    void fromTrackSegmentList() {
        final List<TrackSegment> trackSegmentList = GPX_TYPE.getTrk().get(0).getTrkseg();

        ElevationProfile distance = DistanceCalculator.fromTrackSegmentList(trackSegmentList, SlopeSensitivity.HIGHEST);
        assertNotNull(distance);
        assertEquals(11.822080163097478, distance.getAscent().doubleValue());
        assertEquals(117.0878647797619, distance.getFlat().doubleValue());
        assertEquals(154.33773779672697, distance.getDescent().doubleValue());
        assertNull(distance.getUnknown());

        distance = DistanceCalculator.fromTrackSegmentList(null, SlopeSensitivity.HIGHEST);
        assertNull(distance);

        distance = DistanceCalculator.fromTrackSegmentList(new ArrayList<>(), SlopeSensitivity.HIGHEST);
        assertNull(distance);
    }

    @Test
    void fromTrackSegmentListWithoutElevation() {
        final List<TrackSegment> trackSegmentList = GPX_TYPE_WITHOUT_ELEVATION.getTrk().get(0).getTrkseg();

        final ElevationProfile distance = DistanceCalculator.fromTrackSegmentList(trackSegmentList, SlopeSensitivity.HIGHEST);
        assertNotNull(distance);
        assertNull(distance.getAscent());
        assertNull(distance.getFlat());
        assertNull(distance.getDescent());
        assertEquals(282.998658999786672, distance.getUnknown().doubleValue());
    }

    @Test
    void fromTrack() {
        final Track track = GPX_TYPE.getTrk().get(0);
        final double expectedTotalDistance = 283.247682739586360;

        ElevationProfile distance = DistanceCalculator.fromTrack(track, SlopeSensitivity.HIGHEST);
        assertNotNull(distance);
        assertEquals(11.822080163097478, distance.getAscent().doubleValue());
        assertEquals(117.0878647797619, distance.getFlat().doubleValue());
        assertEquals(154.33773779672697, distance.getDescent().doubleValue());
        assertNull(distance.getUnknown());
        assertEquals(expectedTotalDistance, distance.sum().doubleValue());

        distance = DistanceCalculator.fromTrack(track, SlopeSensitivity.HIGH);
        assertNotNull(distance);
        assertEquals(11.822080163097478, distance.getAscent().doubleValue());
        assertEquals(91.55728509940407, distance.getFlat().doubleValue());
        assertEquals(179.86831747708482, distance.getDescent().doubleValue());
        assertNull(distance.getUnknown());
        assertEquals(expectedTotalDistance, distance.sum().doubleValue());

        distance = DistanceCalculator.fromTrack(track, SlopeSensitivity.MEDIUM);
        assertNotNull(distance);
        assertEquals(22.166428881868878, distance.getAscent().doubleValue());
        assertEquals(47.56586209331613, distance.getFlat().doubleValue());
        assertEquals(213.51539176440136, distance.getDescent().doubleValue());
        assertNull(distance.getUnknown());
        assertEquals(expectedTotalDistance, distance.sum().doubleValue());

        distance = DistanceCalculator.fromTrack(track, SlopeSensitivity.LOW);
        assertNotNull(distance);
        assertEquals(0.0, distance.getAscent().doubleValue());
        assertEquals(37.31460287337097, distance.getFlat().doubleValue());
        assertEquals(245.9330798662154, distance.getDescent().doubleValue());
        assertNull(distance.getUnknown());
        assertEquals(expectedTotalDistance, distance.sum().doubleValue());

        distance = DistanceCalculator.fromTrack(null, SlopeSensitivity.HIGHEST);
        assertNull(distance);

        distance = DistanceCalculator.fromTrack(new Track(), SlopeSensitivity.HIGHEST);
        assertNull(distance);
    }

    @Test
    void fromTrackWithoutElevation() {
        final Track track = GPX_TYPE_WITHOUT_ELEVATION.getTrk().get(0);
        final double expectedTotalDistance = 282.998658999786672;
        final double expectedUnknownDistance = expectedTotalDistance;

        ElevationProfile distance = DistanceCalculator.fromTrack(track, SlopeSensitivity.HIGHEST);
        assertNotNull(distance);
        assertNull(distance.getAscent());
        assertNull(distance.getFlat());
        assertNull(distance.getDescent());
        assertEquals(expectedUnknownDistance, distance.getUnknown().doubleValue());
        assertEquals(expectedTotalDistance, distance.sum().doubleValue());

        distance = DistanceCalculator.fromTrack(track, SlopeSensitivity.HIGH);
        assertNotNull(distance);
        assertNull(distance.getAscent());
        assertNull(distance.getFlat());
        assertNull(distance.getDescent());
        assertEquals(expectedUnknownDistance, distance.getUnknown().doubleValue());
        assertEquals(expectedTotalDistance, distance.sum().doubleValue());

        distance = DistanceCalculator.fromTrack(track, SlopeSensitivity.MEDIUM);
        assertNotNull(distance);
        assertNull(distance.getAscent());
        assertNull(distance.getFlat());
        assertNull(distance.getDescent());
        assertEquals(expectedUnknownDistance, distance.getUnknown().doubleValue());
        assertEquals(expectedTotalDistance, distance.sum().doubleValue());

        distance = DistanceCalculator.fromTrack(track, SlopeSensitivity.LOW);
        assertNotNull(distance);
        assertNull(distance.getAscent());
        assertNull(distance.getFlat());
        assertNull(distance.getDescent());
        assertEquals(expectedUnknownDistance, distance.getUnknown().doubleValue());
        assertEquals(expectedTotalDistance, distance.sum().doubleValue());
    }
}
