package com.walterjwhite.queue.api.deprecated;

import com.walterjwhite.queue.api.job.AbstractRunnable;
import lombok.Getter;

import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;

@Getter
public class ScheduledFutureCallable<ResultType>
        implements ScheduledFuture<ResultType>, QueuedCallable {
    protected final QueuedCallable runnable;
    protected final ScheduledFuture<ResultType> future;

    public ScheduledFutureCallable(QueuedCallable runnable, ScheduledFuture<ResultType> future) {
        this.runnable = runnable;
        this.future = future;
    }

    @Override
    public boolean cancel(boolean b) {
        return future.cancel(b);
    }

    @Override
    public boolean isCancelled() {
        return future.isCancelled();
    }

    @Override
    public boolean isDone() {
        return future.isDone();
    }

    @Override
    public ResultType get() throws InterruptedException, ExecutionException {
        return future.get();
    }

    @Override
    public ResultType get(long l, TimeUnit timeUnit)
            throws InterruptedException, ExecutionException, TimeoutException {
        return future.get(l, timeUnit);
    }

    @Override
    public long getDelay(TimeUnit timeUnit) {
        return future.getDelay(timeUnit);
    }

    @Override
    public int compareTo(Delayed delayed) {
        return future.compareTo(delayed);
    }

    @Override
    public AbstractRunnable getCallable() {
        return runnable.getCallable();
    }
}
