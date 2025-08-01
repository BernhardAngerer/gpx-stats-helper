package at.bernhardangerer.gpxStatsHelper;

import at.bernhardangerer.gpxStatsHelper.enumeration.StepRoundingMode;
import at.bernhardangerer.gpxStatsHelper.model.AscentDescentPair;
import at.bernhardangerer.gpxStatsHelper.model.BoundingBox;
import at.bernhardangerer.gpxStatsHelper.model.DateTimeSegments;
import at.bernhardangerer.gpxStatsHelper.model.ElevationRange;
import at.bernhardangerer.gpxStatsHelper.model.api.GeocodeReverseModel;
import at.bernhardangerer.gpxStatsHelper.service.GeocodeService;
import at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil;
import at.bernhardangerer.gpxStatsHelper.util.DistanceCalculator;
import at.bernhardangerer.gpxStatsHelper.util.DurationInMotionCalculator;
import at.bernhardangerer.gpxStatsHelper.util.ElevationCalculator;
import at.bernhardangerer.gpxStatsHelper.util.ElevationPeakUtil;
import at.bernhardangerer.gpxStatsHelper.util.ElevationRangeCalculator;
import at.bernhardangerer.gpxStatsHelper.util.GeoCoordinateConverter;
import at.bernhardangerer.gpxStatsHelper.util.GeocodeUtil;
import at.bernhardangerer.gpxStatsHelper.util.GeographicExtentUtil;
import at.bernhardangerer.gpxStatsHelper.util.GpxReader;
import at.bernhardangerer.gpxStatsHelper.util.PaceUtil;
import at.bernhardangerer.gpxStatsHelper.util.SlopeCalculator;
import at.bernhardangerer.gpxStatsHelper.util.SlopeUtil;
import at.bernhardangerer.gpxStatsHelper.util.SpeedAvgCalculator;
import at.bernhardangerer.gpxStatsHelper.util.SpeedMaxCalculator;
import at.bernhardangerer.gpxStatsHelper.util.TrackUtil;
import at.bernhardangerer.gpxStatsHelper.util.WaypointUtil;
import com.topografix.model.Gpx;
import com.topografix.model.Track;
import com.topografix.model.Waypoint;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static at.bernhardangerer.gpxStatsHelper.util.Constants.CET;
import static at.bernhardangerer.gpxStatsHelper.util.Constants.ESCAPE_DOUBLE_QUOTES;
import static at.bernhardangerer.gpxStatsHelper.util.Constants.H;
import static at.bernhardangerer.gpxStatsHelper.util.Constants.KM;
import static at.bernhardangerer.gpxStatsHelper.util.Constants.KMPH;
import static at.bernhardangerer.gpxStatsHelper.util.Constants.M;
import static at.bernhardangerer.gpxStatsHelper.util.Constants.MIN_PER_KM;
import static at.bernhardangerer.gpxStatsHelper.util.Constants.MSL;
import static at.bernhardangerer.gpxStatsHelper.util.Constants.ONE_DECIMAL_FORMAT;
import static at.bernhardangerer.gpxStatsHelper.util.Constants.ONE_HUNDRED;
import static at.bernhardangerer.gpxStatsHelper.util.Constants.ONE_THOUSAND;
import static at.bernhardangerer.gpxStatsHelper.util.Constants.SPACE;
import static at.bernhardangerer.gpxStatsHelper.util.Constants.THREE;
import static at.bernhardangerer.gpxStatsHelper.util.Constants.TWO_DECIMAL_FORMAT;
import static at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil.calcDateTimeDifference;
import static at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil.convertFromSeconds;
import static at.bernhardangerer.gpxStatsHelper.util.DateTimeUtil.convertToSeconds;
import static at.bernhardangerer.gpxStatsHelper.util.WaypointUtil.formatWaypoint;

public final class Example {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
    private static final GeocodeService GEOCODE_SERVICE = new GeocodeService();
    private static final String GEOPOSITION = " - Geoposition";

    private Example() {
    }

