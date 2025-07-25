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
     * Generates a mapping of slope percentage (rounded to a step) to total distance in meters
     * across a list of waypoints. The slope between each consecutive pair of waypoints is calculated,
     * rounded using the provided {@link StepRoundingMode}, and grouped accordingly.
     * <p>
     * This is commonly used to build elevation/distance distribution charts (e.g. how many meters of ascent fall into 0–5%, 5–10%, etc).
     *
     * @param waypointList     the list of ordered {@link Waypoint} entries (must contain at least 2 points)
     * @param percentageStep   the rounding interval in percent (e.g. 5 means group by 5% slope bins)
     * @param stepRoundingMode defines how to round slope values (UP, DOWN, or NEAREST)
     * @return a {@code Map<Integer, Double>} where:
     *         <ul>
     *             <li><b>key</b> = slope % (rounded according to mode)</li>
     *             <li><b>value</b> = total distance [m] covered under this slope bin</li>
     *         </ul>
     *         or {@code null} if the input list is {@code null} or contains fewer than two points
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
