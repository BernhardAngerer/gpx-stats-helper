package at.bernhardangerer.gpxStatsHelper.service;

import at.bernhardangerer.gpxStatsHelper.enumeration.OutputFormat;
import at.bernhardangerer.gpxStatsHelper.exception.WebserviceCallException;
import at.bernhardangerer.gpxStatsHelper.webservice.ApiClient;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

public final class GeocodeService {

    /**
     * API Documentation: https://nominatim.org/release-docs/develop/api/Overview/
     */
    private static final String BASE_URL = "https://nominatim.openstreetmap.org/reverse";
    private final ApiClient client;

    public GeocodeService() {
        this.client = new ApiClient();
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
        return reverseGeocodeAsJson(lat, lon, OutputFormat.JSON, 18, true, false, false, false);
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
    public String reverseGeocodeAsJson(final String lat, final String lon, final OutputFormat outputFormat, final int zoomLevel,
                                       final boolean addressDetails, final boolean extraTags, final boolean nameDetails,
                                       final boolean polygonSvg)
            throws IOException, WebserviceCallException, InterruptedException, URISyntaxException {
        final URIBuilder builder = new URIBuilder(BASE_URL);
        builder.setParameter("lat", lat);
        builder.setParameter("lon", lon);
        builder.setParameter("format", outputFormat.getFormat());
        builder.setParameter("zoom", String.valueOf(zoomLevel));
        builder.setParameter("addressdetails", addressDetails ? "1" : "0");
        builder.setParameter("extratags", extraTags ? "1" : "0");
        builder.setParameter("namedetails", nameDetails ? "1" : "0");
        builder.setParameter("polygon_svg", polygonSvg ? "1" : "0");
        return client.sendHttpRequest(builder.build());
    }
}
