package com.walterjwhite.geolocation;

public interface GeolocationService {
  Geolocation fromIP(final String IP) throws Exception;
}
