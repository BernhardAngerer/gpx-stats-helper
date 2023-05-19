package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.Duration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public final class DateTimeUtil {
    private DateTimeUtil() {
    }

    public static LocalDateTime utcToCet(final LocalDateTime utcTime) {
        final ZonedDateTime utcTimeZoned = ZonedDateTime.of(utcTime, ZoneId.of("UTC"));
        return utcTimeZoned.withZoneSameInstant(ZoneId.of("CET")).toLocalDateTime();
    }

    public static Duration calcDateTimeDifference(final LocalDateTime startTime, final LocalDateTime endTime) {
        final Duration duration = new Duration();
        LocalDateTime tempDateTime = LocalDateTime.from(startTime);

        final long years = tempDateTime.until(endTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);
        duration.setYears(years);

        final long months = tempDateTime.until(endTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);
        duration.setMonths(months);

        final long days = tempDateTime.until(endTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);
        duration.setDays(days);

        final long hours = tempDateTime.until(endTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);
        duration.setHours(hours);

        final long minutes = tempDateTime.until(endTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);
        duration.setMinutes(minutes);

        final long seconds = tempDateTime.until(endTime, ChronoUnit.SECONDS);
        duration.setSeconds(seconds);

        return duration;
    }

    public static long calcDateTimeDifferenceInSeconds(final LocalDateTime startTime, final LocalDateTime endTime) {
        return ChronoUnit.SECONDS.between(startTime, endTime);
    }

    public static Duration convertFromSeconds(final long seconds) {
        final Duration duration = new Duration();
        duration.setHours(seconds / 3600);
        duration.setMinutes((seconds % 3600) / 60);
        duration.setSeconds(seconds % 60);
        return duration;
    }
}
