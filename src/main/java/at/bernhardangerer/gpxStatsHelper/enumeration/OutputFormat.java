package at.bernhardangerer.gpxStatsHelper.enumeration;

import lombok.Getter;

@Getter
public enum OutputFormat {
    XML("xml"),
    JSON("json"),
    JSON_V2("jsonv2"),
    GEO_JSON("geojson"),
    GEOCODE_JSON("geocodejson");

    private final String format;

    OutputFormat(final String format) {
        this.format = format;
    }

}
