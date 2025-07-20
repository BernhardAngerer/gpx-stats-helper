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
     * Formats the time segments into a more readable format.
     *
     * @return formated time segments
     */
    public String format() {
        final DecimalFormat df = new DecimalFormat("00");
        return df.format(getHours()) + COLON + df.format(getMinutes()) + COLON + df.format(getSeconds());
    }
}
