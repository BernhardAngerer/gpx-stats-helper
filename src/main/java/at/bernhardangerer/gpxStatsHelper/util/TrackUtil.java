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

}
