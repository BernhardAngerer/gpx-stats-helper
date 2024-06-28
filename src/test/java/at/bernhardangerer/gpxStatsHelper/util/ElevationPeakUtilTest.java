package at.bernhardangerer.gpxStatsHelper.util;

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
    void findElevationPeaks_upDown() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(500));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(505));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint3);
        final Waypoint waypoint4 = new Waypoint();
        waypoint4.setEle(BigDecimal.valueOf(515));
        waypoints.add(waypoint4);
        final Waypoint waypoint5 = new Waypoint();
        waypoint5.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint5);
        final Waypoint waypoint6 = new Waypoint();
        waypoint6.setEle(BigDecimal.valueOf(505));
        waypoints.add(waypoint6);
        final Waypoint waypoint7 = new Waypoint();
        waypoint7.setEle(BigDecimal.valueOf(500));
        waypoints.add(waypoint7);

        List<Waypoint> peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(515), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(515), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findElevationPeaks_upDown_smoothStart_smoothEnd() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(500));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(500));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint3);
        final Waypoint waypoint4 = new Waypoint();
        waypoint4.setEle(BigDecimal.valueOf(515));
        waypoints.add(waypoint4);
        final Waypoint waypoint5 = new Waypoint();
        waypoint5.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint5);
        final Waypoint waypoint6 = new Waypoint();
        waypoint6.setEle(BigDecimal.valueOf(505));
        waypoints.add(waypoint6);
        final Waypoint waypoint7 = new Waypoint();
        waypoint7.setEle(BigDecimal.valueOf(505));
        waypoints.add(waypoint7);

        List<Waypoint> peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(515), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(515), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findElevationPeaks_upDownUp() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(500));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(505));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint3);
        final Waypoint waypoint4 = new Waypoint();
        waypoint4.setEle(BigDecimal.valueOf(515));
        waypoints.add(waypoint4);
        final Waypoint waypoint5 = new Waypoint();
        waypoint5.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint5);
        final Waypoint waypoint6 = new Waypoint();
        waypoint6.setEle(BigDecimal.valueOf(505));
        waypoints.add(waypoint6);
        final Waypoint waypoint7 = new Waypoint();
        waypoint7.setEle(BigDecimal.valueOf(500));
        waypoints.add(waypoint7);
        final Waypoint waypoint8 = new Waypoint();
        waypoint8.setEle(BigDecimal.valueOf(550));
        waypoints.add(waypoint8);
        final Waypoint waypoint9 = new Waypoint();
        waypoint9.setEle(BigDecimal.valueOf(600));
        waypoints.add(waypoint9);

        List<Waypoint> peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(515), firstPeak.getEle());
        Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(600), secondPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(515), firstPeak.getEle());
        secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(600), secondPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        secondPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(600), secondPeak.getEle());
    }

    @Test
    void findElevationPeaks_upDownUpDown() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(500));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(505));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint3);
        final Waypoint waypoint4 = new Waypoint();
        waypoint4.setEle(BigDecimal.valueOf(515));
        waypoints.add(waypoint4);
        final Waypoint waypoint5 = new Waypoint();
        waypoint5.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint5);
        final Waypoint waypoint6 = new Waypoint();
        waypoint6.setEle(BigDecimal.valueOf(515));
        waypoints.add(waypoint6);
        final Waypoint waypoint7 = new Waypoint();
        waypoint7.setEle(BigDecimal.valueOf(500));
        waypoints.add(waypoint7);

        List<Waypoint> peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(515), firstPeak.getEle());
        Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(515), secondPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(515), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPeaks_upOnly() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(585));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(590));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(595));
        waypoints.add(waypoint3);

        List<Waypoint> peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(595), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(595), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPeaks_upOnly_smoothStart_smoothEnd() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(580));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(580));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(585));
        waypoints.add(waypoint3);
        final Waypoint waypoint4 = new Waypoint();
        waypoint4.setEle(BigDecimal.valueOf(588));
        waypoints.add(waypoint4);
        final Waypoint waypoint5 = new Waypoint();
        waypoint5.setEle(BigDecimal.valueOf(588));
        waypoints.add(waypoint5);

        List<Waypoint> peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(588), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findElevationPeaks_downUp() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(520));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint3);
        final Waypoint waypoint4 = new Waypoint();
        waypoint4.setEle(BigDecimal.valueOf(515));
        waypoints.add(waypoint4);
        final Waypoint waypoint5 = new Waypoint();
        waypoint5.setEle(BigDecimal.valueOf(520));
        waypoints.add(waypoint5);
        final Waypoint waypoint6 = new Waypoint();
        waypoint6.setEle(BigDecimal.valueOf(525));
        waypoints.add(waypoint6);

        List<Waypoint> peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(520), firstPeak.getEle());
        Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(525), secondPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(520), firstPeak.getEle());
        secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(525), secondPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findElevationPeaks_downUp_smoothStart_smoothEnd() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(520));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(520));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint3);
        final Waypoint waypoint4 = new Waypoint();
        waypoint4.setEle(BigDecimal.valueOf(515));
        waypoints.add(waypoint4);
        final Waypoint waypoint5 = new Waypoint();
        waypoint5.setEle(BigDecimal.valueOf(520));
        waypoints.add(waypoint5);
        final Waypoint waypoint6 = new Waypoint();
        waypoint6.setEle(BigDecimal.valueOf(525));
        waypoints.add(waypoint6);
        final Waypoint waypoint7 = new Waypoint();
        waypoint7.setEle(BigDecimal.valueOf(525));
        waypoints.add(waypoint7);

        List<Waypoint> peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(520), firstPeak.getEle());
        Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(525), secondPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(520), firstPeak.getEle());
        secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(525), secondPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findElevationPeaks_downUpDown() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(520));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint3);
        final Waypoint waypoint4 = new Waypoint();
        waypoint4.setEle(BigDecimal.valueOf(515));
        waypoints.add(waypoint4);
        final Waypoint waypoint5 = new Waypoint();
        waypoint5.setEle(BigDecimal.valueOf(520));
        waypoints.add(waypoint5);
        final Waypoint waypoint6 = new Waypoint();
        waypoint6.setEle(BigDecimal.valueOf(525));
        waypoints.add(waypoint6);
        final Waypoint waypoint7 = new Waypoint();
        waypoint7.setEle(BigDecimal.valueOf(500));
        waypoints.add(waypoint7);

        List<Waypoint> peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(520), firstPeak.getEle());
        Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(525), secondPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(520), firstPeak.getEle());
        secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(525), secondPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(525), firstPeak.getEle());
    }

    @Test
    void findElevationPeaks_downUpDownUp() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(520));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint3);
        final Waypoint waypoint4 = new Waypoint();
        waypoint4.setEle(BigDecimal.valueOf(515));
        waypoints.add(waypoint4);
        final Waypoint waypoint5 = new Waypoint();
        waypoint5.setEle(BigDecimal.valueOf(520));
        waypoints.add(waypoint5);
        final Waypoint waypoint6 = new Waypoint();
        waypoint6.setEle(BigDecimal.valueOf(525));
        waypoints.add(waypoint6);
        final Waypoint waypoint7 = new Waypoint();
        waypoint7.setEle(BigDecimal.valueOf(500));
        waypoints.add(waypoint7);
        final Waypoint waypoint8 = new Waypoint();
        waypoint8.setEle(BigDecimal.valueOf(530));
        waypoints.add(waypoint8);

        List<Waypoint> peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(3, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(520), firstPeak.getEle());
        Waypoint secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(525), secondPeak.getEle());
        Waypoint thirdPeak = peaks.get(2);
        assertEquals(BigDecimal.valueOf(530), thirdPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(3, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(520), firstPeak.getEle());
        secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(525), secondPeak.getEle());
        thirdPeak = peaks.get(2);
        assertEquals(BigDecimal.valueOf(530), thirdPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(2, peaks.size());
        firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(525), firstPeak.getEle());
        secondPeak = peaks.get(1);
        assertEquals(BigDecimal.valueOf(530), secondPeak.getEle());
    }

    @Test
    void findPeaks_downOnly() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(580));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(575));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(572));
        waypoints.add(waypoint3);

        List<Waypoint> peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(580), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPeaks_downOnly_smoothStart_smoothEnd() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(580));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(580));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(575));
        waypoints.add(waypoint3);
        final Waypoint waypoint4 = new Waypoint();
        waypoint4.setEle(BigDecimal.valueOf(572));
        waypoints.add(waypoint4);
        final Waypoint waypoint5 = new Waypoint();
        waypoint5.setEle(BigDecimal.valueOf(572));
        waypoints.add(waypoint5);

        List<Waypoint> peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertFalse(peaks.isEmpty());
        assertEquals(1, peaks.size());
        Waypoint firstPeak = peaks.get(0);
        assertEquals(BigDecimal.valueOf(580), firstPeak.getEle());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(20));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPeaks_illegalArgument() {
        assertThrows(IllegalArgumentException.class, ()-> ElevationPeakUtil.findPeaks(null, BigDecimal.valueOf(-1)));
    }

    @Test
    void findPeaks_null() {
        final List<Waypoint> peaks = ElevationPeakUtil.findPeaks(null, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPeaks_empty() {
        final List<Waypoint> peaks = ElevationPeakUtil.findPeaks(new ArrayList<>(), BigDecimal.ZERO);
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPeaks_single() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(587));
        waypoints.add(waypoint1);

        List<Waypoint> peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPeaks_flat2() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(587));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(587));
        waypoints.add(waypoint2);

        List<Waypoint> peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }

    @Test
    void findPeaks_flat3() {
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

        List<Waypoint> peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.ZERO);
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());

        peaks = ElevationPeakUtil.findPeaks(waypoints, BigDecimal.valueOf(10));
        assertNotNull(peaks);
        assertTrue(peaks.isEmpty());
    }
}
