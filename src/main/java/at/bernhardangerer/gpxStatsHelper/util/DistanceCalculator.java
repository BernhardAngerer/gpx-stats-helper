package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.enumeration.SlopeSensitivity;
import at.bernhardangerer.gpxStatsHelper.model.ElevationProfile;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;

import java.math.BigDecimal;
import java.util.List;

public final class DistanceCalculator {

    private DistanceCalculator() {
    }

    /**
     * Calculates the elevation profile (distance classified into ascent, flat, descent, unknown)
     * for a given ordered list of waypoints using a sliding window approach.
     * <p>
     * The window size and slope threshold are determined by the provided {@link SlopeSensitivity}.
     * The method adapts to the track length if there are fewer waypoints than the configured window size.
     * <p>
     * The method sums the distances between consecutive waypoints and classifies each segment according
     * to the slope calculated over the sliding window centered around that segment.
     *
     * @param waypoints   an ordered list of {@link Waypoint} objects representing the track points; must contain at least 2 points
     * @param sensitivity the {@link SlopeSensitivity} setting defining the sliding window size and slope threshold
     * @return an {@link ElevationProfile} summarizing total distances for ascent, flat, descent, and unknown categories;
     *         returns {@code null} if input is {@code null} or has fewer than 2 waypoints
     */
    static ElevationProfile fromWaypointList(final List<Waypoint> waypoints, final SlopeSensitivity sensitivity) {
        if (waypoints == null || sensitivity == null || waypoints.size() < 2) {
            return null;
        }

        final int windowSize = Math.min(sensitivity.getWindowSize(), waypoints.size());
        final double threshold = sensitivity.getSlopeThreshold();
        final int waypointSize = waypoints.size();
        final ElevationProfile result = new ElevationProfile();

        final int half = windowSize / 2;
        final int maxStart = waypointSize - windowSize;

        for (int iter = 0; iter < waypointSize - 1; iter++) {
            int start = iter + 1 - half;
            if (start < 0) {
                start = 0;
            } else if (start > maxStart) {
                start = maxStart;
            }
            final int end = start + windowSize - 1;

            final Double slope = SlopeUtil.calcSlopePercent(waypoints.get(start), waypoints.get(end));
            final Double distance = DistanceUtil.calcDistance(waypoints.get(iter), waypoints.get(iter + 1));
            final BigDecimal distBd = BigDecimal.valueOf(distance);

            classifyDistance(result, slope, threshold, distBd);
        }

        return result;
    }

    /**
     * Classifies the given distance into ascent, flat, descent, or unknown category within the
     * given {@link ElevationProfile} based on the slope value and threshold.
     *
     * @param profile   the {@link ElevationProfile} object to accumulate results into; must not be {@code null}
     * @param slope     the slope value (percentage) used for classification; may be {@code null} indicating unknown slope
     * @param threshold the threshold value for slope to differentiate ascent, flat, and descent
     * @param distance  the distance between two waypoints as a {@link BigDecimal}, to add to the appropriate category
     */
    private static void classifyDistance(final ElevationProfile profile, final Double slope, final double threshold,
                                         final BigDecimal distance) {
        if (slope != null) {
            if (profile.getAscent() == null) {
                profile.setAscent(BigDecimal.ZERO);
            }
            if (profile.getFlat() == null) {
                profile.setFlat(BigDecimal.ZERO);
            }
            if (profile.getDescent() == null) {
                profile.setDescent(BigDecimal.ZERO);
            }

            if (slope > threshold) {
                profile.setAscent(profile.getAscent().add(distance));
            } else if (slope >= -threshold) {
                profile.setFlat(profile.getFlat().add(distance));
            } else {
                profile.setDescent(profile.getDescent().add(distance));
            }
        } else {
            if (profile.getUnknown() == null) {
                profile.setUnknown(BigDecimal.ZERO);
            }
            profile.setUnknown(profile.getUnknown().add(distance));
        }
    }

