package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.DateTimeSegments;
import at.bernhardangerer.gpxStatsHelper.model.TimeSegments;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public final class DateTimeUtil {

    private static final String UTC = "UTC";
    private static final int SIXTY = 60;
    private static final int THREE_SIX_ZERO_ZERO = SIXTY * SIXTY;

    private DateTimeUtil() {
    }

    /**
     * Convert LocalDateTime in UTC to another time zone.
     *
     * @param utcTime
     * @param timeZone
     * @return LocalDateTime into the provided time zone
     */
    public static LocalDateTime convertFromUtcTime(final LocalDateTime utcTime, String timeZone) {
        if (timeZone != null && !UTC.equals(timeZone)) {
            final ZonedDateTime utcTimeZoned = ZonedDateTime.of(utcTime, ZoneId.of(UTC));
            return utcTimeZoned.withZoneSameInstant(ZoneId.of(timeZone)).toLocalDateTime();
        }
        return utcTime;
    }

    /**
     * Calculate time differance between two provided LocalDateTime.
     *
     * @param startTime
     * @param endTime
     * @return DateTimeSegments
     */
    public static DateTimeSegments calcDateTimeDifference(final LocalDateTime startTime, final LocalDateTime endTime) {
        LocalDateTime tempDateTime = startTime;

        final long years = tempDateTime.until(endTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);

        final long months = tempDateTime.until(endTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);

        final long days = tempDateTime.until(endTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);

        final long hours = tempDateTime.until(endTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);

        final long minutes = tempDateTime.until(endTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);

        final long seconds = tempDateTime.until(endTime, ChronoUnit.SECONDS);

        return new DateTimeSegments(years, months, days, hours, minutes, seconds);
    }

    /**
     * Calculate time difference in seconds between two provided LocalDateTime.
     *
     * @param startTime
     * @param endTime
     * @return time difference in seconds
     */
    public static long calcDateTimeDifferenceInSeconds(final LocalDateTime startTime, final LocalDateTime endTime) {
        return ChronoUnit.SECONDS.between(startTime, endTime);
    }

    /**
     * Convert seconds to TimeSegments.
     *
     * @param seconds
     * @return TimeSegments
     */
    public static TimeSegments convertFromSeconds(final long seconds) {
        final TimeSegments segments = new TimeSegments();
        segments.setHours(seconds / THREE_SIX_ZERO_ZERO);
        segments.setMinutes((seconds % THREE_SIX_ZERO_ZERO) / SIXTY);
        segments.setSeconds(seconds % SIXTY);
        return segments;
    }

    /**
     * Convert DateTimeSegments to seconds.
     *
     * @param dateTimeSegments
     * @return seconds
     */
    public static long convertToSeconds(final DateTimeSegments dateTimeSegments) {
        return dateTimeSegments.getHours() * THREE_SIX_ZERO_ZERO + dateTimeSegments.getMinutes() * SIXTY + dateTimeSegments.getSeconds();
    }
}
