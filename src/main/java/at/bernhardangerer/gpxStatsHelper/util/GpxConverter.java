package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Gpx;

import javax.xml.bind.JAXB;
import java.io.File;
import java.io.StringReader;

public final class GpxConverter {

    private GpxConverter() {
    }

    public static Gpx convertGpxFromString(final String gpx) {
        if (gpx != null) {
            return JAXB.unmarshal(new StringReader(gpx), Gpx.class);
        }
        return null;
    }

    public static Gpx convertGpxFromFile(final File gpxFile) {
        if (gpxFile != null) {
            return JAXB.unmarshal(gpxFile, Gpx.class);
        }
        return null;
    }
}
