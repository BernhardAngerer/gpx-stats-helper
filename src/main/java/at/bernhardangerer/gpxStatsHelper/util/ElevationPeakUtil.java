package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.ElevationExtremes;
import com.topografix.model.Waypoint;
import org.apache.commons.lang3.SerializationUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class ElevationPeakUtil {

    private ElevationPeakUtil() {
    }

    /**
     * Finds elevation positive peaks from a list of waypoints based on a given threshold.
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
    public static List<Waypoint> findPositivePeaks(final List<Waypoint> waypoints, final BigDecimal thresholdInMeters) {
        return find(waypoints, thresholdInMeters, true);
    }

    /**
     * Finds elevation negative peaks from a list of waypoints based on a given threshold.
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
    public static List<Waypoint> findNegativePeaks(final List<Waypoint> waypoints, final BigDecimal thresholdInMeters) {
        return find(waypoints, thresholdInMeters, false);
    }

    /**
     * Finds elevation negative and positive peaks from a list of waypoints based on a given threshold.
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
        final List<Waypoint> waypointList = new ArrayList<>();
        waypointList.addAll(find(waypoints, thresholdInMeters, true));
        waypointList.addAll(find(waypoints, thresholdInMeters, false));

        return waypointList.stream()
                .sorted(Comparator.comparing(Waypoint::getTime))
                .collect(Collectors.toList());
    }

    private static List<Waypoint> find(final List<Waypoint> waypoints, final BigDecimal thresholdInMeters,
                                       final boolean positivePeaks) {
        if (thresholdInMeters == null || thresholdInMeters.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Parameter thresholdInMeters must be greater than or equal to zero.");
        }

        final List<Waypoint> peaks = new ArrayList<>();
        if (isValidWaypointList(waypoints)) {
            final ElevationExtremes elevationExtremes = ElevationExtremesCalculator.fromWaypointList(waypoints);
            final BigDecimal elevationDiff =
                    elevationExtremes.getHighestPoint().getEle().subtract(elevationExtremes.getLowestPoint().getEle());

            if (elevationDiff.compareTo(thresholdInMeters) < 0) {
                return peaks;
            }

            final List<Waypoint> tempWaypoints = positivePeaks ? waypoints : new ArrayList<>(waypoints).stream()
                    .map(SerializationUtils::clone)
                    .peek(waypoint -> waypoint.setEle(waypoint.getEle().negate()))
                    .collect(Collectors.toList());
            for (int idx = 0; idx < waypoints.size(); idx++) {
                if ((peaks.isEmpty() || isPeakCandidate(tempWaypoints, idx, thresholdInMeters))
                        && isPeak(tempWaypoints, idx, thresholdInMeters)) {
                    peaks.add(waypoints.get(idx));
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
            for (int idx = index + 1; idx < size; idx++) {
                final BigDecimal subsequentElevation = waypoints.get(idx).getEle();

                if (subsequentElevation.compareTo(currentElevation) > 0) {
                    break;
                }

                if (subsequentElevation.compareTo(currentElevation) < 0
                        && currentElevation.subtract(subsequentElevation).compareTo(threshold) >= 0
                        || subsequentElevation.compareTo(currentElevation) == 0
                                && waypoints.subList(idx + 1, size).stream()
                                        .allMatch(waypoint -> waypoint.getEle().compareTo(currentElevation) <= 0)) {
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
            for (int idx = index - 1; idx >= 0; idx--) {
                final BigDecimal previousElevation = waypoints.get(idx).getEle();
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
