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
import at.bernhardangerer.gpxStatsHelper.util.ElevationPeakUtil;
import at.bernhardangerer.gpxStatsHelper.util.SpeedAvgCalculator;
import at.bernhardangerer.gpxStatsHelper.util.SpeedMaxCalculator;
import com.topografix.model.Gpx;
import com.topografix.model.Track;
import com.topografix.model.Waypoint;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Objects;

import static at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil.calcDateTimeDifference;
import static at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil.convertFromSeconds;
import static at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil.convertToSeconds;

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
            System.out.println("\n### Track Nr. " + count
                    + (track.getName() != null ? " - \"" + track.getName() + "\"" : "") + " ###");

            final Double distance = DistanceTotalCalculator.fromTrack(track);
            System.out.println("Total Distance: " + DECIMAL_FORMAT.format(distance / 1000) + SPACE + KM);

            final ElevationDelta delta = ElevationDeltaCalculator.fromTrack(track);
            System.out.println("Ascent: " + delta.getAscent().setScale(0, RoundingMode.HALF_UP) + SPACE + M);
            System.out.println("Descent: " + delta.getDescent().setScale(0, RoundingMode.HALF_UP) + SPACE + M);

            final ElevationRange range = ElevationRangeCalculator.fromTrack(track);
            System.out.println("Highest Point: "
                    + range.getHighest().getEle().setScale(0, RoundingMode.HALF_UP) + SPACE + MSL);
            final GeocodeReverseModel highestGeoposition =
                    GeocodeUtil.convertFromJson(GEOCODE_SERVICE.reverseGeocodeAsJson(range.getHighest()));
            System.out.println("Highest Geoposition: " + highestGeoposition.getDisplayName());

            System.out.println("Lowest Point: "
                    + range.getLowest().getEle().setScale(0, RoundingMode.HALF_UP) + SPACE + MSL);
            final GeocodeReverseModel lowestGeoposition =
                    GeocodeUtil.convertFromJson(GEOCODE_SERVICE.reverseGeocodeAsJson(range.getLowest()));
            System.out.println("Lowest Geoposition: " + lowestGeoposition.getDisplayName());

            final FirstLastWaypoint firstLast = FirstLastWaypointCalculator.fromTrack(track);

            System.out.println("Start Time: " + DATE_TIME_FORMATTER.format(
                    DateTimeUtil.convertFromUtcTime(firstLast.getFirst().getTime(), CET)) + SPACE + H);
            System.out.println("End Time: " + DATE_TIME_FORMATTER.format(
                    DateTimeUtil.convertFromUtcTime(firstLast.getLast().getTime(), CET)) + SPACE + H);

            final Duration durationTotal =
                    calcDateTimeDifference(firstLast.getFirst().getTime(), firstLast.getLast().getTime());
            System.out.println("Total Duration: " + durationTotal.format() + SPACE + H);

            final Long durationInMotion = DurationInMotionCalculator.fromTrack(track);
            System.out.println("Duration In Motion: " + convertFromSeconds(durationInMotion).format() + SPACE + H);

            final long durationAtRest = convertToSeconds(durationTotal) - durationInMotion;
            System.out.println("Duration At Rest: " + convertFromSeconds(durationAtRest).format() + SPACE + H);

            final Double speedMax = SpeedMaxCalculator.fromTrack(track);
            System.out.println("Maximum Speed: " + DECIMAL_FORMAT.format(speedMax) + SPACE + KMPH);

            final Double averageSpeed = SpeedAvgCalculator.fromTrack(track);
            System.out.println("Average Speed: " + DECIMAL_FORMAT.format(averageSpeed) + SPACE + KMPH);

            System.out.println("Start Position: Lat " + firstLast.getFirst().getLat()
                    + " / Lon " + firstLast.getFirst().getLon());
            System.out.println("End Position: Lat " + firstLast.getLast().getLat()
                    + " / Lon " + firstLast.getLast().getLon());

            final GeocodeReverseModel startPos =
                    GeocodeUtil.convertFromJson(GEOCODE_SERVICE.reverseGeocodeAsJson(firstLast.getFirst()));
            if (GeocodeUtil.isBounded(firstLast.getLast().getLat().doubleValue(),
                    firstLast.getLast().getLon().doubleValue(), startPos.getBoundingbox()[0],
                    startPos.getBoundingbox()[2], startPos.getBoundingbox()[1], startPos.getBoundingbox()[3])) {
                System.out.println("Start = End Geoposition: " + startPos.getDisplayName());
            } else {
                System.out.println("Start Geoposition: " + startPos.getDisplayName());
                System.out.println("End Geoposition: " + GeocodeUtil.convertFromJson(GEOCODE_SERVICE.reverseGeocodeAsJson(
                    firstLast.getLast())).getDisplayName());
            }

            final List<Waypoint> peaks = ElevationPeakUtil.findPeaks(track.getTrkseg().get(0).getTrkpt(), BigDecimal.valueOf(100));
            System.out.println("Number of (positive) Peaks: " + peaks.size());
            peaks.forEach(waypoint -> System.out.println(GeocodeUtil.createOpenStreetMapUrl(waypoint) + " / " + waypoint));

            count++;
        }
    }
}
