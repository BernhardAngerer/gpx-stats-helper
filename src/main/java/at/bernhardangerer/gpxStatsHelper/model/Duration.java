package at.bernhardangerer.gpxStatsHelper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@NoArgsConstructor
@AllArgsConstructor
@Data
public final class Duration {
    private static final String SEPARATOR = " / ";
    private static final String COLON = ":";
    private long years;
    private long months;
    private long days;
    private long hours;
    private long minutes;
    private long seconds;

    public String format() {
        final DecimalFormat df = new DecimalFormat("00");
        final StringBuilder stringBuilder = new StringBuilder();
        if (years > 0) {
            stringBuilder.append(years)
                    .append(" years");
        }
        if (months > 0) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(SEPARATOR);
            }
            stringBuilder.append(months)
                    .append(" months");
        }
        if (days > 0) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(SEPARATOR);
            }
            stringBuilder.append(days)
                    .append(" days");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.append(SEPARATOR);
        }
        stringBuilder.append(df.format(hours))
                .append(COLON)
                .append(df.format(minutes))
                .append(COLON)
                .append(df.format(seconds));
        return stringBuilder.toString();
    }

}
