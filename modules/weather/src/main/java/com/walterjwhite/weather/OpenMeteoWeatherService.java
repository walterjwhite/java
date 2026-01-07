package com.walterjwhite.weather;

import com.walterjwhite.geolocation.Geolocation;
import com.walterjwhite.rest.client.JSONUtil;
import java.net.http.HttpClient;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.JsonNode;

@Slf4j
public class OpenMeteoWeatherService implements WeatherService {
  public Weather forGeolocation(final Geolocation geolocation) throws Exception {
    try (final HttpClient httpClient =
        HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build()) {

      final String weatherUrl =
          String.format(
              "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&current_weather=true",
              geolocation.getLatitude(), geolocation.getLongitude());
      JsonNode weather = JSONUtil.getJson(httpClient, weatherUrl).path("current_weather");

      return new Weather(
          get(weather, "temperature"), get(weather, "windspeed"), weather.path("time").asText(""));
    }
  }

  private static double get(final JsonNode jsonNode, final String fieldName) {
    if (jsonNode.has(fieldName)) {
      return Double.parseDouble(String.valueOf(jsonNode.path(fieldName)));
    }

    return -1;
  }
}
