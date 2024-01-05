package com.walterjwhite.remote.impl.service.configuration;

import com.walterjwhite.remote.impl.service.monitor.AbstractMonitor;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class MonitorGroupConfiguration {

  protected String name;

  protected List<MonitorConfiguration> configurations = new ArrayList<>();

  protected List<AbstractMonitor> monitors = new ArrayList<>();
}
