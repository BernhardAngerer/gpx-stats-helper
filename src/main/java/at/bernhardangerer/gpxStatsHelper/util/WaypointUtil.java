package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Track;
import com.topografix.model.Waypoint;

public final class WaypointUtil {

    private WaypointUtil() {
    }

    /**
     * Return the first waypoint of the track.
     *
     * @param track
     * @return Waypoint
     */
    public static Waypoint findFirstWaypoint(final Track track) {
        if (track != null && track.getTrkseg() != null && !track.getTrkseg().isEmpty()
                && track.getTrkseg().get(0) != null && track.getTrkseg().get(0).getTrkpt() != null
                && !track.getTrkseg().get(0).getTrkpt().isEmpty()) {
            return track.getTrkseg()
                .get(0)
                .getTrkpt()
                .get(0);
        }
        return null;
    }

    /**
     * Return the last waypoint of the track.
     *
     * @param track
     * @return Waypoint
     */
    public static Waypoint findLastWaypoint(final Track track) {
        if (track != null && track.getTrkseg() != null && !track.getTrkseg().isEmpty()
                && track.getTrkseg().get(track.getTrkseg().size() - 1) != null
                && track.getTrkseg().get(track.getTrkseg().size() - 1).getTrkpt() != null
                && !track.getTrkseg().get(track.getTrkseg().size() - 1).getTrkpt().isEmpty()) {
            return track.getTrkseg()
                    .get(track.getTrkseg().size() - 1)
                    .getTrkpt()
                    .get(track.getTrkseg().get(track.getTrkseg().size() - 1).getTrkpt().size() - 1);
        }
        return null;
    }

}
