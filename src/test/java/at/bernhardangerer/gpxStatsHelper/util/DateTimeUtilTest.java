package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.DateTimeSegments;
import at.bernhardangerer.gpxStatsHelper.model.TimeSegments;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DateTimeUtilTest {

    @Test
    void convertFromSeconds() {
        final TimeSegments result = DateTimeUtil.convertFromSeconds(4000L);
        assertNotNull(result);
        assertEquals(1, result.getHours());
        assertEquals(6, result.getMinutes());
        assertEquals(40, result.getSeconds());
    }

    @Test
    void convertToSeconds() {
        final long result = DateTimeUtil.convertToSeconds(new DateTimeSegments(0, 0, 0, 1, 6, 40));
        assertEquals(4000L, result);
    }

    @Test
    void convertFromUtcTime() {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime result = DateTimeUtil.convertFromUtcTime(now, "CET");

        final ZonedDateTime utcZoned = now.atZone(ZoneId.of("UTC"));
        final ZonedDateTime cetZoned = utcZoned.withZoneSameInstant(ZoneId.of("CET"));
        final long hoursOffset = cetZoned.getOffset().getTotalSeconds() / 3600;

        assertNotNull(result);
        assertEquals(now.plusHours(hoursOffset), result);
    }

    @Test
    void calcDateTimeDifference() {
        final LocalDateTime now = LocalDateTime.now();
        final DateTimeSegments result = DateTimeUtil.calcDateTimeDifference(now, now.plusSeconds(62L));
        assertNotNull(result);
        assertEquals(0, result.getYears());
        assertEquals(0, result.getMonths());
        assertEquals(0, result.getDays());
        assertEquals(0, result.getHours());
        assertEquals(1, result.getMinutes());
        assertEquals(2, result.getSeconds());
    }

    @Test
    void calcDateTimeDifferenceInSeconds() {
        final LocalDateTime now = LocalDateTime.now();
        final long result = DateTimeUtil.calcDateTimeDifferenceInSeconds(now, now.plusMinutes(1L));
        assertEquals(60, result);
    }
}
