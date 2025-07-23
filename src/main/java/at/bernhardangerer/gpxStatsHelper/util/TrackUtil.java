package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Track;

public final class TrackUtil {

    private TrackUtil() {
    }

    /**
     * Count the number of waypoints of the track.
     *
     * @param track
     * @return number of waypoints
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
