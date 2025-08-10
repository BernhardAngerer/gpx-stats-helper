package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.enumeration.SlopeSensitivity;
import at.bernhardangerer.gpxStatsHelper.enumeration.StepRoundingMode;
import com.topografix.model.Waypoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static at.bernhardangerer.gpxStatsHelper.util.RoundingUtil.roundToStep;

public final class SlopeCalculator {

    private SlopeCalculator() {
    }

    /**
     * Computes a histogram of traveled distances grouped by rounded average slope percentage.
     * <p>
     * This method applies a sliding window defined by the given {@link SlopeSensitivity} to calculate
     * the average slope for each segment in the {@code waypointList}. The slope for a segment is
     * calculated as the average of all sub-segment slopes between the start and end of the window.
     * Each segment's distance is then added to a bucket corresponding to its slope percentage,
     * rounded to the nearest multiple of {@code percentageStep} using the specified
     * {@link StepRoundingMode}.
     * <p>
     * Distances for segments with no valid slope measurements in the window are classified
     * as having a slope of {@code 0.0}.
     * <p>
     * The sum of all values in the returned map equals the total traveled distance, regardless
     * of the chosen {@code sensitivity}.
     *
     * @param waypointList   the ordered list of waypoints representing the track; must contain
     *                       at least two waypoints
     * @param percentageStep the slope percentage bucket size (e.g., 5 groups slopes into
     *                       -10%, -5%, 0%, 5%, 10%, etc.)
     * @param stepRoundingMode the rounding strategy to apply when assigning slope percentages
     *                         to buckets
     * @param sensitivity    the slope sensitivity settings that determine the sliding window size
     * @return a map where the key is the rounded slope percentage bucket, and the value is the
     *         total distance (in meters) traveled in that slope range; or {@code null} if
     *         {@code waypointList} or {@code sensitivity} is {@code null}, or if fewer than two
     *         waypoints are provided
     */
    public static Map<Integer, Double> fromWaypointList(final List<Waypoint> waypointList, final int percentageStep,
                                                        final StepRoundingMode stepRoundingMode,
                                                        final SlopeSensitivity sensitivity) {
        if (waypointList == null || sensitivity == null || waypointList.size() < 2) {
            return null;
        }

        final int windowSize = Math.min(sensitivity.getWindowSize(), waypointList.size());
        final int half = windowSize / 2;
        final int n = waypointList.size();
        final Map<Integer, Double> result = new HashMap<>();

        for (int iter = 0; iter < n - 1; iter++) {
            int start = iter + 1 - half;
            if (start < 0) {
                start = 0;
            } else if (start > n - windowSize) {
                start = n - windowSize;
            }
            final int end = start + windowSize - 1;

            // --- Average slope calculation ---
            double totalSlope = 0.0;
            int slopeCount = 0;
            for (int avgIter = start; avgIter < end; avgIter++) {
                final Double segSlope = SlopeUtil.calcSlopePercent(waypointList.get(avgIter), waypointList.get(avgIter + 1));
                if (segSlope != null) {
                    totalSlope += segSlope;
                    slopeCount++;
                }
            }
            final double avgSlopePercent = (slopeCount > 0) ? (totalSlope / slopeCount) : 0.0;

            final Double distance = DistanceUtil.calcDistance(waypointList.get(iter), waypointList.get(iter + 1));
            final int roundedStep = roundToStep(avgSlopePercent, percentageStep, stepRoundingMode);
            result.merge(roundedStep, distance, Double::sum);
        }

        return result;
    }

}
