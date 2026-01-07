package com.walterjwhite.weather;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Weather {
  protected final double temperature;
  protected final double windSpeed;
  protected final String time;
}
