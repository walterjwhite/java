package com.walterjwhite.queue.api.deprecated;

import com.walterjwhite.queue.api.job.AbstractRunnable;
import lombok.Getter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;

@Getter
public class FutureCallable<ResultType> implements Future<ResultType>, QueuedCallable {
    protected final AbstractRunnable callable;
    protected final Future<ResultType> future;

    public FutureCallable(AbstractRunnable callable, Future<ResultType> future) {
        this.callable = callable;
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
    public AbstractRunnable getCallable() {
        return callable;
    }
}
