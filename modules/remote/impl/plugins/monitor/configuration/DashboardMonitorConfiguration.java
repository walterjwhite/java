package com.walterjwhite.remote.impl.service.configuration;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class DashboardMonitorConfiguration {

  protected List<MonitorGroupConfiguration> monitors = new ArrayList<MonitorGroupConfiguration>();
}
