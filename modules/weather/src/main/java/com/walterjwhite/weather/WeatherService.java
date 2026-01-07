package com.walterjwhite.weather;

import com.walterjwhite.geolocation.Geolocation;

public interface WeatherService {
  Weather forGeolocation(final Geolocation geolocation) throws Exception;
}
