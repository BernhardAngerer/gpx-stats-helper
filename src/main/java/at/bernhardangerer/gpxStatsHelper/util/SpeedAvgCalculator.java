package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.DistanceDuration;
import com.topografix.model.TrkType;
import com.topografix.model.TrksegType;
import com.topografix.model.WptType;

import java.util.List;

import static at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil.calcDateTimeDifferenceInSeconds;
import static at.bernhardangerer.gpxStatsHelper.util.PropertyUtil.loadValue;

public final class SpeedAvgCalculator {

    private static final double MOTION_MIN_SPEED = Double.parseDouble(loadValue("motionMinSpeed"));

    private SpeedAvgCalculator() {
    }

    public static DistanceDuration fromTrkpts(final WptType fromTrkpt, final WptType toTrkpt) {
        if (fromTrkpt != null && fromTrkpt.getEle() != null && toTrkpt != null && toTrkpt.getEle() != null
                && fromTrkpt.getTime() != null && toTrkpt.getTime() != null) {
            final Double distance = DistanceTotalCalculator.fromTrkpts(fromTrkpt, toTrkpt);
            final long duration = calcDateTimeDifferenceInSeconds(fromTrkpt.getTime(), toTrkpt.getTime());
            if (distance != null) {
                final double speed = SpeedUtil.calculateSpeed(distance, duration);
                if (speed > MOTION_MIN_SPEED) {
                    return new DistanceDuration(distance, duration);
                }
            }
        }
        return null;
    }

    public static DistanceDuration fromTrkptList(final List<WptType> trkptList) {
        if (trkptList != null && trkptList.size() >= 2) {
            final DistanceDuration distanceDuration = new DistanceDuration();
            for (int count = 0; (count + 1) < trkptList.size(); count++) {
                final DistanceDuration tempDistanceDuration = fromTrkpts(trkptList.get(count), trkptList.get(count + 1));
                if (tempDistanceDuration != null) {
                    distanceDuration.setDistance(distanceDuration.getDistance() + tempDistanceDuration.getDistance());
                    distanceDuration.setDuration(distanceDuration.getDuration() + tempDistanceDuration.getDuration());
                }
            }
            return distanceDuration;
        }
        return null;
    }

    public static DistanceDuration fromTrkseg(final TrksegType trackSegment) {
        if (trackSegment != null) {
            return fromTrkptList(trackSegment.getTrkpt());
        }
        return null;
    }

    public static DistanceDuration fromTrksegList(final List<TrksegType> trksegList) {
        if (trksegList != null && !trksegList.isEmpty()) {
            final DistanceDuration distanceDuration = new DistanceDuration();
            for (final TrksegType trackSegment : trksegList) {
                final DistanceDuration tempDistanceDuration = fromTrkseg(trackSegment);
                if (tempDistanceDuration != null) {
                    distanceDuration.setDistance(distanceDuration.getDistance() + tempDistanceDuration.getDistance());
                    distanceDuration.setDuration(distanceDuration.getDuration() + tempDistanceDuration.getDuration());
                }
            }
            return distanceDuration;
        }
        return null;
    }

    public static DistanceDuration fromTrk(final TrkType track) {
        if (track != null) {
            return fromTrksegList(track.getTrkseg());
        }
        return null;
    }
}
