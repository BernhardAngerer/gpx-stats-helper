package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Gpx;
import jakarta.xml.bind.JAXB;

import java.io.File;
import java.io.StringReader;
import java.net.URL;

public final class GpxReader {

    private GpxReader() {
    }

    /**
     * Reads a GPX string and converts it into a {@code Gpx} object.
     *
     * @param gpx String
     * @return a {@code Gpx} object parsed from the string
     * @throws IllegalArgumentException if the gpx string is null or empty
     */
    public static Gpx fromString(final String gpx) {
        if (gpx == null || gpx.isEmpty()) {
            throw new IllegalArgumentException("gpx string must not be null or empty");
        }
        return JAXB.unmarshal(new StringReader(gpx), Gpx.class);
    }

    /**
     * Loads a GPX file and converts it into a {@code Gpx} object.
     *
     * @param gpxFile File
     * @return a {@code Gpx} object parsed from the file
     * @throws IllegalArgumentException if the file is null
     */
    public static Gpx fromFile(final File gpxFile) {
        if (gpxFile == null) {
            throw new IllegalArgumentException("file must not be null");
        }
        return JAXB.unmarshal(gpxFile, Gpx.class);
    }

    /**
     * Loads a GPX file from the given classpath-relative path and converts it into a {@code Gpx} object.
     *
     * @param pathName the classpath-relative path to the GPX file (e.g. "example/example.gpx")
     * @return a {@code Gpx} object parsed from the file
     * @throws IllegalArgumentException if the pathName is null or empty or the resource is not found
     */
    public static Gpx fromFile(final String pathName) {
        if (pathName == null || pathName.isEmpty()) {
            throw new IllegalArgumentException("pathName must not be null or empty");
        }
        final URL resource = GpxReader.class.getClassLoader().getResource(pathName);
        if (resource == null) {
            throw new IllegalArgumentException("Resource not found: " + pathName);
        }

        final File file = new File(resource.getFile());
        return fromFile(file);
    }
}
