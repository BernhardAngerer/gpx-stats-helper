package at.bernhardangerer.gpxStatsHelper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TimeSegments {
    private static final String COLON = ":";
    private long hours;
    private long minutes;
    private long seconds;

    /**
     * Formats this {@code TimeSegments} instance into a zero-padded, human-readable string in "HH:mm:ss" format.
     * <p>
     * Example: if hours = 3, minutes = 7, seconds = 4 → result = {@code "03:07:04"}
     *
     * @return a formatted time string, e.g., {@code "01:23:45"}
     */
    public String format() {
        final DecimalFormat df = new DecimalFormat("00");
        return df.format(getHours()) + COLON + df.format(getMinutes()) + COLON + df.format(getSeconds());
    }
}
