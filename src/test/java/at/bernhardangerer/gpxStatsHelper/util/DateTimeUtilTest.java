package at.bernhardangerer.gpxStatsHelper.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DateTimeUtilTest {

    @Test
    void convertFromSeconds() {
        final var result = DateTimeUtil.convertFromSeconds(4000L);
        assertNotNull(result);
        assertEquals(0, result.getYears());
        assertEquals(0, result.getMonths());
        assertEquals(0, result.getDays());
        assertEquals(1, result.getHours());
        assertEquals(6, result.getMinutes());
        assertEquals(40, result.getSeconds());
    }

    @Test
    void utcToCet() {
        final var now = LocalDateTime.now();
        final var result = DateTimeUtil.utcToCet(now);
        assertNotNull(result);
        assertEquals(now.plusHours(2L), result);
    }

    @Test
    void calcDateTimeDifference() {
        final var now = LocalDateTime.now();
        final var result = DateTimeUtil.calcDateTimeDifference(now, now.plusSeconds(62L));
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
        final var now = LocalDateTime.now();
        final var result = DateTimeUtil.calcDateTimeDifferenceInSeconds(now, now.plusMinutes(1L));
        assertEquals(60, result);
    }
}
