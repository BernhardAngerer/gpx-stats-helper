package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.CopyrightType;
import com.topografix.model.Gpx;
import com.topografix.model.MetadataType;
import com.topografix.model.PersonType;
import com.topografix.model.Track;
import com.topografix.model.TrackSegment;
import com.topografix.model.Waypoint;
import org.apache.xml.security.Init;
import org.apache.xml.security.c14n.Canonicalizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static at.bernhardangerer.gpxStatsHelper.util.WaypointUtil.createWaypoint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GpxWriterTest {

    static {
        Init.init(); // required to initialize Apache XML Security
    }

    private Gpx gpx;

    private final String canonicalizedGpxString =
            """
                    <gpx xmlns="http://www.topografix.com/GPX/1/1" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" \
                    creator="gpx-stats-helper" xsi:schemaLocation="http://www.topografix.com/GPX/1/1 \
                    http://www.topografix.com/GPX/1/1/gpx.xsd">
                        <metadata>
                            <name>My gpx file</name>
                            <author>
                                <name>Bernhard Angerer</name>
                            </author>
                            <copyright author="gpx-stats-helper">
                                <year>2025</year>
                                <license>free</license>
                            </copyright>
                        </metadata>
                        <trk>
                            <name>My short tour</name>
                            <trkseg>
                                <trkpt lat="47.80743" lon="12.378228">
                                    <ele>587</ele>
                                    <time>2025-04-07T16:14:16Z</time>
                                </trkpt>
                                <trkpt lat="47.807343" lon="12.378138">
                                    <ele>588</ele>
                                    <time>2025-04-07T16:14:17Z</time>
                                </trkpt>
                                <trkpt lat="47.807343" lon="12.378">
                                    <ele>589</ele>
                                    <time>2025-04-07T16:14:18Z</time>
                                </trkpt>
                            </trkseg>
                        </trk>
                    </gpx>""";

    @BeforeEach
    void init() throws DatatypeConfigurationException {
        gpx = new Gpx();
        gpx.setCreator("gpx-stats-helper");

        final MetadataType metadataType = new MetadataType();
        metadataType.setName("My gpx file");
        final PersonType personType = new PersonType();
        personType.setName("Bernhard Angerer");
        metadataType.setAuthor(personType);
        final CopyrightType copyrightType = new CopyrightType();
        copyrightType.setAuthor("gpx-stats-helper");
        copyrightType.setLicense("free");
        final DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        final XMLGregorianCalendar xmlYear = datatypeFactory.newXMLGregorianCalendarDate(
                2025, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED);
        copyrightType.setYear(xmlYear);
        metadataType.setCopyright(copyrightType);
        gpx.setMetadata(metadataType);

        final List<Track> tracks = gpx.getTrk();
        final Track track = new Track();
        track.setName("My short tour");
        tracks.add(track);
        final List<TrackSegment> trackSegments = track.getTrkseg();
        final TrackSegment trackSegment = new TrackSegment();
        trackSegments.add(trackSegment);
        final List<Waypoint> waypoints = trackSegment.getTrkpt();

        final Waypoint waypoint1 = createWaypoint(47.80743, 12.378228, 587, LocalDateTime.of(2025, 4, 7, 16, 14, 16));
        waypoints.add(waypoint1);

        final Waypoint waypoint2 = createWaypoint(47.807343, 12.378138, 588, LocalDateTime.of(2025, 4, 7, 16, 14, 17));
        waypoints.add(waypoint2);

        final Waypoint waypoint3 = createWaypoint(47.807343, 12.378000, 589, LocalDateTime.of(2025, 4, 7, 16, 14, 18));
        waypoints.add(waypoint3);
    }

    @Test
    void toStringSuccess() throws Exception {
        final String xml = GpxWriter.toString(gpx);
        assertNotNull(xml);
        assertEquals(canonicalizedGpxString, canonicalize(xml));
    }

    @Test
    void toStringFails() {
        assertThrows(IllegalArgumentException.class, () -> GpxWriter.toString(null));
    }

    @Test
    void toStringWithSimplestGpxSuccess() throws Exception {
        final String canonicalizedGpxString = "<gpx xmlns=\"http://www.topografix.com/GPX/1/1\" "
                + "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" creator=\"gpx-stats-helper\" "
                + "xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd\"></gpx>";
        final String xml = GpxWriter.toString(new Gpx());
        assertNotNull(xml);
        assertEquals(canonicalizedGpxString, canonicalize(xml));
    }

    @Test
    void toFileSuccess() throws Exception {
        final String fileName = "writerOutputFile.gpx";
        final String pathName = "output/" + fileName;
        File file = null;
        try {
            file = GpxWriter.toFile(gpx, pathName);
            assertNotNull(file);
            assertEquals(fileName, file.getName());
            final String xml = Files.readString(Path.of(pathName));
            assertEquals(canonicalizedGpxString, canonicalize(xml));
        } finally {
            if (file != null && file.exists()) {
                file.delete();

                final File parentDir = file.getParentFile();
                if (parentDir != null && parentDir.exists() && parentDir.isDirectory()) {
                    parentDir.delete();
                }
            }
        }
    }

    @Test
    void toFileFails() {
        assertThrows(IllegalArgumentException.class, () -> GpxWriter.toFile(null, null));
    }

    private static String canonicalize(final String xmlInput) throws Exception {
        final Canonicalizer canonicalizer = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_WITH_COMMENTS);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        canonicalizer.canonicalize(xmlInput.getBytes(StandardCharsets.UTF_8), outputStream, false);
        return outputStream.toString(StandardCharsets.UTF_8);
    }
}
