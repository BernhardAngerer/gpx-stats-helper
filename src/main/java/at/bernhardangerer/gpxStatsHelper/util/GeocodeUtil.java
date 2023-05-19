package at.bernhardangerer.gpxStatsHelper.util;

import at.bernhardangerer.gpxStatsHelper.model.api.GeocodeReverseModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GeocodeUtil {
    private static final Gson GSON = new GsonBuilder().create();

    private GeocodeUtil() {
    }

    public static GeocodeReverseModel convertFromJson(final String json) {
        return GSON.fromJson(json, GeocodeReverseModel.class);
    }

    public static boolean isBounded(final double lat, final double lon,
                                    final double lat1, final double lon1,
                                    final double lat2, final double lon2) {
        return lat >= lat1 && lat <= lat2 && lon >= lon1 && lon <= lon2;
    }
}
