package at.bernhardangerer.gpxStatsHelper.util;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public final class Constants {

    public static final String CET = "CET";
    public static final String UTC = "UTC";
    public static final String SPACE = " ";
    public static final String H = "h";
    public static final String M = "m";
    public static final String KM = "km";
    public static final String KMPH = "km/h";
    public static final String MIN_PER_KM = "min/km";
    public static final String MSL = "m.s.l.";
    public static final String ESCAPE_DOUBLE_QUOTES = "\"";

    public static final int ONE_THOUSAND = 1000;
    public static final int ONE_HUNDRED = 100;
    public static final int SIXTY = 60;
    public static final int THREE_SIX_ZERO_ZERO = SIXTY * SIXTY;
    public static final int THREE = 3;

    public static final DecimalFormat ONE_DECIMAL_FORMAT = new DecimalFormat("#.#");
    public static final DecimalFormat TWO_DECIMAL_FORMAT = new DecimalFormat("#.##");
    public static final DecimalFormat TWO_DIGIT_PADDING_FORMAT = new DecimalFormat("00");

    public static final DateTimeFormatter FORMATTER_WITH_MILLIS =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
    public static final DateTimeFormatter FORMATTER_WITHOUT_MILLIS =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");

    private Constants() {
    }

}
