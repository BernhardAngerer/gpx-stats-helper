package at.bernhardangerer.gpxStatsHelper.service;

import at.bernhardangerer.gpxStatsHelper.enumeration.OutputFormat;
import at.bernhardangerer.gpxStatsHelper.exception.WebserviceCallException;
import at.bernhardangerer.gpxStatsHelper.util.PropertyUtil;
import at.bernhardangerer.gpxStatsHelper.webservice.ApiClient;
import com.topografix.model.Waypoint;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

public final class GeocodeService {

    /**
     * API Documentation: https://nominatim.org/release-docs/develop/api/Overview/
     */
    private static final String REVERSE_BASE_URL = PropertyUtil.loadConfigValue("reverseBaseUrl");
    private static final String ZERO = "0";
    private static final String ONE = "1";
    private static final int EIGHTEEN = 18;
    private final ApiClient client;

    public GeocodeService() {
        this.client = new ApiClient();
    }

    GeocodeService(final ApiClient client) {
        this.client = client;
    }

    /**
     * Performs reverse geocoding for the specified latitude and longitude using the OpenStreetMap Nominatim API,
     * and returns the result in JSON format.
     *
     * @param lat the latitude value as a string
     * @param lon the longitude value as a string
     * @return a JSON string representing the reverse-geocoded location
     * @throws IOException if an I/O error occurs
     * @throws WebserviceCallException if the web service call fails or returns a non-successful status
     * @throws InterruptedException if the operation is interrupted
     * @throws URISyntaxException if the generated URI is invalid
     */
    public String reverseGeocodeAsJson(final String lat, final String lon)
            throws IOException, WebserviceCallException, InterruptedException, URISyntaxException {
        return reverseGeocode(lat, lon, OutputFormat.JSON, EIGHTEEN, true, false, false, false);
    }

    /**
     * Performs reverse geocoding using coordinates from the given {@link Waypoint},
     * and returns the result in JSON format.
     *
     * @param waypoint the {@code Waypoint} object containing latitude and longitude (must not be {@code null})
     * @return a JSON string representing the reverse-geocoded location
     * @throws IOException if an I/O error occurs
     * @throws WebserviceCallException if the web service call fails or returns a non-successful status
     * @throws InterruptedException if the operation is interrupted
     * @throws URISyntaxException if the generated URI is invalid
     */
    public String reverseGeocodeAsJson(final Waypoint waypoint)
            throws IOException, WebserviceCallException, InterruptedException, URISyntaxException {
        return reverseGeocode(waypoint.getLat().toString(), waypoint.getLon().toString(), OutputFormat.JSON,
                EIGHTEEN, true, false, false, false);
    }

    /**
     * Executes a reverse geocoding request to the OpenStreetMap Nominatim API with advanced options.
     *
     * @param lat the latitude as a string
     * @param lon the longitude as a string
     * @param outputFormat desired output format (e.g., {@code JSON}, {@code XML})
     * @param zoomLevel level of detail (0 = continent, 18 = building)
     * @param addressDetails if {@code true}, include address breakdown (e.g., street, city)
     * @param extraTags if {@code true}, include additional tags such as website, opening hours
     * @param nameDetails if {@code true}, include multilingual name details
     * @param polygonSvg if {@code true}, return the location's shape as an SVG polygon
     * @return a string representing the geocoded result, formatted according to the selected output format
     * @throws IOException if an I/O error occurs
     * @throws WebserviceCallException if the web service call fails or returns a non-successful status
     * @throws InterruptedException if the operation is interrupted
     * @throws URISyntaxException if the generated URI is invalid
     */
    @SuppressWarnings("checkstyle:ParameterNumber")
    public String reverseGeocode(final String lat, final String lon, final OutputFormat outputFormat, final int zoomLevel,
                                 final boolean addressDetails, final boolean extraTags, final boolean nameDetails,
                                 final boolean polygonSvg)
            throws IOException, WebserviceCallException, InterruptedException, URISyntaxException {
        final URIBuilder builder = new URIBuilder(REVERSE_BASE_URL);
        builder.setParameter("lat", lat);
        builder.setParameter("lon", lon);
        builder.setParameter("format", outputFormat.getFormat());
        builder.setParameter("zoom", String.valueOf(zoomLevel));
        builder.setParameter("addressdetails", addressDetails ? ONE : ZERO);
        builder.setParameter("extratags", extraTags ? ONE : ZERO);
        builder.setParameter("namedetails", nameDetails ? ONE : ZERO);
        builder.setParameter("polygon_svg", polygonSvg ? ONE : ZERO);

        return client.sendHttpRequest(builder.build());
    }
}
