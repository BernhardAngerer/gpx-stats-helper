package at.bernhardangerer.gpxStatsHelper.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PropertyUtilTest {

    public static final String TEST_PROPERTIES = "test.properties";

    @Test
    void loadValueShouldReturnValueWhenKeyExists() {
        final String value = PropertyUtil.loadValue("my.key", TEST_PROPERTIES);
        assertEquals("test-value", value);
    }

    @Test
    void loadValueShouldReturnValueWhenAnotherKeyExists() {
        final String value = PropertyUtil.loadValue("another.key", TEST_PROPERTIES);
        assertEquals("12345", value);
    }

    @Test
    void loadValueShouldReturnNullWhenKeyDoesNotExist() {
        final String value = PropertyUtil.loadValue("non.existent.key", TEST_PROPERTIES);
        assertNull(value);
    }

    @Test
    void loadValueShouldThrowExceptionWhenKeyIsNull() {
        assertThrows(IllegalArgumentException.class, () -> PropertyUtil.loadValue(null, TEST_PROPERTIES));
    }

    @Test
    void loadValueShouldThrowExceptionWhenKeyIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> PropertyUtil.loadValue("   ", TEST_PROPERTIES));
    }

    @Test
    void loadConfigValueShouldThrowExceptionWhenKeyIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> PropertyUtil.loadConfigValue(null));
    }
}
