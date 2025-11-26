package com.walterjwhite.queue.api.deprecated;

import com.walterjwhite.queue.api.job.AbstractRunnable;

public interface QueuedCallable {
  AbstractRunnable getCallable();
}
