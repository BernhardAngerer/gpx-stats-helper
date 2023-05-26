package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.TrkType;
import com.topografix.model.TrksegType;
import com.topografix.model.WptType;

import java.util.List;

public final class DistanceTotalCalculator {

    private DistanceTotalCalculator() {
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * @param lat1 Start point Lat
     * @param lat2 End point Lat
     * @param lon1 Start point Lon
     * @param lon2 End point Lon
     * @param el1 Start point elevation
     * @param el2 End point elevation
     * @return Distance in Meters
     */
    static double calcDistance(final double lat1, final double lat2, final double lon1,
                               final double lon2, final double el1, final double el2) {
        final int radiusEarth = 6371; // Radius of the earth
        final double latDistance = Math.toRadians(lat2 - lat1);
        final double lonDistance = Math.toRadians(lon2 - lon1);
        final double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = radiusEarth * c * 1000; // convert to meters
        final double height = el1 - el2;
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        return Math.sqrt(distance);
    }

    static Double fromTrkpts(final WptType fromTrkpt, final WptType toTrkpt) {
        if (fromTrkpt != null && fromTrkpt.getLat() != null && fromTrkpt.getLon() != null && fromTrkpt.getEle() != null
            && toTrkpt != null && toTrkpt.getLat() != null && toTrkpt.getLon() != null && toTrkpt.getEle() != null) {
            return calcDistance(fromTrkpt.getLat().doubleValue(), toTrkpt.getLat().doubleValue(),
                fromTrkpt.getLon().doubleValue(), toTrkpt.getLon().doubleValue(),
                fromTrkpt.getEle().doubleValue(), toTrkpt.getEle().doubleValue());
        }
        return null;
    }

    static Double fromTrkptList(final List<WptType> trkptList) {
        if (trkptList != null && trkptList.size() >= 2) {
            double distance = 0;
            for (int count = 0; (count + 1) < trkptList.size(); count++) {
                final Double dist = fromTrkpts(trkptList.get(count), trkptList.get(count + 1));
                if (dist != null) {
                    distance = distance + dist;
                } else {
                    return null;
                }
            }
            return distance;
        }
        return null;
    }

    static Double fromTrkseg(final TrksegType trackSegment) {
        if (trackSegment != null) {
            return fromTrkptList(trackSegment.getTrkpt());
        }
        return null;
    }

    static Double fromTrksegList(final List<TrksegType> trksegList) {
        if (trksegList != null && !trksegList.isEmpty()) {
            double distance = 0;
            for (final TrksegType trackSegment : trksegList) {
                final Double dist = fromTrkseg(trackSegment);
                if (dist != null) {
                    distance = distance + dist;
                } else {
                    return null;
                }
            }
            return distance;
        }
        return null;
    }

    public static Double fromTrk(final TrkType track) {
        if (track != null) {
            return fromTrksegList(track.getTrkseg());
        }
        return null;
    }
}
