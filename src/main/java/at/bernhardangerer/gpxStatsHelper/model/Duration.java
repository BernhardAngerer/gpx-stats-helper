package at.bernhardangerer.gpxStatsHelper.model;

import java.text.DecimalFormat;

public final class Duration {
    private long years;
    private long months;
    private long days;
    private long hours;
    private long minutes;
    private long seconds;

    public Duration() {
    }

    public Duration(long years, long months, long days, long hours, long minutes, long seconds) {
        this.years = years;
        this.months = months;
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public long getYears() {
        return years;
    }

    public void setYears(final long years) {
        this.years = years;
    }

    public long getMonths() {
        return months;
    }

    public void setMonths(final long months) {
        this.months = months;
    }

    public long getDays() {
        return days;
    }

    public void setDays(final long days) {
        this.days = days;
    }

    public long getHours() {
        return hours;
    }

    public void setHours(final long hours) {
        this.hours = hours;
    }

    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(final long minutes) {
        this.minutes = minutes;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(final long seconds) {
        this.seconds = seconds;
    }

    public String format() {
        final DecimalFormat df = new DecimalFormat("00");
        final StringBuilder stringBuilder = new StringBuilder();
        if (years > 0) {
            stringBuilder.append(years)
                    .append(" years");
        }
        if (months > 0) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(" / ");
            }
            stringBuilder.append(months)
                    .append(" months");
        }
        if (days > 0) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(" / ");
            }
            stringBuilder.append(days)
                    .append(" days");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.append(" / ");
        }
        stringBuilder.append(df.format(hours))
                .append(":")
                .append(df.format(minutes))
                .append(":")
                .append(df.format(seconds));
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "Duration {"
            + "years=" + years
            + ", months=" + months
            + ", days=" + days
            + ", hours=" + hours
            + ", minutes=" + minutes
            + ", seconds=" + seconds
            + '}';
    }
}
