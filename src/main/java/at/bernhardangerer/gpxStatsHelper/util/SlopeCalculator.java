package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.enumeration.StepRoundingMode;
import com.topografix.model.Waypoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static at.bernhardangerer.gpxStatsHelper.util.DistanceUtil.calcDistance;
import static at.bernhardangerer.gpxStatsHelper.util.RoundingUtil.roundToStep;
import static at.bernhardangerer.gpxStatsHelper.util.SlopeUtil.calcSlopePercent;

public final class SlopeCalculator {

    private SlopeCalculator() {
    }

    /**
     * Generates a map of rounded percentage / distance summary.
     *
     * @param waypointList list of waypoints
     * @param percentageStep step in percentage
     * @param stepRoundingMode StepRoundingMode
     * @return a map of rounded percentage [%] / distance summary [m]
     */
    public static Map<Integer, Double> fromWaypointList(final List<Waypoint> waypointList, final int percentageStep,
                                                        final StepRoundingMode stepRoundingMode) {
        if (waypointList != null && waypointList.size() >= 2) {
            final Map<Integer, Double> result = new HashMap<>();

            for (int count = 0; (count + 1) < waypointList.size(); count++) {
                final Waypoint waypoint1 = waypointList.get(count);
                final Waypoint waypoint2 = waypointList.get(count + 1);

                final Double slopePercent = calcSlopePercent(waypoint1, waypoint2);
                final int roundedPercentageStep = roundToStep(slopePercent, percentageStep, stepRoundingMode);

                final Double deltaDistance = calcDistance(waypoint1, waypoint2);

                if (result.containsKey(roundedPercentageStep)) {
                    result.compute(roundedPercentageStep, (key, value) -> Double.sum(value, deltaDistance));
                } else {
                    result.put(roundedPercentageStep, deltaDistance);
                }
            }

            return result;
        }

        return null;
    }

}
