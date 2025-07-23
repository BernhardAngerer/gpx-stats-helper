package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.enumeration.StepRoundingMode;
import com.topografix.model.Waypoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static at.bernhardangerer.gpxStatsHelper.util.WaypointUtil.createWaypoint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class SlopeCalculatorTest {

    private List<Waypoint> waypoints;

    @BeforeEach
    void init() {
        final Waypoint waypoint1 = createWaypoint(47.80743, 12.378228, 587);
        final Waypoint waypoint2 = createWaypoint(47.807343, 12.378138, 588);
        final Waypoint waypoint3 = createWaypoint(47.807343, 12.378000, 589);

        waypoints = Arrays.asList(waypoint1, waypoint2, waypoint3);
    }

    @Test
    public void testFromWaypointListWithNearestRounding() {
        final Map<Integer, Double> result = SlopeCalculator.fromWaypointList(waypoints, 1, StepRoundingMode.NEAREST);

        assertNotNull(result);
        assertEquals(2, result.size());
        final Double expectedDistance1 = DistanceUtil.calcDistance(waypoints.get(0), waypoints.get(1));
        assertEquals(expectedDistance1, result.get(8), 0.0001);
        final Double expectedDistance2 = DistanceUtil.calcDistance(waypoints.get(1), waypoints.get(2));
        assertEquals(expectedDistance2, result.get(10), 0.0001);
    }

    @Test
    public void testFromWaypointListWithStepRoundingUp() {
        final Map<Integer, Double> result = SlopeCalculator.fromWaypointList(waypoints, 1, StepRoundingMode.UP);

        assertNotNull(result);
        assertEquals(2, result.size());
        final Double expectedDistance1 = DistanceUtil.calcDistance(waypoints.get(0), waypoints.get(1));
        assertEquals(expectedDistance1, result.get(9), 0.0001);
        final Double expectedDistance2 = DistanceUtil.calcDistance(waypoints.get(1), waypoints.get(2));
        assertEquals(expectedDistance2, result.get(10), 0.0001);
    }

    @Test
    public void testFromWaypointListWithStepRoundingDown() {
        final Map<Integer, Double> result = SlopeCalculator.fromWaypointList(waypoints, 1, StepRoundingMode.DOWN);

        assertNotNull(result);
        assertEquals(2, result.size());
        final Double expectedDistance1 = DistanceUtil.calcDistance(waypoints.get(0), waypoints.get(1));
        assertEquals(expectedDistance1, result.get(8), 0.0001);
        final Double expectedDistance2 = DistanceUtil.calcDistance(waypoints.get(1), waypoints.get(2));
        assertEquals(expectedDistance2, result.get(9), 0.0001);
    }

    @Test
    public void testFromWaypointListWithEmptyInput() {
        final List<Waypoint> waypoints = Collections.emptyList();

        final Map<Integer, Double> result = SlopeCalculator.fromWaypointList(waypoints, 10, StepRoundingMode.UP);

        assertNull(result);
    }

    @Test
    public void testFromWaypointListWithSingleWaypoint() {
        final Waypoint waypoint1 = waypoints.get(0);
        final List<Waypoint> waypoints = Collections.singletonList(waypoint1);

        final Map<Integer, Double> result = SlopeCalculator.fromWaypointList(waypoints, 10, StepRoundingMode.UP);

        assertNull(result);
    }

    @Test
    public void testFromWaypointListWithNullInput() {
        final Map<Integer, Double> result = SlopeCalculator.fromWaypointList(null, 10, StepRoundingMode.UP);

        assertNull(result);
    }
}
