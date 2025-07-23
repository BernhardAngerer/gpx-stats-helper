package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.api.GeocodeReverseModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.topografix.model.Waypoint;
import org.apache.hc.core5.net.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public final class GeocodeUtil {

    private static final String REVERSE_UI_URL = PropertyUtil.loadConfigValue("reverseUiUrl");
    private static final Gson GSON = new GsonBuilder().create();

    private GeocodeUtil() {
    }

    /**
     * Converts a JSON string into a {@link GeocodeReverseModel} object.
     *
     * @param json the JSON string representing a reverse geocoding response
     * @return a {@code GeocodeReverseModel} parsed from the JSON input
     * @throws JsonSyntaxException if the JSON is malformed or does not match the model structure
     */
    public static GeocodeReverseModel convertFromJson(final String json) {
        return GSON.fromJson(json, GeocodeReverseModel.class);
    }

    /**
     * Determines whether a given position lies within a rectangular bounding box
     * defined by two opposite corner coordinates.
     * <p>
     * Assumes {@code lat1 <= lat2} and {@code lon1 <= lon2}.
     *
     * @param lat latitude of the point to test
     * @param lon longitude of the point to test
     * @param lat1 latitude of the first corner of the bounding box
     * @param lon1 longitude of the first corner of the bounding box
     * @param lat2 latitude of the opposite corner of the bounding box
     * @param lon2 longitude of the opposite corner of the bounding box
     * @return {@code true} if the position is inside the bounding box; otherwise {@code false}
     */
    public static boolean isBounded(final double lat, final double lon,
                                    final double lat1, final double lon1,
                                    final double lat2, final double lon2) {
        return lat >= lat1 && lat <= lat2 && lon >= lon1 && lon <= lon2;
    }

    /**
     * Constructs a URL pointing to the OpenStreetMap reverse geocoding UI for the specified {@link Waypoint}.
     * <p>
     * The generated URL opens a browser map centered at the given location using zoom level 18 and layer "poi".
     *
     * @param waypoint the {@code Waypoint} containing latitude and longitude (must not be {@code null})
     * @return a string containing the OpenStreetMap reverse geocoding URL
     * @throws IllegalArgumentException if the waypoint is {@code null}
     * @throws RuntimeException if the URL cannot be constructed
     */
    public static String createOpenStreetMapUrl(final Waypoint waypoint) {
        if (waypoint == null) {
            throw new IllegalArgumentException("Waypoint must not be null!");
        }
        try {
            final URI uri = new URIBuilder(REVERSE_UI_URL)
                    .addParameter("lat", String.valueOf(waypoint.getLat()))
                    .addParameter("lon", String.valueOf(waypoint.getLon()))
                    .addParameter("zoom", "18")
                    .addParameter("layer", "poi")
                    .build();
            return uri.toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Error creating URI", e);
        }
    }
}
