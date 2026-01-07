package com.walterjwhite.rest.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JSONUtil {
  static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  public static JsonNode getJson(final HttpClient httpClient, String url) throws Exception {
    var req = HttpRequest.newBuilder(URI.create(url)).timeout(Duration.ofSeconds(15)).GET().build();
    var resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
    if (resp.statusCode() / 100 != 2) {
      throw new RuntimeException("HTTP " + resp.statusCode() + " from " + url + ": " + resp.body());
    }
    return OBJECT_MAPPER.readTree(resp.body());
  }
}
