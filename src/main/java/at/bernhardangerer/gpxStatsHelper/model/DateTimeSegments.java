package at.bernhardangerer.gpxStatsHelper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public final class DateTimeSegments extends TimeSegments {
    private static final String SEPARATOR = " / ";
    private long years;
    private long months;
    private long days;

    public DateTimeSegments(long years, long months, long days, long hours, long minutes, long seconds) {
        super(hours, minutes, seconds);
        this.years = years;
        this.months = months;
        this.days = days;
    }

    public String format() {
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

        return stringBuilder + super.format();
    }

}
