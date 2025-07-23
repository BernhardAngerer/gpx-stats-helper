package at.bernhardangerer.gpxStatsHelper.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyUtil {

    private PropertyUtil() {
    }

    /**
     * Loads the value associated with the specified key from the default {@code config.properties} file
     * located in the classpath.
     *
     * <p>This is a convenience method that delegates to
     * {@link #loadValue(String, String)} using {@code "config.properties"} as the default resource name.
     *
     * @param key the key whose associated value is to be returned; must not be {@code null} or blank
     * @return the value associated with the specified key, or {@code null} if the key does not exist in the properties file
     *
     * @throws IllegalArgumentException if {@code key} is {@code null} or blank, or the default properties file cannot be found
     * @throws RuntimeException if an I/O error occurs while reading the properties file
     */
    public static String loadConfigValue(final String key) {
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Key must not be null or empty");
        }
        return loadValue(key, "config.properties");
    }

    /**
     * Loads the value associated with the specified key from a properties file located in the classpath.
     *
     * <p>This method expects a valid {@code .properties} file (e.g., {@code config.properties})
     * to be available in the classpath and attempts to load the value for the given key.
     *
     * @param key the key whose associated value is to be returned; must not be {@code null} or blank
     * @param resourceName the name of the properties file (e.g., {@code "config.properties"}); must not be {@code null} or blank
     * @return the value associated with the specified key, or {@code null} if the key does not exist in the properties file
     *
     * @throws IllegalArgumentException if {@code key} or {@code resourceName} is {@code null}, blank, or the file cannot be found
     * @throws RuntimeException if an I/O error occurs while reading the properties file
     */
    public static String loadValue(final String key, final String resourceName) {
        if (key == null || key.isBlank() || resourceName == null || resourceName.isBlank()) {
            throw new IllegalArgumentException("Key or resourceName must not be null or empty");
        }

        try (InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream(resourceName)) {
            if (input == null) {
                throw new IllegalArgumentException("Resource file '" + resourceName + "' not found in classpath");
            }

            final Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(key);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load property value for key: " + key, ex);
        }
    }
}
