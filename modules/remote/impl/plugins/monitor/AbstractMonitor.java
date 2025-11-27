package com.walterjwhite.remote.impl.service.monitor;

import java.util.concurrent.Callable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
public abstract class AbstractMonitor<ResultType> implements Callable<Void> {

  protected int refreshInterval;

  protected ResultType result;
}
