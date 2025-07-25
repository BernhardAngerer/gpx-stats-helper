package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.DateTimeSegments;
import at.bernhardangerer.gpxStatsHelper.model.TimeSegments;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static at.bernhardangerer.gpxStatsHelper.util.Constants.SIXTY;
import static at.bernhardangerer.gpxStatsHelper.util.Constants.THREE_SIX_ZERO_ZERO;
import static at.bernhardangerer.gpxStatsHelper.util.Constants.UTC;

public final class DateTimeUtil {

    private DateTimeUtil() {
    }

    /**
     * Converts a {@link LocalDateTime} in UTC to a different time zone.
     *
     * @param utcTime  the UTC-based LocalDateTime to convert
     * @param timeZone target IANA time zone ID (e.g. "Europe/Vienna", "Asia/Tokyo")
     * @return LocalDateTime in the target time zone, or the original UTC time if zone is {@code null} or "UTC"
     */
    public static LocalDateTime convertFromUtcTime(final LocalDateTime utcTime, String timeZone) {
        if (timeZone != null && !UTC.equals(timeZone)) {
            final ZonedDateTime utcTimeZoned = ZonedDateTime.of(utcTime, ZoneId.of(UTC));
            return utcTimeZoned.withZoneSameInstant(ZoneId.of(timeZone)).toLocalDateTime();
        }
        return utcTime;
    }

    /**
     * Calculates the full difference between two {@link LocalDateTime} values
     * in terms of years, months, days, hours, minutes, and seconds.
     *
     * @param startTime the earlier time
     * @param endTime   the later time
     * @return {@link DateTimeSegments} object representing each time unit difference
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
     * Calculates the total time difference in seconds between two {@link LocalDateTime} values.
     *
     * @param startTime the starting timestamp
     * @param endTime   the ending timestamp
     * @return time difference in seconds as {@code long}
     */
    public static long calcDateTimeDifferenceInSeconds(final LocalDateTime startTime, final LocalDateTime endTime) {
        return ChronoUnit.SECONDS.between(startTime, endTime);
    }

    /**
     * Converts a number of seconds into a {@link TimeSegments} object
     * representing hours, minutes, and seconds.
     *
     * @param seconds total duration in seconds
     * @return {@link TimeSegments} with hours/minutes/seconds populated
     */
    public static TimeSegments convertFromSeconds(final long seconds) {
        final TimeSegments segments = new TimeSegments();
        segments.setHours(seconds / THREE_SIX_ZERO_ZERO);
        segments.setMinutes((seconds % THREE_SIX_ZERO_ZERO) / SIXTY);
        segments.setSeconds(seconds % SIXTY);
        return segments;
    }

    /**
     * Converts a {@link DateTimeSegments} instance into total number of seconds.
     * Note: Only hours, minutes, and seconds fields are considered.
     *
     * @param dateTimeSegments object containing time units
     * @return total time in seconds
     */
    public static long convertToSeconds(final DateTimeSegments dateTimeSegments) {
        return dateTimeSegments.getHours() * THREE_SIX_ZERO_ZERO + dateTimeSegments.getMinutes() * SIXTY + dateTimeSegments.getSeconds();
    }
}
