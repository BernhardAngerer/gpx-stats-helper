package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.ElevationRange;
import com.topografix.model.Waypoint;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class ElevationPeakUtil {

    private ElevationPeakUtil() {
    }

    /**
     * Finds elevation peaks from a list of waypoints based on a given threshold.
     *
     * <p>This method identifies waypoints as peaks if they meet the criteria of being at a local maximum
     * with respect to elevation difference from adjacent waypoints, considering a specified threshold.
     * <p>The method ensures that the list of waypoints is not null and contains more than one distinct elevation value.
     * If these conditions are not met, an empty list is returned.
     *
     * @param waypoints the list of waypoints to analyze for peaks
     * @param thresholdInMeters the threshold in meters that defines when an elevation difference constitutes a peak
     * @return a list of waypoints that are identified as peaks based on the given threshold
     * @throws IllegalArgumentException if the thresholdInMeters is negative
     */
    public static List<Waypoint> findPeaks(final List<Waypoint> waypoints, final BigDecimal thresholdInMeters) {
        if (thresholdInMeters == null || thresholdInMeters.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Parameter thresholdInMeters must be greater than or equal to zero.");
        }

        final List<Waypoint> peaks = new ArrayList<>();
        if (isValidWaypointList(waypoints)) {
            final ElevationRange elevationRange = ElevationRangeCalculator.fromWaypointList(waypoints);
            final BigDecimal elevationDiff = elevationRange.getHighest().getEle().subtract(elevationRange.getLowest().getEle());

            if (elevationDiff.compareTo(thresholdInMeters) < 0) {
                return peaks;
            }

            for (int i = 0; i < waypoints.size(); i++) {
                if ((peaks.isEmpty() || isPeakCandidate(waypoints, i, thresholdInMeters)) && isPeak(waypoints, i, thresholdInMeters)) {
                    peaks.add(waypoints.get(i));
                }
            }
        }

        return peaks;
    }

    private static boolean isValidWaypointList(final List<Waypoint> waypoints) {
        return waypoints != null && !waypoints.isEmpty() && waypoints.size() > 1
                && waypoints.stream().map(Waypoint::getEle).distinct().count() > 1;
    }

    private static boolean isPeak(final List<Waypoint> waypoints, final int index, final BigDecimal threshold) {
        final int size = waypoints.size();
        final BigDecimal currentElevation = waypoints.get(index).getEle();

        if (index == size - 1) { // last iteration
            return waypoints.get(index - 1).getEle().compareTo(currentElevation) < 0;
        } else {
            for (int s = index + 1; s < size; s++) {
                final BigDecimal subsequentElevation = waypoints.get(s).getEle();

                if (subsequentElevation.compareTo(currentElevation) > 0) {
                    break;
                }

                if ((subsequentElevation.compareTo(currentElevation) < 0
                        && currentElevation.subtract(subsequentElevation).compareTo(threshold) >= 0)
                        || (subsequentElevation.compareTo(currentElevation) == 0
                                && waypoints.subList(s + 1, size).stream()
                                        .allMatch(wp -> wp.getEle().compareTo(currentElevation) == 0))) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isPeakCandidate(final List<Waypoint> waypoints, final int index, final BigDecimal threshold) {
        if (index == 0) { // first iteration
            return true;
        } else {
            // Check previous elevations
            final BigDecimal currentElevation = waypoints.get(index).getEle();
            for (int p = index - 1; p >= 0; p--) {
                final BigDecimal previousElevation = waypoints.get(p).getEle();
                if (previousElevation.compareTo(currentElevation) >= 0) {
                    break;
                }
                if (previousElevation.compareTo(currentElevation) < 0
                        && currentElevation.subtract(previousElevation).compareTo(threshold) >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

}
