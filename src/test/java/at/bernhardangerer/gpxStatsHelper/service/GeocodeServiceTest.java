package at.bernhardangerer.gpxStatsHelper.service;

import at.bernhardangerer.gpxStatsHelper.enumeration.OutputFormat;
import at.bernhardangerer.gpxStatsHelper.exception.WebserviceCallException;
import at.bernhardangerer.gpxStatsHelper.model.api.GeocodeReverseModel;
import at.bernhardangerer.gpxStatsHelper.webservice.ApiClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import static at.bernhardangerer.gpxStatsHelper.util.GeocodeUtil.convertFromJson;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GeocodeServiceTest {

    private final ApiClient clientMock = Mockito.mock(ApiClient.class);
    private final GeocodeService geocodeService;

    GeocodeServiceTest() {
        this.geocodeService = new GeocodeService(clientMock);
    }

    @Test
    void reverseGeocodeSimple() throws IOException, URISyntaxException, WebserviceCallException, InterruptedException {
        final String mockedResult = loadTestFile("ReverseGeocodeSimple.json");
        when(clientMock.sendHttpRequest(any())).thenReturn(mockedResult);

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

        verify(clientMock, never()).send(any());
    }

    @Test
    void reverseGeocodeComplex() throws IOException, URISyntaxException, WebserviceCallException, InterruptedException {
        final String mockedResult = loadTestFile("ReverseGeocodeComplex.json");
        when(clientMock.sendHttpRequest(any())).thenReturn(mockedResult);

        final String resultJson = geocodeService.reverseGeocode(String.valueOf(47.392036), String.valueOf(11.266654),
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

        verify(clientMock, never()).send(any());
    }

    private String loadTestFile(final String fileName) throws IOException {
        try (InputStream is = getClass().getResourceAsStream("/" + fileName)) {
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
