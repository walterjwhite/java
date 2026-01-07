package com.walterjwhite.geolocation;

import lombok.Data;

@Data
public class Geolocation {
  protected final String country;
  protected final String region;
  protected final String city;

  protected final double latitude;
  protected final double longitude;
}
