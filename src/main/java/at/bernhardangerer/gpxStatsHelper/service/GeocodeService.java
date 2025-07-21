package at.bernhardangerer.gpxStatsHelper.service;

import at.bernhardangerer.gpxStatsHelper.enumeration.OutputFormat;
import at.bernhardangerer.gpxStatsHelper.exception.WebserviceCallException;
import at.bernhardangerer.gpxStatsHelper.webservice.ApiClient;
import com.topografix.model.Waypoint;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

import static at.bernhardangerer.gpxStatsHelper.util.PropertyUtil.loadValue;

public final class GeocodeService {

    /**
     * API Documentation: https://nominatim.org/release-docs/develop/api/Overview/
     */
    private static final String REVERSE_BASE_URL = loadValue("reverseBaseUrl");
    private static final String ZERO = "0";
    private static final String ONE = "1";
    private static final int EIGHTEEN = 18;
    private final ApiClient client;

    public GeocodeService() {
        this.client = new ApiClient();
    }

    GeocodeService(ApiClient client) {
        this.client = client;
    }

    /**
     * Simple wrapper to return the json string representation of the provided position.
     *
     * @param lat latitude
     * @param lon longitude
     * @return String representation of the provided position
     * @throws IOException
     * @throws WebserviceCallException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    public String reverseGeocodeAsJson(final String lat, final String lon)
            throws IOException, WebserviceCallException, InterruptedException, URISyntaxException {
        return reverseGeocode(lat, lon, OutputFormat.JSON, EIGHTEEN, true, false, false, false);
    }

    /**
     * Simple wrapper to return the json string representation of the provided position from waypoint.
     *
     * @param waypoint Waypoint
     * @return String representation of the provided position
     * @throws IOException
     * @throws WebserviceCallException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    public String reverseGeocodeAsJson(final Waypoint waypoint)
            throws IOException, WebserviceCallException, InterruptedException, URISyntaxException {
        return reverseGeocode(waypoint.getLat().toString(), waypoint.getLon().toString(), OutputFormat.JSON,
                EIGHTEEN, true, false, false, false);
    }

    /**
     * Return the string representation of the provided position
     * using an API call to the openstreetmap nominatim servers.
     *
     * @param lat latitude
     * @param lon longitude
     * @param outputFormat data format of the output
     * @param zoomLevel from 0 (continent/sea) to 18 (building)
     * @param addressDetails true for address details
     * @param extraTags true for extra tags
     * @param nameDetails true for name details
     * @param polygonSvg true for SVG polygon
     * @return String representation of the provided position
     * @throws IOException
     * @throws WebserviceCallException
     * @throws InterruptedException
     * @throws URISyntaxException
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
