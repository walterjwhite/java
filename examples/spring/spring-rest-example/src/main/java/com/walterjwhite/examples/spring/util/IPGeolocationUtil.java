package com.walterjwhite.examples.spring.util;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.time.Duration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IPGeolocationUtil {
    public static Geolocation fromIP(final String IP) throws Exception {
        try(final HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build()) {

            String geoUrl = "http://ip-api.com/json/" + URLEncoder.encode(IP, "UTF-8")
                    + "?fields=status,message,lat,lon,city,regionName,country";
            JsonNode geo = JSONUtil.getJson(httpClient, geoUrl);
            if (!"success".equalsIgnoreCase(geo.path("status").asText())) {
                throw new RuntimeException("Geolocation failed: " + geo.path("message").asText(""));
            }

            return new Geolocation(geo.path("country").asText(""),
                    geo.path("regionName").asText(""),
                    geo.path("city").asText(""),
                    geo.path("lat").asDouble(Double.NaN),
                    geo.path("lon").asDouble(Double.NaN));
        }
    }
}
