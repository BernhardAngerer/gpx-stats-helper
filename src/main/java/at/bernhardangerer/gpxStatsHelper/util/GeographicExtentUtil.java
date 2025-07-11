package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.BoundingBox;
import com.topografix.model.Track;
import com.topografix.model.Waypoint;

import java.math.BigDecimal;
import java.util.Comparator;

public final class GeographicExtentUtil {

    private GeographicExtentUtil() {
    }

    /**
     * Calculates the geographic bounding box (minimum and maximum latitude and longitude) for the given GPS track.
     *
     * @param track
     * @return BoundingBox
     */
    public static BoundingBox findBounding(final Track track) {
        if (track != null && track.getTrkseg() != null && !track.getTrkseg().isEmpty()) {
            final BigDecimal minLat = track.getTrkseg().stream()
                    .flatMap(trackSegment -> trackSegment.getTrkpt().stream())
                    .min(Comparator.comparing(Waypoint::getLat)).get().getLat();
            final BigDecimal minLon = track.getTrkseg().stream()
                    .flatMap(trackSegment -> trackSegment.getTrkpt().stream())
                    .min(Comparator.comparing(Waypoint::getLon)).get().getLon();
            final BigDecimal maxLat = track.getTrkseg().stream()
                    .flatMap(trackSegment -> trackSegment.getTrkpt().stream())
                    .max(Comparator.comparing(Waypoint::getLat)).get().getLat();
            final BigDecimal maxLon = track.getTrkseg().stream()
                    .flatMap(trackSegment -> trackSegment.getTrkpt().stream())
                    .max(Comparator.comparing(Waypoint::getLon)).get().getLon();

            return new BoundingBox(minLat, minLon, maxLat, maxLon);
        }

        return null;
    }

}
