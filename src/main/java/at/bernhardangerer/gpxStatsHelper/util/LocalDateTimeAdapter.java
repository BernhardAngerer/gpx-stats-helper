package at.bernhardangerer.gpxStatsHelper.util;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

import static at.bernhardangerer.gpxStatsHelper.util.Constants.FORMATTER_WITHOUT_MILLIS;
import static at.bernhardangerer.gpxStatsHelper.util.Constants.FORMATTER_WITHOUT_MILLIS_WITH_OFFSET;
import static at.bernhardangerer.gpxStatsHelper.util.Constants.FORMATTER_WITH_MILLIS;

public final class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    private static final List<DateTimeFormatter> FORMATTERS = List.of(
            FORMATTER_WITH_MILLIS,
            FORMATTER_WITHOUT_MILLIS,
            FORMATTER_WITHOUT_MILLIS_WITH_OFFSET
    );

    @Override
    public LocalDateTime unmarshal(final String v) {
        if (v == null) {
            return null;
        }

        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return OffsetDateTime.parse(v, formatter).toLocalDateTime();
            } catch (DateTimeParseException ignored) {
                // Try next formatter
            }
        }

        throw new RuntimeException("Failed to parse datetime: " + v);
    }

    @Override
    public String marshal(final LocalDateTime v) {
        if (Objects.nonNull(v)) {
            return v.atOffset(ZoneOffset.UTC).format(FORMATTER_WITHOUT_MILLIS);
        }
        return null;
    }
}
