package com.walterjwhite.examples.serialization;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@Data
public class Car implements Serializable {
  protected String make;
  protected String model;
  protected String year;
  protected String color;

  protected Engine engine;

  protected Duration clutchEngineDuration;

  protected Map<String, String> configuration;
  protected AxleType axleType;
  protected List<SwitchSetting> switchSettings;
}
