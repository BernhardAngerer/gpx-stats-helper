package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;

public final class TrackUtil {

    private TrackUtil() {
    }

    /**
     * Counts the total number of {@link Waypoint} entries across all {@link TrackSegment}s in the given GPX {@link Track}.
     * <p>
     * This is a useful utility for analytics, validation, or preview summaries.
     *
     * @param track the {@link Track} object to analyze
     * @return total count of {@link Waypoint}s, or {@code 0} if track is {@code null}, has no segments, or empty points
     */
    public static long countWaypoints(final Track track) {
        if (track != null && track.getTrkseg() != null && !track.getTrkseg().isEmpty()) {
            return track.getTrkseg().stream()
                    .mapToLong(trackSegment -> trackSegment.getTrkpt().size())
                    .sum();
        }

        return 0;
    }

    /**
     * Checks whether all waypoints in the given track have a non-null timestamp.
     *
     * <p>This method traverses all track segments and their corresponding waypoints,
     * verifying that each waypoint has a timestamp set (i.e., {@code waypoint.getTime() != null}).
     *
     * @param track the {@link Track} object containing the track segments and waypoints to be checked
     * @return {@code true} if all waypoints have a non-null time; {@code false} if the track,
     *         segments, or any waypoint is missing or lacks a timestamp
     */
    public static boolean allWaypointsWithTime(final Track track) {
        if (track != null && track.getTrkseg() != null && !track.getTrkseg().isEmpty()) {
            return track.getTrkseg().stream()
                    .flatMap(trackSegment -> trackSegment.getTrkpt().stream())
                    .allMatch(waypoint -> waypoint.getTime() != null);
        }

        return false;
    }

    /**
     * Checks whether all waypoints in the given track have a non-null elevation.
     *
     * <p>This method traverses all track segments and their corresponding waypoints,
     * verifying that each waypoint has an elevation set (i.e., {@code waypoint.getEle() != null}).
     *
     * @param track the {@link Track} object containing the track segments and waypoints to be checked
     * @return {@code true} if all waypoints have a non-null elevation; {@code false} if the track,
     *         segments, or any waypoint is missing or lacks elevation data
     */
    public static boolean allWaypointsWithElevation(final Track track) {
        if (track != null && track.getTrkseg() != null && !track.getTrkseg().isEmpty()) {
            return track.getTrkseg().stream()
                    .flatMap(trackSegment -> trackSegment.getTrkpt().stream())
                    .allMatch(waypoint -> waypoint.getEle() != null);
        }

        return false;
    }

}
