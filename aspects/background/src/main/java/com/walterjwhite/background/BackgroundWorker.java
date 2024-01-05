package com.walterjwhite.background;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public interface BackgroundWorker {
  ScheduledExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();
}
