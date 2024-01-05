package com.walterjwhite.remote.impl.service.monitor;

import java.util.concurrent.Callable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Base class for tailing server logs polling the database for statistics, etc.
 *
 * @param <ResultType> String for tailing log files, Tuple / Object for displays
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
public abstract class AbstractMonitor<ResultType> implements Callable<Void> {

  // = 0 :none
  // > 0 :time in milliseconds
  protected int refreshInterval;

  protected ResultType result;
}
