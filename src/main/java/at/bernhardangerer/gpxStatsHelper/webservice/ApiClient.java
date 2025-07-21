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
     * Do an HTTP request to the provided endpoint URI.
     *
     * @param uri
     * @return String representation of the webservice call.
     * @throws IOException
     * @throws InterruptedException
     * @throws WebserviceCallException
     */
    public String sendHttpRequest(final URI uri) throws IOException, InterruptedException, WebserviceCallException {
        return send(HttpRequest.newBuilder().uri(uri).build());
    }

    public String send(final HttpRequest request) throws IOException, InterruptedException, WebserviceCallException {
        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            return response.body();
        } else {
            throw new WebserviceCallException("Endpoint answered with status code " + response.statusCode());
        }
    }
}
