package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Gpx;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import javax.xml.namespace.QName;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public final class GpxWriter {

    private GpxWriter() {
    }

    /**
     * Converts the given {@code Gpx} object to its XML string representation.
     *
     * @param gpx the GPX object to convert
     * @return a string containing the XML representation of the GPX data
     * @throws IllegalArgumentException if the GPX object is {@code null}
     * @throws RuntimeException if marshalling fails
     */
    public static String toString(final Gpx gpx) {
        if (gpx == null) {
            throw new IllegalArgumentException("GPX object must not be null");
        }

        ensureCreator(gpx);

        try (StringWriter writer = new StringWriter()) {
            final JAXBElement<Gpx> rootElement = createGpxJaxbElement(gpx);
            final Marshaller marshaller = createGpxMarshaller();
            marshaller.marshal(rootElement, writer);
            return writer.toString();
        } catch (JAXBException | IOException e) {
            throw new RuntimeException("Failed to convert GPX object to string", e);
        }
    }

    /**
     * Writes the given {@code Gpx} object to a file at the specified path.
     *
     * @param gpx the GPX object to write
     * @param pathName the target file path
     * @return the written file
     * @throws IllegalArgumentException if the GPX object or path is {@code null} or empty
     * @throws RuntimeException if writing fails
     */
    public static File toFile(final Gpx gpx, final String pathName) {
        if (gpx == null || pathName == null || pathName.isBlank()) {
            throw new IllegalArgumentException("GPX object and pathName must not be null or empty");
        }

        ensureCreator(gpx);

        final File targetFile = new File(pathName);
        try {
            final File parentDir = targetFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                if (!parentDir.mkdirs()) {
                    throw new RuntimeException("Failed to create parent directory: " + parentDir.getAbsolutePath());
                }
            }

            final JAXBElement<Gpx> rootElement = createGpxJaxbElement(gpx);
            final Marshaller marshaller = createGpxMarshaller();
            marshaller.marshal(rootElement, targetFile);

            return targetFile;
        } catch (JAXBException e) {
            if (targetFile.exists() && !targetFile.delete()) {
                System.err.println("Warning: Failed to delete corrupted file: " + targetFile.getAbsolutePath());
            }
            throw new RuntimeException("Failed to write GPX object to file: " + pathName, e);
        }
    }

    private static void ensureCreator(final Gpx gpx) {
        if (gpx.getCreator() == null || gpx.getCreator().isEmpty()) {
            gpx.setCreator("gpx-stats-helper");
        }
    }

    private static JAXBElement<Gpx> createGpxJaxbElement(final Gpx gpx) {
        final QName qName = new QName("http://www.topografix.com/GPX/1/1", "gpx");
        return new JAXBElement<>(qName, Gpx.class, gpx);
    }

    private static Marshaller createGpxMarshaller() throws JAXBException {
        final JAXBContext context = JAXBContext.newInstance(Gpx.class);
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(
                Marshaller.JAXB_SCHEMA_LOCATION,
                "http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd"
        );
        return marshaller;
    }
}
