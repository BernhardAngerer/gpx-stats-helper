package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.ElevationRange;
import com.topografix.model.TrkType;
import com.topografix.model.TrksegType;
import com.topografix.model.WptType;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public final class ElevationRangeCalculator {

    private ElevationRangeCalculator() {
    }

    static ElevationRange fromTrkptList(final List<WptType> trkptList) {
        if (trkptList != null && !trkptList.isEmpty()) {
            final ElevationRange range = new ElevationRange();
            range.setHighest(trkptList.stream().max(Comparator.comparing(WptType::getEle)).map(wptType -> wptType.getEle()).get());
            range.setLowest(trkptList.stream().min(Comparator.comparing(WptType::getEle)).map(wptType -> wptType.getEle()).get());
            return range;
        }
        return null;
    }

    static ElevationRange fromTrkseg(final TrksegType trackSegment) {
        if (trackSegment != null) {
            return fromTrkptList(trackSegment.getTrkpt());
        }
        return null;
    }

    static ElevationRange fromTrksegList(final List<TrksegType> trksegList) {
        if (trksegList != null && !trksegList.isEmpty()) {
            final ElevationRange range = new ElevationRange();
            range.setHighest(trksegList.stream().map(trksegType -> fromTrkseg(trksegType).getHighest()).max(BigDecimal::compareTo).get());
            range.setLowest(trksegList.stream().map(trksegType -> fromTrkseg(trksegType).getLowest()).min(BigDecimal::compareTo).get());
            return range;
        }
        return null;
    }

    public static ElevationRange fromTrk(final TrkType track) {
        if (track != null) {
            return fromTrksegList(track.getTrkseg());
        }
        return null;
    }
}
