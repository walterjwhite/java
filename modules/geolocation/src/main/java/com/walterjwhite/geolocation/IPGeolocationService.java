package com.walterjwhite.geolocation;

import com.walterjwhite.rest.client.JSONUtil;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tools.jackson.databind.JsonNode;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IPGeolocationService implements GeolocationService {
  public static final String IP_GEOLOCATION_SERVICE_ENDPOINT_URI_FORMAT =
      "http://ip-api.com/json/%s?fields=status,message,lat,lon,city,regionName,country";

  public Geolocation fromIP(final String IP) throws Exception {
    try (final HttpClient httpClient =
        HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build()) {

      final String geoUrl =
          String.format(
              IP_GEOLOCATION_SERVICE_ENDPOINT_URI_FORMAT,
              URLEncoder.encode(IP, StandardCharsets.UTF_8));
      JsonNode geo = JSONUtil.getJson(httpClient, geoUrl);
      if (!"success".equalsIgnoreCase(geo.path("status").asText())) {
        throw new RuntimeException("Geolocation failed: " + geo.path("message").asText(""));
      }

      return new Geolocation(
          geo.path("country").asText(""),
          geo.path("regionName").asText(""),
          geo.path("city").asText(""),
          geo.path("lat").asDouble(Double.NaN),
          geo.path("lon").asDouble(Double.NaN));
    }
  }
}
