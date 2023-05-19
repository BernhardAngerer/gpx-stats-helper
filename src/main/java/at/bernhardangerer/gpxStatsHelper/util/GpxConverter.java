package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.GpxType;

import javax.xml.bind.JAXB;
import java.io.File;
import java.io.StringReader;

public final class GpxConverter {

    private GpxConverter() {
    }

    public static GpxType convertGpxFromString(final String gpx) {
        if (gpx != null) {
            return JAXB.unmarshal(new StringReader(gpx), GpxType.class);
        }
        return null;
    }

    public static GpxType convertGpxFromFile(final File gpxFile) {
        if (gpxFile != null) {
            return JAXB.unmarshal(gpxFile, GpxType.class);
        }
        return null;
    }
}
