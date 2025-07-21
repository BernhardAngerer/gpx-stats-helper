package at.bernhardangerer.gpxStatsHelper.webservice;

import at.bernhardangerer.gpxStatsHelper.exception.WebserviceCallException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class ApiClientTest {

    @Mock
    private HttpClient mockHttpClient;

    @Mock
    private HttpResponse<String> mockHttpResponse;

    @InjectMocks
    private ApiClient apiClient;

    private final URI testUri = URI.create("https://example.com/test");

    @BeforeEach
    void setUp() {
        apiClient = new ApiClient(mockHttpClient);
    }

    @Test
    void testSendHttpRequestSuccess() throws Exception {
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockHttpResponse);
        when(mockHttpResponse.statusCode()).thenReturn(200);
        when(mockHttpResponse.body()).thenReturn("OK");

        final String response = apiClient.sendHttpRequest(testUri);

        assertEquals("OK", response);
        verify(mockHttpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }

    @Test
    void testSendHttpRequestNon200Response() throws Exception {
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockHttpResponse);
        when(mockHttpResponse.statusCode()).thenReturn(500);

        final WebserviceCallException ex = assertThrows(WebserviceCallException.class, () -> {
            apiClient.sendHttpRequest(testUri);
        });
        assertTrue(ex.getMessage().contains("Endpoint answered with status code"));
    }
}