    /**
     * Calculates the ascent, descent, flat, and unknown distances for a single
     * {@link TrackSegment} using slope-based classification with the specified sensitivity.
     * <p>
     * This method delegates to {@link #fromWaypointList(List, SlopeSensitivity)},
     * extracting the ordered list of waypoints from the track segment and applying
     * sliding-window slope analysis.
     *
     * @param trackSegment the GPX track segment to analyze; must not be {@code null}
     * @param sensitivity  slope sensitivity settings (window size and slope threshold)
     * @return an {@link ElevationProfile} with total ascent, descent, flat, and unknown distances,
     *         or {@code null} if the track segment is {@code null} or contains insufficient data
     * @see #fromWaypointList(List, SlopeSensitivity)
     * @see SlopeSensitivity
     */
    static ElevationProfile fromTrackSegment(final TrackSegment trackSegment, final SlopeSensitivity sensitivity) {
        if (trackSegment == null) {
            return null;
        }
        return fromWaypointList(trackSegment.getTrkpt(), sensitivity);
    }

    /**
     * Aggregates ascent, flat, descent, and unknown distances over a list of GPX track segments
     * using the specified slope sensitivity for classification.
     * <p>
     * Each {@link TrackSegment} is processed individually via {@link #fromTrackSegment(TrackSegment, SlopeSensitivity)},
     * and the resulting {@link ElevationProfile} values are summed into a cumulative profile.
     * <p>
     * Only non-null distance values are accumulated. If a given profile field (ascent, flat, descent, unknown)
     * is {@code null} in the result before adding, it will be initialized to {@link BigDecimal#ZERO}.
     *
     * @param trackSegmentList the ordered list of track segments to process; must not be {@code null} or empty
     * @param sensitivity      the slope sensitivity preset that defines the window size and classification threshold
     * @return a combined {@link ElevationProfile} representing the total classified distances across all segments,
     *         or {@code null} if the input list is invalid
     *
     * @see SlopeSensitivity
     * @see #fromTrackSegment(TrackSegment, SlopeSensitivity)
     */
    static ElevationProfile fromTrackSegmentList(final List<TrackSegment> trackSegmentList, final SlopeSensitivity sensitivity) {
        if (trackSegmentList == null || trackSegmentList.isEmpty()) {
            return null;
        }

        final ElevationProfile result = new ElevationProfile();
        for (final TrackSegment trackSegment : trackSegmentList) {
            final ElevationProfile profile = fromTrackSegment(trackSegment, sensitivity);
            if (profile == null) {
                continue;
            }

            if (profile.getAscent() != null || profile.getFlat() != null || profile.getDescent() != null) {
                if (result.getAscent() == null) {
                    result.setAscent(BigDecimal.ZERO);
                }
                result.setAscent(result.getAscent().add(profile.getAscent()));

                if (result.getFlat() == null) {
                    result.setFlat(BigDecimal.ZERO);
                }
                result.setFlat(result.getFlat().add(profile.getFlat()));

                if (result.getDescent() == null) {
                    result.setDescent(BigDecimal.ZERO);
                }
                result.setDescent(result.getDescent().add(profile.getDescent()));
            }
            if (profile.getUnknown() != null) {
                if (result.getUnknown() == null) {
                    result.setUnknown(BigDecimal.ZERO);
                }
                result.setUnknown(result.getUnknown().add(profile.getUnknown()));
            }
        }
        return result;
    }

    /**
     * Calculates the aggregated ascent, flat, descent, and unknown distance values
     * for an entire GPX {@link Track} using slope classification with the specified sensitivity.
     * <p>
     * The computation is performed by iterating over all {@link TrackSegment} elements in the track
     * and summing their classified distances according to the {@link SlopeSensitivity} settings.
     * <p>
     * If the track is {@code null} or contains no valid segments, this method returns {@code null}.
     *
     * @param track       the GPX {@link Track} to process; may be {@code null}
     * @param sensitivity the slope classification sensitivity to apply; must not be {@code null}
     * @return an aggregated {@link ElevationProfile} with total distances for ascent, flat, descent, and unknown segments,
     *         or {@code null} if the track is {@code null} or contains no valid data
     */
    public static ElevationProfile fromTrack(final Track track, final SlopeSensitivity sensitivity) {
        if (track == null) {
            return null;
        }
        return fromTrackSegmentList(track.getTrkseg(), sensitivity);
    }

}
