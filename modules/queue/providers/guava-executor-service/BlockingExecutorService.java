package com.walterjwhite.google.guice.executor.provider;

import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.RateLimiter;
import com.walterjwhite.job.impl.property.property.NumberOfExecutorServiceThreads;
import com.walterjwhite.property.impl.annotation.Property;
import jakarta.inject.Inject;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;


@Deprecated
public class BlockingExecutorService {
  protected final ListeningScheduledExecutorService executorService;
  protected final int numberOfThreads;
  protected final Set<Future> futures = new HashSet<>();
  protected ThreadLocal<Integer> runningJobs = new ThreadLocal<>();



  private static final RateLimiter rateLimiter = RateLimiter.create(1.0); 

  @Inject
  public BlockingExecutorService(
      @Property(NumberOfExecutorServiceThreads.class) int numberOfThreads,
      ListeningScheduledExecutorService executorService) {

    this.executorService = executorService;
    this.numberOfThreads = numberOfThreads;

    this.runningJobs.set(Integer.valueOf(0));
  }

  public void runImmediatelyWhenFree(Callable job, Runnable... listeners) {
    if (rateLimiter.tryAcquire()) rateLimiter.acquire();















  }

  
  protected int getAvailableProcesses() {
    int available = numberOfThreads;


    final Iterator<Future> futureIterator = futures.iterator();
    while (futureIterator.hasNext()) {
      final Future future = futureIterator.next();
      if (future.isDone() || future.isCancelled()) futures.remove(future);
      else available--;
    }

    return (available);
  }
}