    /**
     * Load an example GPX file and calculate statistic parameters.
     *
     * @param args optional commandLine arguments
     */
    @SuppressWarnings({"checkstyle:UncommentedMain", "checkstyle:MultipleStringLiterals", "checkstyle:AvoidEscapedUnicodeCharacters"})
    public static void main(final String[] args) {
        final String pathName = "example/example1.gpx";
        final Gpx gpx = GpxReader.fromFile(pathName);
        System.out.println("File: \"" + pathName + ESCAPE_DOUBLE_QUOTES);

        if (gpx.getMetadata() != null) {
            System.out.println("\n\uD83D\uDDC2\uFE0F Metadata");
            if (gpx.getMetadata().getName() != null) {
                System.out.println("Name: \"" + gpx.getMetadata().getName() + ESCAPE_DOUBLE_QUOTES);
            }
            if (gpx.getMetadata().getAuthor() != null) {
                System.out.println("Author Name: \"" + gpx.getMetadata().getAuthor().getName() + ESCAPE_DOUBLE_QUOTES);
            }
            if (gpx.getMetadata().getCopyright() != null) {
                if (gpx.getMetadata().getCopyright().getYear() != null) {
                    System.out.println("Copyright Year: \"" + gpx.getMetadata().getCopyright().getYear().getYear() + ESCAPE_DOUBLE_QUOTES);
                }
                if (gpx.getMetadata().getCopyright().getAuthor() != null) {
                    System.out.println("Copyright Author: \"" + gpx.getMetadata().getCopyright().getAuthor() + ESCAPE_DOUBLE_QUOTES);
                }
            }
            if (gpx.getMetadata().getLink() != null && !gpx.getMetadata().getLink().isEmpty()) {
                gpx.getMetadata().getLink()
                        .forEach(linkType -> System.out.println("Link Href: \"" + linkType.getHref() + ESCAPE_DOUBLE_QUOTES));
            }
        }

        int count = 1;
        for (final Track track : gpx.getTrk()) {
            System.out.println("\n### Track Nr. " + count
                    + (track.getName() != null ? " - \"" + track.getName() + ESCAPE_DOUBLE_QUOTES : "") + " ###");

            System.out.println("\n\uD83E\uDDED General Track Info");
            final long numberOfWaypoints = TrackUtil.countWaypoints(track);
            System.out.println("Number Of Waypoints: " + numberOfWaypoints);

            System.out.println("\n\uD83D\uDCCF Distance & Elevation");
            final AscentDescentPair distance = DistanceCalculator.fromTrack(track);
            final double totalDistance = distance.getAscent().doubleValue() + distance.getDescent().doubleValue();
            System.out.println("Distance (Total): " + TWO_DECIMAL_FORMAT.format(totalDistance / ONE_THOUSAND) + SPACE + KM);
            System.out.println("Distance (Ascent): "
                    + TWO_DECIMAL_FORMAT.format(distance.getAscent().doubleValue() / ONE_THOUSAND) + SPACE + KM);
            System.out.println("Distance (Descent): "
                    + TWO_DECIMAL_FORMAT.format(distance.getDescent().doubleValue() / ONE_THOUSAND) + SPACE + KM);

            final AscentDescentPair elevation = ElevationCalculator.fromTrack(track);
            System.out.println("Elevation (Ascent): " + elevation.getAscent().setScale(0, RoundingMode.HALF_UP) + SPACE + M);
            System.out.println("Elevation (Descent): " + elevation.getDescent().setScale(0, RoundingMode.HALF_UP) + SPACE + M);

            final ElevationRange range = ElevationRangeCalculator.fromTrack(track);

            final Waypoint highestWaypoint = range.getHighest();
            System.out.println("Highest Point: "
                    + highestWaypoint.getEle().setScale(0, RoundingMode.HALF_UP) + SPACE + MSL);
            final Waypoint lowestWaypoint = range.getLowest();
            System.out.println("Lowest Point: "
                    + lowestWaypoint.getEle().setScale(0, RoundingMode.HALF_UP) + SPACE + MSL);

            final Waypoint firstWaypoint = WaypointUtil.findFirstWaypoint(track);
            final Waypoint lastWaypoint = WaypointUtil.findLastWaypoint(track);
            final Double slope = SlopeUtil.calcSlopePercent(firstWaypoint, lastWaypoint);
            System.out.println("Slope between start and end point: " + ONE_DECIMAL_FORMAT.format(slope) + " %");

            final int percentageStep = 10;
            final Map<Integer, Double> percentageStepMap =
                    SlopeCalculator.fromWaypointList(track.getTrkseg().get(0).getTrkpt(), percentageStep, StepRoundingMode.DOWN);
            percentageStepMap.entrySet().stream()
                    .sorted(Map.Entry.<Integer, Double>comparingByKey().reversed())
                    .forEach(entry -> System.out.println("Slope " + entry.getKey() + " to " + (entry.getKey() + percentageStep) + " % -> "
                            + TWO_DECIMAL_FORMAT.format(entry.getValue() / ONE_THOUSAND) + SPACE + KM));

            System.out.println("\n\uD83D\uDDFA\uFE0F Geographic Extents");
            final BoundingBox boundingBox = GeographicExtentUtil.findBounding(track);
            System.out.println("Latitude Range: " + boundingBox.getMinLat() + " => " + boundingBox.getMaxLat());
            System.out.println("Longitude Range: " + boundingBox.getMinLon() + " => " + boundingBox.getMaxLon());

            System.out.println("\n⏱\uFE0F Time & Duration");
            final DateTimeSegments durationTotal =
                    calcDateTimeDifference(firstWaypoint.getTime(), lastWaypoint.getTime());
            System.out.println("Duration (Total): " + durationTotal.format() + SPACE + H);

            final Long durationInMotion = DurationInMotionCalculator.fromTrack(track);
            System.out.println("Duration (Motion): " + convertFromSeconds(durationInMotion).format() + SPACE + H);

            final long durationAtRest = convertToSeconds(durationTotal) - durationInMotion;
            System.out.println("Duration (Rest): " + convertFromSeconds(durationAtRest).format() + SPACE + H);

            System.out.println("Start Time: " + DATE_TIME_FORMATTER.format(
                    DateTimeUtil.convertFromUtcTime(firstWaypoint.getTime(), CET)) + SPACE + H);
            System.out.println("End Time: " + DATE_TIME_FORMATTER.format(
                    DateTimeUtil.convertFromUtcTime(lastWaypoint.getTime(), CET)) + SPACE + H);

            System.out.println("\n\uD83D\uDEB4 Speed & Movement");
            final Double speedMax = SpeedMaxCalculator.fromTrack(track);
            System.out.println("Speed (Maximum): " + ONE_DECIMAL_FORMAT.format(speedMax) + SPACE + KMPH);

            final Double averageSpeed = SpeedAvgCalculator.fromTrack(track);
            System.out.println("Speed (Average): " + ONE_DECIMAL_FORMAT.format(averageSpeed) + SPACE + KMPH);

            final double paceTotal = PaceUtil.calculatePace(totalDistance, convertToSeconds(durationTotal));
            System.out.println("Pace Average (Total): " + PaceUtil.format(paceTotal) + SPACE + MIN_PER_KM);
            final double paceInMotion = PaceUtil.calculatePace(totalDistance, durationInMotion);
            System.out.println("Pace Average (Motion): " + PaceUtil.format(paceInMotion) + SPACE + MIN_PER_KM);

            System.out.println("\n\uD83D\uDCCD Geopositions");
            System.out.println("Start Position: " + formatWaypoint(firstWaypoint));
            System.out.println("End Position: " + formatWaypoint(lastWaypoint));

            final Waypoint farthestWaypoint = WaypointUtil.findFarthestWaypoint(firstWaypoint, track);
            System.out.println("Farthest Point: " + formatWaypoint(farthestWaypoint));

            final List<Waypoint> positivePeaks =
                    ElevationPeakUtil.findPositivePeaks(track.getTrkseg().get(0).getTrkpt(), BigDecimal.valueOf(ONE_HUNDRED));
            final AtomicInteger counter = new AtomicInteger(0);
            positivePeaks.forEach(waypoint -> {
                System.out.println("Positive Peak " + counter.incrementAndGet() + ": " + formatWaypoint(waypoint));
            });

            final List<Waypoint> negativePeaks =
                    ElevationPeakUtil.findNegativePeaks(track.getTrkseg().get(0).getTrkpt(), BigDecimal.valueOf(ONE_HUNDRED));
            counter.set(0);
            negativePeaks.forEach(waypoint -> {
                System.out.println("Negative Peak " + counter.incrementAndGet() + ": " + formatWaypoint(waypoint));
            });

            System.out.println("\n\uD83D\uDD04 Coordinate Format Conversion");
            System.out.println(formatWaypoint(firstWaypoint, false) + " -> " + GeoCoordinateConverter.waypointToDms(firstWaypoint));

            System.out.println("\n\uD83D\uDDE3\uFE0F Geolocation Metadata");
            final GeocodeReverseModel startPos = getGeocodeReverseModel(firstWaypoint);
            if (GeocodeUtil.isBounded(lastWaypoint.getLat().doubleValue(),
                    lastWaypoint.getLon().doubleValue(), startPos.getBoundingBox()[0],
                    startPos.getBoundingBox()[2], startPos.getBoundingBox()[1], startPos.getBoundingBox()[THREE])) {
                printPositionDescription("Start = End" + GEOPOSITION, startPos);
                printPositionUrl("Start = End" + GEOPOSITION, firstWaypoint);
            } else {
                printPositionDescription("Start" + GEOPOSITION, startPos);
                printPositionUrl("Start" + GEOPOSITION, firstWaypoint);
                final GeocodeReverseModel endPos = getGeocodeReverseModel(lastWaypoint);
                printPositionDescription("End" + GEOPOSITION, endPos);
                printPositionUrl("End" + GEOPOSITION, lastWaypoint);
            }

            count++;
        }
    }

    private static GeocodeReverseModel getGeocodeReverseModel(final Waypoint waypoint) {
        try {
            return GeocodeUtil.convertFromJson(GEOCODE_SERVICE.reverseGeocodeAsJson(waypoint));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void printPositionDescription(final String text, final GeocodeReverseModel pos) {
        System.out.println(text + ": " + pos.getDisplayName());
    }

    private static void printPositionUrl(final String text, final Waypoint waypoint) {
        System.out.println(text + " URL: " + GeocodeUtil.createOpenStreetMapUrl(waypoint));
    }
}
