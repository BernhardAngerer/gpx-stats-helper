package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Gpx;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import javax.xml.namespace.QName;
import java.io.File;
import java.io.StringWriter;

public final class GpxWriter {

    private GpxWriter() {
    }

    /**
     * Writes Gpx object into a String.
     *
     * @param gpx
     * @return Gpx string
     * @throws RuntimeException iif writing to string was not possible
     */
    public static String toString(final Gpx gpx) {
        if (gpx == null) {
            return null;
        }
        ensureCreator(gpx);

        try {
            final JAXBElement<Gpx> rootElement = createGpxJaxbElement(gpx);
            final Marshaller marshaller = createMarshaller();

            final StringWriter writer = new StringWriter();
            marshaller.marshal(rootElement, writer);

            return writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException("Unable to write string from gpx object", e);
        }
    }

    /**
     * Writes Gpx object into a File.
     *
     * @param gpx
     * @param pathName
     * @return Gpx File
     * @throws RuntimeException if writing to file was not possible
     */
    public static File toFile(final Gpx gpx, final String pathName) {
        if (gpx == null || pathName == null) {
            return null;
        }
        ensureCreator(gpx);

        try {
            // Ensure parent directories exist
            final File tempFile = new File(pathName);
            final File parentDir = tempFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            final JAXBElement<Gpx> rootElement = createGpxJaxbElement(gpx);
            final Marshaller marshaller = createMarshaller();

            final File gpxFile = new File(pathName);
            marshaller.marshal(rootElement, gpxFile);

            return gpxFile;
        } catch (JAXBException e) {
            throw new RuntimeException("Unable to write to file from gpx object", e);
        }
    }

    private static Marshaller createMarshaller() throws JAXBException {
        final JAXBContext context = JAXBContext.newInstance(Gpx.class);
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(
                Marshaller.JAXB_SCHEMA_LOCATION,
                "http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd"
        );
        return marshaller;
    }

    private static void ensureCreator(Gpx gpx) {
        if (gpx.getCreator() == null || gpx.getCreator().isEmpty()) {
            gpx.setCreator("gpx-stats-helper");
        }
    }

    private static JAXBElement<Gpx> createGpxJaxbElement(Gpx gpx) {
        final QName qName = new QName("http://www.topografix.com/GPX/1/1", "gpx");
        return new JAXBElement<>(qName, Gpx.class, gpx);
    }
}
