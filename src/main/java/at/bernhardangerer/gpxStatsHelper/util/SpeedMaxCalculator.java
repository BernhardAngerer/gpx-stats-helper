package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.TrkType;
import com.topografix.model.TrksegType;
import com.topografix.model.WptType;

import java.util.List;

import static at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil.calcDateTimeDifferenceInSeconds;

public final class SpeedMaxCalculator {

    private SpeedMaxCalculator() {
    }

    public static double fromTrkpts(final WptType fromTrkpt, final WptType toTrkpt) {
        if (fromTrkpt != null && fromTrkpt.getEle() != null && toTrkpt != null && toTrkpt.getEle() != null
                && fromTrkpt.getTime() != null && toTrkpt.getTime() != null) {
            final Double distance = DistanceTotalCalculator.fromTrkpts(fromTrkpt, toTrkpt);
            final long duration = calcDateTimeDifferenceInSeconds(fromTrkpt.getTime(), toTrkpt.getTime());
            if (distance != null) {
                return SpeedUtil.calculateSpeed(distance, duration);
            }
        }
        return 0;
    }

    public static double fromTrkptList(final List<WptType> trkptList) {
        if (trkptList != null && trkptList.size() >= 2) {
            double speedMax = 0;
            for (int count = 0; (count + 1) < trkptList.size(); count++) {
                final double speed = fromTrkpts(trkptList.get(count), trkptList.get(count + 1));
                speedMax = Math.max(speed, speedMax);
            }
            return speedMax;
        }
        return 0;
    }

    public static double fromTrkseg(final TrksegType trackSegment) {
        if (trackSegment != null) {
            return fromTrkptList(trackSegment.getTrkpt());
        }
        return 0;
    }

    public static double fromTrksegList(final List<TrksegType> trksegList) {
        if (trksegList != null && !trksegList.isEmpty()) {
            double speedMax = 0;
            for (final TrksegType trackSegment : trksegList) {
                final double speed = fromTrkseg(trackSegment);
                speedMax = Math.max(speed, speedMax);
            }
            return speedMax;
        }
        return 0;
    }

    public static double fromTrk(final TrkType track) {
        if (track != null) {
            return fromTrksegList(track.getTrkseg());
        }
        return 0;
    }
}
