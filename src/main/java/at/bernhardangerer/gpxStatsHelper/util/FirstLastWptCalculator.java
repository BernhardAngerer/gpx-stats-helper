package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.FirstLastWpt;
import com.topografix.model.TrkType;
import com.topografix.model.WptType;

public final class FirstLastWptCalculator {

    private FirstLastWptCalculator() {
    }

    public static FirstLastWpt fromTrk(final TrkType track) {
        if (track != null) {
            if (track.getTrkseg() != null && !track.getTrkseg().isEmpty()) {
                WptType first = null;
                if (track.getTrkseg().get(0) != null
                    && track.getTrkseg().get(0).getTrkpt() != null
                    && !track.getTrkseg().get(0).getTrkpt().isEmpty()) {
                    first = track.getTrkseg()
                        .get(0)
                        .getTrkpt()
                        .get(0);
                }
                WptType last = null;
                if (track.getTrkseg().get(track.getTrkseg().size() - 1) != null
                    && track.getTrkseg().get(track.getTrkseg().size() - 1).getTrkpt() != null
                    && !track.getTrkseg().get(track.getTrkseg().size() - 1).getTrkpt().isEmpty()) {
                    last = track.getTrkseg()
                        .get(track.getTrkseg().size() - 1)
                        .getTrkpt()
                        .get(track.getTrkseg().get(track.getTrkseg().size() - 1).getTrkpt().size() - 1);
                }
                return new FirstLastWpt(first, last);
            }
        }
        return null;
    }
}
