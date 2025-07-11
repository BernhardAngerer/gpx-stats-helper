package at.bernhardangerer.gpxStatsHelper.service;

import at.bernhardangerer.gpxStatsHelper.enumeration.OutputFormat;
import at.bernhardangerer.gpxStatsHelper.exception.WebserviceCallException;
import at.bernhardangerer.gpxStatsHelper.model.api.GeocodeReverseModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static at.bernhardangerer.gpxStatsHelper.util.GeocodeUtil.convertFromJson;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GeocodeServiceIT {

    private final GeocodeService geocodeService;

    GeocodeServiceIT() {
        this.geocodeService = new GeocodeService();
    }

    @Test
    void reverseGeocodeSimple() throws IOException, URISyntaxException, WebserviceCallException, InterruptedException {
        final String resultJson = geocodeService.reverseGeocodeAsJson(String.valueOf(47.392036), String.valueOf(11.266654));
        assertNotNull(resultJson);
        final GeocodeReverseModel model = convertFromJson(resultJson);
        assertNotNull(model);
        assertTrue(model.getDisplayName().length() > 30);
        assertTrue(model.getDisplayName().contains("Scharnitz"));
        assertNotNull(model.getBoundingBox());
        assertNotNull(model.getAddress());
        assertNotNull(model.getLat());
        assertNotNull(model.getLon());
        assertNotNull(model.getLicence());
        assertTrue(model.getPlaceId() > 0);
        assertTrue(model.getOsmId() > 0);
        assertNotNull(model.getOsmType());
        assertNull(model.getExtraTags());
        assertNull(model.getSvg());
        assertNull(model.getNameDetails());
    }

    @Test
    void reverseGeocodeComplex() throws IOException, URISyntaxException, WebserviceCallException, InterruptedException {
        final String resultJson = geocodeService.reverseGeocodeAsJson(String.valueOf(47.392036), String.valueOf(11.266654),
                OutputFormat.JSON, 10, true, true, true, true);
        assertNotNull(resultJson);
        final GeocodeReverseModel model = convertFromJson(resultJson);
        assertNotNull(model);
        assertTrue(model.getDisplayName().contains("Scharnitz"));
        assertNotNull(model.getBoundingBox());
        assertNotNull(model.getAddress());
        assertNotNull(model.getLat());
        assertNotNull(model.getLon());
        assertNotNull(model.getLicence());
        assertTrue(model.getPlaceId() > 0);
        assertTrue(model.getOsmId() > 0);
        assertNotNull(model.getOsmType());
        assertNotNull(model.getExtraTags());
        assertNotNull(model.getSvg());
        assertNotNull(model.getNameDetails());
    }
}
