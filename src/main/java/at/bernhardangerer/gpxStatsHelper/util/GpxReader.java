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
     * Parses a GPX-formatted XML string and converts it into a {@code Gpx} object.
     *
     * @param gpx the GPX XML content as a string (must not be {@code null} or empty)
     * @return a {@code Gpx} object representing the parsed data
     * @throws IllegalArgumentException if the input string is {@code null} or empty
     */
    public static Gpx fromString(final String gpx) {
        if (gpx == null || gpx.isEmpty()) {
            throw new IllegalArgumentException("gpx string must not be null or empty");
        }
        return JAXB.unmarshal(new StringReader(gpx), Gpx.class);
    }

    /**
     * Reads a GPX file from disk and converts it into a {@code Gpx} object.
     *
     * @param gpxFile the file containing GPX XML data (must not be {@code null})
     * @return a {@code Gpx} object representing the parsed data
     * @throws IllegalArgumentException if the file is {@code null}
     */
    public static Gpx fromFile(final File gpxFile) {
        if (gpxFile == null) {
            throw new IllegalArgumentException("file must not be null");
        }
        return JAXB.unmarshal(gpxFile, Gpx.class);
    }

    /**
     * Loads a GPX file from the classpath using the given relative path and parses it into a {@code Gpx} object.
     * <p>
     * This method expects the file to be available as a resource on the classpath
     * (e.g. {@code src/main/resources/example/example.gpx}).
     *
     * @param pathName the classpath-relative path to the GPX file (e.g. {@code "example/example.gpx"})
     * @return a {@code Gpx} object parsed from the specified file
     * @throws IllegalArgumentException if {@code pathName} is {@code null}, empty, or the resource cannot be found
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
