package com.walterjwhite.weather;

import com.walterjwhite.geolocation.Geolocation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OpenMeteoWeatherServiceTest {
  @Test
  void testWeatherForPittsburgh() throws Exception {
    final Weather weather =
        new OpenMeteoWeatherService().forGeolocation(new Geolocation("", "", "", 40.4406, 79.9959));
    Assertions.assertThat(weather).isNotNull();
    Assertions.assertThat(weather.getTemperature()).isNotEqualTo(0.0);
    Assertions.assertThat(weather.getWindSpeed()).isNotEqualTo(0.0);
    Assertions.assertThat(weather.getTime()).isNotNull();
  }
}
