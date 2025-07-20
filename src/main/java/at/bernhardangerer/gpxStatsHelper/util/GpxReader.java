package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Gpx;
import jakarta.xml.bind.JAXB;

import java.io.File;
import java.io.StringReader;

public final class GpxReader {

    private GpxReader() {
    }

    /**
     * Reads gpx string and converts into a Gpx object.
     *
     * @param gpx
     * @return Gpx object
     */
    public static Gpx fromString(final String gpx) {
        if (gpx != null) {
            return JAXB.unmarshal(new StringReader(gpx), Gpx.class);
        }
        return null;
    }

    /**
     * Reads gpx file and converts into a Gpx object.
     *
     * @param gpxFile
     * @return Gpx object
     */
    public static Gpx fromFile(final File gpxFile) {
        if (gpxFile != null) {
            return JAXB.unmarshal(gpxFile, Gpx.class);
        }
        return null;
    }
}
