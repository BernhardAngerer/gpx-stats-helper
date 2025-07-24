package at.bernhardangerer.gpxStatsHelper.util;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import static at.bernhardangerer.gpxStatsHelper.util.Constants.FORMATTER_WITHOUT_MILLIS;
import static at.bernhardangerer.gpxStatsHelper.util.Constants.FORMATTER_WITH_MILLIS;

public final class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(final String v) {
        if (Objects.nonNull(v)) {
            try {
                return OffsetDateTime.parse(v, FORMATTER_WITH_MILLIS).toLocalDateTime();
            } catch (DateTimeParseException ignored) {
                try {
                    return OffsetDateTime.parse(v, FORMATTER_WITHOUT_MILLIS).toLocalDateTime();
                } catch (DateTimeParseException e) {
                    throw new RuntimeException("Failed to parse datetime: " + v, e);
                }
            }
        }
        return null;
    }

    @Override
    public String marshal(final LocalDateTime v) {
        if (Objects.nonNull(v)) {
            return v.atOffset(ZoneOffset.UTC).format(FORMATTER_WITHOUT_MILLIS);
        }
        return null;
    }
}
