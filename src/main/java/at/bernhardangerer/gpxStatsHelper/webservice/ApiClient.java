package at.bernhardangerer.gpxStatsHelper.webservice;

import at.bernhardangerer.gpxStatsHelper.exception.WebserviceCallException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public final class ApiClient {

    private final HttpClient client;

    public ApiClient() {
        this.client = HttpClient.newHttpClient();
    }

    ApiClient(HttpClient client) {
        this.client = client;
    }

    /**
     * Sends an HTTP GET request to the given URI and returns the response body as a string.
     *
     * @param uri the target URI for the HTTP request (must not be {@code null})
     * @return the response body as a string if the request is successful (HTTP 200 OK)
     * @throws IOException if an I/O error occurs when sending or receiving
     * @throws InterruptedException if the operation is interrupted
     * @throws WebserviceCallException if the response status code is not 200 (OK)
     * @throws IllegalArgumentException if the URI is {@code null}
     */
    public String sendHttpRequest(final URI uri) throws IOException, InterruptedException, WebserviceCallException {
        if (uri == null) {
            throw new IllegalArgumentException("URI must not be null");
        }
        return send(HttpRequest.newBuilder().uri(uri).build());
    }

    /**
     * Sends the given {@link HttpRequest} and returns the response body as a string.
     *
     * @param request the HTTP request to send (must not be {@code null})
     * @return the response body as a string if the response code is 200 (OK)
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     * @throws WebserviceCallException if the server responds with a non-200 status code
     * @throws IllegalArgumentException if the request is {@code null}
     */
    public String send(final HttpRequest request) throws IOException, InterruptedException, WebserviceCallException {
        if (request == null) {
            throw new IllegalArgumentException("HttpRequest must not be null");
        }

        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            return response.body();
        } else {
            throw new WebserviceCallException("Endpoint responded with status code " + response.statusCode());
        }
    }
}
