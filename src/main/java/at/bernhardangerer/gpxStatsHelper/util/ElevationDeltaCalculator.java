package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.ElevationDelta;
import com.topografix.model.TrkType;
import com.topografix.model.TrksegType;
import com.topografix.model.WptType;

import java.math.BigDecimal;
import java.util.List;

public final class ElevationDeltaCalculator {

    private ElevationDeltaCalculator() {
    }

    public static BigDecimal fromTrkpts(final WptType fromTrkpt, final WptType toTrkpt) {
        if (fromTrkpt != null && fromTrkpt.getEle() != null && toTrkpt != null && toTrkpt.getEle() != null) {
            return toTrkpt.getEle().subtract(fromTrkpt.getEle());
        }
        return null;
    }

    public static ElevationDelta fromTrkptList(final List<WptType> trkptList) {
        if (trkptList != null && trkptList.size() >= 2) {
            final ElevationDelta delta = new ElevationDelta();
            for (int count = 0; (count + 1) < trkptList.size(); count++) {
                final BigDecimal elevationDelta = fromTrkpts(trkptList.get(count), trkptList.get(count + 1));
                if (elevationDelta != null) {
                    if (elevationDelta.doubleValue() > 0) {
                        if (delta.getAscent() == null) {
                            delta.setAscent(BigDecimal.ZERO);
                        }
                        delta.setAscent(delta.getAscent().add(elevationDelta));
                    } else if (elevationDelta.doubleValue() < 0) {
                        if (delta.getDescent() == null) {
                            delta.setDescent(BigDecimal.ZERO);
                        }
                        delta.setDescent(delta.getDescent().add(elevationDelta.abs()));
                    }
                } else {
                    return null;
                }
            }
            return delta;
        }
        return null;
    }

    public static ElevationDelta fromTrkseg(final TrksegType trackSegment) {
        if (trackSegment != null) {
            return fromTrkptList(trackSegment.getTrkpt());
        }
        return null;
    }

    public static ElevationDelta fromTrksegList(final List<TrksegType> trksegList) {
        if (trksegList != null && !trksegList.isEmpty()) {
            final ElevationDelta delta = new ElevationDelta();
            for (final TrksegType trackSegment : trksegList) {
                final ElevationDelta elevationDelta = fromTrkseg(trackSegment);
                if (elevationDelta != null) {
                    if (elevationDelta.getAscent() != null) {
                        if (delta.getAscent() == null) {
                            delta.setAscent(BigDecimal.ZERO);
                        }
                        delta.setAscent(delta.getAscent().add(elevationDelta.getAscent()));
                    }
                    if (elevationDelta.getDescent() != null) {
                        if (delta.getDescent() == null) {
                            delta.setDescent(BigDecimal.ZERO);
                        }
                        delta.setDescent(delta.getDescent().add(elevationDelta.getDescent()));
                    }
                } else {
                    return null;
                }
            }
            return delta;
        }
        return null;
    }

    public static ElevationDelta fromTrk(final TrkType track) {
        if (track != null) {
            return fromTrksegList(track.getTrkseg());
        }
        return null;
    }
}
