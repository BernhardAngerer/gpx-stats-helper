package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Gpx;

import javax.xml.bind.JAXB;
import java.io.File;
import java.io.StringReader;

public final class GpxConverter {

    private GpxConverter() {
    }

    /**
     * Convert gpx string into a Gpx object.
     *
     * @param gpx
     * @return Gpx object
     */
    public static Gpx convertGpxFromString(final String gpx) {
        if (gpx != null) {
            return JAXB.unmarshal(new StringReader(gpx), Gpx.class);
        }
        return null;
    }

    /**
     * Convert gpx file input into a Gpx object.
     *
     * @param gpxFile
     * @return Gpx object
     */
    public static Gpx convertGpxFromFile(final File gpxFile) {
        if (gpxFile != null) {
            return JAXB.unmarshal(gpxFile, Gpx.class);
        }
        return null;
    }
}
