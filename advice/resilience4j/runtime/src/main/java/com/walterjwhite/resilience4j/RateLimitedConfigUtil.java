package com.walterjwhite.resilience4j;

import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class RateLimitedConfigUtil {
  @SneakyThrows
  public static void init() {
    final RateLimiterRegistry registry =
        (RateLimiterRegistry) InterceptorType.RatedLimitedType.getRegistry();

    final String resourceName = "rate-limit.properties"; // don't use leading '/'
    try (final InputStream inputStream =
        Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName)) {
      if (inputStream == null) {
        LOGGER.warn(
            "Resource '{}' not found on classpath; skipping rate limiter configuration load",
            resourceName);
        return;
      }

      try (final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        reader
            .lines()
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .filter(s -> !s.startsWith("#"))
            .forEach(
                line -> {
                  try {
                    registerRateLimiterConfig(registry, line);
                  } catch (Exception e) {
                    LOGGER.warn("Skipping invalid rate-limit line [{}]: {}", line, e.getMessage());
                  }
                });
      } catch (Exception e) {
        LOGGER.error("Error reading '{}'", resourceName, e);
      }
    }
  }

  public static void registerRateLimiterConfig(
      final RateLimiterRegistry registry, final String line) {
    final String[] parts = line.split(",");
    if (parts.length < 4) {
      LOGGER.warn("Invalid rate-limit line (expected 4 comma-separated fields): {}", line);
      return;
    }

    final String name = parts[0].trim();
    try {
      final int limitForPeriod = Integer.parseInt(parts[1].trim());
      final Duration limitRefreshPeriod =
          Duration.parse(parts[2].trim()); // ensure format: PTnS etc
      final Duration timeout = Duration.parse(parts[3].trim());

      RateLimiterConfig cfg =
          RateLimiterConfig.custom()
              .limitForPeriod(limitForPeriod)
              .limitRefreshPeriod(limitRefreshPeriod)
              .timeoutDuration(timeout)
              .build();

      registry.addConfiguration(name, cfg);
    } catch (Exception e) {
      LOGGER.error("Failed to parse rate-limit config for line [{}]: {}", line, e.getMessage());
    }
  }
}
