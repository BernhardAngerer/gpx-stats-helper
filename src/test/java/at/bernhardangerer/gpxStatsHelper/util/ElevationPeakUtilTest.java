package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static at.bernhardangerer.gpxStatsHelper.util.ElevationPeakUtilFixture.getWaypointsDownOnly;
import static at.bernhardangerer.gpxStatsHelper.util.ElevationPeakUtilFixture.getWaypointsDownOnlySmoothStartSmoothEnd;
import static at.bernhardangerer.gpxStatsHelper.util.ElevationPeakUtilFixture.getWaypointsDownUp;
import static at.bernhardangerer.gpxStatsHelper.util.ElevationPeakUtilFixture.getWaypointsDownUpDown;
import static at.bernhardangerer.gpxStatsHelper.util.ElevationPeakUtilFixture.getWaypointsDownUpDownUp;
import static at.bernhardangerer.gpxStatsHelper.util.ElevationPeakUtilFixture.getWaypointsDownUpSmoothStartSmoothEnd;
import static at.bernhardangerer.gpxStatsHelper.util.ElevationPeakUtilFixture.getWaypointsUpDown;
import static at.bernhardangerer.gpxStatsHelper.util.ElevationPeakUtilFixture.getWaypointsUpDownSmoothStartSmoothEnd;
import static at.bernhardangerer.gpxStatsHelper.util.ElevationPeakUtilFixture.getWaypointsUpDownUp;
import static at.bernhardangerer.gpxStatsHelper.util.ElevationPeakUtilFixture.getWaypointsUpDownUpDown;
import static at.bernhardangerer.gpxStatsHelper.util.ElevationPeakUtilFixture.getWaypointsUpOnly;
import static at.bernhardangerer.gpxStatsHelper.util.ElevationPeakUtilFixture.getWaypointsUpOnlySmoothStartSmoothEnd;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ElevationPeakUtilTest {

    @Test
    void findPositivePeaks_upDown() {
        final List<Waypoint> waypoints = getWaypointsUpDown();

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
    void findPositivePeaks_upDown_smoothStart_smoothEnd() {
        final List<Waypoint> waypoints = getWaypointsUpDownSmoothStartSmoothEnd();

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
    void findPositivePeaks_upDownUp() {
        final List<Waypoint> waypoints = getWaypointsUpDownUp();

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
    void findPositivePeaks_upDownUpDown() {
        final List<Waypoint> waypoints = getWaypointsUpDownUpDown();

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(515), firstPeak.getEle());
        Waypoint secondPeak = peaks.get(1);
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
    void findPositivePeaks_upOnly() {
        final List<Waypoint> waypoints = getWaypointsUpOnly();

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
    void findPositivePeaks_upOnly_smoothStart_smoothEnd() {
        final List<Waypoint> waypoints = getWaypointsUpOnlySmoothStartSmoothEnd();

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(588), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPositivePeaks_downUp() {
        final List<Waypoint> waypoints = getWaypointsDownUp();

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
    void findPositivePeaks_downUp_smoothStart_smoothEnd() {
        final List<Waypoint> waypoints = getWaypointsDownUpSmoothStartSmoothEnd();

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
    void findPositivePeaks_downUpDown() {
        final List<Waypoint> waypoints = getWaypointsDownUpDown();

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
    void findPositivePeaks_downUpDownUp() {
        final List<Waypoint> waypoints = getWaypointsDownUpDownUp();

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
    void findPositivePeaks_downOnly() {
        final List<Waypoint> waypoints = getWaypointsDownOnly();

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(580), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPositivePeaks_downOnly_smoothStart_smoothEnd() {
        final List<Waypoint> waypoints = getWaypointsDownOnlySmoothStartSmoothEnd();

        List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(580), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findPositivePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPositivePeaks_illegalArgument() {
        assertThrows(IllegalArgumentException.class, ()-> ElevationPeakUtil.findPositivePeaks(null, BigDecimal.valueOf(-1)));
    }

    @Test
    void findPositivePeaks_null() {
        final List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(null, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPositivePeaks_empty() {
        final List<Waypoint> peaks = ElevationPeakUtil.findPositivePeaks(new ArrayList<>(), BigDecimal.ZERO);
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPositivePeaks_single() {
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
    void findPositivePeaks_flat2() {
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
    void findPositivePeaks_flat3() {
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
    void findNegativePeaks_upDown() {
        final List<Waypoint> waypoints = getWaypointsUpDown();

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
    void findNegativePeaks_upDown_smoothStart_smoothEnd() {
        final List<Waypoint> waypoints = getWaypointsUpDownSmoothStartSmoothEnd();

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
    void findNegativePeaks_upDownUp() {
        final List<Waypoint> waypoints = getWaypointsUpDownUp();

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
    void findNegativePeaks_upDownUpDown() {
        final List<Waypoint> waypoints = getWaypointsUpDownUpDown();

        List<Waypoint> peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(3, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(500), firstPeak.getEle());
        Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(510), secondPeak.getEle());
        Waypoint thirdPeak = peaks.get(2);
        assertEquals(BigDecimal.valueOf(500), thirdPeak.getEle());

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
    void findNegativePeaks_upOnly() {
        final List<Waypoint> waypoints = getWaypointsUpOnly();

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
    void findNegativePeaks_upOnly_smoothStart_smoothEnd() {
        final List<Waypoint> waypoints = getWaypointsUpOnlySmoothStartSmoothEnd();

        List<Waypoint> peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(580), firstPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findNegativePeaks_downUp() {
        final List<Waypoint> waypoints = getWaypointsDownUp();

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
    void findNegativePeaks_downUp_smoothStart_smoothEnd() {
        final List<Waypoint> waypoints = getWaypointsDownUpSmoothStartSmoothEnd();

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
    void findNegativePeaks_downUpDown() {
        final List<Waypoint> waypoints = getWaypointsDownUpDown();

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
    void findNegativePeaks_downUpDownUp() {
        final List<Waypoint> waypoints = getWaypointsDownUpDownUp();

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
    void findNegativePeaks_downOnly() {
        final List<Waypoint> waypoints = getWaypointsDownOnly();

        List<Waypoint> peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(572), firstPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findNegativePeaks_downOnly_smoothStart_smoothEnd() {
        final List<Waypoint> waypoints = getWaypointsDownOnlySmoothStartSmoothEnd();

        List<Waypoint> peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(572), firstPeak.getEle());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findNegativePeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

}
