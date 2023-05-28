package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.Duration;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DateTimeUtilTest {

    @Test
    void convertFromSeconds() {
        final Duration result = DateTimeUtil.convertFromSeconds(4000L);
        assertNotNull(result);
        assertEquals(0, result.getYears());
        assertEquals(0, result.getMonths());
        assertEquals(0, result.getDays());
        assertEquals(1, result.getHours());
        assertEquals(6, result.getMinutes());
        assertEquals(40, result.getSeconds());
    }

    @Test
    void convertFromUtcTime() {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime result = DateTimeUtil.convertFromUtcTime(now, "CET");
        assertNotNull(result);
        assertEquals(now.plusHours(2L), result);
    }

    @Test
    void calcDateTimeDifference() {
        final LocalDateTime now = LocalDateTime.now();
        final Duration result = DateTimeUtil.calcDateTimeDifference(now, now.plusSeconds(62L));
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
