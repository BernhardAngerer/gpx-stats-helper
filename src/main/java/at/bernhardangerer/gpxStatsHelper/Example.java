package at.bernhardangerer.gpxStatsHelper;

import at.bernhardangerer.gpxStatsHelper.exception.WebserviceCallException;
import at.bernhardangerer.gpxStatsHelper.model.DistanceDuration;
import at.bernhardangerer.gpxStatsHelper.model.Duration;
import at.bernhardangerer.gpxStatsHelper.model.ElevationDelta;
import at.bernhardangerer.gpxStatsHelper.model.ElevationRange;
import at.bernhardangerer.gpxStatsHelper.model.FirstLastWpt;
import at.bernhardangerer.gpxStatsHelper.model.api.GeocodeReverseModel;
import at.bernhardangerer.gpxStatsHelper.service.GeocodeService;
import at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil;
import at.bernhardangerer.gpxStatsHelper.util.DistanceTotalCalculator;
import at.bernhardangerer.gpxStatsHelper.util.DurationInMotionCalculator;
import at.bernhardangerer.gpxStatsHelper.util.ElevationDeltaCalculator;
import at.bernhardangerer.gpxStatsHelper.util.ElevationRangeCalculator;
import at.bernhardangerer.gpxStatsHelper.util.FirstLastWptCalculator;
import at.bernhardangerer.gpxStatsHelper.util.GeocodeUtil;
import at.bernhardangerer.gpxStatsHelper.util.GpxConverter;
import at.bernhardangerer.gpxStatsHelper.util.SpeedAvgCalculator;
import at.bernhardangerer.gpxStatsHelper.util.SpeedMaxCalculator;
import at.bernhardangerer.gpxStatsHelper.util.SpeedUtil;
import com.topografix.model.GpxType;
import com.topografix.model.TrkType;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil.calcDateTimeDifference;
import static at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil.convertFromSeconds;

public final class Example {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#");
    private static final GeocodeService GEOCODE_SERVICE = new GeocodeService();

    private Example() {
    }

    public static void main(final String[] args)
            throws IOException, URISyntaxException, WebserviceCallException, InterruptedException {
        final File file = new File(Example.class.getClassLoader().getResource("example/example1.gpx").getFile());
        final GpxType gpx = GpxConverter.convertGpxFromFile(file);

        int count = 1;
        for (final TrkType track : gpx.getTrk()) {
            System.out.println("### track nr. " + count + " ###");

            final Double distance = DistanceTotalCalculator.fromTrk(track);
            System.out.println("total distance: " + DECIMAL_FORMAT.format(distance / 1000) + " km");

            final ElevationDelta delta = ElevationDeltaCalculator.fromTrk(track);
            System.out.println("ascent: " + delta.getAscent().setScale(0, RoundingMode.HALF_UP) + " m");
            System.out.println("descent: " + delta.getDescent().setScale(0, RoundingMode.HALF_UP) + " m");

            final ElevationRange range = ElevationRangeCalculator.fromTrk(track);
            System.out.println("highest point: " + range.getHighest().setScale(0, RoundingMode.HALF_UP) + " m.s.l.");
            System.out.println("lowest point: " + range.getLowest().setScale(0, RoundingMode.HALF_UP) + " m.s.l.");

            final FirstLastWpt firstLast = FirstLastWptCalculator.fromTrk(track);

            System.out.println("start time: " + DATE_TIME_FORMATTER.format(DateTimeUtil.utcToCet(firstLast.getFirst().getTime())) + " h");
            System.out.println("end time: " + DATE_TIME_FORMATTER.format(DateTimeUtil.utcToCet(firstLast.getLast().getTime())) + " h");

            final Duration durationTotal = calcDateTimeDifference(firstLast.getFirst().getTime(), firstLast.getLast().getTime());
            System.out.println("total duration: " + durationTotal.format() + " h");

            final Long durationInMotion = DurationInMotionCalculator.fromTrk(track);
            System.out.println("duration in motion: " + convertFromSeconds(durationInMotion).format() + " h");

            final Double speedMax = SpeedMaxCalculator.fromTrk(track);
            System.out.println("maximum speed: " + DECIMAL_FORMAT.format(speedMax) + " km/h");

            final DistanceDuration distanceDuration = SpeedAvgCalculator.fromTrk(track);
            System.out.println("average speed: " + DECIMAL_FORMAT.format(SpeedUtil.calculateSpeed(distanceDuration)) + " km/h");

            System.out.println("start position: Lat " + firstLast.getFirst().getLat() + " / Lon " + firstLast.getFirst().getLon());
            System.out.println("end position: Lat " + firstLast.getLast().getLat() + " / Lon " + firstLast.getLast().getLon());

            final GeocodeReverseModel startPos = GeocodeUtil.convertFromJson(GEOCODE_SERVICE.reverseGeocode(
                firstLast.getFirst().getLat().toString(), firstLast.getFirst().getLon().toString()));
            if (GeocodeUtil.isBounded(firstLast.getLast().getLat().doubleValue(), firstLast.getLast().getLon().doubleValue(),
                startPos.getBoundingbox()[0], startPos.getBoundingbox()[2], startPos.getBoundingbox()[1], startPos.getBoundingbox()[3])) {
                System.out.println("start = end geoposition: " + startPos.getDisplayName());
            } else {
                System.out.println("start geoposition: " + startPos.getDisplayName());
                System.out.println("end geoposition: " + GeocodeUtil.convertFromJson(GEOCODE_SERVICE.reverseGeocode(
                    firstLast.getLast().getLat().toString(), firstLast.getLast().getLon().toString())).getDisplayName());
            }

            count++;
        }
    }
}
