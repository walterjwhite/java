package com.walterjwhite.resilience4j;

import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RateLimitedConfigUtilTest {

  @Test
  public void testInitLoadsConfigurations() {
    RateLimitedConfigUtil.init();

    RateLimiterRegistry registry =
        (RateLimiterRegistry) InterceptorType.RatedLimitedType.getRegistry();

    Assertions.assertThat(
            registry
                .getConfiguration("com.walterjwhite.examples.spring.async.AsyncController.submit")
                .isPresent())
        .as("AsyncController.submit configuration should be present")
        .isTrue();
    Assertions.assertThat(
            registry
                .getConfiguration(
                    "com.walterjwhite.examples.spring.chat.ChatController.sendMessage")
                .isPresent())
        .as("ChatController.sendMessage configuration should be present")
        .isTrue();

    Assertions.assertThat(registry.getConfiguration("default").isPresent())
        .as("Default configuration should be present")
        .isTrue();
  }
}
