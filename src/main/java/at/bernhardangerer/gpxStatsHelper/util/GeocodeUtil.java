package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.api.GeocodeReverseModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GeocodeUtil {

    private static final Gson GSON = new GsonBuilder().create();

    private GeocodeUtil() {
    }

    /**
     * Convert json into a GeocodeReverseModel object.
     *
     * @param json
     * @return GeocodeReverseModel
     */
    public static GeocodeReverseModel convertFromJson(final String json) {
        return GSON.fromJson(json, GeocodeReverseModel.class);
    }

    /**
     * Lookup, if the provided position is inside a rectangle area (limited by two opposite corner positions).
     *
     * @param lat lat position
     * @param lon lon position
     * @param lat1 lat position of corner 1 of the rectangle
     * @param lon1 lon position of corner 1 of the rectangle
     * @param lat2 lat position of corner 2 of the rectangle
     * @param lon2 lon position of corner 2 of the rectangle
     * @return true if the position is inside the rectangle area
     */
    public static boolean isBounded(final double lat, final double lon,
                                    final double lat1, final double lon1,
                                    final double lat2, final double lon2) {
        return lat >= lat1 && lat <= lat2 && lon >= lon1 && lon <= lon2;
    }
}
