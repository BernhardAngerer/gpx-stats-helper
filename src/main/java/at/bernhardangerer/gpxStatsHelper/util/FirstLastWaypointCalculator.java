package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.FirstLastWaypoint;
import com.topografix.model.Track;
import com.topografix.model.Waypoint;

public final class FirstLastWaypointCalculator {

    private FirstLastWaypointCalculator() {
    }

    /**
     * Return the first and last waypoint of the track.
     *
     * @param track
     * @return FirstLastWpt
     */
    public static FirstLastWaypoint fromTrack(final Track track) {
        if (track != null) {
            if (track.getTrkseg() != null && !track.getTrkseg().isEmpty()) {
                Waypoint first = null;
                if (track.getTrkseg().get(0) != null
                    && track.getTrkseg().get(0).getTrkpt() != null
                    && !track.getTrkseg().get(0).getTrkpt().isEmpty()) {
                    first = track.getTrkseg()
                        .get(0)
                        .getTrkpt()
                        .get(0);
                }
                Waypoint last = null;
                if (track.getTrkseg().get(track.getTrkseg().size() - 1) != null
                    && track.getTrkseg().get(track.getTrkseg().size() - 1).getTrkpt() != null
                    && !track.getTrkseg().get(track.getTrkseg().size() - 1).getTrkpt().isEmpty()) {
                    last = track.getTrkseg()
                        .get(track.getTrkseg().size() - 1)
                        .getTrkpt()
                        .get(track.getTrkseg().get(track.getTrkseg().size() - 1).getTrkpt().size() - 1);
                }
                return new FirstLastWaypoint(first, last);
            }
        }
        return null;
    }
}
