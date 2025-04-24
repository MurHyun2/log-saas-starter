
package com.oursaas.autolog;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class LogSender {
    private static final String LOG_ENDPOINT = "https://log-api.oursaas.com/collect";

    public void send(Map<String, Object> logData) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String json = new ObjectMapper().writeValueAsString(logData);

            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(LOG_ENDPOINT))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            client.sendAsync(req, HttpResponse.BodyHandlers.discarding());
        } catch (Exception e) {
            // fail silently
        }
    }
}
