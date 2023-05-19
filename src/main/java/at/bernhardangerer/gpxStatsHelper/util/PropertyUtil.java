package at.bernhardangerer.gpxStatsHelper.util;

import java.io.InputStream;
import java.util.Properties;

public final class PropertyUtil {

    private PropertyUtil() {
    }

    public static String loadValue(final String key) {
        try (InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            final Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
