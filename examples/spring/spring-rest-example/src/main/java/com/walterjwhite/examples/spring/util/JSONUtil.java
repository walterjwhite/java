package com.walterjwhite.examples.spring.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JSONUtil {
    static final ObjectMapper M = new ObjectMapper();
    static JsonNode getJson(final HttpClient httpClient, String url) throws Exception {
        var req = HttpRequest.newBuilder(URI.create(url))
                .timeout(Duration.ofSeconds(15))
                .GET()
                .build();
        var resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
        if (resp.statusCode() / 100 != 2) {
            throw new RuntimeException("HTTP " + resp.statusCode() + " from " + url + ": " + resp.body());
        }
        return M.readTree(resp.body());
    }
}
