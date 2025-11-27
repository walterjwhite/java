package com.walterjwhite.examples.spring.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WeatherByIPTest {
    @Test
    void testWeatherForPittsburgh() throws Exception {
        final Weather weather = WeatherByIP.forGeolocation(new Geolocation("", "", "", 40.4406, 79.9959));
        Assertions.assertThat(weather).isNotNull();
        Assertions.assertThat(weather.getTemperature()).isNotEqualTo(0.0);
        Assertions.assertThat(weather.getWindSpeed()).isNotEqualTo(0.0);
        Assertions.assertThat(weather.getTime()).isNotNull();
    }
}