package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.fixture.ElevationWaypointFixture;
import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ElevationPeakUtilTest {

    @Test
    void findPositivePeaksUpDown() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsUpDown();

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(515), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(515), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPositivePeaksUpDownSmoothStartSmoothEnd() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsUpDownSmoothStartSmoothEnd();

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(515), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(515), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPositivePeaksUpDownUp() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsUpDownUp();

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(515), firstPeak.getEle());
        Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(600), secondPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(515), firstPeak.getEle());
        secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(600), secondPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        secondPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(600), secondPeak.getEle());
    }

    @Test
    void findPositivePeaksUpDownUpDown() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsUpDownUpDown();

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(515), firstPeak.getEle());
        final Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(515), secondPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(515), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPositivePeaksUpOnly() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsUpOnly();

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(595), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(595), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPositivePeaksUpOnlySmoothStartSmoothEnd() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsUpOnlySmoothStartSmoothEnd();

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        final Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(588), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPositivePeaksDownUp() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsDownUp();

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(520), firstPeak.getEle());
        Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(525), secondPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(520), firstPeak.getEle());
        secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(525), secondPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPositivePeaksDownUpSmoothStartSmoothEnd() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsDownUpSmoothStartSmoothEnd();

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(520), firstPeak.getEle());
        Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(525), secondPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(520), firstPeak.getEle());
        secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(525), secondPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPositivePeaksDownUpDown() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsDownUpDown();

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(520), firstPeak.getEle());
        Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(525), secondPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(520), firstPeak.getEle());
        secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(525), secondPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(525), firstPeak.getEle());
    }

    @Test
    void findPositivePeaksDownUpDownUp() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsDownUpDownUp();

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(3, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(520), firstPeak.getEle());
        Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(525), secondPeak.getEle());
        Waypoint thirdPeak = peaks.get(2);
        assertEquals(BigDecimal.valueOf(530), thirdPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(3, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(520), firstPeak.getEle());
        secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(525), secondPeak.getEle());
        thirdPeak = peaks.get(2);
        assertEquals(BigDecimal.valueOf(530), thirdPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(525), firstPeak.getEle());
        secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(530), secondPeak.getEle());
    }

    @Test
    void findPositivePeaksDownOnly() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsDownOnly();

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        final Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(580), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPositivePeaksDownOnlySmoothStartSmoothEnd() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsDownOnlySmoothStartSmoothEnd();

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        final Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(580), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPositivePeaksIllegalArgument() {
        assertThrows(IllegalArgumentException.class,
                () -> ElevationPeakUtil.findPositivePeaks(null, BigDecimal.valueOf(-1)));
    }

    @Test
    void findPositivePeaksNull() {
        final List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(null, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPositivePeaksEmpty() {
        final List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(new ArrayList<>(), BigDecimal.ZERO);
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPositivePeaksSingle() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(587));
        waypoints.add(waypoint1);

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPositivePeaksFlat2() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(587));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(587));
        waypoints.add(waypoint2);

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPositivePeaksFlat3() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(587));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(587));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(587));
        waypoints.add(waypoint3);

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findNegativePeaksUpDown() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsUpDown();

        List<Waypoint> peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(500), firstPeak.getEle());
        Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(500), secondPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(500), firstPeak.getEle());
        secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(500), secondPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findNegativePeaksUpDownSmoothStartSmoothEnd() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsUpDownSmoothStartSmoothEnd();

        List<Waypoint> peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(500), firstPeak.getEle());
        Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(505), secondPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(500), firstPeak.getEle());
        secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(505), secondPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findNegativePeaksUpDownUp() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsUpDownUp();

        List<Waypoint> peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(500), firstPeak.getEle());
        Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(500), secondPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(500), firstPeak.getEle());
        secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(500), secondPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        secondPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(500), secondPeak.getEle());
    }

    @Test
    void findNegativePeaksUpDownUpDown() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsUpDownUpDown();

        List<Waypoint> peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(3, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(500), firstPeak.getEle());
        final Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(510), secondPeak.getEle());
        final Waypoint thirdPeak = peaks.get(2);
        assertEquals(BigDecimal.valueOf(510), thirdPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(500), firstPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findNegativePeaksUpOnly() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsUpOnly();

        List<Waypoint> peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(585), firstPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(585), firstPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findNegativePeaksUpOnlySmoothStartSmoothEnd() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsUpOnlySmoothStartSmoothEnd();

        List<Waypoint> peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        final Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(580), firstPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findNegativePeaksDownUp() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsDownUp();

        List<Waypoint> peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(510), firstPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(510), firstPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findNegativePeaksDownUpSmoothStartSmoothEnd() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsDownUpSmoothStartSmoothEnd();

        List<Waypoint> peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(510), firstPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(510), firstPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findNegativePeaksDownUpDown() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsDownUpDown();

        List<Waypoint> peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(510), firstPeak.getEle());
        Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(500), secondPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(510), firstPeak.getEle());
        secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(500), secondPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(500), firstPeak.getEle());
    }

    @Test
    void findNegativePeaksDownUpDownUp() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsDownUpDownUp();

        List<Waypoint> peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(510), firstPeak.getEle());
        Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(500), secondPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(510), firstPeak.getEle());
        secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(500), secondPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(500), firstPeak.getEle());
    }

    @Test
    void findNegativePeaksDownOnly() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsDownOnly();

        List<Waypoint> peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        final Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(572), firstPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findNegativePeaksDownOnlySmoothStartSmoothEnd() {
        final List<Waypoint> waypoints = ElevationWaypointFixture.getWaypointsDownOnlySmoothStartSmoothEnd();

        List<Waypoint> peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        final Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(572), firstPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

}
