package at.bernhardangerer.gpxStatsHelper;

import at.bernhardangerer.gpxStatsHelper.exception.WebserviceCallException;
import at.bernhardangerer.gpxStatsHelper.model.Duration;
import at.bernhardangerer.gpxStatsHelper.model.ElevationDelta;
import at.bernhardangerer.gpxStatsHelper.model.ElevationRange;
import at.bernhardangerer.gpxStatsHelper.model.FirstLastWaypoint;
import at.bernhardangerer.gpxStatsHelper.model.api.GeocodeReverseModel;
import at.bernhardangerer.gpxStatsHelper.service.GeocodeService;
import at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil;
import at.bernhardangerer.gpxStatsHelper.util.DistanceTotalCalculator;
import at.bernhardangerer.gpxStatsHelper.util.DurationInMotionCalculator;
import at.bernhardangerer.gpxStatsHelper.util.ElevationDeltaCalculator;
import at.bernhardangerer.gpxStatsHelper.util.ElevationRangeCalculator;
import at.bernhardangerer.gpxStatsHelper.util.FirstLastWaypointCalculator;
import at.bernhardangerer.gpxStatsHelper.util.GeocodeUtil;
import at.bernhardangerer.gpxStatsHelper.util.GpxConverter;
import at.bernhardangerer.gpxStatsHelper.util.SpeedAvgCalculator;
import at.bernhardangerer.gpxStatsHelper.util.SpeedMaxCalculator;
import com.topografix.model.Gpx;
import com.topografix.model.Track;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

import static at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil.calcDateTimeDifference;
import static at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil.convertFromSeconds;

public final class Example {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#");
    private static final GeocodeService GEOCODE_SERVICE = new GeocodeService();
    private static final String CET = "CET";
    private static final String SPACE = " ";
    private static final String H = "h";
    private static final String M = "m";
    private static final String KM = "km";
    private static final String KMPH = "km/h";
    private static final String MSL = "m.s.l.";

    private Example() {
    }

    /**
     * Load an example GPX file and calculate statistic parameters.
     *
     * @param args
     * @throws IOException
     * @throws URISyntaxException
     * @throws WebserviceCallException
     * @throws InterruptedException
     */
    public static void main(final String[] args)
            throws IOException, URISyntaxException, WebserviceCallException, InterruptedException {
        final File file = new File(Objects.requireNonNull(
                Example.class.getClassLoader().getResource("example/example1.gpx")).getFile());
        final Gpx gpx = GpxConverter.convertGpxFromFile(file);

        if (gpx.getMetadata().getName() != null) {
            System.out.println("GPX name: \"" + gpx.getMetadata().getName() + "\"");
        }
        int count = 1;
        for (final Track track : gpx.getTrk()) {
            System.out.println("### track nr. " + count
                    + (track.getName() != null ? " - \"" + track.getName() : "\"") + " ###");

            final Double distance = DistanceTotalCalculator.fromTrack(track);
            System.out.println("total distance: " + DECIMAL_FORMAT.format(distance / 1000) + SPACE + KM);

            final ElevationDelta delta = ElevationDeltaCalculator.fromTrack(track);
            System.out.println("ascent: " + delta.getAscent().setScale(0, RoundingMode.HALF_UP) + SPACE + M);
            System.out.println("descent: " + delta.getDescent().setScale(0, RoundingMode.HALF_UP) + SPACE + M);

            final ElevationRange range = ElevationRangeCalculator.fromTrack(track);
            System.out.println("highest point: "
                    + range.getHighest().setScale(0, RoundingMode.HALF_UP) + SPACE + MSL);
            System.out.println("lowest point: "
                    + range.getLowest().setScale(0, RoundingMode.HALF_UP) + SPACE + MSL);

            final FirstLastWaypoint firstLast = FirstLastWaypointCalculator.fromTrack(track);

            System.out.println("start time: " + DATE_TIME_FORMATTER.format(
                    DateTimeUtil.convertFromUtcTime(firstLast.getFirst().getTime(), CET)) + SPACE + H);
            System.out.println("end time: " + DATE_TIME_FORMATTER.format(
                    DateTimeUtil.convertFromUtcTime(firstLast.getLast().getTime(), CET)) + SPACE + H);

            final Duration durationTotal =
                    calcDateTimeDifference(firstLast.getFirst().getTime(), firstLast.getLast().getTime());
            System.out.println("total duration: " + durationTotal.format() + SPACE + H);

            final Long durationInMotion = DurationInMotionCalculator.fromTrack(track);
            System.out.println("duration in motion: " + convertFromSeconds(durationInMotion).format() + SPACE + H);

            final Double speedMax = SpeedMaxCalculator.fromTrack(track);
            System.out.println("maximum speed: " + DECIMAL_FORMAT.format(speedMax) + SPACE + KMPH);

            final Double averageSpeed = SpeedAvgCalculator.fromTrack(track);
            System.out.println("average speed: " + DECIMAL_FORMAT.format(averageSpeed) + SPACE + KMPH);

            System.out.println("start position: Lat " + firstLast.getFirst().getLat()
                    + " / Lon " + firstLast.getFirst().getLon());
            System.out.println("end position: Lat " + firstLast.getLast().getLat()
                    + " / Lon " + firstLast.getLast().getLon());

            final GeocodeReverseModel startPos = GeocodeUtil.convertFromJson(GEOCODE_SERVICE.reverseGeocode(
                firstLast.getFirst().getLat().toString(), firstLast.getFirst().getLon().toString()));
            if (GeocodeUtil.isBounded(firstLast.getLast().getLat().doubleValue(),
                    firstLast.getLast().getLon().doubleValue(), startPos.getBoundingbox()[0],
                    startPos.getBoundingbox()[2], startPos.getBoundingbox()[1], startPos.getBoundingbox()[3])) {
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
