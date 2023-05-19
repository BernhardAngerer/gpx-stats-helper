package at.bernhardangerer.gpxStatsHelper.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public final class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @Override
    public LocalDateTime unmarshal(final String v) {
        if (Objects.nonNull(v)) {
            try {
                return LocalDateTime.parse(v, DATE_TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                throw new RuntimeException("Failed to parse datetime: " + v, e);
            }
        }
        return null;
    }

    @Override
    public String marshal(final LocalDateTime v) {
        if (Objects.nonNull(v)) {
            return v.format(DATE_TIME_FORMATTER);
        }
        return null;
    }
}
