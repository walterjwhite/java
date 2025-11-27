package com.walterjwhite.examples.spring.util;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Weather {
    protected final double temperature;
    protected final double windSpeed;
    protected final String time;
}
