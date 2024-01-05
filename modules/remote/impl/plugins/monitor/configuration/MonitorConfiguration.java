package com.walterjwhite.remote.impl.service.configuration;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class MonitorConfiguration {

  protected String name;
  protected String className;

  protected Map<String, String> properties = new HashMap<String, String>();
}
