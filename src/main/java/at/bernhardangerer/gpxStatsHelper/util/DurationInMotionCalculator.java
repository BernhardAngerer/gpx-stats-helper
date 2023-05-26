package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.TrkType;
import com.topografix.model.TrksegType;
import com.topografix.model.WptType;

import java.util.List;
import java.util.Objects;

import static at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil.calcDateTimeDifferenceInSeconds;
import static at.bernhardangerer.gpxStatsHelper.util.PropertyUtil.loadValue;

public final class DurationInMotionCalculator {

    private static final double MOTION_MIN_SPEED = Double.parseDouble(loadValue("motionMinSpeed"));

    private DurationInMotionCalculator() {
    }

    static Long fromTrkpts(final WptType fromTrkpt, final WptType toTrkpt) {
        if (fromTrkpt != null && fromTrkpt.getEle() != null && toTrkpt != null && toTrkpt.getEle() != null
                && fromTrkpt.getTime() != null && toTrkpt.getTime() != null) {
            final Double distance = DistanceTotalCalculator.fromTrkpts(fromTrkpt, toTrkpt);
            final long duration = calcDateTimeDifferenceInSeconds(fromTrkpt.getTime(), toTrkpt.getTime());
            if (distance != null) {
                final double speed = SpeedUtil.calculateSpeed(distance, duration);
                if (speed > MOTION_MIN_SPEED) {
                    return duration;
                }
            }
        }
        return null;
    }

    static Long fromTrkptList(final List<WptType> trkptList) {
        if (trkptList != null && trkptList.size() >= 2) {
            Long durationInSec = null;
            for (int count = 0; (count + 1) < trkptList.size(); count++) {
                final Long durInSec = fromTrkpts(trkptList.get(count), trkptList.get(count + 1));
                if (durInSec != null) {
                    if (durationInSec == null) {
                        durationInSec = 0L;
                    }
                    durationInSec += durInSec;
                }
            }
            return durationInSec;
        }
        return null;
    }

    static Long fromTrkseg(final TrksegType trackSegment) {
        if (trackSegment != null) {
            return fromTrkptList(trackSegment.getTrkpt());
        }
        return null;
    }

    static Long fromTrksegList(final List<TrksegType> trksegList) {
        if (trksegList != null && !trksegList.isEmpty()) {
            return trksegList.stream().map(trksegType -> fromTrkseg(trksegType)).filter(Objects::nonNull).reduce(0L, Long::sum);
        }
        return null;
    }

    public static Long fromTrk(final TrkType track) {
        if (track != null) {
            return fromTrksegList(track.getTrkseg());
        }
        return null;
    }
}
